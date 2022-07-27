package nodes;

public class AssignVariable {
    private Name name;
    private Node exp;


    public AssignVariable(Name name, Node exp) {
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
