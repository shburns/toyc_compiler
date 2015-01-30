/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ISTORE_1 implements JVMinstruction, LineOfCode{
  public ISTORE_1() {
  }

  public String toString() {
    return "\tistore_1";
  }
}
