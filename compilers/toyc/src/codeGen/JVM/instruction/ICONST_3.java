/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ICONST_3 implements JVMinstruction, LineOfCode{
  public ICONST_3() {
  }

  public String toString() {
    return "\ticonst_3";
  }
}
