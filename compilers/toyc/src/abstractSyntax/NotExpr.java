/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

import java.util.List;

public class NotExpr implements Expression {
	
	Expression expr;
	
	public NotExpr (Expression e) {
		expr = e;
	}
	
	public Expression getExpression() {
		return expr;
	}
	
	public String toString() {
		return ("notExpression(\n\t" + expr.toString() + "\n)");
	}
}