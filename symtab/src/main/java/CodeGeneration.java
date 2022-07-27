import nodes.*;
import scopes.Scope;
import symbols.MethodSymbol;
import symbols.Symbol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.TreeSet;

public class CodeGeneration {
    private final File file;

    private Scope currentScope;
    private int count = 0;
    private int addCounter = 0;
    private int mulCounter = 0;
    private int subCounter = 0;
    private int divCounter = 0;
    private int cmpCounter = 0;
    private int callCounter = 0;
    private int childrenCounter = 0;

    String lastVar;
    String lastVarType;

    int currentCmp = 0;

    private TreeSet<String> strings = new TreeSet<>();
    StringBuilder outStr = new StringBuilder();
    StringBuilder sourceCode = new StringBuilder();

    public CodeGeneration(Scope rootScope) {
        this.currentScope = rootScope;
        Path out = Path.of("data/a.ll");
        file = new File(out.toString());

        MethodSymbol methodSymbol1 = new MethodSymbol("printInt", "Void", rootScope, 1);
        MethodSymbol methodSymbol2 = new MethodSymbol("printLine", "Void", rootScope, 1);
        MethodSymbol methodSymbol3 = new MethodSymbol("readInt", "Void", rootScope, 1);
        rootScope.define(methodSymbol1);
        rootScope.define(methodSymbol2);
        rootScope.define(methodSymbol3);

        sourceCode.append("target triple = \"x86_64-pc-linux-gnu\"\n")
                .append("declare i32 @printf(i8*, ...)\n")
                .append("declare dso_local i32 @__isoc99_scanf(i8*, ...)\n")
                .append("@.digitPrintStr = private unnamed_addr constant [4 x i8] c\"%d\\0A\\00\"\n")
                .append("@.stringPrintStr = private unnamed_addr constant [4 x i8] c\"%s\\0A\\00\"\n")
                .append("@.digitWriteStr = private unnamed_addr constant [3 x i8] c\"%d\\00\"\n\n")
                .append("define void @printInt(i32 %x) {\n")
                .append("%rv = call i32 (i8*, ...) @printf(i8* getelementptr ([4 x i8], [4 x i8]* @.digitPrintStr, i64 0, i64 0), i32 %x)\n")
                .append("ret void\n}\n")
                .append("define void @printLine(i8* %x) {\n")
                .append("%rv = call i32 (i8*, ...) @printf(i8* getelementptr ([4 x i8], [4 x i8]* @.stringPrintStr, i64 0, i64 0), i8* %x)\n")
                .append("ret void\n}\n")
                .append("define void @readInt(i32* %x) {\n")
                .append("%rv = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr ([3 x i8], [3 x i8]* @.digitWriteStr, i64 0, i64 0), i32* %x)\n")
                .append("ret void\n}\n");
    }

    public void generate(Program program) throws VisitorException, IOException {
        for (int i = 0; i < program.getStatements().size(); i++) {
            if (program.getStatements().get(i) != null) {
                generate(program.getStatements().get(i));
            }
        }
        sourceCode.append("\n").append(outStr);
        this.write();
    }

    private void generate(Statement statement) throws VisitorException {
        if (statement.getInitVariable() != null) {
            generate(statement.getInitVariable());
        }
        if (statement.getFunctionDefinition() != null) {
            generate(statement.getFunctionDefinition());
        }
        if (statement.getFunctionCall() != null) {
            generate(statement.getFunctionCall());
        }
    }

