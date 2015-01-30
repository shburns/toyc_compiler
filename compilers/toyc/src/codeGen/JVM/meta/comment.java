/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.meta;

import compilers.LineOfCode;
import codegen.JVM.JVMmeta;

public class comment implements JVMmeta, LineOfCode {
  public String text;

  public comment(String text) { this.text = text;}

  public String toString() {
    return "; " + (text == null ? "" : text);
  }

}
