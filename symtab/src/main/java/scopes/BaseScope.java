package scopes;

import symbols.Symbol;
import symbols.VariableSymbol;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaseScope implements Scope {
    protected Scope enclosingScope;

    protected ArrayList<Scope> children = new ArrayList<>();

    protected Map<String, Symbol> symbols = new LinkedHashMap<>();

    public BaseScope() {
    }

    public BaseScope(Scope enclosingScope) {
        this.enclosingScope = enclosingScope;
    }

    @Override
    public String getScopeName() {
        return "base";
    }

    @Override
    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public void define(Symbol sym) throws IllegalArgumentException {
        if (sym.isEmpty()) return;
        if (symbols.containsKey(sym.getName())) return;
        sym.setScope(this);
        symbols.put(sym.getName(), sym);
    }

    @Override
    public Symbol resolve(String name) {
        Symbol s = symbols.get(name);
        if (s != null) {
            return s;
        }
        Scope parent = getEnclosingScope();
        if (parent != null) return parent.resolve(name);
        return null;
    }

    public ArrayList<Scope> getChildren() {
        return children;
    }

    public void addChildren(Scope scope) {
        this.children.add(scope);
    }

    @Override
    public String toString() {
        return symbols.keySet().toString();
    }
}