    public void generate(FunctionDefinition functionDefinition) throws VisitorException {
        MethodSymbol methodSymbol = (MethodSymbol) currentScope.resolve(functionDefinition.getName().getNameID());
        String typeOfFunction = getTypeFunc(methodSymbol.getType());
        outStr.append("define dso_local ").append(typeOfFunction).append(" @")
                .append(functionDefinition.getName().getNameID()).append("(");
        generate(functionDefinition.getParameters());
        outStr.append(") {\nentry:\n");
        if (!functionDefinition.getType().equals("Void")) {
            outStr.append("%retval = alloca ").append(getTypeFunc(functionDefinition.getType()))
                    .append("\n");
        }
        generateInitVars(functionDefinition.getParameters());
        for (var scope : currentScope.getChildren()) {
            if (scope.getScopeName().equals(functionDefinition.getName().getNameID())) {
                currentScope = scope;
            }
        }
        count = 0;
        generate(functionDefinition.getBody());
        if (currentScope.getScopeName().equals("main")
                && !outStr.substring(outStr.length() - 10, outStr.length()).equals("ret i32 0\n")) {
            outStr.append("ret i32 0\n");
        } else if (methodSymbol.getType().equals("Void")) {
            outStr.append("ret void\n");
        }
        outStr.append("}\n\n");
        currentScope = currentScope.getEnclosingScope();
    }

    private void generateInitVars(Parameters parameters) {
        for (int i = 0; i < parameters.getNames().size(); i++) {
            String nameID = parameters.getNames().get(i).getNameID();
            String typeVar = getTypeVar(parameters.getDataTypes().get(i));
            outStr.append("%").append(nameID)
                    .append(" = alloca ").append(typeVar);
            if (typeVar.equals("i8*")) {
                outStr.append(", align 8\n");
            } else {
                outStr.append(", align 4\n");
            }
            switch (parameters.getDataTypes().get(i)) {
                case "Int" -> outStr.append("store i32 %").append(nameID).append("1").append(", i32* %")
                        .append(nameID).append(", align 4\n");
                case "Char" -> outStr.append("store i8 %").append(nameID).append("1").append(", i8* %")
                        .append(nameID).append(", align 4\n");
                case "Boolean" -> outStr.append("store i1 %").append(nameID).append("1").append(", i1* %")
                        .append(nameID).append(", align 4\n");
                default -> outStr.append("store i8* %").append(nameID).append("1").append(", i8** %")
                        .append(nameID).append(", align 8\n");
            }
        }
    }

