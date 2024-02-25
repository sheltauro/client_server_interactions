// Generated from Parser.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ParserParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ParserParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ParserParser#messageBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessageBody(ParserParser.MessageBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ParserParser#messageRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessageRest(ParserParser.MessageRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link ParserParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(ParserParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link ParserParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(ParserParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ParserParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(ParserParser.TypeContext ctx);
}