package nodes;

public class DereferenceArray implements Node {
    private Name name;
    private Node exp;

    public DereferenceArray(Name name, Node exp) {
        this.name = name;
        this.exp = exp;
    }

    public Name getName() {
        return name;
    }

    public Node getExp() {
        return exp;
    }
}
