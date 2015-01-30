/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM;

import java.util.Map;
import compilers.TargetCode;
import compilers.Symbol;
import symTable.TCsymbol;
import parser.TCtoken;

import codegen.JVM.instruction.ISTORE_1;
import codegen.JVM.instruction.ISTORE_2;
import codegen.JVM.instruction.ISTORE_3;
import codegen.JVM.instruction.ISTORE;

import codegen.JVM.instruction.ILOAD_1;
import codegen.JVM.instruction.ILOAD_2;
import codegen.JVM.instruction.ILOAD_3;
import codegen.JVM.instruction.ILOAD;

import codegen.JVM.instruction.ASTORE_1;
import codegen.JVM.instruction.ASTORE_2;
import codegen.JVM.instruction.ASTORE_3;
import codegen.JVM.instruction.ASTORE;

import codegen.JVM.instruction.ALOAD_1;
import codegen.JVM.instruction.ALOAD_2;
import codegen.JVM.instruction.ALOAD_3;
import codegen.JVM.instruction.ALOAD;

import codegen.JVM.instruction.ICONST_M1;
import codegen.JVM.instruction.ICONST_0;
import codegen.JVM.instruction.ICONST_1;
import codegen.JVM.instruction.ICONST_2;
import codegen.JVM.instruction.ICONST_3;
import codegen.JVM.instruction.ICONST_4;
import codegen.JVM.instruction.ICONST_5;
import codegen.JVM.instruction.BIPUSH;
import codegen.JVM.instruction.SIPUSH;
import codegen.JVM.instruction.LDC;

import codegen.JVM.instruction.IADD;
import codegen.JVM.instruction.ISUB;
import codegen.JVM.instruction.IMUL;
import codegen.JVM.instruction.IDIV;

import codegen.JVM.instruction.IF_ICMPEQ;
import codegen.JVM.instruction.IF_ICMPNE;
import codegen.JVM.instruction.IF_ICMPLT;
import codegen.JVM.instruction.IF_ICMPLE;
import codegen.JVM.instruction.IF_ICMPGT;
import codegen.JVM.instruction.IF_ICMPGE;

import codegen.JVM.instruction.IFEQ;
import codegen.JVM.instruction.IFNE;
import codegen.JVM.instruction.GOTO;
import codegen.JVM.instruction.SWAP;
import codegen.JVM.instruction.POP;

import codegen.JVM.label.label;
import codegen.JVM.label.codeLabel;

public class JVMgenUtils {
	
	private static int uniqueInt = 0;
	
	public static void gen_ISTORE(Symbol id,TargetCode tc){
		int offset = ((Integer)((TCsymbol)id).getOffset());
		switch (offset) {
			case 1:
				tc.add(new ISTORE_1());
				break;
			case 2:
				tc.add(new ISTORE_2());
				break;
			case 3:
				tc.add(new ISTORE_3());
				break;
			default:
				tc.add(new ISTORE(offset));
				break;
		}
	}
	
	public static void gen_ILOAD(Symbol id,TargetCode tc){
		int offset = ((Integer)((TCsymbol)id).getOffset());
		switch(offset){
			case 1:
				tc.add(new ILOAD_1());
				break;
			case 2:
				tc.add(new ILOAD_2());
				break;
			case 3:
				tc.add(new ILOAD_3());
				break;
			default:
				tc.add(new ILOAD(offset));
				break;
		}
	}
	
