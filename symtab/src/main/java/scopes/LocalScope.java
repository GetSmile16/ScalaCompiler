package scopes;

public class LocalScope extends BaseScope {
    public LocalScope(Scope parent) {
        super(parent);
        parent.addChildren(this);
    }
    @Override
    public String getScopeName() {
        return "local";
    }
}
