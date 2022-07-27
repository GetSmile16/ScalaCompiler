package nodes;

import java.util.ArrayList;

public class IfElse implements Node {
    private IfExp ifExp;
    private IfBody ifBody;
    private ArrayList<ElseIf> elseIf;
    private Else_ else_;

    public IfElse(IfExp ifExp, IfBody ifBody) {
        this.ifExp = ifExp;
        this.ifBody = ifBody;
    }

    public IfElse(IfExp ifExp, IfBody ifBody, Else_ _else) {
        this.ifExp = ifExp;
        this.ifBody = ifBody;
        this.else_ = _else;
    }

    public IfElse(IfExp ifExp, IfBody ifBody, ArrayList<ElseIf> elseIf) {
        this.ifExp = ifExp;
        this.ifBody = ifBody;
        this.elseIf = elseIf;
    }

    public IfElse(IfExp ifExp, IfBody ifBody, ArrayList<ElseIf> elseIf, Else_ _else) {
        this.ifExp = ifExp;
        this.ifBody = ifBody;
        this.elseIf = elseIf;
        this.else_ = _else;
    }

    public IfExp getIfExp() {
        return ifExp;
    }

    public IfBody getIfBody() {
        return ifBody;
    }

    public ArrayList<ElseIf> getElseIf() {
        return elseIf;
    }

    public Else_ get_else() {
        return else_;
    }
}
