/*
 * Sean Burns
 * 
 * TCscanner
 */
package parser;

import compilers.Lexer;
import compilers.Token;

import globals.TCglobals;
import output.TCoutput;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/********************************************************************************************
 * class TCscanner
 * 		The lexical analyzer for the ToyC compiler. Scans character by character building
 * 		tokens of valid components of the language to be passed to the parser.'
 *@author - shburns
 *********************************************************************************************/
public class TCscanner implements Lexer {
	
	/********************************************************************************************
	 * Private variables used to scan and iterate through the source code, including a line and
	 * position variable for accurate reporting of syntactical errors.
	 *********************************************************************************************/
	private Scanner scan;
	private String line = null;
	private static int pos = 0;
	private static int lineNumber = 0;
	private String lexeme = "";
	
	private static final char EOF = '$';
	private static char charBuff;
	
	/********************************************************************************************
	 * TCscanner constructor
	 * 		Instantiates the lexer which then scans the target source file.
	 *@param fileName
	 *		The source file to be scanned.
	 *********************************************************************************************/
	public TCscanner (String fileName) throws FileNotFoundException {
		TCglobals.inputFileName = fileName;
		try {
			scan = new Scanner (new File(fileName));
			getNextLine();
			charBuff = getChar();
		} catch(FileNotFoundException e) {
				throw new FileNotFoundException("source code file '" + fileName + "' does not exist");
		}
	}
	
	/********************************************************************************************
	 * getNextLine()
	 * 		Returns the next line of source code for analysis and tokenizing.
	 *@return
	 *		The string of characters representing a line of source code or null if EOF.
	 *********************************************************************************************/
	private String getNextLine() {
		if (scan.hasNextLine()) {
			line = scan.nextLine();
			lineNumber ++;
			pos = 0;
			return line;
		} else {
			return null;
		}
	}
	
	/********************************************************************************************
	 * getChar()
	 * 		Gets the next character in the string of source code to build out tokens.
	 *@return
	 *		The next character in the buffer.
	 *********************************************************************************************/
	private char getChar() {
		char returnChar;
		if (line.length() > pos) {
			returnChar = line.charAt(pos);
			pos++;
			return returnChar;
		} else if (scan.hasNextLine()) {
			getNextLine();
			return getChar();
		} else {
			return EOF;
		}
	}
	
	/********************************************************************************************
	 * isInAlphabet()
	 * 		Determines whether or not the character in the string is a valid character in the
	 * 		alphabet of ToyC.
	 *@param ch
	 *		The character to be evaluated as being in the language or not.
	 *@return
	 *		Boolean indicating the characters validity in the alphabet.
	 *********************************************************************************************/
	private boolean isInAlphabet (char ch) {
		return (Character.isLetterOrDigit(ch) ||
				(ch == '=') || (ch == '>') || (ch == '<') || (ch == '!') ||
				(ch == '+') || (ch == '-') || (ch == '|') || (ch == '*') ||
				(ch == '/') || (ch == '%') || (ch == '&') || (ch == '.') ||
				(ch == '(') || (ch == ')') || (ch == '{') || (ch == '}') ||
				(ch == '[') || (ch == ']') || (ch == ',') || (ch == ';') ||
				(ch == ':'));
	}
	
