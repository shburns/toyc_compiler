/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.directive;

import compilers.LineOfCode;
import codegen.JVM.JVMdirective;

public class source implements JVMdirective, LineOfCode {
    private static final String directive = ".source";
    public String fileName;

    public source(String fileName){
        this.fileName = fileName;
    }

  public String toString(){
    String s = directive + " " + fileName;
    return s;
  }

}
