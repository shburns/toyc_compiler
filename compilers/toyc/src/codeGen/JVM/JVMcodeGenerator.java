/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM;

import compilers.CodeGenerator;
import compilers.TargetCode;
import compilers.AbstractSyntax;
import abstractSyntax.Program;

public class JVMcodeGenerator implements CodeGenerator {
	private TargetCode tc;
	
	public JVMcodeGenerator() {
		tc = new JVMtargetCode();
	}
	public TargetCode generateCode(AbstractSyntax ast) {
		JVMgenerateProgram.genProgram((Program)ast,(JVMtargetCode)tc);
		return tc;
	}
}