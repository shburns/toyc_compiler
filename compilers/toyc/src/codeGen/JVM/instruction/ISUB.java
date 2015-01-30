/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ISUB implements JVMinstruction, LineOfCode{
  public ISUB() {
  }

  public String toString() {
    return "\tisub";
  }
}
