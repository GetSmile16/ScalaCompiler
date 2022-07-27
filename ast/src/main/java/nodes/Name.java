package nodes;

public class Name implements Node {
    private String nameID;

    public Name(String nameID) {
        this.nameID = nameID;
    }

    public String getNameID() {
        return nameID;
    }
}
