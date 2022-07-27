// Generated from C:/Users/zar16/IdeaProjects/compilers-1-GetSmile16/antlr/src/main/antlr\ScalaLexer.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ScalaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, WHITE_SPACE=2, NEW_LINE=3, INT=4, DOUBLE=5, BOOLEAN=6, CHAR=7, 
		ARRAY=8, STRING_WORD=9, UNIT=10, TYPE=11, CASE=12, DEF=13, DO=14, ELSE=15, 
		FALSE=16, FOR=17, IF=18, NEW=19, NULL=20, OBJECT=21, PACKAGE=22, RETURN=23, 
		THEN=24, TRUE=25, VAL=26, VAR=27, WHILE=28, ID=29, PLUS=30, MINUS=31, 
		ASSIGN=32, EQ=33, MULT=34, MOD=35, DIV=36, AND=37, OR=38, MOVEMENT=39, 
		L_ARROW=40, R_ARROW=41, L_ARROW_EQ=42, R_ARROW_EQ=43, NOT_EQ=44, NOT=45, 
		L_SQUARE=46, R_SQUARE=47, L_CURL=48, R_CURL=49, L_PAREN=50, R_PAREN=51, 
		DOT=52, COMMA=53, COLON=54, SEMICOLON=55, CHAR_ESCAPE_SEQ=56, INTEGER_LITERAL=57, 
		BOOL_LITERAL=58, CHAR_LITERAL=59, STRING_LITERAL=60;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"DIGIT", "DIGIT_NO_ZERO", "SYMBOL", "UNDERLINE", "COMMENT", "WHITE_SPACE", 
			"NEW_LINE", "INT", "DOUBLE", "BOOLEAN", "CHAR", "ARRAY", "STRING_WORD", 
			"UNIT", "TYPE", "CASE", "DEF", "DO", "ELSE", "FALSE", "FOR", "IF", "NEW", 
			"NULL", "OBJECT", "PACKAGE", "RETURN", "THEN", "TRUE", "VAL", "VAR", 
			"WHILE", "ID", "PLUS", "MINUS", "ASSIGN", "EQ", "MULT", "MOD", "DIV", 
			"AND", "OR", "MOVEMENT", "L_ARROW", "R_ARROW", "L_ARROW_EQ", "R_ARROW_EQ", 
			"NOT_EQ", "NOT", "L_SQUARE", "R_SQUARE", "L_CURL", "R_CURL", "L_PAREN", 
			"R_PAREN", "DOT", "COMMA", "COLON", "SEMICOLON", "CHAR_ESCAPE_SEQ", "INTEGER_LITERAL", 
			"BOOL_LITERAL", "CHAR_LITERAL", "STRING_LITERAL"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'Int'", "'Double'", "'Boolean'", "'Char'", "'Array'", 
			"'String'", "'Unit'", null, "'case'", "'def'", "'do'", "'else'", "'false'", 
			"'for'", "'if'", "'new'", "'null'", "'object'", "'package'", "'return'", 
			"'then'", "'true'", "'val'", "'var'", "'while'", null, "'+'", "'-'", 
			"'='", "'=='", "'*'", "'%'", "'/'", "'&&'", "'||'", "'=>'", "'<'", "'>'", 
			"'<='", "'>='", "'!='", "'!'", "'['", "']'", "'{'", "'}'", "'('", "')'", 
			"'.'", "','", "':'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMENT", "WHITE_SPACE", "NEW_LINE", "INT", "DOUBLE", "BOOLEAN", 
			"CHAR", "ARRAY", "STRING_WORD", "UNIT", "TYPE", "CASE", "DEF", "DO", 
			"ELSE", "FALSE", "FOR", "IF", "NEW", "NULL", "OBJECT", "PACKAGE", "RETURN", 
			"THEN", "TRUE", "VAL", "VAR", "WHILE", "ID", "PLUS", "MINUS", "ASSIGN", 
			"EQ", "MULT", "MOD", "DIV", "AND", "OR", "MOVEMENT", "L_ARROW", "R_ARROW", 
			"L_ARROW_EQ", "R_ARROW_EQ", "NOT_EQ", "NOT", "L_SQUARE", "R_SQUARE", 
			"L_CURL", "R_CURL", "L_PAREN", "R_PAREN", "DOT", "COMMA", "COLON", "SEMICOLON", 
			"CHAR_ESCAPE_SEQ", "INTEGER_LITERAL", "BOOL_LITERAL", "CHAR_LITERAL", 
			"STRING_LITERAL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ScalaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ScalaLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000<\u0198\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007"+
		":\u0002;\u0007;\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007"+
		"?\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0003\u0002"+
		"\u0087\b\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004\u008f\b\u0004\n\u0004\f\u0004\u0092\t\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005"+
		"\u0004\u009a\b\u0004\n\u0004\f\u0004\u009d\t\u0004\u0003\u0004\u009f\b"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e"+
		"\u00da\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001 \u0001 \u0003 \u0133\b \u0001 \u0001 \u0001"+
		" \u0005 \u0138\b \n \f \u013b\t \u0001!\u0001!\u0001\"\u0001\"\u0001#"+
		"\u0001#\u0001$\u0001$\u0001$\u0001%\u0001%\u0001&\u0001&\u0001\'\u0001"+
		"\'\u0001(\u0001(\u0001(\u0001)\u0001)\u0001)\u0001*\u0001*\u0001*\u0001"+
		"+\u0001+\u0001,\u0001,\u0001-\u0001-\u0001-\u0001.\u0001.\u0001.\u0001"+
		"/\u0001/\u0001/\u00010\u00010\u00011\u00011\u00012\u00012\u00013\u0001"+
		"3\u00014\u00014\u00015\u00015\u00016\u00016\u00017\u00017\u00018\u0001"+
		"8\u00019\u00019\u0001:\u0001:\u0001;\u0001;\u0001;\u0001<\u0001<\u0001"+
		"<\u0005<\u017e\b<\n<\f<\u0181\t<\u0003<\u0183\b<\u0001=\u0001=\u0003="+
		"\u0187\b=\u0001>\u0001>\u0001>\u0003>\u018c\b>\u0001>\u0001>\u0001?\u0001"+
		"?\u0005?\u0192\b?\n?\f?\u0195\t?\u0001?\u0001?\u0001\u0090\u0000@\u0001"+
		"\u0000\u0003\u0000\u0005\u0000\u0007\u0000\t\u0001\u000b\u0002\r\u0003"+
		"\u000f\u0004\u0011\u0005\u0013\u0006\u0015\u0007\u0017\b\u0019\t\u001b"+
		"\n\u001d\u000b\u001f\f!\r#\u000e%\u000f\'\u0010)\u0011+\u0012-\u0013/"+
		"\u00141\u00153\u00165\u00177\u00189\u0019;\u001a=\u001b?\u001cA\u001d"+
		"C\u001eE\u001fG I!K\"M#O$Q%S&U\'W(Y)[*]+_,a-c.e/g0i1k2m3o4q5s6u7w8y9{"+
		":};\u007f<\u0001\u0000\u0007\u0001\u000009\u0001\u000019\u0002\u0000A"+
		"Zaz\u0002\u0000\n\n\r\r\u0003\u0000\t\n\f\f  \b\u0000\"\"\'\'\\\\bbff"+
		"nnrrtt\u0003\u0000\n\n\r\r\"\"\u01a3\u0000\t\u0001\u0000\u0000\u0000\u0000"+
		"\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013"+
		"\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017"+
		"\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b"+
		"\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f"+
		"\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000"+
		"\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000"+
		"\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000"+
		"-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001"+
		"\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000"+
		"\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000"+
		";\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001"+
		"\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000"+
		"\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0000"+
		"I\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000\u0000M\u0001"+
		"\u0000\u0000\u0000\u0000O\u0001\u0000\u0000\u0000\u0000Q\u0001\u0000\u0000"+
		"\u0000\u0000S\u0001\u0000\u0000\u0000\u0000U\u0001\u0000\u0000\u0000\u0000"+
		"W\u0001\u0000\u0000\u0000\u0000Y\u0001\u0000\u0000\u0000\u0000[\u0001"+
		"\u0000\u0000\u0000\u0000]\u0001\u0000\u0000\u0000\u0000_\u0001\u0000\u0000"+
		"\u0000\u0000a\u0001\u0000\u0000\u0000\u0000c\u0001\u0000\u0000\u0000\u0000"+
		"e\u0001\u0000\u0000\u0000\u0000g\u0001\u0000\u0000\u0000\u0000i\u0001"+
		"\u0000\u0000\u0000\u0000k\u0001\u0000\u0000\u0000\u0000m\u0001\u0000\u0000"+
		"\u0000\u0000o\u0001\u0000\u0000\u0000\u0000q\u0001\u0000\u0000\u0000\u0000"+
		"s\u0001\u0000\u0000\u0000\u0000u\u0001\u0000\u0000\u0000\u0000w\u0001"+
		"\u0000\u0000\u0000\u0000y\u0001\u0000\u0000\u0000\u0000{\u0001\u0000\u0000"+
		"\u0000\u0000}\u0001\u0000\u0000\u0000\u0000\u007f\u0001\u0000\u0000\u0000"+
		"\u0001\u0081\u0001\u0000\u0000\u0000\u0003\u0083\u0001\u0000\u0000\u0000"+
		"\u0005\u0086\u0001\u0000\u0000\u0000\u0007\u0088\u0001\u0000\u0000\u0000"+
		"\t\u009e\u0001\u0000\u0000\u0000\u000b\u00a2\u0001\u0000\u0000\u0000\r"+
		"\u00a6\u0001\u0000\u0000\u0000\u000f\u00aa\u0001\u0000\u0000\u0000\u0011"+
		"\u00ae\u0001\u0000\u0000\u0000\u0013\u00b5\u0001\u0000\u0000\u0000\u0015"+
		"\u00bd\u0001\u0000\u0000\u0000\u0017\u00c2\u0001\u0000\u0000\u0000\u0019"+
		"\u00c8\u0001\u0000\u0000\u0000\u001b\u00cf\u0001\u0000\u0000\u0000\u001d"+
		"\u00d9\u0001\u0000\u0000\u0000\u001f\u00db\u0001\u0000\u0000\u0000!\u00e0"+
		"\u0001\u0000\u0000\u0000#\u00e4\u0001\u0000\u0000\u0000%\u00e7\u0001\u0000"+
		"\u0000\u0000\'\u00ec\u0001\u0000\u0000\u0000)\u00f2\u0001\u0000\u0000"+
		"\u0000+\u00f6\u0001\u0000\u0000\u0000-\u00f9\u0001\u0000\u0000\u0000/"+
		"\u00fd\u0001\u0000\u0000\u00001\u0102\u0001\u0000\u0000\u00003\u0109\u0001"+
		"\u0000\u0000\u00005\u0111\u0001\u0000\u0000\u00007\u0118\u0001\u0000\u0000"+
		"\u00009\u011d\u0001\u0000\u0000\u0000;\u0122\u0001\u0000\u0000\u0000="+
		"\u0126\u0001\u0000\u0000\u0000?\u012a\u0001\u0000\u0000\u0000A\u0132\u0001"+
		"\u0000\u0000\u0000C\u013c\u0001\u0000\u0000\u0000E\u013e\u0001\u0000\u0000"+
		"\u0000G\u0140\u0001\u0000\u0000\u0000I\u0142\u0001\u0000\u0000\u0000K"+
		"\u0145\u0001\u0000\u0000\u0000M\u0147\u0001\u0000\u0000\u0000O\u0149\u0001"+
		"\u0000\u0000\u0000Q\u014b\u0001\u0000\u0000\u0000S\u014e\u0001\u0000\u0000"+
		"\u0000U\u0151\u0001\u0000\u0000\u0000W\u0154\u0001\u0000\u0000\u0000Y"+
		"\u0156\u0001\u0000\u0000\u0000[\u0158\u0001\u0000\u0000\u0000]\u015b\u0001"+
		"\u0000\u0000\u0000_\u015e\u0001\u0000\u0000\u0000a\u0161\u0001\u0000\u0000"+
		"\u0000c\u0163\u0001\u0000\u0000\u0000e\u0165\u0001\u0000\u0000\u0000g"+
		"\u0167\u0001\u0000\u0000\u0000i\u0169\u0001\u0000\u0000\u0000k\u016b\u0001"+
		"\u0000\u0000\u0000m\u016d\u0001\u0000\u0000\u0000o\u016f\u0001\u0000\u0000"+
		"\u0000q\u0171\u0001\u0000\u0000\u0000s\u0173\u0001\u0000\u0000\u0000u"+
		"\u0175\u0001\u0000\u0000\u0000w\u0177\u0001\u0000\u0000\u0000y\u0182\u0001"+
		"\u0000\u0000\u0000{\u0186\u0001\u0000\u0000\u0000}\u0188\u0001\u0000\u0000"+
		"\u0000\u007f\u018f\u0001\u0000\u0000\u0000\u0081\u0082\u0007\u0000\u0000"+
		"\u0000\u0082\u0002\u0001\u0000\u0000\u0000\u0083\u0084\u0007\u0001\u0000"+
		"\u0000\u0084\u0004\u0001\u0000\u0000\u0000\u0085\u0087\u0007\u0002\u0000"+
		"\u0000\u0086\u0085\u0001\u0000\u0000\u0000\u0087\u0006\u0001\u0000\u0000"+
		"\u0000\u0088\u0089\u0005_\u0000\u0000\u0089\b\u0001\u0000\u0000\u0000"+
		"\u008a\u008b\u0005/\u0000\u0000\u008b\u008c\u0005*\u0000\u0000\u008c\u0090"+
		"\u0001\u0000\u0000\u0000\u008d\u008f\t\u0000\u0000\u0000\u008e\u008d\u0001"+
		"\u0000\u0000\u0000\u008f\u0092\u0001\u0000\u0000\u0000\u0090\u0091\u0001"+
		"\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091\u0093\u0001"+
		"\u0000\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0093\u0094\u0005"+
		"*\u0000\u0000\u0094\u009f\u0005/\u0000\u0000\u0095\u0096\u0005/\u0000"+
		"\u0000\u0096\u0097\u0005/\u0000\u0000\u0097\u009b\u0001\u0000\u0000\u0000"+
		"\u0098\u009a\b\u0003\u0000\u0000\u0099\u0098\u0001\u0000\u0000\u0000\u009a"+
		"\u009d\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000\u0000\u009b"+
		"\u009c\u0001\u0000\u0000\u0000\u009c\u009f\u0001\u0000\u0000\u0000\u009d"+
		"\u009b\u0001\u0000\u0000\u0000\u009e\u008a\u0001\u0000\u0000\u0000\u009e"+
		"\u0095\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0"+
		"\u00a1\u0006\u0004\u0000\u0000\u00a1\n\u0001\u0000\u0000\u0000\u00a2\u00a3"+
		"\u0007\u0004\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a5"+
		"\u0006\u0005\u0000\u0000\u00a5\f\u0001\u0000\u0000\u0000\u00a6\u00a7\u0007"+
		"\u0003\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9\u0006"+
		"\u0006\u0000\u0000\u00a9\u000e\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005"+
		"I\u0000\u0000\u00ab\u00ac\u0005n\u0000\u0000\u00ac\u00ad\u0005t\u0000"+
		"\u0000\u00ad\u0010\u0001\u0000\u0000\u0000\u00ae\u00af\u0005D\u0000\u0000"+
		"\u00af\u00b0\u0005o\u0000\u0000\u00b0\u00b1\u0005u\u0000\u0000\u00b1\u00b2"+
		"\u0005b\u0000\u0000\u00b2\u00b3\u0005l\u0000\u0000\u00b3\u00b4\u0005e"+
		"\u0000\u0000\u00b4\u0012\u0001\u0000\u0000\u0000\u00b5\u00b6\u0005B\u0000"+
		"\u0000\u00b6\u00b7\u0005o\u0000\u0000\u00b7\u00b8\u0005o\u0000\u0000\u00b8"+
		"\u00b9\u0005l\u0000\u0000\u00b9\u00ba\u0005e\u0000\u0000\u00ba\u00bb\u0005"+
		"a\u0000\u0000\u00bb\u00bc\u0005n\u0000\u0000\u00bc\u0014\u0001\u0000\u0000"+
		"\u0000\u00bd\u00be\u0005C\u0000\u0000\u00be\u00bf\u0005h\u0000\u0000\u00bf"+
		"\u00c0\u0005a\u0000\u0000\u00c0\u00c1\u0005r\u0000\u0000\u00c1\u0016\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c3\u0005A\u0000\u0000\u00c3\u00c4\u0005r\u0000"+
		"\u0000\u00c4\u00c5\u0005r\u0000\u0000\u00c5\u00c6\u0005a\u0000\u0000\u00c6"+
		"\u00c7\u0005y\u0000\u0000\u00c7\u0018\u0001\u0000\u0000\u0000\u00c8\u00c9"+
		"\u0005S\u0000\u0000\u00c9\u00ca\u0005t\u0000\u0000\u00ca\u00cb\u0005r"+
		"\u0000\u0000\u00cb\u00cc\u0005i\u0000\u0000\u00cc\u00cd\u0005n\u0000\u0000"+
		"\u00cd\u00ce\u0005g\u0000\u0000\u00ce\u001a\u0001\u0000\u0000\u0000\u00cf"+
		"\u00d0\u0005U\u0000\u0000\u00d0\u00d1\u0005n\u0000\u0000\u00d1\u00d2\u0005"+
		"i\u0000\u0000\u00d2\u00d3\u0005t\u0000\u0000\u00d3\u001c\u0001\u0000\u0000"+
		"\u0000\u00d4\u00da\u0003\u000f\u0007\u0000\u00d5\u00da\u0003\u0011\b\u0000"+
		"\u00d6\u00da\u0003\u0013\t\u0000\u00d7\u00da\u0003\u0015\n\u0000\u00d8"+
		"\u00da\u0003\u0019\f\u0000\u00d9\u00d4\u0001\u0000\u0000\u0000\u00d9\u00d5"+
		"\u0001\u0000\u0000\u0000\u00d9\u00d6\u0001\u0000\u0000\u0000\u00d9\u00d7"+
		"\u0001\u0000\u0000\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000\u00da\u001e"+
		"\u0001\u0000\u0000\u0000\u00db\u00dc\u0005c\u0000\u0000\u00dc\u00dd\u0005"+
		"a\u0000\u0000\u00dd\u00de\u0005s\u0000\u0000\u00de\u00df\u0005e\u0000"+
		"\u0000\u00df \u0001\u0000\u0000\u0000\u00e0\u00e1\u0005d\u0000\u0000\u00e1"+
		"\u00e2\u0005e\u0000\u0000\u00e2\u00e3\u0005f\u0000\u0000\u00e3\"\u0001"+
		"\u0000\u0000\u0000\u00e4\u00e5\u0005d\u0000\u0000\u00e5\u00e6\u0005o\u0000"+
		"\u0000\u00e6$\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005e\u0000\u0000\u00e8"+
		"\u00e9\u0005l\u0000\u0000\u00e9\u00ea\u0005s\u0000\u0000\u00ea\u00eb\u0005"+
		"e\u0000\u0000\u00eb&\u0001\u0000\u0000\u0000\u00ec\u00ed\u0005f\u0000"+
		"\u0000\u00ed\u00ee\u0005a\u0000\u0000\u00ee\u00ef\u0005l\u0000\u0000\u00ef"+
		"\u00f0\u0005s\u0000\u0000\u00f0\u00f1\u0005e\u0000\u0000\u00f1(\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f3\u0005f\u0000\u0000\u00f3\u00f4\u0005o\u0000"+
		"\u0000\u00f4\u00f5\u0005r\u0000\u0000\u00f5*\u0001\u0000\u0000\u0000\u00f6"+
		"\u00f7\u0005i\u0000\u0000\u00f7\u00f8\u0005f\u0000\u0000\u00f8,\u0001"+
		"\u0000\u0000\u0000\u00f9\u00fa\u0005n\u0000\u0000\u00fa\u00fb\u0005e\u0000"+
		"\u0000\u00fb\u00fc\u0005w\u0000\u0000\u00fc.\u0001\u0000\u0000\u0000\u00fd"+
		"\u00fe\u0005n\u0000\u0000\u00fe\u00ff\u0005u\u0000\u0000\u00ff\u0100\u0005"+
		"l\u0000\u0000\u0100\u0101\u0005l\u0000\u0000\u01010\u0001\u0000\u0000"+
		"\u0000\u0102\u0103\u0005o\u0000\u0000\u0103\u0104\u0005b\u0000\u0000\u0104"+
		"\u0105\u0005j\u0000\u0000\u0105\u0106\u0005e\u0000\u0000\u0106\u0107\u0005"+
		"c\u0000\u0000\u0107\u0108\u0005t\u0000\u0000\u01082\u0001\u0000\u0000"+
		"\u0000\u0109\u010a\u0005p\u0000\u0000\u010a\u010b\u0005a\u0000\u0000\u010b"+
		"\u010c\u0005c\u0000\u0000\u010c\u010d\u0005k\u0000\u0000\u010d\u010e\u0005"+
		"a\u0000\u0000\u010e\u010f\u0005g\u0000\u0000\u010f\u0110\u0005e\u0000"+
		"\u0000\u01104\u0001\u0000\u0000\u0000\u0111\u0112\u0005r\u0000\u0000\u0112"+
		"\u0113\u0005e\u0000\u0000\u0113\u0114\u0005t\u0000\u0000\u0114\u0115\u0005"+
		"u\u0000\u0000\u0115\u0116\u0005r\u0000\u0000\u0116\u0117\u0005n\u0000"+
		"\u0000\u01176\u0001\u0000\u0000\u0000\u0118\u0119\u0005t\u0000\u0000\u0119"+
		"\u011a\u0005h\u0000\u0000\u011a\u011b\u0005e\u0000\u0000\u011b\u011c\u0005"+
		"n\u0000\u0000\u011c8\u0001\u0000\u0000\u0000\u011d\u011e\u0005t\u0000"+
		"\u0000\u011e\u011f\u0005r\u0000\u0000\u011f\u0120\u0005u\u0000\u0000\u0120"+
		"\u0121\u0005e\u0000\u0000\u0121:\u0001\u0000\u0000\u0000\u0122\u0123\u0005"+
		"v\u0000\u0000\u0123\u0124\u0005a\u0000\u0000\u0124\u0125\u0005l\u0000"+
		"\u0000\u0125<\u0001\u0000\u0000\u0000\u0126\u0127\u0005v\u0000\u0000\u0127"+
		"\u0128\u0005a\u0000\u0000\u0128\u0129\u0005r\u0000\u0000\u0129>\u0001"+
		"\u0000\u0000\u0000\u012a\u012b\u0005w\u0000\u0000\u012b\u012c\u0005h\u0000"+
		"\u0000\u012c\u012d\u0005i\u0000\u0000\u012d\u012e\u0005l\u0000\u0000\u012e"+
		"\u012f\u0005e\u0000\u0000\u012f@\u0001\u0000\u0000\u0000\u0130\u0133\u0003"+
		"\u0007\u0003\u0000\u0131\u0133\u0003\u0005\u0002\u0000\u0132\u0130\u0001"+
		"\u0000\u0000\u0000\u0132\u0131\u0001\u0000\u0000\u0000\u0133\u0139\u0001"+
		"\u0000\u0000\u0000\u0134\u0138\u0003\u0005\u0002\u0000\u0135\u0138\u0003"+
		"\u0001\u0000\u0000\u0136\u0138\u0003\u0007\u0003\u0000\u0137\u0134\u0001"+
		"\u0000\u0000\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0137\u0136\u0001"+
		"\u0000\u0000\u0000\u0138\u013b\u0001\u0000\u0000\u0000\u0139\u0137\u0001"+
		"\u0000\u0000\u0000\u0139\u013a\u0001\u0000\u0000\u0000\u013aB\u0001\u0000"+
		"\u0000\u0000\u013b\u0139\u0001\u0000\u0000\u0000\u013c\u013d\u0005+\u0000"+
		"\u0000\u013dD\u0001\u0000\u0000\u0000\u013e\u013f\u0005-\u0000\u0000\u013f"+
		"F\u0001\u0000\u0000\u0000\u0140\u0141\u0005=\u0000\u0000\u0141H\u0001"+
		"\u0000\u0000\u0000\u0142\u0143\u0005=\u0000\u0000\u0143\u0144\u0005=\u0000"+
		"\u0000\u0144J\u0001\u0000\u0000\u0000\u0145\u0146\u0005*\u0000\u0000\u0146"+
		"L\u0001\u0000\u0000\u0000\u0147\u0148\u0005%\u0000\u0000\u0148N\u0001"+
		"\u0000\u0000\u0000\u0149\u014a\u0005/\u0000\u0000\u014aP\u0001\u0000\u0000"+
		"\u0000\u014b\u014c\u0005&\u0000\u0000\u014c\u014d\u0005&\u0000\u0000\u014d"+
		"R\u0001\u0000\u0000\u0000\u014e\u014f\u0005|\u0000\u0000\u014f\u0150\u0005"+
		"|\u0000\u0000\u0150T\u0001\u0000\u0000\u0000\u0151\u0152\u0005=\u0000"+
		"\u0000\u0152\u0153\u0005>\u0000\u0000\u0153V\u0001\u0000\u0000\u0000\u0154"+
		"\u0155\u0005<\u0000\u0000\u0155X\u0001\u0000\u0000\u0000\u0156\u0157\u0005"+
		">\u0000\u0000\u0157Z\u0001\u0000\u0000\u0000\u0158\u0159\u0005<\u0000"+
		"\u0000\u0159\u015a\u0005=\u0000\u0000\u015a\\\u0001\u0000\u0000\u0000"+
		"\u015b\u015c\u0005>\u0000\u0000\u015c\u015d\u0005=\u0000\u0000\u015d^"+
		"\u0001\u0000\u0000\u0000\u015e\u015f\u0005!\u0000\u0000\u015f\u0160\u0005"+
		"=\u0000\u0000\u0160`\u0001\u0000\u0000\u0000\u0161\u0162\u0005!\u0000"+
		"\u0000\u0162b\u0001\u0000\u0000\u0000\u0163\u0164\u0005[\u0000\u0000\u0164"+
		"d\u0001\u0000\u0000\u0000\u0165\u0166\u0005]\u0000\u0000\u0166f\u0001"+
		"\u0000\u0000\u0000\u0167\u0168\u0005{\u0000\u0000\u0168h\u0001\u0000\u0000"+
		"\u0000\u0169\u016a\u0005}\u0000\u0000\u016aj\u0001\u0000\u0000\u0000\u016b"+
		"\u016c\u0005(\u0000\u0000\u016cl\u0001\u0000\u0000\u0000\u016d\u016e\u0005"+
		")\u0000\u0000\u016en\u0001\u0000\u0000\u0000\u016f\u0170\u0005.\u0000"+
		"\u0000\u0170p\u0001\u0000\u0000\u0000\u0171\u0172\u0005,\u0000\u0000\u0172"+
		"r\u0001\u0000\u0000\u0000\u0173\u0174\u0005:\u0000\u0000\u0174t\u0001"+
		"\u0000\u0000\u0000\u0175\u0176\u0005;\u0000\u0000\u0176v\u0001\u0000\u0000"+
		"\u0000\u0177\u0178\u0005\\\u0000\u0000\u0178\u0179\u0007\u0005\u0000\u0000"+
		"\u0179x\u0001\u0000\u0000\u0000\u017a\u0183\u0003\u0001\u0000\u0000\u017b"+
		"\u017f\u0003\u0003\u0001\u0000\u017c\u017e\u0003\u0001\u0000\u0000\u017d"+
		"\u017c\u0001\u0000\u0000\u0000\u017e\u0181\u0001\u0000\u0000\u0000\u017f"+
		"\u017d\u0001\u0000\u0000\u0000\u017f\u0180\u0001\u0000\u0000\u0000\u0180"+
		"\u0183\u0001\u0000\u0000\u0000\u0181\u017f\u0001\u0000\u0000\u0000\u0182"+
		"\u017a\u0001\u0000\u0000\u0000\u0182\u017b\u0001\u0000\u0000\u0000\u0183"+
		"z\u0001\u0000\u0000\u0000\u0184\u0187\u00039\u001c\u0000\u0185\u0187\u0003"+
		"\'\u0013\u0000\u0186\u0184\u0001\u0000\u0000\u0000\u0186\u0185\u0001\u0000"+
		"\u0000\u0000\u0187|\u0001\u0000\u0000\u0000\u0188\u018b\u0005\'\u0000"+
		"\u0000\u0189\u018c\t\u0000\u0000\u0000\u018a\u018c\u0003w;\u0000\u018b"+
		"\u0189\u0001\u0000\u0000\u0000\u018b\u018a\u0001\u0000\u0000\u0000\u018c"+
		"\u018d\u0001\u0000\u0000\u0000\u018d\u018e\u0005\'\u0000\u0000\u018e~"+
		"\u0001\u0000\u0000\u0000\u018f\u0193\u0005\"\u0000\u0000\u0190\u0192\b"+
		"\u0006\u0000\u0000\u0191\u0190\u0001\u0000\u0000\u0000\u0192\u0195\u0001"+
		"\u0000\u0000\u0000\u0193\u0191\u0001\u0000\u0000\u0000\u0193\u0194\u0001"+
		"\u0000\u0000\u0000\u0194\u0196\u0001\u0000\u0000\u0000\u0195\u0193\u0001"+
		"\u0000\u0000\u0000\u0196\u0197\u0005\"\u0000\u0000\u0197\u0080\u0001\u0000"+
		"\u0000\u0000\u000e\u0000\u0086\u0090\u009b\u009e\u00d9\u0132\u0137\u0139"+
		"\u017f\u0182\u0186\u018b\u0193\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}