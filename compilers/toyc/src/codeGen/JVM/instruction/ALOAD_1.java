/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ALOAD_1 implements JVMinstruction, LineOfCode{
  public ALOAD_1() {
  }

  public String toString() {
    return "\taload_1";
  }
}
