// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, NUMBER=5, VARIABLE=6, SPACES=7;
  public static final int
    RULE_root = 0, RULE_expression = 1, RULE_addExpression = 2, RULE_mulExpression = 3;
  public static final String[] ruleNames = {
    "root", "expression", "addExpression", "mulExpression"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'+'", "'*'", "'('", "')'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, "NUMBER", "VARIABLE", "SPACES"
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

  @Override
  public String getGrammarFileName() { return "Expression.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public ATN getATN() { return _ATN; }


      // This method makes the lexer or parser stop running if it encounters
      // invalid input and throw a ParseCancellationException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol,
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }

  public ExpressionParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class RootContext extends ParserRuleContext {
    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class,0);
    }
    public TerminalNode EOF() { return getToken(ExpressionParser.EOF, 0); }
    public RootContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_root; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterRoot(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitRoot(this);
    }
  }

  public final RootContext root() throws RecognitionException {
    RootContext _localctx = new RootContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_root);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(8);
      expression();
      setState(9);
      match(EOF);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class ExpressionContext extends ParserRuleContext {
    public List<AddExpressionContext> addExpression() {
      return getRuleContexts(AddExpressionContext.class);
    }
    public AddExpressionContext addExpression(int i) {
      return getRuleContext(AddExpressionContext.class,i);
    }
    public ExpressionContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_expression; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterExpression(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitExpression(this);
    }
  }

  public final ExpressionContext expression() throws RecognitionException {
    ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_expression);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(11);
      addExpression();
      setState(16);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__0) {
        {
        {
        setState(12);
        match(T__0);
        setState(13);
        addExpression();
        }
        }
        setState(18);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class AddExpressionContext extends ParserRuleContext {
    public List<MulExpressionContext> mulExpression() {
      return getRuleContexts(MulExpressionContext.class);
    }
    public MulExpressionContext mulExpression(int i) {
      return getRuleContext(MulExpressionContext.class,i);
    }
    public AddExpressionContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_addExpression; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterAddExpression(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitAddExpression(this);
    }
  }

  public final AddExpressionContext addExpression() throws RecognitionException {
    AddExpressionContext _localctx = new AddExpressionContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_addExpression);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(19);
      mulExpression();
      setState(24);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__1) {
        {
        {
        setState(20);
        match(T__1);
        setState(21);
        mulExpression();
        }
        }
        setState(26);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class MulExpressionContext extends ParserRuleContext {
    public TerminalNode NUMBER() { return getToken(ExpressionParser.NUMBER, 0); }
    public TerminalNode VARIABLE() { return getToken(ExpressionParser.VARIABLE, 0); }
    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class,0);
    }
    public MulExpressionContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_mulExpression; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterMulExpression(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitMulExpression(this);
    }
  }

  public final MulExpressionContext mulExpression() throws RecognitionException {
    MulExpressionContext _localctx = new MulExpressionContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_mulExpression);
    try {
      setState(33);
      switch (_input.LA(1)) {
      case NUMBER:
        enterOuterAlt(_localctx, 1);
        {
        setState(27);
        match(NUMBER);
        }
        break;
      case VARIABLE:
        enterOuterAlt(_localctx, 2);
        {
        setState(28);
        match(VARIABLE);
        }
        break;
      case T__2:
        enterOuterAlt(_localctx, 3);
        {
        setState(29);
        match(T__2);
        setState(30);
        expression();
        setState(31);
        match(T__3);
        }
        break;
      default:
        throw new NoViableAltException(this);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\t&\4\2\t\2\4\3"+
      "\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\3\3\3\3\3\7\3\21\n\3\f\3\16\3\24"+
      "\13\3\3\4\3\4\3\4\7\4\31\n\4\f\4\16\4\34\13\4\3\5\3\5\3\5\3\5\3\5"+
      "\3\5\5\5$\n\5\3\5\2\2\6\2\4\6\b\2\2%\2\n\3\2\2\2\4\r\3\2\2\2\6\25"+
      "\3\2\2\2\b#\3\2\2\2\n\13\5\4\3\2\13\f\7\2\2\3\f\3\3\2\2\2\r\22\5\6"+
      "\4\2\16\17\7\3\2\2\17\21\5\6\4\2\20\16\3\2\2\2\21\24\3\2\2\2\22\20"+
      "\3\2\2\2\22\23\3\2\2\2\23\5\3\2\2\2\24\22\3\2\2\2\25\32\5\b\5\2\26"+
      "\27\7\4\2\2\27\31\5\b\5\2\30\26\3\2\2\2\31\34\3\2\2\2\32\30\3\2\2"+
      "\2\32\33\3\2\2\2\33\7\3\2\2\2\34\32\3\2\2\2\35$\7\7\2\2\36$\7\b\2"+
      "\2\37 \7\5\2\2 !\5\4\3\2!\"\7\6\2\2\"$\3\2\2\2#\35\3\2\2\2#\36\3\2"+
      "\2\2#\37\3\2\2\2$\t\3\2\2\2\5\22\32#";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}