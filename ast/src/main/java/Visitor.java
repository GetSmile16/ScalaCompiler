import nodes.*;

import java.util.ArrayList;

class Visitor extends ScalaParserBaseVisitor {
    @Override
    public Object visitProgram(ScalaParser.ProgramContext ctx) {
        if (ctx.statement() != null) {
            ArrayList<Statement> statements = new ArrayList<>();
            for (int i = 0; i < ctx.statement().size(); i++) {
                statements.add((Statement) this.visit(ctx.statement(i)));
            }
            return new Program(statements);
        }
        return null;
    }

    @Override
    public Object visitStatement(ScalaParser.StatementContext ctx) {
        if (ctx.initArray() != null) {
            return new Statement((InitArray) this.visit(ctx.initArray()));
        }
        if (ctx.initVariable() != null) {
            return new Statement((InitVariable) this.visit(ctx.initVariable()));
        }
        if (ctx.functionDefinition() != null) {
            return new Statement((FunctionDefinition) this.visit(ctx.functionDefinition()));
        }
        if (ctx.functionCall() != null) {
            return new Statement((FunctionCall) this.visit(ctx.functionCall()));
        }
        return null;
    }

    @Override
    public Object visitBody(ScalaParser.BodyContext ctx) {
        if (ctx.action() != null) {
            ArrayList<Action> actions = new ArrayList<>();
            for (int i = 0; i < ctx.action().size(); i++) {
                actions.add((Action) this.visit(ctx.action(i)));
            }
            return new Body(actions);
        }
        return null;
    }

    @Override
    public Object visitAction(ScalaParser.ActionContext ctx) {
        if (ctx.initArray() != null) {
            return new Action((InitArray) this.visit(ctx.initArray()));
        }
        if (ctx.initVariable() != null) {
            return new Action((InitVariable) this.visit(ctx.initVariable()));
        }
        if (ctx.assignVariable() != null) {
            return new Action((AssignVariable) this.visit(ctx.assignVariable()));
        }
        if (ctx.ret() != null) {
            return new Action((Ret) this.visit(ctx.ret()));
        }
        if (ctx.functionCall() != null) {
            return new Action((FunctionCall) this.visit(ctx.functionCall()));
        }
        if (ctx.assignArray() != null) {
            return new Action((AssignArray) this.visit(ctx.assignArray()));
        }
        if (ctx.ifElse() != null) {
            return new Action((IfElse) this.visit(ctx.ifElse()));
        }
        if (ctx.cycleWhile() != null) {
            return new Action((CycleWhile) this.visit(ctx.cycleWhile()));
        }
        return null;
    }

    @Override
    public Object visitFunctionDefinition(ScalaParser.FunctionDefinitionContext ctx) {
        if (ctx.name() != null && ctx.parameters() != null
                && ctx.type() != null && ctx.body() != null) {
            return new FunctionDefinition(
                    (Name) this.visit(ctx.name()),
                    (Parameters) this.visit(ctx.parameters()),
                    ctx.type().getText(),
                    (Body) this.visit(ctx.body()));
        }
        if (ctx.name() != null && ctx.parameters() != null
                && ctx.UNIT() != null && ctx.body() != null) {
            return new FunctionDefinition(
                    (Name) this.visit(ctx.name()),
                    (Parameters) this.visit(ctx.parameters()),
                    "Void",
                    (Body) this.visit(ctx.body()));
        }
        return null;
    }

    @Override
    public Object visitParameters(ScalaParser.ParametersContext ctx) {
        if (ctx.name() != null && ctx.type() != null) {
            ArrayList<String> types = new ArrayList<>();
            for (int i = 0; i < ctx.type().size(); i++) {
                types.add((String) this.visit(ctx.type(i)));
            }
            return new Parameters(getNames(ctx), types);
        }
        if (ctx.name() != null && ctx.arrayType() != null) {
            ArrayList<String> arrayTypes = new ArrayList<>();
            for (int i = 0; i < ctx.arrayType().size(); i++) {
                arrayTypes.add((String) this.visit(ctx.arrayType(i)));
            }
            return new Parameters(getNames(ctx), arrayTypes);
        }
        return null;
    }

    private ArrayList<Name> getNames(ScalaParser.ParametersContext ctx) {
        ArrayList<Name> names = new ArrayList<>();
        for (int i = 0; i < ctx.name().size(); i++) {
            names.add((Name) this.visit(ctx.name(i)));
        }
        return names;
    }

    @Override
    public Object visitName(ScalaParser.NameContext ctx) {
        if (ctx.ID() != null) {
            return new Name(ctx.ID().getText());
        }
        return null;
    }

