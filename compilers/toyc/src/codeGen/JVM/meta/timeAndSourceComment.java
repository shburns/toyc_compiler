/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.meta;

import compilers.LineOfCode;
import codegen.JVM.JVMmeta;

public class timeAndSourceComment implements JVMmeta, LineOfCode{
    public String text;

    public timeAndSourceComment() {
        text = (new java.util.Date ()).toString ();
    }

    public String toString() {
        return "; " + (text == null ? "" : text);
    }
}
