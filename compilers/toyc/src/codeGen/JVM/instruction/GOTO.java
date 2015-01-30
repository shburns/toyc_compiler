/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
import codegen.JVM.label.label;

public class GOTO implements JVMinstruction, LineOfCode{
  private label l;

  public GOTO(label l) { this.l = l; }

  public label getLabel() { return l; }

  public String toString() {
      return "\tgoto " + l.toString();
  }

}