    @Override
    public Object visitFunctionCall(ScalaParser.FunctionCallContext ctx) {
        if (ctx.name() != null && ctx.arguments() != null) {
            return new FunctionCall(
                    (Name) this.visit(ctx.name()),
                    (Arguments) this.visit(ctx.arguments()));
        }
        return null;
    }

    @Override
    public Object visitArguments(ScalaParser.ArgumentsContext ctx) {
        if (ctx.exp() != null) {
            ArrayList<Node> exps = new ArrayList<>();
            for (int i = 0; i < ctx.exp().size(); i++) {
                exps.add((Node) this.visit(ctx.exp(i)));
            }
            return new Arguments(exps);
        }
        return null;
    }

    @Override
    public Object visitRet(ScalaParser.RetContext ctx) {
        if (ctx.exp() != null) {
            return new Ret((Node) this.visit(ctx.exp()));
        }
        return null;
    }

    @Override
    public Object visitInitVariable(ScalaParser.InitVariableContext ctx) {
        if (ctx.name() != null && ctx.type() != null && ctx.exp() != null) {
            return new InitVariable(
                    (Name) this.visit(ctx.name()),
                    (String) this.visit(ctx.type()),
                    (Node) this.visit(ctx.exp()));
        }
        if (ctx.name() != null && ctx.exp() != null) {
            return new InitVariable(
                    (Name) this.visit(ctx.name()),
                    (Node) this.visit(ctx.exp()));
        }
        return null;
    }

    @Override
    public Object visitAssignVariable(ScalaParser.AssignVariableContext ctx) {
                if (ctx.name() != null && ctx.exp() != null) {
            return new AssignVariable(
                    (Name) this.visit(ctx.name()),
                    (Node) this.visit(ctx.exp()));
        }
        return null;
    }

    @Override
    public Object visitInitArray(ScalaParser.InitArrayContext ctx) {
        if (ctx.name() != null && ctx.arrayType() != null
                && ctx.arrayLiteral() != null) {
            return new InitArray(
                    (Name) this.visit(ctx.name()),
                    (String) this.visit(ctx.arrayType()),
                    (ArrayLiteral) this.visit(ctx.arrayLiteral()));
        }
        if (ctx.name() != null && ctx.arrayLiteral() != null) {
            return new InitArray(
                    (Name) this.visit(ctx.name()),
                    (ArrayLiteral) this.visit(ctx.arrayLiteral()));
        }
        return null;
    }

    @Override
    public Object visitAssignArray(ScalaParser.AssignArrayContext ctx) {
        if (ctx.dereferenceArray() != null && ctx.exp() != null) {
            return new AssignArray(
                    (DereferenceArray) this.visit(ctx.dereferenceArray()),
                    (Node) this.visit(ctx.exp()));
        }
        return null;
    }

    @Override
    public Object visitDereferenceArray(ScalaParser.DereferenceArrayContext ctx) {
        if (ctx.name() != null && ctx.exp() != null) {
            return new DereferenceArray(
                    (Name) this.visit(ctx.name()),
                    (Node) this.visit(ctx.exp()));
        }
        return null;
    }

    @Override
    public Object visitArrayLiteral(ScalaParser.ArrayLiteralContext ctx) {
        if (ctx.exp() != null) {
            ArrayList<Node> exps = new ArrayList<>();
            for (int i = 0; i < ctx.exp().size(); i++) {
                exps.add((Node) this.visit(ctx.exp(i)));
            }
            return new ArrayLiteral(exps);
        }
        return null;
    }

    @Override
    public Object visitIfElse(ScalaParser.IfElseContext ctx) {
        if (ctx.ifExp() != null && ctx.ifBody() != null
                && ctx.elseIf() != null && ctx.else_() != null) {
            ArrayList<ElseIf> elseIfs = new ArrayList<>();
            for (int i = 0; i < ctx.elseIf().size(); i++) {
                elseIfs.add((ElseIf) this.visit(ctx.elseIf(i)));
            }
            return new IfElse(
                    (IfExp) this.visit(ctx.ifExp()),
                    (IfBody) this.visit(ctx.ifBody()),
                    elseIfs,
                    (Else_) this.visit(ctx.else_()));
        }
        if (ctx.ifExp() != null && ctx.ifBody() != null && ctx.elseIf() != null) {
            ArrayList<ElseIf> elseIfs = new ArrayList<>();
            for (int i = 0; i < ctx.elseIf().size(); i++) {
                elseIfs.add((ElseIf) this.visit(ctx.elseIf(i)));
            }
            return new IfElse(
                    (IfExp) this.visit(ctx.ifExp()),
                    (IfBody) this.visit(ctx.ifBody()),
                    elseIfs);
        }
        if (ctx.ifExp() != null && ctx.ifBody() != null && ctx.else_() != null) {
            return new IfElse(
                    (IfExp) this.visit(ctx.ifExp()),
                    (IfBody) this.visit(ctx.ifBody()),
                    (Else_) this.visit(ctx.else_()));
        }
        if (ctx.ifExp() != null && ctx.ifBody() != null) {
            return new IfElse(
                    (IfExp) this.visit(ctx.ifExp()),
                    (IfBody) this.visit(ctx.ifBody()));
        }
        return null;
    }

