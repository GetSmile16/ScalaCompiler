package nodes;

public class AssignArray implements Node {
    private DereferenceArray dereferenceArray;
    private Node exp;

    public AssignArray(DereferenceArray dereferenceArray, Node exp) {
        this.dereferenceArray = dereferenceArray;
        this.exp = exp;
    }

    public DereferenceArray getDereferenceArray() {
        return dereferenceArray;
    }

    public Node getExp() {
        return exp;
    }
}
