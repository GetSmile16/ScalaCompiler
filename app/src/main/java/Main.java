import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import nodes.Program;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        try {
            if (args.length == 2 && args[0].equals("--dump-tokens")) {
                dumpTokens(args[1]);
            } else if (args.length == 2 && args[0].equals("--dump-ast")) {
                dumpAst(args[1]);
            } else if (args.length == 2 && args[0].equals("--dump-asm")) {
                dumpAsm(args[1], true);
            } else if (args.length == 2 && args[0].equals("--dump-asm-exec")) {
                dumpAsm(args[1], false);
            } else {
                throw new IllegalArgumentException("Invalid argument");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dumpAsm(String pathString, boolean show) throws IOException, VisitorException {
        Program ast = getAst(pathString);

        SymbolVisitor symbolVisitor = new SymbolVisitor();
        symbolVisitor.visit(ast);

        CodeGeneration codeGeneration = new CodeGeneration(symbolVisitor.getSymbolTable().getGlobals());
        codeGeneration.generate(ast);

        if (show)
            System.out.println(Files.readString(Path.of("data/a.ll")));

        System.out.println();
    }

    private static Program getAst(String pathString) throws IOException {
        Path path = Paths.get(Path.of( "src", "main", "resources", "examples") + "/" + pathString);
        ScalaLexer scalaLexer = new ScalaLexer(CharStreams.fromPath(path));
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
        return (Program) visitor.visit(parseTree);
    }

    private static void dumpAst(String pathString) throws IOException {
        Program ast = getAst(pathString);
        ObjectMapper mapper = new ObjectMapper();

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        System.out.println(mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(ast));

        System.out.println();
    }

    private static StreamErrorListener errorInit(ScalaParser scalaParser) {
        StreamErrorListener errorListener = new StreamErrorListener();
        scalaParser.removeErrorListeners();
        scalaParser.addErrorListener(errorListener);

        return errorListener;
    }

    private static void dumpTokens(String pathString) throws IOException {
        Path path = Paths.get(Path.of("src", "main", "resources", "examples") + "/" + pathString);
        ScalaLexer scalaLexer = new ScalaLexer(CharStreams.fromPath(path));
        List<Token> tokens = getTokens(scalaLexer);

        for (Token token : tokens) {
            System.out.println("Loc=<" + token.getLine() + ":" + token.getCharPositionInLine() +
                    "> " + scalaLexer.getVocabulary().getSymbolicName(token.getType()) +
                    " '" + token.getText() + "'");
        }
        System.out.println();
    }

    private static List<Token> getTokens(ScalaLexer scalaLexer) {
        CommonTokenStream tokenStream = new CommonTokenStream(scalaLexer);
        tokenStream.fill();
        return tokenStream.getTokens();
    }
}
