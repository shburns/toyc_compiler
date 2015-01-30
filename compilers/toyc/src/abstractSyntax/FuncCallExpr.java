/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

import java.util.List;

public class FuncCallExpr implements Expression {
	
	SimpleExpr id;
	List<Expression> exprs;
	
	public FuncCallExpr(SimpleExpr s, List<Expression> e) {
		id = s;
		exprs = e;
	}
	
	public SimpleExpr getId() {
		return id;
	}
	
	public List<Expression> getExprs() {
		return exprs; 
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < exprs.size(); i ++) {
			s = s + exprs.get(i).toString() + "\n\t";
		}
		
		return ("functionCallExpression(\n\t" + id.toString() + "\n\t" + s + "\n)");
	}
}