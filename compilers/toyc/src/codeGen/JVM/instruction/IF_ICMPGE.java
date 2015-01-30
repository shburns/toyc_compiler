/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
import codegen.JVM.label.label;

public class IF_ICMPGE implements JVMinstruction, LineOfCode{
  label l;

  public IF_ICMPGE(label l) {
    this.l = l;
  }

  public String toString() {
    return "\tif_icmpge " + l.toString();
  }
}
