/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ASTORE_0 implements JVMinstruction, LineOfCode{
  public ASTORE_0() {
  }

  public String toString() {
    return "\tastore_0";
  }
}
