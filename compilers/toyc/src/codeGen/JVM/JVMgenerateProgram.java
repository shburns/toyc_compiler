/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM;

import java.util.List;
import java.util.Iterator;
import compilers.TargetCode;
import compilers.SymbolTable;
import globals.TCglobals;
import output.TCoutput;
import abstractSyntax.Program;
import abstractSyntax.Definition;
import abstractSyntax.Statement;
import abstractSyntax.VarDefinition;
import abstractSyntax.FuncDefinition;
import abstractSyntax.WriteState;
import abstractSyntax.ReadState;
import symTable.TCsymbol;
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

public class JVMgenerateProgram {
	private static SymbolTable st = TCglobals.symTable;
	
	public static void genProgram(Program ast, JVMtargetCode tc) {
		genIntro(tc);
		genConstructor(tc);
		genMainMethod(ast.getDefinitionList(),tc);
	}
	private static void genIntro(TargetCode tc) {
		gen_intro_comments(TCglobals.inputFileName,TCglobals.targetFileName,tc);
		tc.add(new blankLine());
		gen_source_directive(TCglobals.inputFileName, tc);
		gen_class_directive(TCglobals.outputClassFileName, tc);
		gen_super_directive(JVMgenGlobals.ROOT_CLASS, tc);
		tc.add(new blankLine());
	}
	
	private static void genConstructor(TargetCode tc) {
		method m = new method("<init>", "V");
		m.addAccessSpec("public");
		tc.add(m);
		tc.add(new limit("stack",1));
		tc.add(new limit("locals",1));
		tc.add(new ALOAD_0());
		tc.add(new INVOKESPECIAL(JVMgenGlobals.OBJECT_CONSTRUCTOR));
		tc.add(new RETURN());
		tc.add(new end());
		tc.add(new blankLine());
	}
	
	private static void genMainMethod(List<Definition> definitions, TargetCode tc) {
		gen_main_header(tc);
		gen_stack_limit_directive(tc);
		gen_locals_limit_directive(tc);
		Iterator<Definition> itr = definitions.iterator();
		while (itr.hasNext()) {
			JVMgenerateDefinition.genDefinition((Definition)itr.next(),(JVMtargetCode)tc);
			TCoutput.printCode(tc);
		}
		tc.add(new RETURN());
		tc.add(new end());
	}
	
	private static void gen_intro_comments(String s, String t, TargetCode tc) {
		tc.add(new blankComment());
		tc.add(new sourceFileComment(s));
		tc.add(new targetFileComment(t));
		tc.add(new timeAndSourceComment());
		tc.add(new blankComment());
	
	}
	
	private static void gen_source_directive(String file, TargetCode tc) {
		tc.add(new source(file));
	}
	
	private static void gen_class_directive(String file, TargetCode tc) {
		class_ cDirective = new class_(file);
		cDirective.addAccessSpec("public");
		tc.add(cDirective);
	}
	
	private static void gen_super_directive(String parent, TargetCode tc) {
		tc.add(new super_(parent));
	}
	
	private static void gen_main_header(TargetCode tc) {
		method m = new method("main", "V");
		m.addArg("[Ljava/lang/String;");
		m.addAccessSpec("public");
		m.addAccessSpec("static");
		tc.add(m);
	}
	
	private static void gen_throws_directive(TargetCode tc){
		tc.add(new throws_(JVMgenGlobals.IOEXCEPTION));
	}
	
	private static boolean thereIsInput(List<Definition> l) {
		Iterator<Definition> itr = l.iterator();
		while(itr.hasNext())
			if (itr.next() instanceof ReadState)
				return true;
		return false;
	}
	
	private static void gen_stack_limit_directive(TargetCode tc){
		tc.add(new limit("stack",10)); // arbitrary, for now
	}
	
	private static void gen_locals_limit_directive(TargetCode tc){
		tc.add(new limit("locals",10)); // arbitrary, for now
	}
}