    @Override
    public Object visitIfExp(ScalaParser.IfExpContext ctx) {
        if (ctx.exp() != null) {
            return new IfExp((Node) this.visit(ctx.exp()));
        }
        return null;
    }

    @Override
    public Object visitIfBody(ScalaParser.IfBodyContext ctx) {
        if (ctx.body() != null) {
            return new IfBody((Body) this.visit(ctx.body()));
        }
        return null;
    }

    @Override
    public Object visitElseIf(ScalaParser.ElseIfContext ctx) {
        if (ctx.ifExp() != null && ctx.ifBody() != null) {
            return new ElseIf(
                    (IfExp) this.visit(ctx.ifExp()),
                    (IfBody) this.visit(ctx.ifBody()));
        }
        return null;
    }

    @Override
    public Object visitElse_(ScalaParser.Else_Context ctx) {
        if (ctx.ifBody() != null) {
            return new Else_((IfBody) this.visit(ctx.ifBody()));
        }
        return null;
    }

    @Override
    public Object visitCycleWhile(ScalaParser.CycleWhileContext ctx) {
        if (ctx.exp() != null && ctx.body() != null) {
            return new CycleWhile(
                    (Node) this.visit(ctx.exp()),
                    (Body) this.visit(ctx.body()));
        }
        return null;
    }


    @Override
    public Object visitLiteralExp(ScalaParser.LiteralExpContext ctx) {
        if (ctx.INTEGER_LITERAL() != null) {
            return new LiteralExp(ctx.INTEGER_LITERAL().getSymbol());
        }
        if (ctx.CHAR_LITERAL() != null) {
            return new LiteralExp(ctx.CHAR_LITERAL().getSymbol());
        }
        if (ctx.BOOL_LITERAL() != null) {
            return new LiteralExp(ctx.BOOL_LITERAL().getSymbol());
        }
        if (ctx.STRING_LITERAL() != null) {
            return new LiteralExp(ctx.STRING_LITERAL().getSymbol());
        }
        return null;
    }

    @Override
    public Object visitIdExp(ScalaParser.IdExpContext ctx) {
        if (ctx.name() != null) {
            return new IdExp((Name) this.visit(ctx.name()));
        }
        return null;
    }

    @Override
    public Object visitNotExp(ScalaParser.NotExpContext ctx) {
        return new NotExp((Node) this.visit(ctx.exp()));
    }

    @Override
    public Object visitCallExp(ScalaParser.CallExpContext ctx) {
        return new CallExp((FunctionCall) this.visit(ctx.functionCall()));
    }

    @Override
    public Object visitIndexArrExp(ScalaParser.IndexArrExpContext ctx) {
        return new IndexArrExp((DereferenceArray) this.visit(ctx.dereferenceArray()));
    }

    @Override
    public Object visitLogicExp(ScalaParser.LogicExpContext ctx) {
        LogicExp logicExp = new LogicExp(ctx.op.getText());
        logicExp.setLeftOperand((Node) this.visit(ctx.exp(0)));
        logicExp.setRightOperand((Node) this.visit(ctx.exp(1)));
        return logicExp;
    }

    @Override
    public Object visitMathExp(ScalaParser.MathExpContext ctx) {
        MathExp mathExp = new MathExp(ctx.op.getText());
        mathExp.setLeftOperand((Node) this.visit(ctx.exp(0)));
        mathExp.setRightOperand((Node) this.visit(ctx.exp(1)));
        return mathExp;
    }

    @Override
    public Object visitArrayType(ScalaParser.ArrayTypeContext ctx) {
        if (ctx.type() != null) {
            return "Array[" + this.visit(ctx.type()) + "]";
        }
        return null;
    }

    @Override
    public Object visitType(ScalaParser.TypeContext ctx) {
        if (ctx.INT() != null) {
            return ctx.INT().getText();
        }
        if (ctx.CHAR() != null) {
            return ctx.CHAR().getText();
        }
        if (ctx.BOOLEAN() != null) {
            return ctx.BOOLEAN().getText();
        }
        if (ctx.STRING_WORD() != null) {
            return ctx.STRING_WORD().getText();
        }
        return null;
    }
}