/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ASTORE implements JVMinstruction, LineOfCode{
  public int index;

  public ASTORE(int index) {
    this.index = index;
  }

  public String toString() {
    return "\tastore " + index;
  }
}
