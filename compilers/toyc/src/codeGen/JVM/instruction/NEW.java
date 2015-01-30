/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class NEW implements JVMinstruction, LineOfCode{
  public String className;

  public NEW(String className) {
    this.className = className;
  }

  public String toString() {
    return "\tnew " + className;
  }
}
