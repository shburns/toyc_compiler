/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ISTORE_3 implements JVMinstruction, LineOfCode{
  public ISTORE_3() {
  }

  public String toString() {
    return "\tistore_3";
  }
}
