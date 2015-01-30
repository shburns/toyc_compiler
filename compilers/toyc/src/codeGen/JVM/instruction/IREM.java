/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class IREM implements JVMinstruction, LineOfCode{
  public IREM() {
  }

  public String toString() {
    return "\tirem";
  }
}
