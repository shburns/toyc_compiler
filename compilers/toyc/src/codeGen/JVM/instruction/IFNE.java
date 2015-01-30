/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
import codegen.JVM.label.label;

public class IFNE implements JVMinstruction, LineOfCode{
  label l;

  public IFNE(label l) {
    this.l = l;
  }

  public String toString() {
    return "\tifne " + l.toString();
  }
}
