package nodes;

public class Else_ implements Node {
   private IfBody ifBody;

    public Else_(IfBody ifBody) {
        this.ifBody = ifBody;
    }

    public IfBody getIfBody() {
        return ifBody;
    }
}
