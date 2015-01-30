/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

import compilers.Token;

public class BinaryExpr implements Expression {
	
	SimpleExpr op;
	Expression expr1, expr2;
	
	public BinaryExpr(SimpleExpr o, Expression e1, Expression e2) {
		op = o;
		expr1 = e1;
		expr2 = e2;
	}
	
	public SimpleExpr getOp() {
		return op;
	}
	
	public Expression getExpr1() {
		return expr1; 
	}
	
	public Expression getExpr2() {
		return expr2;
	}
	
	public String toString() {
		
		return ("binaryExpression(\n\t" + op.toString() + "\n\t" + expr1.toString() + "\n\t"
				+ expr2.toString() + "\n)");
	}
}