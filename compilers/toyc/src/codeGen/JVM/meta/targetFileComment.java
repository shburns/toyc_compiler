/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.meta;

import compilers.LineOfCode;
import codegen.JVM.JVMmeta;

public class targetFileComment implements JVMmeta, LineOfCode{
    public String target;

    public targetFileComment(String t) { 
        target = t; 
    }

  public String toString() {
      return ("; " + "Target file: " + target);
  }

}
