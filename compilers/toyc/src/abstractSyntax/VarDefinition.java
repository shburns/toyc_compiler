/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

import compilers.AbstractSyntax;
import compilers.Token;
import java.util.List;

public class VarDefinition implements Definition {
	
	List<SimpleExpr> idList;
	SimpleExpr type;
	
	public VarDefinition (List<SimpleExpr> l, SimpleExpr t) {
		idList = l;
		type = t;
	}
	
	public List<SimpleExpr> getIds() {
		return idList;
	}
	
	public SimpleExpr getType() {
		return type;
	}
	
	public String toString() {
		String ids = idList.get(0).toString();
		for (int i = 1; i < idList.size(); i ++) {
			ids = ids + ", " + idList.get(i).toString();
		}
		return("variableDefinition(\n\t" + ids + ", " + type.toString() + "\n)");
	}
}