package nodes;

public class CycleWhile implements Node {
    private Node exp;
    private Body body;

    public CycleWhile(Node exp, Body body) {
        this.exp = exp;
        this.body = body;
    }

    public Node getExp() {
        return exp;
    }

    public Body getBody() {
        return body;
    }
}
