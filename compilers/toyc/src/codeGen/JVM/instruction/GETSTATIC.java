/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class GETSTATIC implements JVMinstruction, LineOfCode{
  public String name;
  public String type;

  public GETSTATIC(String name, String type) {
    this.name = name;
    this.type = type;
  }

  public String toString() {
    return "\tgetstatic " + name +" "+ type;
  }
}
