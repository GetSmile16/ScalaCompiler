import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

public class StreamErrorListener extends BaseErrorListener {
    static class Error {
        int line;
        int column;
        String message;

        Error(int line, int column, String message) {
            this.line = line;
            this.column = column;
            this.message = message;
        }
    }

    private final List<Error> errorList = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String msg, RecognitionException e) {
        errorList.add(new Error(line, charPositionInLine, msg));
    }

    //Can I use it in SymbolVisitor?
    //private final List<Error> semanticErrors = new ArrayList<>();

    //Or can I use exceptions and don't get all semanticErrors?
    public List<Error> getErrorList() {
        return errorList;
    }
}
