/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.directive;

import compilers.LineOfCode;
import codegen.JVM.JVMdirective;

public class line implements JVMdirective, LineOfCode {
  private static final String directive = ".line";
  private int lineNum;

  public line(int n){ lineNum = n; }

  public String toString(){
    String s = "\t" + directive + " " + lineNum;
    return s;
  }

}
