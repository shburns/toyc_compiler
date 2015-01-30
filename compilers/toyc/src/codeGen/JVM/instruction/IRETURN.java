/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class IRETURN implements JVMinstruction, LineOfCode{
  public IRETURN() {
  }

  public String toString() {
    return "\tireturn";
  }
}
