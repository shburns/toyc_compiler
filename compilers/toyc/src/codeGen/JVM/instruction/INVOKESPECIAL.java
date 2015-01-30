/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class INVOKESPECIAL implements JVMinstruction, LineOfCode{
  public String constructor;

  public INVOKESPECIAL(String constructor) {
    this.constructor = constructor;
  }

  public String toString() {
    String s = "\tinvokespecial " + constructor;

    return s;
  }
}
