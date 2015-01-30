/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.instruction;

import compilers.LineOfCode;
import codegen.JVM.JVMinstruction;
import java.util.Vector;

public class INVOKESTATIC implements JVMinstruction, LineOfCode{

  public String name;
  public String type;
  public Vector<String> args;

  public INVOKESTATIC(String name, String type) {
    this.name = name;
    this.type = type;
    args = null;
  }

  public void addArg(String s) {
    if (args == null) args = new Vector<String>();
    args.addElement(s);
  }

  public String toString() {
    String s = "\tinvokestatic " + name + "(";
    if (args != null)      for (int i=0; i<args.size(); i++)
        s += ((String)args.elementAt(i));
    s += ")" + type;

    return s;
  }
}
