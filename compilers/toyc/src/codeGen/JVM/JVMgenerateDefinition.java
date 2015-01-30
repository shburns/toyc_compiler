/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM;

import compilers.SymbolTable;
import compilers.TargetCode;
import globals.TCglobals;
import abstractSyntax.Definition;
import abstractSyntax.Statement;
import abstractSyntax.Expression;
import abstractSyntax.SimpleExpr;
import abstractSyntax.BlockState;
import abstractSyntax.WriteState;
import abstractSyntax.ReadState;
import abstractSyntax.VarDefinition;
import abstractSyntax.FuncDefinition;
import symTable.TCsymbol;
import output.TCoutput;
import codegen.JVM.instruction.GETSTATIC;
import codegen.JVM.instruction.INVOKEVIRTUAL;
import codegen.JVM.instruction.LDC;
import codegen.JVM.instruction.IFNE;
import codegen.JVM.instruction.GOTO;
import codegen.JVM.label.label;
import codegen.JVM.label.codeLabel;
import codegen.JVM.JVMgenGlobals;
import codegen.JVM.JVMgenerateDefinition;
import codegen.JVM.meta.blankLine;
import codegen.JVM.meta.blankComment;
import codegen.JVM.meta.sourceFileComment;
import codegen.JVM.meta.targetFileComment;
import codegen.JVM.meta.timeAndSourceComment;
import codegen.JVM.directive.source;
import codegen.JVM.directive.class_;
import codegen.JVM.directive.super_;
import codegen.JVM.directive.throws_;
import codegen.JVM.directive.limit;
import codegen.JVM.directive.method;
import codegen.JVM.directive.end;
import codegen.JVM.instruction.ALOAD_0;
import codegen.JVM.instruction.GETSTATIC;
import codegen.JVM.instruction.INVOKESPECIAL;
import codegen.JVM.instruction.RETURN;
import codegen.JVM.instruction.NEW;
import codegen.JVM.instruction.DUP;

import java.util.Iterator;
import java.util.List;

public class JVMgenerateDefinition {
	
	private static SymbolTable st = TCglobals.symTable;
	
	public static void genDefinition(Definition ast, JVMtargetCode tc) {
		if (ast instanceof VarDefinition) {
			List<SimpleExpr> vars = ((VarDefinition)ast).getIds();
			for (int i = 0; i < vars.size(); i ++) {
				st.add(vars.get(i).getValue());
			}
		}
		else {
			if (ast instanceof FuncDefinition) {
				List<VarDefinition> params =((FuncDefinition)ast).getParamList();
				for (int i = 0; i < params.size(); i ++) {
					genDefinition(params.get(i), tc);
				}
				List<Statement> states = ((BlockState)((FuncDefinition)ast).getBody()).getStatements();
				st.add(((FuncDefinition)ast).getId().getValue());
				if (thereIsInput(states)) {
					gen_input_stream_store(tc);
				}
				if (thereIsOutput(states)) {
					gen_output_stream_store(tc);
				}
				for (int i = 0; i < states.size(); i ++) {
					JVMgenerateStatement.genStatement(states.get(i), tc);
				}
			}
		}
		TCoutput.printCode(tc);
	}
	
	private static boolean thereIsInput(List<Statement> l) {
		for (int i = 0; i < l.size(); i ++) {
			if (l.get(i) instanceof ReadState)
				return true;
		}
		return false;
	}
	
	private static boolean thereIsOutput(List<Statement> l) {
		for (int i = 0; i < l.size(); i ++) {
			if (l.get(i) instanceof WriteState)
				return true;
		}
		return false;
	}
	
	private static void gen_input_stream_store(TargetCode tc) {
		tc.add(new NEW(JVMgenGlobals.SCANNER));
		tc.add(new DUP());
		tc.add(new GETSTATIC(JVMgenGlobals.INPUT_FIELD_SPEC,
				JVMgenGlobals.INPUT_DESCRIPTOR));
		tc.add(new INVOKESPECIAL(JVMgenGlobals.SCANNER_CONSTRUCTOR));
		JVMgenUtils.gen_ASTORE(st.find("System.in"),tc);
	}
	
	private static void gen_output_stream_store(TargetCode tc) {
		tc.add(new GETSTATIC(JVMgenGlobals.OUTPUT_FIELD_SPEC,
				JVMgenGlobals.OUTPUT_DESCRIPTOR));
		JVMgenUtils.gen_ASTORE(st.find("System.out"),tc);
	}
}