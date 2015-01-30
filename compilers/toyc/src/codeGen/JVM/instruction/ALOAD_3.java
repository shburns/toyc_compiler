/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ALOAD_3 implements JVMinstruction, LineOfCode{
  public ALOAD_3() {
  }

  public String toString() {
    return "\taload_3";
  }
}
