/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ICONST_2 implements JVMinstruction, LineOfCode{
  public ICONST_2() {
  }

  public String toString() {
    return "\ticonst_2";
  }
}
