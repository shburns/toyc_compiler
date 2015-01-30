/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.directive;

import compilers.LineOfCode;
import codegen.JVM.JVMdirective;

public class throws_ implements JVMdirective, LineOfCode{
    private static final String directive = ".throws";
    public String exception;

  public throws_(String exception){
    this.exception = exception;
  }

  public String toString(){
    String s = "\t" + directive + " " + exception;
    return s;
  }
}
