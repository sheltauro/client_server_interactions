// Generated from Parser.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ParserParser}.
 */
public interface ParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ParserParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ParserParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ParserParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#messageBody}.
	 * @param ctx the parse tree
	 */
	void enterMessageBody(ParserParser.MessageBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#messageBody}.
	 * @param ctx the parse tree
	 */
	void exitMessageBody(ParserParser.MessageBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#messageRest}.
	 * @param ctx the parse tree
	 */
	void enterMessageRest(ParserParser.MessageRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#messageRest}.
	 * @param ctx the parse tree
	 */
	void exitMessageRest(ParserParser.MessageRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(ParserParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(ParserParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(ParserParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(ParserParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ParserParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ParserParser.TypeContext ctx);
}