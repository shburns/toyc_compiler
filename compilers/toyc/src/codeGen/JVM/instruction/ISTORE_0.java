/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ISTORE_0 implements JVMinstruction, LineOfCode{
  public ISTORE_0() {
  }

  public String toString() {
    return "\tistore_0";
  }
}
