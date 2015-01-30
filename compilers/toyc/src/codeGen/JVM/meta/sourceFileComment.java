/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.meta;

import compilers.LineOfCode;
import codegen.JVM.JVMmeta;

public class sourceFileComment implements JVMmeta, LineOfCode{
    public String source;

    public sourceFileComment(String s) { 
        source = s;
    }

  public String toString() {
      return ("; " + "Source file: " + source);
  }

}
