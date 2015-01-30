/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

public class ReturnState implements Statement {
	
	Expression expr = null;
	
	public ReturnState() {
		
	}
	
	public ReturnState(Expression e) {
		expr = e;
	}
	
	public boolean hasExpr() {
		return (expr != null);
	}
	
	public Expression getExpr() {
		return expr;
	}
	
	public String toString() {
		String e = "";
		if (expr != null) {
			e = expr.toString();
		}
		return("returnStatement(\n\t" + e + "\n)");
	}
}