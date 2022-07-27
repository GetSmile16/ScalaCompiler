package nodes;

public class IfBody implements Node {
    private Body body;

    public IfBody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }
}
