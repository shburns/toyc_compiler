/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.meta;

import compilers.LineOfCode;
import codegen.JVM.JVMmeta;

public class blankComment implements JVMmeta, LineOfCode {
  public String toString(){ return ";"; }
}
