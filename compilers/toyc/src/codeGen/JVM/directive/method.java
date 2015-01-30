/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.directive;

import compilers.LineOfCode;
import codegen.JVM.JVMdirective;

import java.util.Vector;

public class method implements JVMdirective, LineOfCode {
    private static final String directive = ".method";
    public Vector<String> accessSpecs;
    public Vector<String> args;
    public String name;
    public String type;

  public method(String name, String type){
    accessSpecs = null;
    args = null;
    this.name = name;
    this.type = type;
  }

  public void addAccessSpec(String s){
    if (accessSpecs == null)
      accessSpecs = new Vector<String>();
    accessSpecs.addElement(s);
  }

  public void addArg(String s){
    if (args == null)
      args = new Vector<String>();
    args.addElement(s);
  }

  public String toString(){
    String s = directive + " ";
    if (accessSpecs != null)
      for (int i=0; i<accessSpecs.size(); i++)
        s += ((String)accessSpecs.elementAt(i)) + " ";
    s += name + "(";
    if (args != null)
      for (int i=0; i<args.size(); i++)
        s += ((String)args.elementAt(i));
    s += ")" + type;
    return s;
  }
}
