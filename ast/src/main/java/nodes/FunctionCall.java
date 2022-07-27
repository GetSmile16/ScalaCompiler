package nodes;

public class FunctionCall implements Node {
    private Name name;
    private Arguments arguments;

    public FunctionCall(Name name, Arguments arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public Name getName() {
        return name;
    }

    public Arguments getArguments() {
        return arguments;
    }
}
