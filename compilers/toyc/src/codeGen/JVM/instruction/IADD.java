/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class IADD implements JVMinstruction, LineOfCode{
  public IADD() {
  }

  public String toString() {
    return "\tiadd";
  }
}