	/********************************************************************************************
	 * getToken()
	 * 		Iterates character by character from the start of a new token until the first instance
	 * 		of a character which would not be valid in that token and creates an instance of a
	 * 		Token item to represent the type and value of the token.
	 *@return
	 *		The token that was generated (assuming valid syntax was used).
	 *********************************************************************************************/
	public Token getToken() {
		lexeme = ""; Token t = new TCtoken(); int currentLine;
		
		//Skip whitespace. Not a token in the language.
		while (Character.isWhitespace(charBuff) && (charBuff != EOF)) {
			charBuff = getChar();
		}
		
		//EOF token indicates end of source code. Any subsequent characters are thrown out.
		if (charBuff == EOF) {
			t = new TCtoken(TCtoken.Tokens.EOF);
			if (TCglobals.verbose) {
				TCoutput.reportDEBUG(" ", "[SCANNER]", t.toString());
			}
			return t;
		
		//Not end of file, therefore scan characters to determine the type and value of the Token.
			//If it's a digit, continue until getChar() no longer returns digits. Represents numeric.
		} else if (Character.isDigit(charBuff)) {
			do {
				lexeme = lexeme + charBuff;
				charBuff = getChar();
			} while (Character.isDigit(charBuff));
			
			//Decimal digit.
			if (charBuff == '.') {
				lexeme = lexeme + charBuff;
				charBuff = getChar();
				if (!Character.isDigit(charBuff)) {
					TCoutput.reportWARNING(this, "", "illegal character '" +
											charBuff + "' ignored");
				}
				do {
					lexeme = lexeme + charBuff;
					charBuff = getChar();
				} while (Character.isDigit(charBuff));
			}
			
			//Exponential value.
			if (charBuff == 'E') {
				lexeme = lexeme + charBuff;
				charBuff = getChar();
				if (charBuff != '+' && charBuff != '-' && !Character.isDigit(charBuff)){
					TCoutput.reportWARNING(this, "", "illegal character '" +
							charBuff + "' ignored");
				}
				do {
					lexeme = lexeme + charBuff;
					charBuff = getChar();
				} while (Character.isDigit(charBuff));
			}
			t = new TCtoken(TCtoken.Tokens.NUMBER, lexeme);
		
		//If a 'char' is in the buffer, determine the token type based on the characters between delimiters.
		} else if (Character.isLetter(charBuff)) {
			//Keep going until there is a delimiter. Then determine the type of the token based on whats in
			//the lexeme.
			do {
				lexeme = lexeme + charBuff;
				charBuff = getChar();
			} while (Character.isLetterOrDigit(charBuff));
			
			if (lexeme.equals("int"))
				t = new TCtoken(TCtoken.Tokens.INT, lexeme);
			else if (lexeme.equals("char"))
				t = new TCtoken(TCtoken.Tokens.CHAR, lexeme);
			else if (lexeme.equals("return"))
				t = new TCtoken(TCtoken.Tokens.RETURN, lexeme);
			else if (lexeme.equals("if"))
				t = new TCtoken(TCtoken.Tokens.IF, lexeme);
			else if (lexeme.equals("else"))
				t = new TCtoken(TCtoken.Tokens.ELSE, lexeme);
			else if (lexeme.equals("for"))
				t = new TCtoken(TCtoken.Tokens.FOR, lexeme);
			else if (lexeme.equals("do"))
				t = new TCtoken(TCtoken.Tokens.DO, lexeme);
			else if (lexeme.equals("while"))
				t = new TCtoken(TCtoken.Tokens.WHILE, lexeme);
			else if (lexeme.equals("switch"))
				t = new TCtoken(TCtoken.Tokens.SWITCH, lexeme);
			else if (lexeme.equals("case"))
				t = new TCtoken(TCtoken.Tokens.CASE, lexeme);
			else if (lexeme.equals("default"))
				t = new TCtoken(TCtoken.Tokens.DEFAULT, lexeme);
			else if (lexeme.equals("write"))
				t = new TCtoken(TCtoken.Tokens.WRITE, lexeme);
			else if (lexeme.equals("read"))
				t = new TCtoken(TCtoken.Tokens.READ, lexeme);
			else if (lexeme.equals("continue"))
				t = new TCtoken(TCtoken.Tokens.CONTINUE, lexeme);
			else if (lexeme.equals("break"))
				t = new TCtoken(TCtoken.Tokens.BREAK, lexeme);
			else if (lexeme.equals("newline"))
				t = new TCtoken(TCtoken.Tokens.NEWLINE, lexeme);
			else
				t = new TCtoken(TCtoken.Tokens.ID, lexeme);
		//If it's not an alpha numeric, determine which kind of token it is.
		} else {
			lexeme = charBuff + "";
			switch (charBuff) {
			case '+':
				t = new TCtoken(TCtoken.Tokens.ADDOP, "+");
				charBuff = getChar();
				break;
			case '-':
				t = new TCtoken(TCtoken.Tokens.ADDOP, "-");
				charBuff = getChar();
				break;
			case '|':
				charBuff = getChar();
				if (charBuff == '|') {
					t = new TCtoken(TCtoken.Tokens.ADDOP, "||");
					charBuff = getChar();
				} else {
					TCoutput.reportWARNING(this, "", "illegal token '|'");
					return t;
				}
				break;
			case '*':
				t = new TCtoken(TCtoken.Tokens.MULOP, "*");
				charBuff = getChar();
				break;
			case '/':
				charBuff = getChar();
				if (charBuff == '/') {
					getNextLine();
					charBuff = getChar();
					t = getToken();
					break;
				} else if (charBuff == '*') {
					char lastChar;
					String combination = "";
					do {
						lastChar = charBuff;
						charBuff = getChar();
						if (charBuff == EOF) {
							TCoutput.reportWARNING(this, "", "run-away comment; comment block not closed before end of file reached");
							t = new TCtoken(TCtoken.Tokens.EOF);
							break;
						}
						combination = "" + lastChar + charBuff;
					} while (!combination.equals("*/"));
					charBuff = getChar();
					t = getToken();
					break;
				} else {
					t = new TCtoken(TCtoken.Tokens.MULOP, "/");
					break;
				}
			case '%':
				t = new TCtoken(TCtoken.Tokens.MULOP, "%");
				charBuff = getChar();
				break;
			case '&':
				charBuff = getChar();
				if (charBuff == '&') {
					t = new TCtoken(TCtoken.Tokens.MULOP, "&&");
					charBuff = getChar();
				} else {
					TCoutput.reportWARNING(this, "", "illegal token '&'");
					return t;
				}
				break;
			case '=':
				charBuff = getChar();
				if (charBuff == '=') {
					t = new TCtoken(TCtoken.Tokens.RELOP, "==");
					charBuff = getChar();
				} else {
					t = new TCtoken(TCtoken.Tokens.ASSIGNOP, lexeme);
				}
				break;
			
			case '<':
				charBuff = getChar();
				if (charBuff == '=') {
					t = new TCtoken(TCtoken.Tokens.RELOP, "<=");
					charBuff = getChar();
				} else {
					t = new TCtoken(TCtoken.Tokens.RELOP, "<");
				}
				break;
			case '>':
				charBuff = getChar();
				if (charBuff == '=') {
					t = new TCtoken(TCtoken.Tokens.RELOP, ">=");
					charBuff = getChar();
				} else {
					t = new TCtoken(TCtoken.Tokens.RELOP, ">");
				}
				break;
			case '!':
				charBuff = getChar();
				if (charBuff == '=') {
					t = new TCtoken(TCtoken.Tokens.RELOP, "!=");
					charBuff = getChar();
				} else {
					t = new TCtoken(TCtoken.Tokens.NOT, "!");
				}
				break;
			case '(':
				charBuff = getChar();
				t = new TCtoken(TCtoken.Tokens.LPAREN, lexeme);
				break;
			case ')':
				charBuff = getChar();
				t = new TCtoken(TCtoken.Tokens.RPAREN, lexeme);
				break;
			case '{':
				charBuff = getChar();
				t = new TCtoken(TCtoken.Tokens.LCURLY, lexeme);
				break;
			case '}':
				charBuff = getChar();
				t = new TCtoken(TCtoken.Tokens.RCURLY, lexeme);
				break;
			case '[':
				charBuff = getChar();
				t = new TCtoken(TCtoken.Tokens.LBRACKET, lexeme);
				break;
			case ']':
				charBuff = getChar();
				t = new TCtoken(TCtoken.Tokens.RBRACKET, lexeme);
				break;
			case ',':
				charBuff = getChar();
				t = new TCtoken(TCtoken.Tokens.COMMA, lexeme);
				break;
			case ';':
				charBuff = getChar();
				t = new TCtoken(TCtoken.Tokens.SEMICOLON, lexeme);
				break;
			case ':':
				charBuff = getChar();
				t = new TCtoken(TCtoken.Tokens.COLON, lexeme);
				break;
			case '\'':
				currentLine = lineNumber;
				charBuff = getChar();
				lexeme = lexeme + charBuff;
				if (charBuff != '\'') {
					charBuff = getChar();
					lexeme = lexeme + charBuff;
					if (charBuff != '\'') {
						TCoutput.reportWARNING(this, "", "unterminated CHARLITERAL at line " + lineNumber);
						charBuff = getChar();
						return t;
					} else if (currentLine == lineNumber) {
						t = new TCtoken(TCtoken.Tokens.CHARLITERAL, lexeme);
						charBuff = getChar();
					} else {
						TCoutput.reportWARNING(this, "", "unterminated CHARLITERAL at line " + currentLine);
						return t;
					}
				} else if (currentLine == lineNumber) {
					t = new TCtoken(TCtoken.Tokens.CHARLITERAL, "''");
				} else {
					TCoutput.reportWARNING(this, "", "unterminated CHARLITERAL at line " + currentLine);
					return t;
				}
				break;
			case '"':
				currentLine = lineNumber;
				do {
					charBuff = getChar();
					lexeme = lexeme + charBuff;
					if (currentLine != lineNumber) {
						TCoutput.reportWARNING(this, "", "unterminated STRING at line " + currentLine);
						return t;
					}
					if (charBuff == EOF) {
						TCoutput.reportWARNING(this, "", "file ended with unterminated STRING");
						t = new TCtoken(TCtoken.Tokens.EOF);
						break;
					}
				} while (charBuff != '"');
				if (charBuff == '"') {
					charBuff = getChar();
					t = new TCtoken(TCtoken.Tokens.STRING, lexeme);
				}
				break;
			}
		}
		if (TCglobals.verbose) TCoutput.reportDEBUG(" ", "[SCANNER]", t.toString());
		return t;
	}
	
