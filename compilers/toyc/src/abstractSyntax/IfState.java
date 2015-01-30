/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

public class IfState implements Statement {
	
	Expression expr;
	Statement ifState;
	Statement elseState = null;
	
	public IfState (Expression e, Statement s) {
		expr = e;
		ifState = s;
	}
	
	public IfState (Expression e, Statement ifS, Statement elseS) {
		expr = e;
		ifState = ifS;
		elseState = elseS;
	}
	
	public boolean hasElse() {
		return (elseState != null);
	}
	
	public Expression getExpression() {
		return expr;
	}
	
	public Statement getIfStatement() {
		return ifState;
	}
	
	public Statement getElseStatement() {
		return elseState;
	}
	
	public String toString(){
		String s = "ifStatement(\n\t" + expr.toString() + "\n\t" + ifState.toString() + "\n\t";
		if (elseState != null) {
			s = s + elseState.toString() + "\n";
		}
		s = s + ")";
		return s;
	}
}

