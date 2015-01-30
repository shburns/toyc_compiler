/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

import compilers.Token;
import parser.TCtoken;

public class SimpleExpr implements Expression {
	
	Token expr;
	
	public SimpleExpr(Token t) {
		expr = t;
	}
	
	public Enum getType() {
		return expr.getTokenType(); 
	}
	
	public String getValue() {
		return expr.getLexeme();
	}
	
	public Token getExpr() {
		return expr;
	}
	
	public String toString() {
		return ("simpleExpression(\n\t" + expr.getLexeme()+ "\n)");
	}
}