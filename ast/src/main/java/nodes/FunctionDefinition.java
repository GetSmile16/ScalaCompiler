package nodes;

public class FunctionDefinition implements Node {
    private Name name;
    private Parameters parameters;
    private String type;
    private Body body;

    public FunctionDefinition(Name name, Parameters parameters, String type, Body body) {
        this.name = name;
        this.parameters = parameters;
        this.type = type;
        this.body = body;
    }

    public Name getName() {
        return name;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public String getType() {
        return type;
    }

    public Body getBody() {
        return body;
    }
}
