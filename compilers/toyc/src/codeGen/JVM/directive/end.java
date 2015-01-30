/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM.directive;

import compilers.LineOfCode;
import codegen.JVM.JVMdirective;

public class end implements JVMdirective, LineOfCode {
    private static final String directive = ".end";

    public String toString() { return directive + " method"; }
}
