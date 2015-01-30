/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ILOAD implements JVMinstruction, LineOfCode{
  public int index;

  public ILOAD(int index) {
    this.index = index;
  }

  public String toString() {
    return "\tiload " + index;
  }
}
