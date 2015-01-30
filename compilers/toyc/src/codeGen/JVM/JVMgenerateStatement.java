/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM;

import java.util.Iterator;
import java.util.List;

import compilers.SymbolTable;
import globals.TCglobals;
import output.TCoutput;
import abstractSyntax.Statement;
import abstractSyntax.Expression;
import abstractSyntax.ExprState;
import abstractSyntax.BreakState;
import abstractSyntax.BlockState;
import abstractSyntax.NewLineState;
import abstractSyntax.NullState;
import abstractSyntax.WriteState;
import abstractSyntax.ReadState;
import abstractSyntax.ReturnState;
import abstractSyntax.IfState;
import abstractSyntax.WhileState;
import symTable.TCsymbol;
import codegen.JVM.instruction.GETSTATIC;
import codegen.JVM.instruction.INVOKEVIRTUAL;
import codegen.JVM.instruction.LDC;
import codegen.JVM.instruction.IFNE;
import codegen.JVM.instruction.GOTO;
import codegen.JVM.label.label;
import codegen.JVM.label.codeLabel;

public class JVMgenerateStatement {
	
	private static SymbolTable st = TCglobals.symTable;
	
	public static void genStatement(Statement ast, JVMtargetCode tc) {
		if (ast instanceof ExprState) {
			JVMgenerateExpression.genExpression(((ExprState)ast).getExpression(), tc);
		}
		else {
			if (ast instanceof WhileState) {
				label conditionalLabel = new label();
				tc.add(new IFNE(conditionalLabel));
				JVMgenerateStatement.genStatement(((WhileState)ast).getState(), tc);
				tc.add(new GOTO(conditionalLabel));
				JVMgenerateExpression.genExpression(((WhileState)ast).getExpr(), tc);
			}
			else {
				if (ast instanceof WriteState) {
					JVMgenUtils.gen_ALOAD(st.find("System.out"),tc);
					for (int i = 0; i < ((WriteState)ast).getIds().size(); i ++) {
						JVMgenerateExpression.genExpression(((WriteState) ast).getIds().get(i),tc);
					}
					tc.add(new INVOKEVIRTUAL(JVMgenGlobals.PRINT_INT_NEWLINE_METHOD_SPEC));
				} 
				else {
					if (ast instanceof ReadState) {
						// prompt first
						JVMgenUtils.gen_ALOAD(st.find("System.out"),tc);
						tc.add(new LDC("input: "));
						tc.add(new INVOKEVIRTUAL(JVMgenGlobals.PRINT_STRING_METHOD_SPEC));
						// now input value
						JVMgenUtils.gen_ALOAD(st.find("System.in"),tc);
						tc.add(new INVOKEVIRTUAL(JVMgenGlobals.READ_INT_METHOD_SPEC));
						for (int i = 0; i < ((ReadState)ast).getIds().size(); i ++) {
							JVMgenUtils.gen_ISTORE(st.find(((ReadState)ast).getIds().get(i).getValue()),tc);
						}
					}
					else { 
						if (ast instanceof IfState) {
							IfState ifStateAST = (IfState) ast;
							JVMgenerateExpression.genExpression(ifStateAST.getExpression(),tc);
							JVMgenerateStatement.genStatement(ifStateAST.getIfStatement(), tc);
						}
					}
				}
			}
		}
		TCoutput.printCode(tc);
	}
}