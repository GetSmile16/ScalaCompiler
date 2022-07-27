package nodes;

public class NotExp implements Node{
    Node exp;

    public NotExp(Node exp) {
        this.exp = exp;
    }

    public Node getExp() {
        return exp;
    }
}