	/********************************************************************************************
	 * getLine()
	 * 		Returns the current line held in the Lexer.
	 * @return
	 * 		The current value of the 'line' property of the lexer.
	 *********************************************************************************************/
	public String getLine() {
		return line;
	}
	
	/********************************************************************************************
	 * getLexeme()
	 * 		Returns the current lexeme (actual value of the current token being scanned).
	 * @return
	 * 		The string containing the currently held lexeme.
	 *********************************************************************************************/
	public String getLexeme() {
		return lexeme;
	}
	
	/********************************************************************************************
	 * getPos()
	 * 		Returns the current position of the scanner in the line of code being scanned.
	 * @return
	 * 		The character number in the line currently being scanned.
	 *********************************************************************************************/
	public int getPos() {
		return pos;
	}
	
	/********************************************************************************************
	 * getLineNumber()
	 * 		Returns the line number that is currently being scanned. Generally used only for
	 * 		diagnostic purposes on syntax errors.
	 * @return
	 * 		The number of the line currently being scanned.
	 *********************************************************************************************/
	public int getLineNum() {
		return lineNumber;
	}
	
	/********************************************************************************************
	 * isInteger()
	 * 		Determines whether or not the passed in string is an integer.
	 * @param s
	 * 		The string whose integerness needs to be determined.
	 * @return
	 * 		bool: whether or not it is an integer... I would expect this particular function to
	 * 		be pretty self explanatory.
	 *********************************************************************************************/
	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}