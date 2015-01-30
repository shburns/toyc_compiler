/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

import java.util.List;

public class WriteState implements Statement {
	
	List<Expression> exprs;
	
	public WriteState(List<Expression> s) {
		exprs = s;
	}
	
	public List<Expression> getIds() {
		return exprs;
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < exprs.size(); i ++){
			s = s + "\t" + exprs.get(i).toString() + "\n";
		}
		return("writeStatement(\n" + s + ")");
	}
}