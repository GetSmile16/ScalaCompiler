import nodes.*;
import scopes.LocalScope;
import scopes.Scope;
import symbols.MethodSymbol;
import symbols.Symbol;
import symbols.VariableSymbol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SymbolVisitor {
    private Scope currentScope;
    public SymbolTable symbolTable = new SymbolTable();

    public SymbolVisitor() {
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void visit(Program program) throws VisitorException {
        currentScope = symbolTable.getGlobals();
        for (int i = 0; i < program.getStatements().size(); i++) {
            if (program.getStatements().get(i) != null) {
                visit(program.getStatements().get(i));
            }
        }
    }

    public void visit(Statement statement) throws VisitorException {
        if (statement.getInitArray() != null) {
            visit(statement.getInitArray());
        }
        if (statement.getInitVariable() != null) {
            visit(statement.getInitVariable());
        }
        if (statement.getFunctionDefinition() != null) {
            visit(statement.getFunctionDefinition());
        }
        if (statement.getFunctionCall() != null) {
            visit(statement.getFunctionCall());
        }
    }

    public void visit(Body body) throws VisitorException {
        for (int i = 0; i < body.getActions().size(); i++) {
            if (body.getActions().get(i) != null) {
                visit(body.getActions().get(i));
            }
        }
    }

    public void visit(Action action) throws VisitorException {
        if (action.getInitArray() != null) {
            visit(action.getInitArray());
        }
        if (action.getInitVariable() != null) {
            visit(action.getInitVariable());
        }
        if (action.getAssignVariable() != null) {
            visit(action.getAssignVariable());
        }
        if (action.getRet() != null) {
            visit(action.getRet());
        }
        if (action.getFunctionCall() != null) {
            visit(action.getFunctionCall());
        }
        if (action.getAssignArray() != null) {
            visit(action.getAssignArray());
        }
        if (action.getIfElse() != null) {
            visit(action.getIfElse());
        }
        if (action.getCycleWhile() != null) {
            visit(action.getCycleWhile());
        }
    }

    public void visit(FunctionDefinition functionDefinition) throws VisitorException {
        MethodSymbol methodSymbol = new MethodSymbol();
        if (functionDefinition.getName() != null) {
            if (isDuplicateVariable(functionDefinition.getName().getNameID())) {
                throw new VisitorException("Duplicate symbol: " + functionDefinition.getName().getNameID());
            }
        }
        if (functionDefinition.getType() != null
            && functionDefinition.getParameters() != null) {
                methodSymbol = new MethodSymbol(functionDefinition.getName().getNameID(),
                        functionDefinition.getType(),
                        currentScope,
                        getCountOfParameters(functionDefinition.getParameters()));
        }
        if (methodSymbol.getName().equals("main")) {
            methodSymbol.setType("Int");
        }
        currentScope.define(methodSymbol);
        currentScope.addChildren(methodSymbol);
        currentScope = methodSymbol;

        if (functionDefinition.getParameters() != null && functionDefinition.getBody() != null) {
            visit(functionDefinition.getParameters());
            visit(functionDefinition.getBody());
        }

        currentScope = currentScope.getEnclosingScope();
    }

    private int getCountOfParameters(Parameters parameters) {
        if (parameters.getDataTypes() != null) {
            return parameters.getDataTypes().size();
        }
        return 0;
    }

    public void visit(Parameters parameters) throws VisitorException {
        for (int i = 0; i < parameters.getNames().size(); i++) {
            String dataType = parameters.getDataTypes().get(i);
            Name name = parameters.getNames().get(i);
            VariableSymbol variableSymbol = new VariableSymbol();
            if (name.getNameID() != null) {
                if (isDuplicateVariable(name.getNameID())) {
                    throw new VisitorException("Duplicate symbol " + name.getNameID());
                }
            }
            if (dataType != null) {
                variableSymbol = new VariableSymbol(name.getNameID(), dataType);
            }
            currentScope.define(variableSymbol);
        }
    }

    private boolean isDuplicateVariable(String id) {
        return currentScope.getSymbols().containsKey(id);
    }

    public void visit(FunctionCall functionCall) throws VisitorException {
        MethodSymbol symbol = (MethodSymbol) currentScope.resolve(functionCall.getName().getNameID());
        if (functionCall.getArguments() != null) {
            if (symbol != null) {
                if (functionCall.getArguments().getExps().size()
                        != symbol.getCountOfParameters())
                    throw new VisitorException("Number of arguments(" + functionCall.getArguments().getExps().size() + ") "
                            + "and parameters(" + symbol.getCountOfParameters() + ") of function don't match!");
                if (!isSameArgsAndParameters(functionCall)) {
                    StringBuilder params = new StringBuilder(functionCall.getName().getNameID() + "(");
                    for (int i = 0; i < functionCall.getArguments().getExps().size(); i++) {
                        params.append(getType(functionCall.getArguments().getExps().get(i))).append(", ");
                    }
                    params.delete(params.length() - 2, params.length() - 1).append(")");
                    throw new VisitorException("Types of parameters(" + (printTypes(functionCall)) + ") "
                            + "and arguments(" + params + ") of function don't match!");
                }
            } else {
                switch (functionCall.getName().getNameID()) {
                    case "readInt":
                    case "printLine":
                    case "printInt":
                        break;
                    default:
                        throw new VisitorException("'" + functionCall.getName().getNameID()
                                + "' was not declared in this scope");
                }
            }
            //codeGeneration.generateFuncCall(symbol, functionCall);
            visit(functionCall.getArguments());
        }
    }

    private String printTypes(FunctionCall functionCall) {
        Iterator<Symbol> iterator = ((MethodSymbol) currentScope.resolve(functionCall.getName().getNameID()))
                .getSymbols().values().iterator();
        StringBuilder params = new StringBuilder(functionCall.getName().getNameID() + "(");
        for (int i = 0; i < functionCall.getArguments().getExps().size(); i++) {
            Symbol symbol = iterator.next();
            params.append(symbol.getType()).append(", ");
        }
        return params.delete(params.length() - 2, params.length() - 1).append(")").toString();
    }

    private boolean isSameArgsAndParameters(FunctionCall functionCall) throws VisitorException {
        Iterator<Symbol> iterator = ((MethodSymbol) currentScope.resolve(functionCall.getName().getNameID()))
                .getSymbols().values().iterator();
        for (int i = 0; i < functionCall.getArguments().getExps().size(); i++) {
            Symbol symbol = iterator.next();
            if (!getType(functionCall.getArguments().getExps().get(i))
                    .equals(symbol.getType())) {
                return false;
            }
        }
        return true;
    }

    private String print(FunctionCall functionCall) {
        StringBuilder res = new StringBuilder(functionCall.getName().getNameID() + "(");
        for (int i = 0; i < functionCall.getArguments().getExps().size(); i++) {
            res.append(print(functionCall.getArguments().getExps().get(i))).append(", ");
        }
        return res.append(")").toString();
    }

    public void visit(Arguments arguments) throws VisitorException {
        for (int i = 0; i < arguments.getExps().size(); i++) {
            Node exp = arguments.getExps().get(i);
            if (exp != null) {
                //codeGeneration.generateArgs(exp, getType(exp), currentScope);
                visit(exp);
            }
        }
    }

    public void visit(Ret ret) throws VisitorException {
        if (ret.getExp() != null) {
            Scope scope = currentScope;
            while (!(scope instanceof MethodSymbol)) {
                scope = scope.getEnclosingScope();
            }
            if (!getType(ret.getExp())
                    .equals(((MethodSymbol) scope).getType())) {
                throw new VisitorException("Return type is invalid in function '" + scope.getScopeName() + "'"
                        + ":expected '" + ((MethodSymbol) scope).getType() + "', got '" +
                        getType(ret.getExp()) + "'");
            }
            visit(ret.getExp());
        }
    }

    public void visit(InitVariable initVariable) throws VisitorException {
        VariableSymbol variableSymbol = new VariableSymbol();
        if (initVariable.getName() != null) {
            if (isDuplicateVariable(initVariable.getName().getNameID())) {
                throw new VisitorException("Duplicate symbol " + initVariable.getName().getNameID());
            }
        }
        if (initVariable.getType() != null && initVariable.getExp() != null) {
                variableSymbol = new VariableSymbol(initVariable.getName().getNameID(),
                        initVariable.getType());
        }
        if (initVariable.getExp() != null) {
            if (initVariable.getType() == null) {
                String type = getType(initVariable.getExp());
                if (type != null) {
                    variableSymbol = new VariableSymbol(initVariable.getName().getNameID(),
                            type);
                }
            } else {
                if (!variableSymbol.getType().equals(getType(initVariable.getExp()))) {
                    throw new VisitorException("Incompatible operand type(s) for: " + variableSymbol.getName()
                            + "(" + variableSymbol.getType() + ") and " + print(initVariable.getExp())
                            + "(" + getType(initVariable.getExp()) + ")");
                }
            }
        }
        currentScope.define(variableSymbol);
        visit(initVariable.getExp());
    }

    public void visit(AssignVariable assignVariable) throws VisitorException {
        if (assignVariable.getExp() != null) {
            Symbol symbol = currentScope.resolve(assignVariable.getName().getNameID());
            if (symbol == null) {
                throw new VisitorException("'" + assignVariable.getName().getNameID()
                        + "' was not declared in this scope");
            } else {
                if (!symbol.getType().equals(getType(assignVariable.getExp()))) {
                    throw new VisitorException("Incompatible operand type(s) for: " + symbol.getName()
                            + "(" + symbol.getType() + ") and " + print(assignVariable.getExp())
                            + "(" + getType(assignVariable.getExp()) + ")");
                }
            }
            visit(assignVariable.getExp());
        }
    }

    public void visit(InitArray initArray) throws VisitorException {
        VariableSymbol variableSymbol = new VariableSymbol();
        if (initArray.getName() != null) {
            if (isDuplicateVariable(initArray.getName().getNameID())) {
                throw new VisitorException("Duplicate symbol " + initArray.getName().getNameID());
            }
        }
        if (initArray.getArrayType() != null && initArray.getArrayLiteral() != null) {
            variableSymbol = new VariableSymbol(initArray.getName().getNameID(),
                    initArray.getArrayType());
        }
        if (initArray.getArrayLiteral() != null) {
            if (!isEqualsOperandsTypes(initArray.getArrayLiteral().getExps())) {
                StringBuilder exception = new StringBuilder("Incompatibles operands types in array "
                        + initArray.getName().getNameID() + ": (");
                for (int i = 0; i < initArray.getArrayLiteral().getExps().size(); i++) {
                    exception.append(getType(initArray.getArrayLiteral().getExps().get(i))).append(", ");
                }
                throw new VisitorException(exception.append(")").toString());
            }
            if (initArray.getArrayType() == null) {
                String type = getType(initArray.getArrayLiteral().getExps().get(0));
                if (type != null) {
                    variableSymbol = new VariableSymbol(initArray.getName().getNameID(), type);
                }
            } else {
                if (!variableSymbol.getType().equals("Array[" + getType(initArray.getArrayLiteral()) + "]")) {
                    throw new VisitorException("Incompatible operand type(s) for: " + variableSymbol.getName()
                            + "(" + variableSymbol.getType() + ") and " + print(initArray.getArrayLiteral())
                            + "(" + getType(initArray.getArrayLiteral()) + ")");
                }
            }
        }
        currentScope.define(variableSymbol);
    }

    private String getType(ArrayLiteral arrayLiteral) throws VisitorException {
        if (!arrayLiteral.getExps().isEmpty()) {
            return getType(arrayLiteral.getExps().get(0));
        }
        return "Void";
    }

    private String print(ArrayLiteral arrayLiteral) {
        StringBuilder sb = new StringBuilder("Array(");
        for (int i = 0; i < arrayLiteral.getExps().size(); i++) {
            sb.append(print(arrayLiteral.getExps().get(i))).append(", ");
        }
        return sb.append(")").toString();
    }

    private boolean isEqualsOperandsTypes(ArrayList<Node> exps) throws VisitorException {
        for (int i = 0; i < exps.size() - 1; i++) {
            if (!(getType(exps.get(i))).equals(getType(exps.get(i + 1)))) {
                return false;
            }
        }
        return true;
    }


    public void visit(AssignArray assignArray) throws VisitorException {
        if (assignArray.getDereferenceArray() != null && assignArray.getExp() != null) {
            Symbol symbol = currentScope.resolve(assignArray.getDereferenceArray().getName().getNameID());
            if (symbol == null) {
                throw new VisitorException("'" + assignArray.getDereferenceArray().getName().getNameID()
                        + "' was not declared in this scope");

            } else {
                if (symbol.getType().equals(getType(assignArray.getExp()))) {
                    throw new VisitorException("Incompatible operand type(s) for: " + symbol.getName()
                            + "(" + symbol.getType() + ") and " + print(assignArray.getExp())
                            + "(" + getType(assignArray.getExp()) + ")");
                }
            }
            visit(assignArray.getDereferenceArray());
            visit(assignArray.getExp());
        }
    }

    public void visit(DereferenceArray dereferenceArray) throws VisitorException {
        if (dereferenceArray.getExp() != null) {
            visit(dereferenceArray.getExp());
        }
    }

    public void visit(IfElse ifElse) throws VisitorException {
        if (ifElse.getIfExp() != null) {
            visit(ifElse.getIfExp());
            visit(ifElse.getIfBody());
        }
        for (int i = 0; i < ifElse.getElseIf().size(); i++) {
            if (ifElse.getElseIf().get(i) != null) {
                visit(ifElse.getElseIf().get(i));
            }
        }
        if (ifElse.get_else() != null) {
            visit(ifElse.get_else());
        }
    }

    public void visit(IfExp ifExp) throws VisitorException {
        if (ifExp.getExp() != null) {
            visit(ifExp.getExp());
        }
    }

    public void visit(IfBody ifBody) throws VisitorException {

        currentScope = new LocalScope(currentScope);

        if (ifBody.getBody() != null) {
            visit(ifBody.getBody());
        }

        currentScope = currentScope.getEnclosingScope();
    }

    public void visit(ElseIf elseIf) throws VisitorException {
        if (elseIf.getIfExp() != null && elseIf.getIfBody() != null) {
            visit(elseIf.getIfExp());
            visit(elseIf.getIfBody());
        }
    }

    public void visit(Else_ else_) throws VisitorException {
        if (else_.getIfBody() != null) {
            visit(else_.getIfBody());
        }
    }

    public void visit(CycleWhile cycleWhile) throws VisitorException {
        if (cycleWhile.getExp() != null && cycleWhile.getBody() != null) {
            visit(cycleWhile.getExp());

            currentScope = new LocalScope(currentScope);

            visit(cycleWhile.getBody());

            currentScope = currentScope.getEnclosingScope();
        }
    }

    private void visit(Node exp) throws VisitorException {
        if (exp instanceof MathExp) {
            Node leftOperand = ((MathExp) exp).getLeftOperand();
            Node rightOperand = ((MathExp) exp).getRightOperand();
            if (!getType(leftOperand).equals(getType(rightOperand))) {
                throw new VisitorException("Incompatible operand type(s) for: '" + print(leftOperand)
                        + "'(" + getType(leftOperand) + ") and '" + print(rightOperand)
                        + "'(" + getType(rightOperand) + ")");
            }
        }
        if (exp instanceof LogicExp) {
            Node leftOperand = ((LogicExp) exp).getLeftOperand();
            Node rightOperand = ((LogicExp) exp).getRightOperand();
            if (!getType(leftOperand).equals(getType(rightOperand))) {
                throw new VisitorException("Incompatible operand type(s) for: '" + print(leftOperand)
                        + "'(" + getType(leftOperand) + ") and '" + print(rightOperand)
                        + "'(" + getType(rightOperand) + ")");
            }
        }
    }

    public String getType(Node exp) throws VisitorException {
        if (exp instanceof LiteralExp) {
            int type = ((LiteralExp) exp).getType();
            if (type == ScalaLexer.INTEGER_LITERAL) {
                return "Int";
            }
            if (type == ScalaLexer.BOOL_LITERAL) {
                return "Boolean";
            }
            if (type == ScalaLexer.CHAR_LITERAL) {
                return "Char";
            }
            if (type == ScalaLexer.STRING_LITERAL) {
                return "String";
            }
        }
        if (exp instanceof MathExp) {
            return "Int";
        }
        if (exp instanceof LogicExp) {
            return "Boolean";
        }
        if (exp instanceof IdExp) {
            Symbol symbol = currentScope.resolve(((IdExp) exp).getName().getNameID());
            if (symbol != null) {
                return symbol.getType();
            } else {
                throw new VisitorException("'" + ((IdExp) exp).getName().getNameID()
                        + "' was not declared in this scope");
            }
        }
        if (exp instanceof IndexArrExp) {
            Symbol symbol = currentScope.resolve(((IndexArrExp) exp)
                    .getDereferenceArray().getName().getNameID());
            if (symbol != null) {
                return symbol.getType();
            } else {
                throw new VisitorException("Array '" + ((IndexArrExp) exp)
                        .getDereferenceArray().getName().getNameID()
                        + "' was not declared in this scope");
            }
        }
        if (exp instanceof CallExp) {
            String nameID = ((CallExp) exp)
                    .getFunctionCall().getName().getNameID();
            if (nameID.equals("readInt")) {
                return "Int";
            }
            if (nameID.equals("readLine")) {
                return "String";
            }
            if (nameID.equals("println")) {
                return "Void";
            }
            Symbol symbol = currentScope.resolve(nameID);
            if (symbol != null) {
                return symbol.getType();
            } else {
                throw new VisitorException("'" + nameID
                        + "' was not declared in this scope");
            }
        }
        if (exp instanceof NotExp) {
            getType(exp);
        }
        return "";
    }

    private String print(Node exp) {
        if (exp instanceof LiteralExp) {
            return ((LiteralExp) exp).getName();
        }
        if (exp instanceof MathExp) {
            return print(((MathExp) exp).getLeftOperand()) + " "
                    + ((MathExp) exp).getOperator() + " "
                    + print(((MathExp) exp).getRightOperand());
        }
        if (exp instanceof LogicExp) {
            return print(((LogicExp) exp).getLeftOperand()) + " "
                    + ((LogicExp) exp).getOperator() + " "
                    + print(((LogicExp) exp).getRightOperand());
        }
        if (exp instanceof IdExp) {
            return ((IdExp) exp).getName().getNameID();
        }
        if (exp instanceof IndexArrExp) {
            return print(((IndexArrExp) exp).getDereferenceArray());
        }
        if (exp instanceof CallExp) {
            return print(((CallExp) exp).getFunctionCall());
        }
        if (exp instanceof NotExp) {
            return "!" + print(exp);
        }
        return "";
    }
}