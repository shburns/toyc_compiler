/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
import codegen.JVM.label.label;

public class IF_ICMPNE implements JVMinstruction, LineOfCode{
  label l;

  public IF_ICMPNE(label l) {
    this.l = l;
  }

  public String toString() {
    return "\tif_icmpne " + l.toString();
  }
}
