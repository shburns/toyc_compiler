/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ISTORE_2 implements JVMinstruction, LineOfCode{
  public ISTORE_2() {
  }

  public String toString() {
    return "\tistore_2";
  }
}
