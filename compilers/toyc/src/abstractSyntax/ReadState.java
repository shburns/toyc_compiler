/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

import java.util.List;

public class ReadState implements Statement {
	
	List<SimpleExpr> ids;
	
	public ReadState(List<SimpleExpr> s) {
		ids = s;
	}
	
	public List<SimpleExpr> getIds() {
		return ids;
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < ids.size(); i ++){
			s = s + "\t" + ids.get(i).toString() + "\n";
		}
		return("readStatement(\n\t" + s + ")");
	}
}