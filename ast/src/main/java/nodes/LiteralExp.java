package nodes;

import org.antlr.v4.runtime.Token;
import org.stringtemplate.v4.ST;

public class LiteralExp implements Node{
    Token literal;

    public LiteralExp(Token literal) {
        this.literal = literal;
    }

    public String getName() {
        return literal.getText();
    }
    public int getType() {
        return literal.getType();
    }
}
