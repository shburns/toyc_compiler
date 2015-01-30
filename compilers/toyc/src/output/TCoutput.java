/*
 * Sean Burns
 * 
 * TCoutput
 */
package output;

import compilers.TargetCode;
import compilers.LineOfCode;
import globals.TCglobals;
import java.util.List;
import codegen.JVM.JVMtargetCode;

/********************************************************************************************
 * class TCoutput
 * 		Interface class to route any diagnostic/informative output when compiling source code
 * 		to the desired output channel.
 * @author shburns
 */
public class TCoutput {
	
	/********************************************************************************************
	 * reportDEBUG
	 * 		Reports the debug statement when the debug flag has been set to true.
	 * @param level
	 * 		The level of severity of the debug statement
	 * @param sender
	 * 		The object or method that triggered the debug statement.
	 * @param message
	 * 		The debug statement itself.
	 */
	public static void reportDEBUG (String level, Object sender, String message) {
		System.out.println(sender + ": " + message);
	}
	
	/********************************************************************************************
	 * reportWARNING
	 * 		Reports warning statements when that particular output flag has been set to true.
	 * @param sender
	 * 		The object or method where the method was triggered.
	 * @param level
	 * 		The level of the warning.
	 * @param message
	 * 		The actual warning message that was generated.
	 */
	public static void reportWARNING(Object sender, String level, String message) {
		System.out.println("WARNING:" + sender + ": " + message);
	}
	
	/********************************************************************************************
	 * reportSYNTAX_ERROR
	 * 		Reports when the source code has a syntax error, stopping compilation.
	 * @param sender
	 * 		The part of the compiler where the syntax error was generated (will be somewhere in
	 * 		the parser).
	 * @param message
	 * 		The error message generated.
	 */
	public static void reportSYNTAX_ERROR(Object sender, String message) {
		System.out.println("SYNTAX ERROR:" + sender + ": " + message);
	}
	
	/********************************************************************************************
	 * reportSEMANTIC_ERROR
	 * 		Error message output when there is a semantic error in the text of the source code.
	 * @param sender
	 * 		The method from which the error was generated (will always be from the lexer).
	 * @param message
	 * 		The specific error message that was generated.
	 */
	public static void reportSEMANTIC_ERROR(Object sender, String message) {
		System.out.println("SEMANTIC ERROR:" + sender + ": " + message);
	}
	
	/********************************************************************************************
	 * printCode
	 * 		When target code is successfully generated, this function will output the code to
	 *		the desired output channel.
	 * @param tc
	 * 		The TargetCode object which is being output.
	 */
	public static void printCode(TargetCode tc) {
		if (TCglobals.verbose) {
			List<LineOfCode> code = ((JVMtargetCode)tc).getCode();
			System.out.println(code.get(code.size() - 1));
		}
	}
}