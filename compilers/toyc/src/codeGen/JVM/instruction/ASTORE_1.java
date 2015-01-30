/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ASTORE_1 implements JVMinstruction, LineOfCode{
  public ASTORE_1() {
  }

  public String toString() {
    return "\tastore_1";
  }
}