    public void generate(FunctionCall functionCall) throws VisitorException {
        MethodSymbol methodSymbol = (MethodSymbol) currentScope.resolve(functionCall.getName().getNameID());
        if (methodSymbol.isEmpty()) {
            return;
        }
        Arguments arguments = functionCall.getArguments();
        int c = 0;

        LinkedList<String> args = new LinkedList<>();
        for (int i = 0; i < arguments.getExps().size(); i++) {
            Node exp = arguments.getExps().get(i);
            if (exp instanceof LiteralExp) {
                if (((LiteralExp) exp).getType() == ScalaLexer.INTEGER_LITERAL) {
                    args.add("i32 " + ((LiteralExp) exp).getName());
                }
                if (((LiteralExp) exp).getType() == ScalaLexer.STRING_LITERAL) {
                    String lname = getStringLiteralName((LiteralExp) exp);
                    String name = ((LiteralExp) exp).getName()
                            .substring(1, ((LiteralExp) exp).getName().length() - 1);
                    addLiteral(name);
                    args.add("i8* getelementptr inbounds ([" + (name.length() + 1) + " x i8], ["
                            + (name.length() + 1) + " x i8]* @\"" + lname + "\", i64 0, i64 0)");
                }
            }
            if (exp instanceof MathExp) {
                generate((MathExp) exp);
                if (lastVarType.equals("Int")) {
                    args.add("i32 " + lastVar);
                } else {
                    args.add("i8* " + lastVar);
                }
            }
            if (exp instanceof CallExp) {
                generate((CallExp) exp);
                if (lastVarType.equals("Int")) {
                    args.add("i32 " + lastVar);
                } else {
                    args.add("i8* " + lastVar);
                }
            }
            if (exp instanceof IdExp) {
                String nameID = ((IdExp) exp).getName().getNameID();
                Symbol id = currentScope.resolve(nameID);
                String parameter = getParam(nameID);
                if (parameter.equals(nameID)) {
                    if (!id.isEmpty()) {
                        switch (id.getType()) {
                            case "Int" -> {
                                if (functionCall.getName().getNameID().equals("readInt")) {
                                    args.add("i32* %" + nameID);
                                    continue;
                                }
                                outStr.append("%").append(count).append(" = load i32, i32* %")
                                        .append(nameID).append(", align 4\n");
                                args.add("i32 %" + count++);
                            }
                            case "String" -> {
                                outStr.append("%").append(count).append(" = load i8*, i8** %")
                                        .append(nameID).append(", align 8\n");
                                args.add("i8* %" + count++);
                            }
                            case "Char" -> {
                                outStr.append("%").append(count).append(" = load i8, i8* %")
                                        .append(nameID).append(", align 4\n");
                                args.add("i8 %" + count++);
                            }
                            default -> {
                                outStr.append("%").append(count).append(" = load i1, i1* %")
                                        .append(nameID).append(", align 4\n");
                                args.add("i1 %" + count++);
                            }
                        }
                    }
                } else {
                    MethodSymbol ms = (MethodSymbol) currentScope;
                    Symbol symbol = ms.resolve(nameID);
                    if (symbol.getType().equals("Int")) {
                        args.add("i32 %" + symbol.getName());
                    } else {
                        args.add("i8* %" + symbol.getName());
                    }
                }
            }
            c++;
        }

        lastVar = (methodSymbol.getType().equals("Int")
                || methodSymbol.getType().equals("String")) ? "%call"
                + callCounter++ : "";
        lastVarType = methodSymbol.getType();
        String retVal = getTypeFunc(lastVarType);
        StringBuilder call = new StringBuilder(((!lastVar.isEmpty()) ? lastVar + " = call " : "call ")
                + retVal + " @" + methodSymbol.getName() + "(");
        for (c = 0; c < args.size(); c++) {
            call.append(args.get(c));
            if (c != args.size() - 1) {
                call.append(", ");
            }
        }
        call.append(")\n");
        outStr.append(call);
    }


    public String getParam(String name) {
        return name;
    }

    public void generate(Ret ret) throws VisitorException {
        Node exp = ret.getExp();
        if (exp instanceof LiteralExp) {
            if (((LiteralExp) exp).getType() == ScalaLexer.INTEGER_LITERAL) {
                outStr.append("ret i32 ").append(((LiteralExp) exp).getName());
            } else if (((LiteralExp) exp).getType() == ScalaLexer.STRING_LITERAL) {
                String lname = getStringLiteralName((LiteralExp) exp);
                String name = ((LiteralExp) exp).getName()
                        .substring(1, ((LiteralExp) exp).getName().length() - 1);
                addLiteral(name);
                outStr.append("ret i8* getelementptr inbounds ([").append(name.length() + 1)
                        .append(" x i8], [").append(name.length() + 1).append(" x i8]* @\"")
                        .append(lname).append("\", i64 0, i64 0)");

            }
        } else if (exp instanceof IdExp) {
            Symbol resolve = currentScope.resolve(((IdExp) exp).getName().getNameID());
            if (resolve.getType().equals("Int")) {
                outStr.append("%").append(count).append(" = load i32, i32* %").append(resolve.getName())
                        .append(", align 4\n").append("ret i32 %").append(count);
            } else if (resolve.getType().equals("Char")) {
                outStr.append("%").append(count).append(" = load i8, i8* %").append(resolve.getName())
                        .append(", align 4\n").append("ret i8 %").append(count);
            } else {
                outStr.append("%").append(count).append(" = load i8*, i8** %").append(resolve.getName())
                        .append(", align 4\n").append("ret i8* %").append(count);
            }
        } else {
            if (exp instanceof MathExp) {
                generate((MathExp) exp);
            } else if (exp instanceof CallExp) {
                generate((CallExp) exp);
            }
            if (lastVarType.equals("Int")) {
                outStr.append("store i32 ").append(lastVar).append(", i32* %retval, align 4\n")
                        .append("%").append(count).append(" = load i32, i32* %retval, align 4\n")
                        .append("ret i32 %").append(count);

            } else {
                outStr.append("store i8* ").append(lastVar).append(", i8** %retval, align 8\n")
                        .append("%").append(count).append(" = load i8*, i8** %retval, align 8\n")// maybe "load i8*"
                        .append("ret i8* %").append(count);
            }
        }
        count++;
        outStr.append("\n");
    }

