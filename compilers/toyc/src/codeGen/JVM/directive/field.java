/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.directive;

import compilers.LineOfCode;
import codegen.JVM.JVMdirective;

import java.util.Vector;

public class field implements JVMdirective, LineOfCode {
    private static final String directive = ".end";
    public String fieldname;
    public String type;
    public Vector<String> accessSpecs;
    public String value;

  public field(String name, String type) {
    fieldname = name;
    type = type;
    accessSpecs = null;
    value = null;
  }

  public void addAccessSpec(String s){
    if (accessSpecs == null)
      accessSpecs = new Vector<String>();
    accessSpecs.addElement(s);
  }

  public void setValue(String value){
    this.value = value;
  }

  public String toString(){
    String s = directive + " ";
    if (accessSpecs != null)
      for (int i=0; i<accessSpecs.size(); i++)
        s += ((String)accessSpecs.elementAt(i)) + " ";
    s += fieldname + " " + type;
    if (value != null)
      s += " = " + value;
    return s;
  }
}
