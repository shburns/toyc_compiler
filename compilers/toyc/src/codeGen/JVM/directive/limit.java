/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.directive;

import compilers.LineOfCode;
import codegen.JVM.JVMdirective;

public class limit implements JVMdirective, LineOfCode {
    private static final String directive = ".limit";
    public String type;
    public int size;

  public limit(String type, int size){
    this.type = type; this.size = size;
  }

  public String toString(){
    String s = "\t" + directive + " " + type + " " + size;
    return s;
  }

}
