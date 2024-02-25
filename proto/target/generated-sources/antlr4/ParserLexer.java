// Generated from Parser.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ParserLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MESSAGE=1, OPTIONAL=2, REQUIRED=3, REPEATED=4, INT32=5, STRING=6, BOOL=7, 
		LCURLY=8, RCURLY=9, EQ=10, SEMI=11, ID=12, INT=13, LINE_COMMENT=14, SPACE=15, 
		NEWLINE=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"MESSAGE", "OPTIONAL", "REQUIRED", "REPEATED", "INT32", "STRING", "BOOL", 
		"LCURLY", "RCURLY", "EQ", "SEMI", "ID", "INT", "LINE_COMMENT", "SPACE", 
		"NEWLINE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'message'", "'optional'", "'required'", "'repeated'", "'int32'", 
		"'string'", "'bool'", "'{'", "'}'", "'='", "';'", null, null, null, "' '", 
		"'\n'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "MESSAGE", "OPTIONAL", "REQUIRED", "REPEATED", "INT32", "STRING", 
		"BOOL", "LCURLY", "RCURLY", "EQ", "SEMI", "ID", "INT", "LINE_COMMENT", 
		"SPACE", "NEWLINE"
	};
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


	public ParserLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Parser.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22\177\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\7\rc\n\r\f\r\16\rf\13\r\3\16\6\16"+
		"i\n\16\r\16\16\16j\3\17\3\17\3\17\3\17\7\17q\n\17\f\17\16\17t\13\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\2\2\22\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22\3\2"+
		"\6\5\2C\\aac|\6\2\62;C\\aac|\3\2\62;\4\2\f\f\17\17\2\u0081\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\3#\3\2\2\2\5+\3\2\2"+
		"\2\7\64\3\2\2\2\t=\3\2\2\2\13F\3\2\2\2\rL\3\2\2\2\17S\3\2\2\2\21X\3\2"+
		"\2\2\23Z\3\2\2\2\25\\\3\2\2\2\27^\3\2\2\2\31`\3\2\2\2\33h\3\2\2\2\35l"+
		"\3\2\2\2\37w\3\2\2\2!{\3\2\2\2#$\7o\2\2$%\7g\2\2%&\7u\2\2&\'\7u\2\2\'"+
		"(\7c\2\2()\7i\2\2)*\7g\2\2*\4\3\2\2\2+,\7q\2\2,-\7r\2\2-.\7v\2\2./\7k"+
		"\2\2/\60\7q\2\2\60\61\7p\2\2\61\62\7c\2\2\62\63\7n\2\2\63\6\3\2\2\2\64"+
		"\65\7t\2\2\65\66\7g\2\2\66\67\7s\2\2\678\7w\2\289\7k\2\29:\7t\2\2:;\7"+
		"g\2\2;<\7f\2\2<\b\3\2\2\2=>\7t\2\2>?\7g\2\2?@\7r\2\2@A\7g\2\2AB\7c\2\2"+
		"BC\7v\2\2CD\7g\2\2DE\7f\2\2E\n\3\2\2\2FG\7k\2\2GH\7p\2\2HI\7v\2\2IJ\7"+
		"\65\2\2JK\7\64\2\2K\f\3\2\2\2LM\7u\2\2MN\7v\2\2NO\7t\2\2OP\7k\2\2PQ\7"+
		"p\2\2QR\7i\2\2R\16\3\2\2\2ST\7d\2\2TU\7q\2\2UV\7q\2\2VW\7n\2\2W\20\3\2"+
		"\2\2XY\7}\2\2Y\22\3\2\2\2Z[\7\177\2\2[\24\3\2\2\2\\]\7?\2\2]\26\3\2\2"+
		"\2^_\7=\2\2_\30\3\2\2\2`d\t\2\2\2ac\t\3\2\2ba\3\2\2\2cf\3\2\2\2db\3\2"+
		"\2\2de\3\2\2\2e\32\3\2\2\2fd\3\2\2\2gi\t\4\2\2hg\3\2\2\2ij\3\2\2\2jh\3"+
		"\2\2\2jk\3\2\2\2k\34\3\2\2\2lm\7\61\2\2mn\7\61\2\2nr\3\2\2\2oq\n\5\2\2"+
		"po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2tr\3\2\2\2uv\b\17\2"+
		"\2v\36\3\2\2\2wx\7\"\2\2xy\3\2\2\2yz\b\20\2\2z \3\2\2\2{|\7\f\2\2|}\3"+
		"\2\2\2}~\b\21\2\2~\"\3\2\2\2\6\2djr\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}