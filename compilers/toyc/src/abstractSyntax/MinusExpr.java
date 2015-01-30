/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

import java.util.List;

public class MinusExpr implements Expression {
	
	Expression expr;
	
	public MinusExpr (Expression e) {
		expr = e;
	}
	
	public Expression getExpression() {
		return expr;
	}
	
	public String toString() {
		return ("minusExpression(\n\t" + expr.toString() + "\n)");
	}
}