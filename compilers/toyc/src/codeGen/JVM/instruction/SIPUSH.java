/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class SIPUSH implements JVMinstruction, LineOfCode{
  int index;

  public SIPUSH(int index) {
    this.index = index;
  }

  public String toString() {
    return "\tsipush " + index;
  }
}
