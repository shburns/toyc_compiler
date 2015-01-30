/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class DUP implements JVMinstruction, LineOfCode{
  public DUP() {
  }

  public String toString() {
    return "\tdup";
  }
}
