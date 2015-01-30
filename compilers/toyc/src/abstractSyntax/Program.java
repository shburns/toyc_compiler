/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

import compilers.LineOfCode;
import compilers.AbstractSyntax;
import java.util.List;
import java.util.Iterator;

public class Program implements AbstractSyntax {
	List<Definition> definitionList;
	String name;
	PrettyPrint print = new PrettyPrint();
	public Program(String n, List<Definition> l) {
		name = n;
		definitionList = l;
	}
	
	public List<Definition> getDefinitionList() {
		return definitionList;
	}
	
	public String toString() {
		if (definitionList.isEmpty()) return "prog([])";
		String s = "prog(\n";
		print.indent();
		s += print.spaces() + name + ",\n";
		s += print.spaces() + "[\n";
		print.indent();
		Iterator itr = definitionList.iterator();
		if (itr.hasNext())
			s += print.spaces()+itr.next().toString();
		while (itr.hasNext())
			s += ",\n"+print.spaces()+itr.next().toString();
		print.outdent();
		s += "\n"+print.spaces() + "]\n";
		print.outdent();
		s += print.spaces() + ")\n";
		return s;
	}
}