/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;

public class SWAP implements JVMinstruction, LineOfCode{
  public SWAP() {
  }

  public String toString() {
    return "\tswap";
  }
}
