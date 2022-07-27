package nodes;

public class IdExp implements Node{
    Name name;

    public IdExp(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
