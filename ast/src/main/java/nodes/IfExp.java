package nodes;

public class IfExp implements Node {
    private Node exp;

    public IfExp(Node exp) {
        this.exp = exp;
    }

    public Node getExp() {
        return exp;
    }
}
