/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ALOAD implements JVMinstruction, LineOfCode{
  public int index;

  public ALOAD(int index) {
    this.index = index;
  }

  public String toString() {
    return "\taload " + index;
  }
}
