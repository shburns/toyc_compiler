/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ICONST_M1 implements JVMinstruction, LineOfCode{
  public ICONST_M1() {
  }

  public String toString() {
    return "\ticonst_m1";
  }
}
