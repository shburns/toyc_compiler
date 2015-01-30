/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

public class WhileState implements Statement {
	
	Expression expr;
	Statement state;
	
	public WhileState(Expression e, Statement s) {
		expr = e;
		state = s;
	}
	
	public Expression getExpr() {
		return expr;
	}
	
	public Statement getState() {
		return state;
	}
	
	public String toString() {
		return("whileStatement(\n\t" + expr.toString() + "\n\t" + state.toString() + "\n)");
	}
}