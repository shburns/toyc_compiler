/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class NOP implements JVMinstruction, LineOfCode{
  public NOP() {
  }

  public String toString() {
    return "\tnop";
  }
}
