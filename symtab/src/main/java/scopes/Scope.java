package scopes;

import symbols.*;

import java.util.ArrayList;
import java.util.Map;

public interface Scope {
    String getScopeName();
    Map<String, Symbol> getSymbols();
    Scope getEnclosingScope();
    void define(Symbol sym);
    Symbol resolve(String name);

    void addChildren(Scope scope);
    ArrayList<Scope> getChildren();
}
