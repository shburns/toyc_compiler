/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM;

import compilers.SymbolTable;
import globals.TCglobals;
import output.TCoutput;
import parser.TCtoken;
import symTable.TCsymbol;
import abstractSyntax.Expression;
import abstractSyntax.BinaryExpr;
import abstractSyntax.SimpleExpr;
import codegen.JVM.instruction.INEG;

public class JVMgenerateExpression {
	
	private static SymbolTable st = TCglobals.symTable;
	
	public static void genExpression(Expression ast, JVMtargetCode tc) {
		if (ast instanceof BinaryExpr) {
			genExpression(((BinaryExpr)ast).getExpr1(),tc);
			genExpression(((BinaryExpr)ast).getExpr2(),tc);
			TCtoken op = (TCtoken)((BinaryExpr)ast).getOp().getExpr();
			switch (op.getTokenType()) {
				case ADDOP:
					JVMgenUtils.gen_ADDOP(op,tc);
					break;
				case MULOP:
					JVMgenUtils.gen_MULOP(op,tc);
					break;
				case RELOP:
					JVMgenUtils.gen_RELOP(op,tc);
					break;
				default: // shouldn’t happen
					throw new RuntimeException("genExpression: internal error");
			}
		}
		if (ast instanceof SimpleExpr) {
			TCtoken op = (TCtoken)((SimpleExpr)ast).getExpr();
			String lexeme = (((SimpleExpr)ast).getExpr()).getLexeme();
			switch (op.getTokenType()) {
				case ID:
					TCsymbol idsym = (TCsymbol)st.find(lexeme);
					JVMgenUtils.gen_ILOAD(idsym,tc);
					break;
				case NUMBER:
					JVMgenUtils.gen_ICONST(Integer.parseInt(lexeme),tc);
					break;
				default: // shouldn’t happen
					throw new RuntimeException("genExpression: internal error");
			}
		}
		TCoutput.printCode(tc);
	}
}