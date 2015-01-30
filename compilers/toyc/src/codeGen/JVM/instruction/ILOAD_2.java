/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class ILOAD_2 implements JVMinstruction, LineOfCode{
  public ILOAD_2() {
  }

  public String toString() {
    return "\tiload_2";
  }
}
