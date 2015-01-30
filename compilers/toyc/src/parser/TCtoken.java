/*
 * Sean Burns
 * 
 * TCtoken
 */
package parser;

import compilers.Token;

/********************************************************************************************
 * class TCtoken
 * 		Represents a token in the abstract language of ToyC. Pretty small, trivial language,
 * 		so there aren't many tokens involved.
 * @author sbhurns
 *********************************************************************************************/
public class TCtoken implements Token {
	
	/********************************************************************************************
	 * Tokens
	 * 		Enumerates all of the possible token types for the language.
	 * @author sbhurns
	 *********************************************************************************************/
	public static enum Tokens { INT, CHAR, RETURN, IF, ELSE, FOR, DO, WHILE, SWITCH,
								CASE, DEFAULT, WRITE, READ, CONTINUE, BREAK, NEWLINE,
								ID, NUMBER, CHARLITERAL, STRING, RELOP, ADDOP, MULOP,
								ASSIGNOP, NOT, LPAREN, RPAREN, LCURLY, RCURLY, LBRACKET,
								RBRACKET, COMMA, SEMICOLON, COLON, EOF, INVALID };
	
	private Tokens tokenType;
	private String lexeme;
	
	/********************************************************************************************
	 * TCtoken()
	 * 		Constructor function for a TCtoken instance.
	 *********************************************************************************************/
	public TCtoken() {
		tokenType = Tokens.INVALID;
	}
	
	/********************************************************************************************
	 * TCtoken
	 * 		Constructor taking as its argument a value of the Tokens enumerated type, indicating
	 * 		the type of the Token being instantiated.
	 * @param t
	 * 		The Tokens enumerated type of the TCtoken being instantiated.
	 *********************************************************************************************/
	public TCtoken(Tokens t) {
		tokenType = t;
		lexeme = null;
	}
	
	/********************************************************************************************
	 * TCtoken
	 * 		Constructor which takes both the Tokens enumerated type and the value of the lexeme
	 * 		that the Token will be representing.
	 * @param t
	 * 		The Tokens enumerated type of the TCtoken object being instatiated.
	 * @param str
	 * 		The value of the lexeme the token is representing in the abstract syntax tree.
	 *********************************************************************************************/
	public TCtoken(Tokens t, String str) {
		tokenType = t;
		lexeme = str;
	}
	
	/********************************************************************************************
	 * getTokenType()
	 * 		Returns the Tokens enumerated type of the instance of the TCtoken object.
	 * @return
	 * 		The Tokens enumerated type of the instance.
	 *********************************************************************************************/
	public Tokens getTokenType() {
		return tokenType;
	}
	
	/********************************************************************************************
	 * getLexeme()
	 * 		Returns the source-code value of the token (the lexeme of the token).
	 * @return
	 * 		The source-code value of the token (e.g. an 'ADDOP' token would return '+')
	 *********************************************************************************************/
	public String getLexeme() {
		return lexeme;
	}
	
	/********************************************************************************************
	 * equals()
	 * 		Determines whether or not two tokens have the same type and lexeme.
	 *@param t
	 *		The TCtoken object being compared to this instance of TCtoken.
	 *@return
	 *		bool: Whether or not the two tokens have the same tokenType and lexeme value.
	 */
	public boolean equals(Object t) {
		TCtoken token = (TCtoken)t;
		return ((token.tokenType == tokenType) && 
				((token.lexeme == null && lexeme == null) ||
				(token.lexeme != null  && token.lexeme.equals(lexeme))));
	}
	
	/********************************************************************************************
	 * toString()
	 * 		Returns a string representation of the TCtoken object, indicating the Token type and
	 * 		the lexeme. For diagnostic purposes when validating the functionality of the lexer.
	 *@return
	 *		The string representing the type and value of the TCtoken object.
	 *********************************************************************************************/
	public String toString() {
		String str = "";
		switch (tokenType) {
		case INT:
			str = str + "(<INT>, \"" + lexeme + "\")";
			break;
		case CHAR:
			str = str + "(<CHAR>, \"" + lexeme + "\")";
			break;
		case RETURN:
			str = str + "(<RETURN>, \"" + lexeme + "\")";
			break;
		case IF:
			str = str + "(<IF>, \"" + lexeme + "\")";
			break;
		case ELSE:
			str = str + "(<ELSE>, \"" + lexeme + "\")";
			break;
		case FOR:
			str = str + "(<FOR>, \"" + lexeme + "\")";
			break;
		case DO:
			str = str + "(<DO>, \"" + lexeme + "\")";
			break;
		case WHILE:
			str = str + "(<WHILE>, \"" + lexeme + "\")";
			break;
		case SWITCH:
			str = str + "(<SWITCH>, \"" + lexeme + "\")";
			break;
		case CASE:
			str = str + "(<CASE>, \"" + lexeme + "\")";
			break;
		case DEFAULT:
			str = str + "(<DEFAULT>, \"" + lexeme + "\")";
			break;
		case WRITE:
			str = str + "(<WRITE>, \"" + lexeme + "\")";
			break;
		case READ:
			str = str + "(<READ>, \"" + lexeme + "\")";
			break;
		case CONTINUE:
			str = str + "(<CONTINUE>, \"" + lexeme + "\")";
			break;
		case BREAK:
			str = str + "(<BREAK>, \"" + lexeme + "\")";
			break;
		case NEWLINE:
			str = str + "(<NEWLINE>, \"" + lexeme + "\")";
			break;
		case ID:
			str = str + "(<ID>, \"" + lexeme + "\")";
			break;
		case NUMBER:
			str = str + "(<NUMBER>, \"" + lexeme + "\")";
			break;
		case CHARLITERAL:
			str = str + "(<CHARLITERAL>," + lexeme + "\")";
			break;
		case STRING:
			str = str + "(<STRING>, \"" + lexeme + "\")";
			break;
		case RELOP:
			str = str + "(<RELOP>, \"" + lexeme + "\")";
			break;
		case ADDOP:
			str = str + "(<ADDOP>, \"" + lexeme + "\")";
			break;
		case MULOP:
			str = str + "(<MULOP>, \"" + lexeme + "\")";
			break;
		case ASSIGNOP:
			str = str + "(<ASSIGNOP, \"" + lexeme + "\")";
			break;
		case NOT:
			str = str + "(<NOT>, \"" + lexeme + "\")";
			break;
		case LPAREN:
			str = str + "(<LPAREN>, \"" + lexeme + "\")";
			break;
		case RPAREN:
			str = str + "(<RPAREN>, \"" + lexeme + "\")";
			break;
		case LCURLY:
			str = str + "(<LCURLY>, \"" + lexeme + "\")";
			break;
		case RCURLY:
			str = str + "(<RCURLY>, \"" + lexeme + "\")";
			break;
		case LBRACKET:
			str = str + "(<LBRACKET>, \"" + lexeme + "\")";
			break;
		case RBRACKET:
			str = str + "(<RBRACKET>, \"" + lexeme + "\")";
			break;
		case COMMA:
			str = str + "(<COMMA>, \"" + lexeme + "\")";
			break;
		case SEMICOLON:
			str = str + "(<SEMICOLON>, \"" + lexeme + "\")";
			break;
		case COLON:
			str = str + "(<COLON>, \"" + lexeme + "\")";
			break;
		case EOF:
			str = str + "(<EOF>, \"EOF\")";
			break;
		default:
			throw new RuntimeException("TCtoken: internal error. No token type defined");
		}
		return str;
	}
}