package nodes;

public class CallExp implements Node{
    FunctionCall functionCall;

    public CallExp(FunctionCall functionCall) {
        this.functionCall = functionCall;
    }

    public FunctionCall getFunctionCall() {
        return functionCall;
    }
}
