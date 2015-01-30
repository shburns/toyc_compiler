/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
public class LDC implements JVMinstruction, LineOfCode{
  int iVal;
  String sVal;

  public LDC(int i) {
      this.iVal = i; this.sVal = null;
  }

  public LDC(String s) {
      this.sVal = s;
  }

  public String toString() {
      return ("\tldc " + 
              (sVal==null?iVal+"":'"'+sVal+'"'));
  }
}
