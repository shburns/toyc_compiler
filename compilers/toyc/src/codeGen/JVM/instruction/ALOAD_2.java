/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ALOAD_2 implements JVMinstruction, LineOfCode{
  public ALOAD_2() {
  }

  public String toString() {
    return "\taload_2";
  }
}
