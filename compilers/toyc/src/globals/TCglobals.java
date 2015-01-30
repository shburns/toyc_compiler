/*
 * Sean Burns
 * 
 * TCglobals
 */
package globals;

import compilers.AbstractSyntax;
import compilers.SymbolTable;
import compilers.TargetCode;

/********************************************************************************************
 * This class defines static globally accessible variables for use throughout the program.
 * @author shburns
 *********************************************************************************************/
public class TCglobals {
	
	public static String inputFileName = "test";
	public static String outputClassFileName = "test";
	public static String targetFileName = null;
	public static final String ASM_FILE_EXTENSION = "j";
	
	public static AbstractSyntax ast = null;
	public static SymbolTable symTable = null;
	public static TargetCode objectcode = null;
	
	public static final String COMPILER = "toyc";
	public static final String VERSION = "v1.0";
	public static final String AUTHOR = "S. Burns";
	
	public static boolean debug_scanner = false;
	public static boolean debug_parser = false;
	public static boolean debug_codeGen = false;
	
	public static boolean dump_abstractCode = false;
	public static boolean dump_objectCode = false;
	public static boolean dump_symbolTable = false;
	
	public static boolean verbose = false;
	public static boolean astOut = false;
}