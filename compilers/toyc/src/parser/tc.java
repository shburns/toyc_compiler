/*
 * Sean Burns
 * 
 * ToyC Compiler
 * 
 * Not complete -- Code Generator will not generate code. I have not successfully implemented
 * the ability to nest variable definitions within function definitions. This causes a null pointer
 * exception as soon as the code generator enters the AST and begins to try generating code.
 * It should all compile and run, but will break with a null pointer exception because I couldn't
 * figure out how to implement the SymbolTable in such a way that it could pass the Symbols to the
 * code generator to generate labels for function definitions, that could then be arrived at by GOTO
 * statements in jasmine.
 */

package parser;
import java.io.FileNotFoundException;
import java.io.IOException;
import compilers.Parser;
import compilers.Lexer;
import compilers.CodeGenerator;
import compilers.CompilationErrorException;
import compilers.CodeGenerationException;
import globals.TCglobals;
import output.TCoutput;
import codegen.JVM.JVMcodeGenerator;
import codegen.JVM.JVMtargetCode;

/********************************************************************************************
 * class tc
 * 		ToyC compiler executable java class. Will process command line arguments and return
 * 		the results of an attempt to compile code from the trivially defined ToyC language
 * 		into Jasmine assembly code for the JVM.
 * @author shburns
 *********************************************************************************************/
public class tc {
	
	/********************************************************************************************
	 * main()
	 * 		The main driver method for the program. Processes command line arguments and attempts
	 * 		to compile code from ToyC into the Jasmine assembly language.
	 * @param args
	 * 		Command line arguments which are used to define the path to the source code file, the
	 * 		name of the target code file, and set any flag variables to turn on debuggin functions
	 * 		of the compiler.
	 * @throws FileNotFoundException
	 * 		Returned if the source-code file isn't found.
	 * @throws IOException
	 * 		Returned if the compiler can't read from the source file or write to the target file.
	 *********************************************************************************************/
	public static void main(String [] args) throws FileNotFoundException,IOException {
		System.out.println ("Sean Burns");
		System.out.println ("ToyC Compiler");
		
		try {
			processCommandLine(args);
			Lexer scanner = new TCscanner(TCglobals.inputFileName);
			Parser parser = new TCparser(scanner);
			//Not terribly useful unless the -abstract flag is set in the command line
			//arguments, which prints out the AST.
			TCglobals.ast = parser.parse();
			//Currently, the CodeGenerator doesn't successfully produce
			//usable JASMINE code and so is commented out of the compiler execution
			//CodeGenerator gen = new JVMcodeGenerator();
			//TCglobals.objectcode = gen.generateCode(TCglobals.ast);
			//((JVMtargetCode)TCglobals.objectcode).writeCode(TCglobals.targetFileName);
			if (TCglobals.verbose) {
				System.out.println();
			}
		}
		catch(UsageException e) { }
		catch(CompilationErrorException e) { }
		catch(CodeGenerationException e) { }
	}
	
	/********************************************************************************************
	 * processCommandLine()
	 * 		Processes command line arguments to get the source code file name, target code file
	 *		name, and any command line flags.
	 * @param args
	 * 		The array of command line arguments to be processed.
	 *********************************************************************************************/
	private static void processCommandLine(String [] args) {
		switch(args.length) {
		case 0:
			printUsageMessage();
			break;
		case 1:
			if (args[0].charAt(0) != '-') {
				TCglobals.inputFileName = args[0];
			}
			else
				printUsageMessage();
			break;
		case 2:
			if (args[0].equals("-v") || args[0].equals("-verbose")) {
				TCglobals.verbose = true; TCglobals.inputFileName = args[1];
			} else if (args[0].equals("-debug 0")) {
				TCglobals.debug_scanner = true;
				TCglobals.debug_parser = true;
				TCglobals.debug_codeGen = true;
				TCglobals.inputFileName = args[1];
			} else if (args[0].equals("-debug 1")) {
				TCglobals.debug_scanner = true;
				TCglobals.inputFileName = args[1];
			} else if (args[0].equals("-abstract")) {
				TCglobals.astOut = true;
				TCglobals.inputFileName = args[1];
			} else if (args[0].equals("output") || args[0].equals("-output")) {
				TCglobals.inputFileName = "test1.tc";
				TCglobals.targetFileName = args[1];
			}
			break;
		case 3:
			if (args[0].equals("-v") || args[0].equals("-verbose")) {
				TCglobals.verbose = true; TCglobals.inputFileName = args[1]; TCglobals.targetFileName = args[2];
			} else if (args[0].equals("-debug 0")) {
				TCglobals.debug_scanner = true;
				TCglobals.debug_parser = true;
				TCglobals.debug_codeGen = true;
				TCglobals.inputFileName = args[1];
				TCglobals.targetFileName = args[2];
			} else if (args[0].equals("-debug 1")) {
				TCglobals.debug_scanner = true;
				TCglobals.inputFileName = args[1];
				TCglobals.targetFileName = args[2];
			} else if (args[0].equals("-abstract")) {
				TCglobals.astOut = true;
				TCglobals.inputFileName = args[1];
				TCglobals.targetFileName = args[2];
			} else {
				TCglobals.inputFileName = args[1];
				TCglobals.targetFileName = args[2];
			}
			break;
		default:
			TCglobals.astOut= false;
			TCglobals.inputFileName = "test1.tc";
			TCglobals.targetFileName = "test1.j";
		}
		TCglobals.outputClassFileName = getProgramName(TCglobals.inputFileName);
		TCglobals.targetFileName = TCglobals.outputClassFileName + "." + TCglobals.ASM_FILE_EXTENSION;
	}
	
	private static String getProgramName(String s) {
		String [] strs = s.split("\\.");
		return strs[0];
	}
	
	/********************************************************************************************
	 * printUsageMessage()
	 * 		In the event that someone doesn't provide minimum required command line arguments,
	 * 		this message is printed to screen to indicate how to use the compiler.
	 *********************************************************************************************/
	private static void printUsageMessage () {
		System.out.println("Usage: java [classpath] parser.tc [options] toyc_source_file toyc_target_file");
		System.out.println(" where options include:");
		System.out.println("-help \t\t display this usage message");
		System.out.println("-debug <level>\t display messages that aid in tracing the");
		System.out.println("\t\t\t compilation process. If level is:");
		System.out.println("\t\t\t\t0 - all messages");
		System.out.println("\t\t\t\t1 - scanner messages only");
		System.out.println("-verbose \t\t display all information");
		throw new UsageException();
	}
	
	/********************************************************************************************
	 * UsageException
	 * 		Instance of a RuntimeException which should be thrown when the user hasn't invoked
	 * 		the compiler with the correct minimum command-line arguments.
	 * @author shurns
	 *********************************************************************************************/
	private static class UsageException extends java.lang.RuntimeException {
		public UsageException() { super(); }
	}
}