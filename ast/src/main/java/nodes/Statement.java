package nodes;

public class Statement implements Node{
    private InitVariable initVariable;
    private FunctionDefinition functionDefinition;
    private FunctionCall functionCall;
    private InitArray initArray;

    public Statement(InitVariable initVariable) {
        this.initVariable = initVariable;
    }

    public Statement(InitArray initArray) {
        this.initArray = initArray;
    }

    public Statement(FunctionCall functionCall) {
        this.functionCall = functionCall;
    }

    public Statement(FunctionDefinition functionDefinition) {
        this.functionDefinition = functionDefinition;
    }

    public InitVariable getInitVariable() {
        return initVariable;
    }

    public FunctionDefinition getFunctionDefinition() {
        return functionDefinition;
    }

    public FunctionCall getFunctionCall() {
        return functionCall;
    }

    public InitArray getInitArray() {
        return initArray;
    }

//    public ObjectDefinition getObjectDefinition() {
//        return objectDefinition;
//    }
}

