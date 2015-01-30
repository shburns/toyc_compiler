/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ASTORE_2 implements JVMinstruction, LineOfCode{
  public ASTORE_2() {
  }

  public String toString() {
    return "\tastore_2";
  }
}
