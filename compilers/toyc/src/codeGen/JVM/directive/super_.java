/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.directive;

import compilers.LineOfCode;
import codegen.JVM.JVMdirective;

import java.util.Vector;

public class super_ implements JVMdirective, LineOfCode{
    private static final String directive = ".super";
    public String parent;

    public super_(String parent){
        this.parent = parent;
    }

    public String toString(){
        String s = directive + " " + parent;
        return s;
    }
}
