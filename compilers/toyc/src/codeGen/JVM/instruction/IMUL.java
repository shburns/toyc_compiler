/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class IMUL implements JVMinstruction, LineOfCode{
  public IMUL() {
  }

  public String toString() {
    return "\timul";
  }
}
