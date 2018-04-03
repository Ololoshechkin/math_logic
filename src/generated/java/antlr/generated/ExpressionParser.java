// Generated from Expression.g4 by ANTLR 4.5.3

package antlr.generated;
import hw0.*;

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
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, IMPL=3, OR=4, AND=5, NOT=6, VAR=7, WS=8;
	public static final int
		RULE_expression = 0, RULE_disjunction = 1, RULE_conjunction = 2, RULE_negation = 3;
	public static final String[] ruleNames = {
		"expression", "disjunction", "conjunction", "negation"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'->'", "'|'", "'&'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "IMPL", "OR", "AND", "NOT", "VAR", "WS"
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

	public ExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public NodeWrapper node;
		public DisjunctionContext disj;
		public DisjunctionContext disj1;
		public ExpressionContext exp1;
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public TerminalNode IMPL() { return getToken(ExpressionParser.IMPL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		enterRule(_localctx, 0, RULE_expression);
		try {
			setState(16);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(8);
				((ExpressionContext)_localctx).disj = disjunction(0);
				((ExpressionContext)_localctx).node =  ((ExpressionContext)_localctx).disj.node;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(11);
				((ExpressionContext)_localctx).disj1 = disjunction(0);
				setState(12);
				match(IMPL);
				setState(13);
				((ExpressionContext)_localctx).exp1 = expression();
				((ExpressionContext)_localctx).node =  new Implication(((ExpressionContext)_localctx).disj1.node, ((ExpressionContext)_localctx).exp1.node);
				}
				break;
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

	public static class DisjunctionContext extends ParserRuleContext {
		public NodeWrapper node;
		public DisjunctionContext disj1;
		public ConjunctionContext conj;
		public ConjunctionContext conj1;
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public TerminalNode OR() { return getToken(ExpressionParser.OR, 0); }
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public DisjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterDisjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitDisjunction(this);
		}
	}

	public final DisjunctionContext disjunction() throws RecognitionException {
		return disjunction(0);
	}

	private DisjunctionContext disjunction(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DisjunctionContext _localctx = new DisjunctionContext(_ctx, _parentState);
		DisjunctionContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_disjunction, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(19);
			((DisjunctionContext)_localctx).conj = conjunction(0);
			((DisjunctionContext)_localctx).node =  ((DisjunctionContext)_localctx).conj.node;
			}
			_ctx.stop = _input.LT(-1);
			setState(29);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DisjunctionContext(_parentctx, _parentState);
					_localctx.disj1 = _prevctx;
					_localctx.disj1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_disjunction);
					setState(22);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(23);
					match(OR);
					setState(24);
					((DisjunctionContext)_localctx).conj1 = conjunction(0);
					((DisjunctionContext)_localctx).node =  new Disjunction(((DisjunctionContext)_localctx).disj1.node, ((DisjunctionContext)_localctx).conj1.node);
					}
					} 
				}
				setState(31);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ConjunctionContext extends ParserRuleContext {
		public NodeWrapper node;
		public ConjunctionContext conj1;
		public NegationContext neg;
		public NegationContext neg1;
		public NegationContext negation() {
			return getRuleContext(NegationContext.class,0);
		}
		public TerminalNode AND() { return getToken(ExpressionParser.AND, 0); }
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitConjunction(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		return conjunction(0);
	}

	private ConjunctionContext conjunction(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, _parentState);
		ConjunctionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_conjunction, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(33);
			((ConjunctionContext)_localctx).neg = negation();
			((ConjunctionContext)_localctx).node =  ((ConjunctionContext)_localctx).neg.node;
			}
			_ctx.stop = _input.LT(-1);
			setState(43);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ConjunctionContext(_parentctx, _parentState);
					_localctx.conj1 = _prevctx;
					_localctx.conj1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_conjunction);
					setState(36);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(37);
					match(AND);
					setState(38);
					((ConjunctionContext)_localctx).neg1 = negation();
					((ConjunctionContext)_localctx).node =  new Conjunction(((ConjunctionContext)_localctx).conj1.node, ((ConjunctionContext)_localctx).neg1.node);
					}
					} 
				}
				setState(45);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NegationContext extends ParserRuleContext {
		public NodeWrapper node;
		public Token VAR;
		public ExpressionContext exp;
		public NegationContext neg;
		public TerminalNode VAR() { return getToken(ExpressionParser.VAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(ExpressionParser.NOT, 0); }
		public NegationContext negation() {
			return getRuleContext(NegationContext.class,0);
		}
		public NegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitNegation(this);
		}
	}

	public final NegationContext negation() throws RecognitionException {
		NegationContext _localctx = new NegationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_negation);
		try {
			setState(57);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				((NegationContext)_localctx).VAR = match(VAR);
				((NegationContext)_localctx).node =  new Letter((((NegationContext)_localctx).VAR!=null?((NegationContext)_localctx).VAR.getText():null));
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				match(T__0);
				setState(49);
				((NegationContext)_localctx).exp = expression();
				setState(50);
				match(T__1);
				((NegationContext)_localctx).node =  ((NegationContext)_localctx).exp.node;
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(53);
				match(NOT);
				setState(54);
				((NegationContext)_localctx).neg = negation();
				((NegationContext)_localctx).node =  new Negation(((NegationContext)_localctx).neg.node);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return disjunction_sempred((DisjunctionContext)_localctx, predIndex);
		case 2:
			return conjunction_sempred((ConjunctionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean disjunction_sempred(DisjunctionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean conjunction_sempred(ConjunctionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\n>\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\23\n\2\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\36\n\3\f\3\16\3!\13\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\7\4,\n\4\f\4\16\4/\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5<\n\5\3\5\2\4\4\6\6\2\4\6\b\2\2>\2\22\3\2\2\2\4"+
		"\24\3\2\2\2\6\"\3\2\2\2\b;\3\2\2\2\n\13\5\4\3\2\13\f\b\2\1\2\f\23\3\2"+
		"\2\2\r\16\5\4\3\2\16\17\7\5\2\2\17\20\5\2\2\2\20\21\b\2\1\2\21\23\3\2"+
		"\2\2\22\n\3\2\2\2\22\r\3\2\2\2\23\3\3\2\2\2\24\25\b\3\1\2\25\26\5\6\4"+
		"\2\26\27\b\3\1\2\27\37\3\2\2\2\30\31\f\3\2\2\31\32\7\6\2\2\32\33\5\6\4"+
		"\2\33\34\b\3\1\2\34\36\3\2\2\2\35\30\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2"+
		"\37 \3\2\2\2 \5\3\2\2\2!\37\3\2\2\2\"#\b\4\1\2#$\5\b\5\2$%\b\4\1\2%-\3"+
		"\2\2\2&\'\f\3\2\2\'(\7\7\2\2()\5\b\5\2)*\b\4\1\2*,\3\2\2\2+&\3\2\2\2,"+
		"/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\7\3\2\2\2/-\3\2\2\2\60\61\7\t\2\2\61<\b"+
		"\5\1\2\62\63\7\3\2\2\63\64\5\2\2\2\64\65\7\4\2\2\65\66\b\5\1\2\66<\3\2"+
		"\2\2\678\7\b\2\289\5\b\5\29:\b\5\1\2:<\3\2\2\2;\60\3\2\2\2;\62\3\2\2\2"+
		";\67\3\2\2\2<\t\3\2\2\2\6\22\37-;";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}