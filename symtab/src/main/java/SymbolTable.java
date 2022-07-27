import scopes.GlobalScope;
import scopes.PredefinedScope;
import symbols.Symbol;

public class SymbolTable {
    private GlobalScope globals = new GlobalScope(new PredefinedScope());

    public SymbolTable() {
    }

    public void defineGlobalSymbol(Symbol s) {
        globals.define(s);
    }

    public GlobalScope getGlobals() {
        return globals;
    }

    public String toString() {
        return globals.toString();
    }
}
