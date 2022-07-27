import nodes.Program;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymbolTableTest {

    @Test
    public void firstTest() throws IOException, VisitorException {
        ScalaLexer scalaLexer = new ScalaLexer(CharStreams.fromPath(
                Path.of( "src", "test", "resources", "symtabExamples", "third.sc")));

        ScalaParser scalaParser = new ScalaParser(new CommonTokenStream(scalaLexer));

        StreamErrorListener errorListener = errorInit(scalaParser);

        ParseTree parseTree = scalaParser.program();

        if (!errorListener.getErrorList().isEmpty()) {
            System.out.println("Errors:");
            errorListener.getErrorList().forEach(error -> System.out.println("\t" + error.line + ":" + error.column + " "
                    + error.message));
            System.out.println();
        }

        Visitor visitor = new Visitor();
        Program ast = (Program) visitor.visit(parseTree);

        SymbolVisitor symbolVisitor = new SymbolVisitor();
        symbolVisitor.visit(ast);
    }

    private StreamErrorListener errorInit(ScalaParser scalaParser) {
        StreamErrorListener errorListener = new StreamErrorListener();
        scalaParser.removeErrorListeners();
        scalaParser.addErrorListener(errorListener);

        return errorListener;
    }
}
