package nodes;

public class InitVariable implements Node {

    private Name name;
    private String type;
    private Node exp;


    public InitVariable(Name name, Node exp) {
        this.name = name;
        this.exp = exp;
    }

    public InitVariable(Name name, String type, Node exp) {
        this.name = name;
        this.type = type;
        this.exp = exp;
    }

    public Name getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Node getExp() {
        return exp;
    }
}
