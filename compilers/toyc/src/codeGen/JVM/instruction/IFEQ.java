/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
import codegen.JVM.label.label;

public class IFEQ implements JVMinstruction, LineOfCode{
  label l;

  public IFEQ(label l) {
    this.l = l;
  }

  public String toString() {
    return "\tifeq " + l.toString();
  }
}
