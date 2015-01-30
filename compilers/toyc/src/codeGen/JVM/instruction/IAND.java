/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class IAND implements JVMinstruction, LineOfCode{
  public IAND() {
  }

  public String toString() {
    return "\tiand";
  }
}
