/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

import compilers.Token;
import java.util.List;

public class FuncDefinition implements Definition {
	SimpleExpr id;
	SimpleExpr type;
	List<VarDefinition> paramList;
	Statement body;
	
	public FuncDefinition (SimpleExpr s, SimpleExpr t, List<VarDefinition> p, Statement b){
		id = s;
		type = t;
		paramList = p;
		body = b;
	}
	
	public SimpleExpr getId() { 
		return id;
	}
	
	public SimpleExpr getType() { 
		return type; 
	}
	
	public List<VarDefinition> getParamList() {
		return paramList;
	}
	
	public Statement getBody() {
		return body;
	}
	
	public String toString() {
		String params = "";
		if (paramList.size() >= 1) {
			params = paramList.get(0).toString();
			for (int i = 1; i < paramList.size(); i ++) {
				params = params + ", " + paramList.get(i).toString();
			}
		}
		else {
			params = " ";
		}
		return ("functionDefinition(\n\t" + id.toString() + ", " + type.toString()  + ", \n\t\t(" + params + ") {\n"
				+ body.toString() + "\n}");
	}
}