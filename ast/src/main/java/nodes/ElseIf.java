package nodes;

public class ElseIf implements Node {
    private IfExp ifExp;
    private IfBody ifBody;

    public ElseIf(IfExp ifExp, IfBody ifBody) {
        this.ifExp = ifExp;
        this.ifBody = ifBody;
    }

    public IfExp getIfExp() {
        return ifExp;
    }

    public IfBody getIfBody() {
        return ifBody;
    }
}
