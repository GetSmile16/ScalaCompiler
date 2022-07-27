package symbols;

import scopes.Scope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MethodSymbol extends Symbol implements Scope {

    private Scope enclosingScope;
    private int countOfParameters;

    protected ArrayList<Scope> children = new ArrayList<>();

    private Map<String, Symbol> symbols = new LinkedHashMap<>();

    public MethodSymbol() {
    }

    public MethodSymbol(String name, String retType, Scope enclosingScope, int countOfParameters) {
        super(name, retType);
        this.enclosingScope = enclosingScope;
        this.countOfParameters = countOfParameters;
    }

    public MethodSymbol(Scope scope, String printInt) {
    }

    @Override
    public String getScopeName() {
        return name;
    }

    @Override
    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    @Override
    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    public int getCountOfParameters() {
        return countOfParameters;
    }

    public void define(Symbol sym) throws IllegalArgumentException {
        if (symbols.containsKey(sym.getName()) ) {
            throw new IllegalArgumentException("duplicate symbol "+ sym.getName());
        }
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

    @Override
    public void addChildren(Scope scope) {
        this.children.add(scope);
    }

    @Override
    public ArrayList<Scope> getChildren() {
        return children;
    }

    public void setType(String type) {
        this.type = type;
    }
}