    private void generate(CallExp exp) throws VisitorException {
        if (exp.getFunctionCall() != null) {
            generate(exp.getFunctionCall());
        }
    }

    private void addLiteral(String name) {
        if (strings.contains(name))
            return;
        sourceCode.append("$\"??_").append(name).append("\" = comdat any\n").append("@\"??_").append(name)
                .append("\" = linkonce_odr dso_local unnamed_addr constant [").append(name.length() + 1)
                .append(" x i8] c\"").append(name).append("\\00\", comdat, align 1\n");
        strings.add(name);
    }

    private void generate(Body body) throws VisitorException {
        for (int i = 0; i < body.getActions().size(); i++) {
            if (body.getActions().get(i) != null) {
                generate(body.getActions().get(i));
            }
        }
    }

    private void generate(Action action) throws VisitorException {
        if (action.getInitVariable() != null) {
            generate(action.getInitVariable());
        }
        if (action.getAssignVariable() != null) {
            generate(action.getAssignVariable());
        }
        if (action.getRet() != null) {
            generate(action.getRet());
        }
        if (action.getFunctionCall() != null) {
            generate(action.getFunctionCall());
        }
        if (action.getIfElse() != null) {
            generate(action.getIfElse());
        }
        if (action.getCycleWhile() != null) {
            generate(action.getCycleWhile());
        }
    }

    private void generate(InitVariable initVariable) throws VisitorException {
        String nameID = initVariable.getName().getNameID();
        Symbol symbol = currentScope.resolve(nameID);
        String typeVar = getTypeVar(symbol.getType());
        outStr.append("%").append(nameID)
                .append(" = alloca ").append(typeVar);
        if (typeVar.equals("i8*")) {
            outStr.append(", align 8\n");
        } else {
            outStr.append(", align 4\n");
        }
        assign(nameID, symbol, initVariable.getExp());
    }

    private void assign(String nameID, Symbol symbol, Node exp) throws VisitorException {
        if (symbol.getType().equals("Int")) {
            if (exp instanceof LiteralExp) {
                if (((LiteralExp) exp).getType() == ScalaLexer.INTEGER_LITERAL) {
                    outStr.append("store i32 ").append(((LiteralExp) exp).getName())
                            .append(", i32* %").append(nameID).append(", align 4\n");
                }
            }
            if (exp instanceof MathExp) {
                generate((MathExp) exp);
                outStr.append("store i32 ").append(lastVar).append(", i32* %").append(nameID).append(", align 4\n");
            }
            if (exp instanceof IdExp) {
                outStr.append("%").append(count).append(" = load i32, i32* %").append(((IdExp) exp).getName())
                        .append(", align 4\n").append("store i32 %").append(count)
                        .append(", i32* %").append(nameID).append(", align 4\n");
            }
            if (exp instanceof CallExp) {
                generate((CallExp) exp);
                outStr.append("store i32 ").append(lastVar).append(", i32* %").append(nameID).append(", align 4\n");
            }
        } else if (symbol.getType().equals("String")) {
            String name = ((LiteralExp) exp).getName()
                    .substring(1, ((LiteralExp) exp).getName().length() - 1);
            String lname = getStringLiteralName(((LiteralExp) exp));
            addLiteral(name);
            outStr.append("store i8* getelementptr inbounds ([")
                    .append(((LiteralExp) exp).getName().length() - 1).append(" x i8], [")
                    .append(((LiteralExp) exp).getName().length() - 1).append(" x i8]* @\"")
                    .append(lname).append("\", i64 0, i64 0), i8** %")
                    .append(nameID).append(", align 8\n");
        }
    }

