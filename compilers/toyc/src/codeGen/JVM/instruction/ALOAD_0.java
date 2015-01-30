/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ALOAD_0 implements JVMinstruction, LineOfCode{
  public ALOAD_0() {
  }

  public String toString() {
    return "\taload_0";
  }
}
