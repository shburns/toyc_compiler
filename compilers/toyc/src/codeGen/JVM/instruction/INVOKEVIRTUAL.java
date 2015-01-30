/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class INVOKEVIRTUAL implements JVMinstruction, LineOfCode{

  public String functionCall;

  public INVOKEVIRTUAL(String functionCall) {
    this.functionCall = functionCall;
  }

  public String toString() {
    String s = "\tinvokevirtual " + functionCall;

    return s;
  }
}
