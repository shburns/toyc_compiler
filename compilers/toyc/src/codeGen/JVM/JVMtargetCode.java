/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM;

import compilers.TargetCode;
import compilers.LineOfCode;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class JVMtargetCode implements TargetCode {
	List<LineOfCode> code;
	public JVMtargetCode() {
		code = new ArrayList<LineOfCode>();
	}
	public void add(LineOfCode l){
		code.add(l);
	}
	public List<LineOfCode> getCode() {
		return code;
	}
	public void writeCode(String file) throws IOException {
		PrintWriter out =
		new PrintWriter(new BufferedWriter(new FileWriter(file)));
		out.print(this.toString());
		out.close();
	}
	public String toString(){
		Iterator itr = code.iterator();
		String s = "";
		while(itr.hasNext())
			s += itr.next()+"\n";
		return s;
	}
}