	public static void gen_ASTORE(Symbol id,TargetCode tc){
		int offset =
				((Integer)((TCsymbol)id).getOffset());
		switch (offset) {
			case 1:
				tc.add(new ASTORE_1());
				break;
			case 2:
				tc.add(new ASTORE_2());
				break;
			case 3:
				tc.add(new ASTORE_3());
				break;
			default:
				tc.add(new ASTORE(offset));
				break;
		}
	}
	public static void gen_ALOAD(Symbol id,TargetCode tc){
		int offset =
				((Integer)((TCsymbol)id).getOffset());
		switch(offset){
			case 1:
				tc.add(new ALOAD_1());
				break;
			case 2:
				tc.add(new ALOAD_2());
				break;
			case 3:
				tc.add(new ALOAD_3());
				break;
			default:
				tc.add(new ALOAD(offset));
				break;
		}
	}
	public static void gen_ICONST(int n,TargetCode tc){
		switch(n){
			case -1:
				tc.add(new ICONST_M1());
				break;
			case 0:
				tc.add(new ICONST_0());
				break;
			case 1:
				tc.add(new ICONST_1());
				break;
			case 2:
				tc.add(new ICONST_2());
				break;
			case 3:
				tc.add(new ICONST_3());
				break;
			case 4:
				tc.add(new ICONST_4());
				break;
			case 5:
				tc.add(new ICONST_5());
				break;
			default:
				if (n >= -128 && n <= 127) {
					tc.add(new BIPUSH(n));
				}
				else { 
					if (n >= -32768 && n <= 32767) {
						tc.add(new SIPUSH(n));
					}
					else { 
						tc.add(new LDC(n));
					}
				}
		}
	}
	
	public static void gen_ADDOP(TCtoken tok, JVMtargetCode tc) {
		if (tok.getLexeme().equals("+")) {
			tc.add(new IADD());
		}
		else { 
			if (tok.getLexeme().equals("-")) {
				tc.add(new ISUB());
			}
			else { // shouldn’t happen
				throw new RuntimeException("gen_ADDOP: internal error");
			}
		}
	}
	
	public static void gen_MULOP(TCtoken tok, JVMtargetCode tc) {
		if (tok.getLexeme().equals("*"))
			tc.add(new IMUL());
		else 
			if (tok.getLexeme().equals("/"))
				tc.add(new IDIV());
			else // shouldn’t happen
				throw new RuntimeException("gen_MULOP: internal error");
	}
	
	public static void gen_RELOP(TCtoken tok, JVMtargetCode tc) {
		label l0 = new label(), l1 = new label();
		
		if (tok.getLexeme().equals("==")) {
			tc.add(new IF_ICMPNE(l0));
		}
		else {
			if (tok.getLexeme().equals("<>")) {
				tc.add(new IF_ICMPEQ(l0));
			}
			else {
				if (tok.getLexeme().equals("<")) {
					tc.add(new IF_ICMPGE(l0));
				}
				else {
					if (tok.getLexeme().equals("<=")) {
						tc.add(new IF_ICMPGT(l0));
					}
					else {
						if (tok.getLexeme().equals(">")) {
							tc.add(new IF_ICMPLE(l0));
						}
						else {
							if (tok.getLexeme().equals(">=")) {
								tc.add(new IF_ICMPLT(l0));
							}
							else { // shouldn’t happen
								throw new RuntimeException("genRELOP: internal error");
							}
						}
					}
				}
			}
		}
				
		tc.add(new ICONST_1());
		tc.add(new GOTO(l1));
		tc.add(new codeLabel(l0+""));
		tc.add(new ICONST_0());
		tc.add(new codeLabel(l1+""));
	}
	
	public static void gen_OR(TCtoken tok, JVMtargetCode tc) {
		label l0 = new label(), l1 = new label(), l2 = new label();
		tc.add(new SWAP());
		tc.add(new IFEQ(l0));
		tc.add(new POP());
		tc.add(new ICONST_1());
		tc.add(new GOTO(l2));
		tc.add(new codeLabel(l0+""));
		tc.add(new IFEQ(l1));
		tc.add(new ICONST_1());
		tc.add(new GOTO(l2));
		tc.add(new codeLabel(l1+""));
		tc.add(new ICONST_0());
		tc.add(new codeLabel(l2+""));
	}
	
	public static void gen_AND(TCtoken tok, JVMtargetCode tc) {
		label l0 = new label(), l1 = new label(), l2 = new label();
		tc.add(new SWAP());
		tc.add(new IFNE(l0));
		tc.add(new POP());
		tc.add(new ICONST_0());
		tc.add(new GOTO(l2));
		tc.add(new codeLabel(l0+""));
		tc.add(new IFNE(l1));
		tc.add(new ICONST_0());
		tc.add(new GOTO(l2));
		tc.add(new codeLabel(l1+""));
		tc.add(new ICONST_1());
		tc.add(new codeLabel(l2+""));
	}
	
	public static int getUniqueInt() {
		return uniqueInt++;
	}
}