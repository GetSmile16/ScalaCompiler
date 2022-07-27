package scopes;

public class GlobalScope extends BaseScope {

    public GlobalScope(Scope scope) {
        super(scope);
    }

    @Override
    public String getScopeName() {
        return "global";
    }
}
