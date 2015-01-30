/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ICONST_0 implements JVMinstruction, LineOfCode{
  public ICONST_0() {
  }

  public String toString() {
    return "\ticonst_0";
  }
}
