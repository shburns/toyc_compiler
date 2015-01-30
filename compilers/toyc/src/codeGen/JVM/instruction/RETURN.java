/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class RETURN implements JVMinstruction, LineOfCode{
  public RETURN() {
  }

  public String toString() {
    return "\treturn";
  }
}
