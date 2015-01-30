/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ILOAD_0 implements JVMinstruction, LineOfCode{
  public ILOAD_0() {
  }

  public String toString() {
    return "\tiload_0";
  }
}
