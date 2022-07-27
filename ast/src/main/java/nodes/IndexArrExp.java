package nodes;

public class IndexArrExp implements Node {
    DereferenceArray dereferenceArray;

    public IndexArrExp(DereferenceArray dereferenceArray) {
        this.dereferenceArray = dereferenceArray;
    }

    public DereferenceArray getDereferenceArray() {
        return dereferenceArray;
    }
}
