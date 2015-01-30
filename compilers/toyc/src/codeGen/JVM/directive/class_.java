/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.directive;

import compilers.LineOfCode;
import codegen.JVM.JVMdirective;
import java.util.Vector;

public class class_ implements JVMdirective, LineOfCode {
    private static final String directive = ".class";
    public String classname;
    public Vector<String> accessSpecs;

  public class_(String name) {
    classname = name;
    accessSpecs = null;
  }

  public void addAccessSpec(String s) {
    if (accessSpecs == null)
      accessSpecs = new Vector<String>();
    accessSpecs.addElement(s);
  }

  public String toString(){
    String s = directive + " ";
    if (accessSpecs != null)
      for (int i=0; i<accessSpecs.size(); i++)
        s += ((String)accessSpecs.elementAt(i)) + " ";
    s += classname;
    return s;
  }
}
