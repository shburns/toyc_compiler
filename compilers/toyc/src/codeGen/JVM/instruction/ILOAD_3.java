/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ILOAD_3 implements JVMinstruction, LineOfCode{
  public ILOAD_3() {
  }

  public String toString() {
    return "\tiload_3";
  }
}
