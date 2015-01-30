/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ICONST_5 implements JVMinstruction, LineOfCode{
  public ICONST_5() {
  }

  public String toString() {
    return "\ticonst_5";
  }
}
