package nodes;

public class Ret implements Node{
    private Node exp;

    public Ret(Node exp) {
        this.exp = exp;
    }

    public Node getExp() {
        return exp;
    }
}
