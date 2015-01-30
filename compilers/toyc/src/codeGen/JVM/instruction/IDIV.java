/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class IDIV implements JVMinstruction, LineOfCode{
  public IDIV() {
  }

  public String toString() {
    return "\tidiv";
  }
}
