/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class POP implements JVMinstruction, LineOfCode{
  public POP() {
  }

  public String toString() {
    return "\tpop";
  }
}
