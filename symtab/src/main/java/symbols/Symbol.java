package symbols;

import scopes.Scope;

public class Symbol {
    protected String name;
    protected String type;
    protected Scope scope;

    public Symbol(String name) {
        this.name = name;
    }

    public Symbol() {
    }

    public Symbol(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        if (type != null) return '<' + getName() + ":" + type + '>';
        return getName();
    }

    public boolean isEmpty() {
        return name == null || type == null;
    }


}
