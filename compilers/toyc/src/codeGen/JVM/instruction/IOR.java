/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class IOR implements JVMinstruction, LineOfCode{
  public IOR() {
  }

  public String toString() {
    return "\tior";
  }
}
