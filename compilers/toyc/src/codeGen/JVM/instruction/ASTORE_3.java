/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ASTORE_3 implements JVMinstruction, LineOfCode{
  public ASTORE_3() {
  }

  public String toString() {
    return "\tastore_3";
  }
}
