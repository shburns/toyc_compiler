/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class BIPUSH implements JVMinstruction, LineOfCode{
  int index;

  public BIPUSH(int index) {
    this.index = index;
  }

  public String toString() {
    return "\tbipush " + index;
  }
}
