/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ACONST_NULL implements JVMinstruction, LineOfCode{
  public ACONST_NULL() {
  }

  public String toString() {
    return "\taconst_null";
  }
}
