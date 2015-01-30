/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class INEG implements JVMinstruction, LineOfCode{
  public INEG() {
  }

  public String toString() {
    return "\tineg";
  }
}
