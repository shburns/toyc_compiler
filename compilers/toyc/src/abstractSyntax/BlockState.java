/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

import compilers.Token;
import java.util.List;

public class BlockState implements Statement {
	
	List<VarDefinition> varDefs;
	List<Statement> statements;
	
	public BlockState(List<VarDefinition> v, List<Statement> s) {
		varDefs = v;
		statements = s;
	}
	
	public List<VarDefinition> getVarDefs() {
		return varDefs;
	}
	
	public List<Statement> getStatements() {
		return statements;
	}
	
	public String toString() {
		String v = "";
		String s = "";
		for (int i = 0; i < varDefs.size(); i ++) {
			v = v + varDefs.get(i).toString() + "\n\t";
		}
		for (int i = 0; i < statements.size(); i ++) {
			s = s + statements.get(i).toString() + "\n\t";
		}
		return ("blockStatement(\n\t" + v + s + ")");
	}
}