    public void generate(AssignVariable assignVariable) throws VisitorException {
        String nameID = assignVariable.getName().getNameID();
        Symbol symbol = currentScope.resolve(nameID);
        assign(nameID, symbol, assignVariable.getExp());
    }

    private String getStringLiteralName(LiteralExp exp) {
        String name = exp.getName().substring(1, exp.getName().length() - 1);
        return "??_" + name;
    }

    private void generate(MathExp exp) throws VisitorException {
        String firstVar, secondVar;
        if (exp.getLeftOperand() instanceof MathExp) {
            generate((MathExp) exp.getLeftOperand());
            firstVar = lastVar;
        } else if (exp.getLeftOperand() instanceof LiteralExp) {
            firstVar = ((LiteralExp) exp.getLeftOperand()).getName();
            lastVar = firstVar;
        } else if (exp.getLeftOperand() instanceof IdExp) {
            outStr.append("%").append(count).append(" = load i32, i32* %")
                    .append(((IdExp) exp.getLeftOperand()).getName().getNameID())
                    .append(", align 4\n");
            lastVar = "%" + count;
            firstVar = lastVar;
            count++;
        } else if ((exp.getLeftOperand() instanceof CallExp)) {
            generate((CallExp) exp.getLeftOperand());
            firstVar = lastVar;
        } else {
            throw new VisitorException("Error");
        }

        if (exp.getRightOperand() instanceof MathExp) {
            generate((MathExp) exp.getRightOperand());
            secondVar = lastVar;
        } else if (exp.getRightOperand() instanceof LiteralExp) {
            secondVar = ((LiteralExp) exp.getRightOperand()).getName();
            lastVar = secondVar;
        } else if (exp.getRightOperand() instanceof IdExp) {
            outStr.append("%").append(count).append(" = load i32, i32* %")
                    .append(((IdExp) exp.getRightOperand()).getName().getNameID())
                    .append(", align 4\n");
            lastVar = "%" + count;
            secondVar = lastVar;
            count++;
        } else if ((exp.getLeftOperand() instanceof CallExp)) {
            generate((CallExp) exp.getLeftOperand());
            secondVar = lastVar;
        } else {
            throw new VisitorException("Error");
        }

        String operation = "";
        switch (exp.getOperator()) {
            case "+" -> {
                lastVar = "%add" + addCounter++;
                lastVarType = "Int";
                operation = "add";
            }
            case "-" -> {
                lastVar = "%sub" + subCounter++;
                lastVarType = "Int";
                operation = "sub";
            }
            case "*" -> {
                lastVar = "%mul" + mulCounter++;
                lastVarType = "Int";
                operation = "mul";
            }
            case "/" -> {
                lastVar = "%div" + divCounter++;
                lastVarType = "Int";
                operation = "div";
            }
        }

        outStr.append(lastVar).append(" = ").append(operation).append(" nsw i32 ")
                .append(firstVar).append(", ").append(secondVar).append("\n");
    }

