/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ICONST_4 implements JVMinstruction, LineOfCode{
  public ICONST_4() {
  }

  public String toString() {
    return "\ticonst_4";
  }
}
