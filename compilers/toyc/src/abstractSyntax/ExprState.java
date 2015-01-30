/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

import java.util.List;

public class ExprState implements Statement {
	
	Expression expr;
	
	public ExprState (Expression e) {
		expr = e;
	}
	
	public Expression getExpression() {
		return expr;
	}
	
	public String toString() {
		return ("expressionStatement(\n\t" + expr.toString() + "\n)");
	}
}