    private void generate(LogicExp exp, boolean isElse, boolean isWhile) throws VisitorException {
        String firstVar, secondVar;
        if (exp.getLeftOperand() instanceof MathExp) {
            generate((MathExp) exp.getLeftOperand());
            firstVar = lastVar;
        } else if (exp.getLeftOperand() instanceof LiteralExp) {
            firstVar = ((LiteralExp) exp.getLeftOperand()).getName();
            lastVar = firstVar;
        } else if (exp.getLeftOperand() instanceof IdExp) {
            String lNameID = ((IdExp) exp.getLeftOperand()).getName().getNameID();
            String param = getParam(lNameID);
            Symbol symbol = currentScope.resolve(lNameID);
            if (param.equals(lNameID)) {
                if (symbol == null) {
                    throw new VisitorException("Error");
                }
            }
            if (symbol.getType().equals("Int")) {
                if (param.contains(".")) {
                    outStr.append("store i32 %").append(lNameID).append(", i32* %")
                            .append(param).append(", align 4\n");
                }
            }
            outStr.append("%").append(count).append(" = load i32, i32* %")
                    .append(lNameID)
                    .append(", align 4\n");
            lastVar = "%" + count;
            firstVar = lastVar;
            count++;
        } else {
            throw new VisitorException("Error");
        }

        if (exp.getRightOperand() instanceof MathExp) {
            generate((MathExp) exp.getRightOperand());
            secondVar = lastVar;
        } else if (exp.getRightOperand() instanceof LiteralExp) {
            secondVar = ((LiteralExp) exp.getRightOperand()).getName();
            lastVar = secondVar;
        } else if (exp.getRightOperand() instanceof IdExp) {
            String rNameID = ((IdExp) exp.getRightOperand()).getName().getNameID();
            String param = getParam(rNameID);
            Symbol symbol = currentScope.resolve(rNameID);
            if (param.equals(rNameID)) {
                if (symbol == null) {
                    throw new VisitorException("Error");
                }
            }
            if (symbol.getType().equals("Int")) {
                if (param.contains(".")) {
                    outStr.append("store i32 %").append(rNameID).append(", i32* %")
                            .append(param).append(", align 4\n");
                }
            }
            outStr.append("%").append(count).append(" = load i32, i32* %")
                    .append(rNameID)
                    .append(", align 4\n");
            lastVar = "%" + count;
            secondVar = lastVar;
            count++;
        } else {
            throw new VisitorException("Error");
        }

        String operation = switch (exp.getOperator()) {
            case "==" -> "eq";
            case "!=" -> "ne";
            case "<" -> "slt";
            case "<=" -> "sle";
            case ">" -> "sgt";
            case ">=" -> "sge";
            default -> "";
        };

        outStr.append("%cmp").append(cmpCounter).append(" = icmp ").append(operation)
                .append(" i32 ").append(firstVar).append(", ").append(secondVar).append("\n");
        if (isWhile) {
            return;
        }
        if (isElse) {
            outStr.append("br i1 %cmp").append(cmpCounter).append(", label %if.then").append(cmpCounter)
                    .append(", label %if.then").append(cmpCounter + 1).append("\n");
        } else {
            outStr.append("br i1 %cmp").append(cmpCounter).append(", label %if.then").append(cmpCounter)
                    .append(", label %if.end").append(cmpCounter).append("\n");
            currentCmp = cmpCounter;
        }
    }

    private void generate(IfElse ifElse) throws VisitorException {
        if (ifElse.getIfExp() != null) {
            if (ifElse.getIfExp().getExp() instanceof IdExp) {
                String nameID = ((IdExp) ifElse.getIfExp().getExp()).getName().getNameID();
                Symbol symbol = currentScope.resolve(nameID);
                if (symbol.getType().equals("Int")) {
                    outStr.append("%").append(count).append(" = load i32, i32* %").append(nameID).append(", align 4\n")
                            .append("%cmp").append(cmpCounter).append(" = icmp ne i32 %").append(count).append(", 0\n")
                            .append("br i2 %cmp").append(cmpCounter).append(", label %if.then").append(cmpCounter)
                            .append(", label %if.end").append(cmpCounter).append("\n");
                    count++;
                    cmpCounter++;
                }
            }
            if (ifElse.getIfExp().getExp() instanceof LogicExp) {
                generate((LogicExp) ifElse.getIfExp().getExp(),
                        !ifElse.getElseIf().isEmpty() || ifElse.get_else() != null, false);
            }

        }
        if (ifElse.getIfBody() != null) {
            generate(ifElse.getIfBody(), false);
        }
        for (int i = 0; i < ifElse.getElseIf().size(); i++) {
            generate(ifElse.getElseIf().get(i));
        }
        if (ifElse.get_else() != null) {
            generate(ifElse.get_else().getIfBody(), true);
        }
        if (!ifElse.getElseIf().isEmpty() || ifElse.get_else() != null) {
            outStr.append("if.end").append(10).append(":\n");
        }
    }

