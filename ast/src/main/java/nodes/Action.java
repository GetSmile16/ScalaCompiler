package nodes;

public class Action implements Node{
    private InitVariable initVariable;
    private AssignVariable assignVariable;
    private Ret ret;
    private FunctionCall functionCall;
    private AssignArray assignArray;
    private InitArray initArray;
    private IfElse ifElse;
    private CycleWhile cycleWhile;
//    private ObjectDefinition objectDefinition;
//
//    public Statement(ObjectDefinition objectDefinition) {
//        this.objectDefinition = objectDefinition;
//    }

    public Action(InitVariable initVariable) {
        this.initVariable = initVariable;
    }

    public Action(AssignVariable assignVariable) {
        this.assignVariable = assignVariable;
    }

    public Action(IfElse ifElse) {
        this.ifElse = ifElse;
    }

    public Action(InitArray initArray) {
        this.initArray = initArray;
    }

    public Action(AssignArray assignArray) {
        this.assignArray = assignArray;
    }

    public Action(FunctionCall functionCall) {
        this.functionCall = functionCall;
    }

    public Action(Ret ret) {
        this.ret = ret;
    }

    public Action(CycleWhile cycleWhile) {
        this.cycleWhile = cycleWhile;
    }

    public InitVariable getInitVariable() {
        return initVariable;
    }

    public AssignVariable getAssignVariable() {
        return assignVariable;
    }

    public Ret getRet() {
        return ret;
    }

    public FunctionCall getFunctionCall() {
        return functionCall;
    }

    public AssignArray getAssignArray() {
        return assignArray;
    }

    public InitArray getInitArray() {
        return initArray;
    }

    public IfElse getIfElse() {
        return ifElse;
    }

    public CycleWhile getCycleWhile() {
        return cycleWhile;
    }

//    public ObjectDefinition getObjectDefinition() {
//        return objectDefinition;
//    }
}
