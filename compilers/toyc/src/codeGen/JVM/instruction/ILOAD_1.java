/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ILOAD_1 implements JVMinstruction, LineOfCode{
  public ILOAD_1() {
  }

  public String toString() {
    return "\tiload_1";
  }
}