    private void generate(ElseIf elseIf) throws VisitorException {
        if (elseIf.getIfExp() != null) {
            generate((LogicExp) elseIf.getIfExp().getExp(), true, false);
        }
        if (elseIf.getIfBody() != null) {
            generate(elseIf.getIfBody(), true);
        }
    }

    private void generate(IfBody ifBody, boolean isElse) throws VisitorException {
        outStr.append("\nif.then").append(cmpCounter).append(":\n");
        int tmp = cmpCounter++;
        currentScope = currentScope.getChildren().get(childrenCounter++); //there isn't enclosing child
        generate(ifBody.getBody());
        currentScope = currentScope.getEnclosingScope();

        if (isElse) {
            outStr.append("br label %if.end").append(10)
                    .append("\n\n");
        } else {
            outStr.append("br label %if.end").append(tmp)
                    .append("\n\n");
            outStr.append("if.end").append(tmp).append(":\n");
        }
//        endCmp = tmp;
    }

    private void generate(CycleWhile cycleWhile) throws VisitorException {
        outStr.append("br label %while.cond").append(cmpCounter).append("\n\n")
                .append("while.cond").append(cmpCounter).append(":\n");
        if (cycleWhile.getExp() != null) {
            if (cycleWhile.getExp() instanceof IdExp) {
                String nameID = ((IdExp) cycleWhile.getExp()).getName().getNameID();
                Symbol symbol = currentScope.resolve(nameID);
                if (symbol.getType().equals("Int")) {
                    outStr.append("%").append(count).append(" = load i32, i32* %").append(nameID).append(", align 4\n")
                            .append("%cmp").append(cmpCounter).append(" = icmp ne i32 %").append(count).append(", 0\n");
                }
            }
            if (cycleWhile.getExp() instanceof LogicExp) {
                generate((LogicExp) cycleWhile.getExp(), false, true);
            }
        }
        outStr.append("br i1 %cmp").append(cmpCounter).append(", label %while.body").append(cmpCounter)
                .append(", label %while.end").append(cmpCounter).append("\n")
                .append("\nwhile.body").append(cmpCounter).append(":\n");
        int tmp = cmpCounter++;
        if (cycleWhile.getBody() != null) {
            currentScope = currentScope.getChildren().get(childrenCounter++);
            generate(cycleWhile.getBody());
            currentScope = currentScope.getEnclosingScope();
        }
        outStr.append("br label %while.cond").append(tmp).append("\n\nwhile.end")
                .append(tmp).append(":\n");
    }

    private void generate(Parameters parameters) {
        for (int i = 0; i < parameters.getNames().size(); i++) {
            String typeOfVariable = getTypeVar(parameters.getDataTypes().get(i));
            outStr.append(typeOfVariable).append(" %").append(parameters.getNames()
                    .get(i).getNameID()).append("1").append(", ");
        }
        if (!parameters.getNames().isEmpty()) {
            outStr.delete(outStr.length() - 2, outStr.length());
        }
    }

    public void write() throws IOException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(sourceCode.toString());
        }
    }

    private String getTypeFunc(String type) {
        String typeOfFunc = "";
        switch (type) {
            case "Void" -> typeOfFunc = "void";
            case "Int" -> typeOfFunc = "i32";
            case "Char" -> typeOfFunc = "i8";
            case "Boolean" -> typeOfFunc = "i1";
            case "String" -> typeOfFunc = "i8*";
        }
        return typeOfFunc;
    }

    private String getTypeVar(String type) {
        String typeOfVar = "";
        if (type.contains("Int")) {
            typeOfVar = "i32";
        } else if (type.contains("Char")) {
            typeOfVar = "i8";
        } else if (type.contains("Boolean")) {
            typeOfVar = "i1";
        } else if (type.contains("String")) {
            typeOfVar = "i8*";
        }
        return typeOfVar;
    }
}

