/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package abstractSyntax;

class PrettyPrint {
	static int pos = 0;
	final static int INDENTSIZE=2;
	public static String spaces() {
		String s = "";
		for (int i = 1; i <= pos; i++)
			s += " ";
		return s;
	} // method
	public static void indent() { pos += INDENTSIZE; }
	public static void outdent() { pos -= INDENTSIZE; }
}