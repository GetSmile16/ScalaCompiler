import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {

    static ScalaLexer SCALA_LEXER;

    @Test
    public void HelloWorldTest() throws IOException {
        SCALA_LEXER = new ScalaLexer(CharStreams.fromPath(
                Path.of( "src", "test", "resources", "examples", "HelloWorld.sc")));

        List<Token> tokens = getTokens();
        String[] expectedTokens = new String[]{
                "DEF", "ID", "L_PAREN", "R_PAREN",
                "COLON", "UNIT", "ASSIGN", "L_CURL", "ID",
                "L_PAREN", "STRING_LITERAL", "R_PAREN", "R_CURL"};

        for (int i = 0; i < expectedTokens.length; i++) {
            assertEquals(expectedTokens[i], SCALA_LEXER.getVocabulary().getSymbolicName(tokens.get(i).getType()));
        }
    }

    @Test
    public void FindSubstringTest() throws IOException {
        SCALA_LEXER = new ScalaLexer(CharStreams.fromPath(
                Path.of( "src", "test", "resources", "examples", "FindSubstring.sc")));

        List<Token> tokens = getTokens();
        String[] expectedTokens = new String[]{
                "DEF", "ID", "L_PAREN", "R_PAREN", "COLON",
                "INT", "ASSIGN", "L_CURL", "RETURN", "STRING_LITERAL",
                "ID", "STRING_LITERAL", "R_CURL"};

        for (int i = 0; i < expectedTokens.length; i++) {
            assertEquals(expectedTokens[i], SCALA_LEXER.getVocabulary().getSymbolicName(tokens.get(i).getType()));
        }
    }

    public List<Token> getTokens() {
        CommonTokenStream tokenStream = new CommonTokenStream(SCALA_LEXER);
        tokenStream.fill();
        return tokenStream.getTokens();
    }
}