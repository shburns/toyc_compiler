/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ISTORE implements JVMinstruction, LineOfCode{
  public int index;

  public ISTORE(int index) {
    this.index = index;
  }

  public String toString() {
    return "\tistore " + index;
  }
}
