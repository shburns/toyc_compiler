/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package parser;

import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.EnumMap;
import compilers.Parser;
import compilers.Lexer;
import compilers.Token;
import compilers.Symbol;
import compilers.SymbolTable;
import compilers.AbstractSyntax;
import globals.TCglobals;
import abstractSyntax.Program;
import abstractSyntax.Definition;
import abstractSyntax.FuncDefinition;
import abstractSyntax.VarDefinition;
import abstractSyntax.Statement;
import abstractSyntax.ExprState;
import abstractSyntax.BreakState;
import abstractSyntax.BlockState;
import abstractSyntax.IfState;
import abstractSyntax.NullState;
import abstractSyntax.ReturnState;
import abstractSyntax.WhileState;
import abstractSyntax.ReadState;
import abstractSyntax.WriteState;
import abstractSyntax.NewLineState;
import abstractSyntax.Expression;
import abstractSyntax.SimpleExpr;
import abstractSyntax.FuncCallExpr;
import abstractSyntax.BinaryExpr;
import abstractSyntax.MinusExpr;
import abstractSyntax.NotExpr;
import output.TCoutput;

/*********************************************************************************************
 * class TCparser
 * 		A recursive decent parse designed to parse programs written in the trivial (though
 * 		computationally complete) language 'ToyC', as defined by my Compilers instructor for
 * 		the purposes of the Compilers class at VCU.
 * @author shurns
 *********************************************************************************************/
public class TCparser implements Parser {
	Lexer scanner;
	Token buff;
	
	/*********************************************************************************************
	 * TCParser
	 * 	Constructor. Instantiates the toyC parser with a lexer object for analyzing lexical tokens.
	 *********************************************************************************************/
	public TCparser(Lexer s) {
		scanner = s;
	}
	
	/*********************************************************************************************
	 * accept()
	 * 	Determines whether the next token in the lexer is valid and matches the expected token
	 *  type, reporting a syntax error otherwise.
	 * @param t
	 * 	The token that is expected to be found in the buffer based on the sytax rules.
	 *********************************************************************************************/
	private void accept(TCtoken.Tokens t) {
		if (t.equals(buff.getTokenType()))
			buff = scanner.getToken();
		else {
			TCoutput.reportSYNTAX_ERROR(scanner, "Currently in buffer" + buff.toString());
			TCoutput.reportSYNTAX_ERROR(scanner, t + " expected");
		}
	}
	
	/********************************************************************************************
	 * parse()
	 * 	Parses the input program and, if the command-line argument requests it, outputs the
	 *  abstract syntax tree.
	 * @return Program
	 * 	The abstract syntax tree that symbolically represents the program to be compiled.
	 *********************************************************************************************/
	public AbstractSyntax parse() {
		buff = scanner.getToken();
		Program p = program();
		if (TCglobals.astOut) {
			System.out.println(p.toString());
		}
		return p;
	}
	
	/*********************************************************************************************
	 *  enteringDEBUG
	 *  	Debugging interface for outputting debug statements in the case of the 'verbose'
	 *  	program argument being toggled on. Indicates which abstract syntax elements are being
	 *  	parsed.
	 * @param s
	 * 		The string to output to the debug log.
	 *********************************************************************************************/
	private static void enteringDEBUG(String s) {
		if (TCglobals.verbose) TCoutput.reportDEBUG("\t", "[PARSER]", "entering " + s);
	}

	/*********************************************************************************************
	 *  exitingDEBUG
	 *  	Debugging interface for outputting debug statements in the case of the 'verbose'
	 *  	program argument being toggled on. Indicates which abstract syntax elements are being
	 *  	have been successfully parsed and are being exited.
	 * @param s
	 * 		The string to output to the debug log.
	 *********************************************************************************************/
	private static void exitingDEBUG(String s) {
		if (TCglobals.verbose) TCoutput.reportDEBUG("\t", "[PARSER]", "exiting " + s);
	}
	
	/********************************************************************************************
	 * program()
	 * 		The main work of the parser. Looks for the opening token of a valid program and
	 * 		begins the process of parsing the list of definition expressions, then looks for the
	 * 		END_OF_FILE token.
	 * @return
	 * 		The AbstractSyntaxTree that was produced by a successful parse.
	 *********************************************************************************************/
	private Program program() {
		enteringDEBUG("program");
		accept(TCtoken.Tokens.LCURLY);
		List<Definition> defList = definitionList();
		accept(TCtoken.Tokens.EOF);
		exitingDEBUG("program");
		return new Program(TCglobals.inputFileName, defList);
	}
	
	/********************************************************************************************
	 * definitionList()
	 * 		Runs through the length of the program to get the list of expression definitions that
	 * 		constitute a valid program, then checks for the closing '}' char and returns.
	 * @return List<Definition>
	 * 		The list of expression definitions that constitute the program being parsed.
	 *********************************************************************************************/
	private List<Definition> definitionList() {
		enteringDEBUG("definitionList");
		Scanner scan = new Scanner (System.in);
		ArrayList<Definition> list = new ArrayList<Definition>();
		while (!buff.getTokenType().equals(TCtoken.Tokens.RCURLY)) {
			list.add(definition());
		}
		accept(TCtoken.Tokens.RCURLY);
		exitingDEBUG("definitionList");
		return list;
	}
	
	/********************************************************************************************
	 * definition()
	 * 		Function for parsing a 'definition' type of the AbstractSyntax.
	 * @return Definition
	 * 		The parsed definition.
	 *********************************************************************************************/
	private Definition definition() {
		Definition s = null;
		Scanner scan = new Scanner(System.in);
		enteringDEBUG("definition");
		
		//If the definition is for an int or char type
		if (buff.getTokenType().equals(TCtoken.Tokens.INT) ||
			buff.getTokenType().equals(TCtoken.Tokens.CHAR)) {
			SimpleExpr type = new SimpleExpr(buff);
			buff = scanner.getToken();
			
			//If the definition is for a variable identifier
			if (buff.getTokenType().equals(TCtoken.Tokens.ID)) {
				SimpleExpr id = new SimpleExpr(buff);
				ArrayList<SimpleExpr> ids = new ArrayList<SimpleExpr>();
				buff = scanner.getToken();
				
				//Check for multiple definitions or the end of the statement.
				if (buff.getTokenType().equals(TCtoken.Tokens.COMMA) ||
					buff.getTokenType().equals(TCtoken.Tokens.SEMICOLON)) {
					ids.add(id);
					if (buff.getTokenType().equals(TCtoken.Tokens.SEMICOLON)) {
						accept(TCtoken.Tokens.SEMICOLON);
						s = new VarDefinition(ids, type);
					}
					else {
						accept(TCtoken.Tokens.COMMA);
						ids.add(new SimpleExpr(buff));
						buff = scanner.getToken();
						while(buff.getTokenType().equals(TCtoken.Tokens.COMMA)) {
							accept(TCtoken.Tokens.COMMA);
							ids.add(new SimpleExpr(buff));
							buff = scanner.getToken();
						}
						accept(TCtoken.Tokens.SEMICOLON);
						s = new VarDefinition(ids, type);
					}
				}
				else {
					//If not multiple definitions or the end of the statement, definition is of a function.
					accept(TCtoken.Tokens.LPAREN);
					ArrayList<VarDefinition> params = new ArrayList<VarDefinition>();
					boolean moreParams = true;
					
					//Read in the parameters for the funciton, if any.
					while (moreParams) {
						if (buff.getTokenType().equals(TCtoken.Tokens.INT) ||
								buff.getTokenType().equals(TCtoken.Tokens.CHAR)) {
							SimpleExpr paramType = new SimpleExpr(buff);
							buff = scanner.getToken();
							if (buff.getTokenType().equals(TCtoken.Tokens.ID)) {
								SimpleExpr paramId = new SimpleExpr(buff);
								ArrayList<SimpleExpr> paramIds = new ArrayList<SimpleExpr>();
								paramIds.add(paramId);
								params.add(new VarDefinition(paramIds, paramType));
								buff = scanner.getToken();
								if (!buff.getTokenType().equals(TCtoken.Tokens.COMMA)) {
									moreParams = false;
								}
								else {
									accept(TCtoken.Tokens.COMMA);
								}
							}
							else {
								TCoutput.reportSYNTAX_ERROR(scanner, "variable id expected");
								moreParams = false;
							}
						}
						else {
							moreParams = false;
						}
					}
					
					//Close function definition and get the block statement which the function defines.
					accept(TCtoken.Tokens.RPAREN);
					Statement func = blockStatement();
					s = new FuncDefinition(id, type, params, func);
				}
			}
			else {
				TCoutput.reportSYNTAX_ERROR(scanner, "ID expected");
			}
		}
		exitingDEBUG("definition");
		return s;
	}
	
	/********************************************************************************************
	 * blockStatement()
	 * 		Parses a block statement, which is any set of statements wrapped in curly braces '{ }'
	 * @return
	 * 		Statement that was parsed.
	 *********************************************************************************************/
	public Statement blockStatement() {
		enteringDEBUG("compountStatement");
		
		//Block statements must be wrapped in curly braces.
		accept(TCtoken.Tokens.LCURLY);
		Scanner scan = new Scanner(System.in);
		ArrayList<VarDefinition> varDefs = new ArrayList<VarDefinition>();
		ArrayList<Statement> states = new ArrayList<Statement>();
		boolean newVarDefs = true;
		
		//Rescursively parse the entire block statement until the closing brace is reached.
		while(!buff.getTokenType().equals(TCtoken.Tokens.RCURLY)) {
			
			//Begin by parsing all variable definitions which, according to the language definition,
			//must come first.  NOTE: We cannot simply call the 'definition' function as written
			//because the language prohibits nested function definition, and definition() checks for
			//and allows function definition.
			while(newVarDefs) {
				if (buff.getTokenType().equals(TCtoken.Tokens.INT) ||
					buff.getTokenType().equals(TCtoken.Tokens.CHAR)) {
					ArrayList<SimpleExpr> id = new ArrayList<SimpleExpr>();
					SimpleExpr type = new SimpleExpr(buff);
					buff = scanner.getToken();
					
					//Make sure of proper syntax.
					if (buff.getTokenType().equals(TCtoken.Tokens.ID)) {
						id.add(new SimpleExpr(buff));
						buff = scanner.getToken();
						
						//Check for variable definition list
						if (buff.getTokenType().equals(TCtoken.Tokens.COMMA)) {
							while(buff.getTokenType().equals(TCtoken.Tokens.COMMA)) {
								accept(TCtoken.Tokens.COMMA);
								if (buff.getTokenType().equals(TCtoken.Tokens.ID)) {
									id.add(new SimpleExpr(buff));
									buff = scanner.getToken();
								}
								else {
									TCoutput.reportSYNTAX_ERROR(scanner, "expected identifier in var definition list");
								}
							}
						}
						varDefs.add(new VarDefinition(id, type));
					}
					//If definition type is not followed by an ID, syntax error.
					else {
						TCoutput.reportSYNTAX_ERROR(scanner, "variable identifier expected");
						newVarDefs = false;
					}
					accept(TCtoken.Tokens.SEMICOLON);
				}
				//Once the definition type is no longer the start of the next statement, assume definitions are complete.
				else {
					newVarDefs = false;
				}
			}
			//After definitions, parse statement list.
			states = (ArrayList)statementList();
		}
		accept(TCtoken.Tokens.RCURLY);
		exitingDEBUG("compoundStatement");
		return new BlockState(varDefs, states);
	}

	/********************************************************************************************
	 * statementList()
	 * 		Parses a list of statements as defined within the abstract syntax.
	 * @return List<Statement>
	 * 		Returns an ArrayList of Statement objects which represents the statementList sub-tree
	 * 		of the abstract syntax tree.
	 *********************************************************************************************/
	public List<Statement> statementList() {
		enteringDEBUG("statementList");
		ArrayList<Statement> states = new ArrayList<Statement>();
		
		//Read statements until the end of the current block of statements.
		while (!buff.getTokenType().equals(TCtoken.Tokens.RCURLY)) {
			Statement s = statement();
			states.add(s);
		}
		exitingDEBUG("statementList");
		return states;
	}
	
	/********************************************************************************************
	 * statement()
	 * 		Recursively parses a 'statement' subtree.
	 * @return
	 * 		The statement being parsed.
	 *********************************************************************************************/
	public Statement statement() {
		enteringDEBUG("statement");
		Statement s = null;
		Scanner scan = new Scanner(System.in);
		
		//Check for type of statement. First, block statements.
		if (buff.getTokenType().equals(TCtoken.Tokens.LCURLY)) {
			s = blockStatement();
			
		//'break' statements
		} else if (buff.getTokenType().equals(TCtoken.Tokens.BREAK)) {
			buff = scanner.getToken();
			accept(TCtoken.Tokens.SEMICOLON);
			s = new BreakState();
			
		//statement termination (null statements are valid).
		} else if (buff.getTokenType().equals(TCtoken.Tokens.SEMICOLON)) {
			accept(TCtoken.Tokens.SEMICOLON);
			s = new NullState();
			
		//newline statement
		} else if (buff.getTokenType().equals(TCtoken.Tokens.NEWLINE)) {
			buff = scanner.getToken();
			accept(TCtoken.Tokens.SEMICOLON);
			s = new NewLineState();
			
		//conditional statement
		} else if (buff.getTokenType().equals(TCtoken.Tokens.IF)) {
			buff = scanner.getToken();
			accept(TCtoken.Tokens.LPAREN);
			Expression test = expr();
			accept(TCtoken.Tokens.RPAREN);
			Statement ifState = statement();
			
			//Ternary conditional with 'else' statement
			if (buff.getTokenType().equals(TCtoken.Tokens.ELSE)) {
				accept(TCtoken.Tokens.ELSE);
				Statement elseState = statement();
				s = new IfState(test, ifState, elseState);
				
			//Binary conditional.
			} else {
				s = new IfState(test, ifState);
			}
			
		//'return' statement, terminates a function and may or may not return a value.
		} else if (buff.getTokenType().equals(TCtoken.Tokens.RETURN)) {
			accept(TCtoken.Tokens.RETURN);
			
			//Return with no value.
			if (buff.getTokenType().equals(TCtoken.Tokens.SEMICOLON)) {
				accept(TCtoken.Tokens.SEMICOLON);
				s = new ReturnState();
			//Return statement returns a value whose id needs to be held for evaluation.
			} else {
				Expression expr = expr();
				accept(TCtoken.Tokens.SEMICOLON);
				s = new ReturnState(expr);
			}
		
		//While statement.
		} else if (buff.getTokenType().equals(TCtoken.Tokens.WHILE)) {
			buff = scanner.getToken();
			accept(TCtoken.Tokens.LPAREN);
			Expression expr = expr();
			accept(TCtoken.Tokens.RPAREN);
			Statement state = statement();
			s = new WhileState(expr, state);
			
		//Write statements. Writes text to designated output.
		} else if (buff.getTokenType().equals(TCtoken.Tokens.WRITE)) {
			buff = scanner.getToken();
			accept(TCtoken.Tokens.LPAREN);
			ArrayList<Expression> exprs = new ArrayList<Expression>();
			exprs.add(expr());
			while(buff.getTokenType().equals(TCtoken.Tokens.COMMA)) {
				exprs.add(expr());
			}
			accept(TCtoken.Tokens.RPAREN);
			accept(TCtoken.Tokens.SEMICOLON);
			s = new WriteState(exprs);
		
		//Read statement. Reads from standard input device.
		} else if (buff.getTokenType().equals(TCtoken.Tokens.READ)) {
			buff = scanner.getToken();
			accept(TCtoken.Tokens.LPAREN);
			ArrayList<SimpleExpr> ids = new ArrayList<SimpleExpr>();
			if (buff.getTokenType().equals(TCtoken.Tokens.ID)) {
				ids.add(new SimpleExpr(buff));
			} else {
				TCoutput.reportSYNTAX_ERROR(scanner, "identifier expected");
			}
			buff = scanner.getToken();
			while(buff.getTokenType().equals(TCtoken.Tokens.COMMA)) {
				buff = scanner.getToken();
				if (buff.getTokenType().equals(TCtoken.Tokens.ID)) {
					ids.add(new SimpleExpr(buff));
				} else {
					TCoutput.reportSYNTAX_ERROR(scanner, "identifier expected");
				}
				buff = scanner.getToken();
			}
			accept(TCtoken.Tokens.RPAREN);
			accept(TCtoken.Tokens.SEMICOLON);
			s = new ReadState(ids);
		
		//Statement constitutes an expression to be evaluated.
		} else {
			Expression e = expr();
			accept(TCtoken.Tokens.SEMICOLON);
			s = new ExprState(e);
		}
		exitingDEBUG("statement");
		return s;
	}
	
	/********************************************************************************************
	 * expr()
	 * 		Parses an expression in the ToyC language.
	 * @return
	 * 		The Expression subtree of the AST
	 *********************************************************************************************/
	public Expression expr() {
		enteringDEBUG("expr");
		Expression e = null;
		SimpleExpr op = null;
		Expression relop2 = null;
		Expression relop1 = relopExpr();
		
		//If this is an assignment expression, evaluate as such.
		if (buff.getTokenType().equals(TCtoken.Tokens.ASSIGNOP)) {
			op = new SimpleExpr(buff);
			buff = scanner.getToken();
			relop2 = relopExpr();
			if (buff.getTokenType().equals(TCtoken.Tokens.ASSIGNOP)) {
				while(buff.getTokenType().equals(TCtoken.Tokens.ASSIGNOP)) {
					e = new BinaryExpr(op, relop1, relop2);
					op = new SimpleExpr(buff);
					buff = scanner.getToken();
					relop1 = e;
					relop2 = relopExpr();
				}
			}
			e = new BinaryExpr(op, relop1, relop2);
		
		//If not an assignment operator, then it's just a relative operator expression.
		} else {
			e = relop1;
		}
		exitingDEBUG("expr");
		return e;
	}

	/********************************************************************************************
	 * relopExpr()
	 * 		Evaluates relational operation expressions (!, <, >, >=, <=, ==)
	 * @return
	 * 		The Expression subtree of the AST
	 *********************************************************************************************/
	public Expression relopExpr() {
		enteringDEBUG("relopExpr");
		Expression e = null;
		SimpleExpr op = null;
		Expression simpExp2 = null;
		Expression simpExp1 = simpleExpr();
		
		//After evaluating the first component of the relational operator, check to see if it's a binary operation.
		if (buff.getTokenType().equals(TCtoken.Tokens.RELOP)) {
			op = new SimpleExpr(buff);
			buff = scanner.getToken();
			simpExp2 = simpleExpr();
			if (buff.getTokenType().equals(TCtoken.Tokens.RELOP)) {
				while(buff.getTokenType().equals(TCtoken.Tokens.RELOP)){
					e = new BinaryExpr(op, simpExp1, simpExp2);
					op = new SimpleExpr(buff);
					buff = scanner.getToken();
					simpExp1 = e;
					simpExp2 = simpleExpr();
				}
			}
			e = new BinaryExpr(op, simpExp1, simpExp2);
		} else {
			e = simpExp1;
		}
		exitingDEBUG("relopExpr");
		return e;
	}
	
	/********************************************************************************************
	 * simpleExpr()
	 * 		Evaluates a simple expression (negation, addition, multiplication)
	 * @return
	 * 		The Expression subtree of the AST as recursively parsed.
	 *********************************************************************************************/
	public Expression simpleExpr() {
		enteringDEBUG("simpleExpr");
		Expression e = null;
		SimpleExpr op = null;
		Expression termExp2 = null;
		Expression termExp1 = term();
		
		//After evaluating the first term, determine if it is an addition operation.
		//The MULOP is scanned for and assessed first because it has precedence in ToyC.
		if (buff.getTokenType().equals(TCtoken.Tokens.ADDOP)) {
			op = new SimpleExpr(buff);
			buff = scanner.getToken();
			termExp2 = term();
			if (buff.getTokenType().equals(TCtoken.Tokens.ADDOP)) {
				while(buff.getTokenType().equals(TCtoken.Tokens.ADDOP)){
					e = new BinaryExpr(op, termExp1, termExp2);
					op = new SimpleExpr(buff);
					buff = scanner.getToken();
					termExp1 = e;
					termExp2 = term();
				}
			}
			e = new BinaryExpr(op, termExp1, termExp2);
		}
		else {
			e = termExp1;
		}
		exitingDEBUG("simpleExpr");
		return e;
	}
	
	/********************************************************************************************
	 * term()
	 * 		Evaluates a term of an expression as a set of primary terms (which can, themselves be
	 * 		expression terms wrapped in parentheses to establish evaluation precedence). This is
	 * 		where we evaluate MULOPs because multiply has precedence over addition. So this
	 * 		evaluates multiplication first and then returns to 'simpleExpr()' which evaluates
	 * 		addition.
	 * @return
	 * 		The Expression subtree of the AST.
	 *********************************************************************************************/
	public Expression term() {
		enteringDEBUG("term");
		Expression e = null;
		SimpleExpr op = null;
		Expression primExp2 = null;
		Expression primExp1 = primary();
		
		//After evaluating the first component, check for multiplication operators.
		if (buff.getTokenType().equals(TCtoken.Tokens.MULOP)) {
			op = new SimpleExpr(buff);
			buff = scanner.getToken();
			primExp2 = primary();
			if (buff.getTokenType().equals(TCtoken.Tokens.MULOP)) {
				while(buff.getTokenType().equals(TCtoken.Tokens.MULOP)){
					e = new BinaryExpr(op, primExp1, primExp2);
					op = new SimpleExpr(buff);
					buff = scanner.getToken();
					primExp1 = e;
					primExp2 = primary();
				}
			}
			e = new BinaryExpr(op, primExp1, primExp2);
		}
		else {
			e = primExp1;
		}
		exitingDEBUG("term");
		return e;
	}

	/********************************************************************************************
	 * primary()
	 * 		Evaluates the primary term of an Expression subtree, which itself may be a fully
	 * 		qualified expression where parenthesis have given the expression precedence and so
	 * 		its result needs to be evaluated and used as the primary component of an outer
	 * 		expression sub-tree.
	 * @return
	 * 		The Expression sub-tree of the AST.
	 */
	public Expression primary() {
		enteringDEBUG("primary");
		Expression e = null;
		if (buff.getTokenType().equals(TCtoken.Tokens.ID)) {
			SimpleExpr id = new SimpleExpr(buff);
			buff = scanner.getToken();
			if (buff.getTokenType().equals(TCtoken.Tokens.LPAREN)) {
				accept(TCtoken.Tokens.LPAREN);
				ArrayList<Expression> params = new ArrayList<Expression>();
				params.add(expr());
				while(buff.getTokenType().equals(TCtoken.Tokens.COMMA)) {
					accept(TCtoken.Tokens.COMMA);
					params.add(expr());
				}
				if (buff.getTokenType().equals(TCtoken.Tokens.RPAREN)) {
					accept(TCtoken.Tokens.RPAREN);
				}
				else {
					TCoutput.reportSYNTAX_ERROR(scanner, "no closing parens on function call " + id.toString());
				}
				e = new FuncCallExpr(id, params);
			}
			else {
				e = id;
			}
		}
		else {
			if (buff.getTokenType().equals(TCtoken.Tokens.LPAREN)){
				accept(TCtoken.Tokens.LPAREN);
				e = expr();
				accept(TCtoken.Tokens.RPAREN);
			}
			else {
				if (buff.getTokenType().equals(TCtoken.Tokens.ADDOP) ||
					buff.getTokenType().equals(TCtoken.Tokens.NOT)) {
					if (buff.getTokenType().equals(TCtoken.Tokens.ADDOP)) {
						if (buff.getLexeme().equals("-")) {
							accept(TCtoken.Tokens.ADDOP);
							Expression prim = primary();
							e = new MinusExpr(prim);
						}
						else {
							TCoutput.reportSYNTAX_ERROR(scanner, "invalid operator on expression");
						}
					}
					else {
						accept(TCtoken.Tokens.NOT);
						Expression prim = primary();
						e = new NotExpr(prim);
					}
				}
				else {
					e = new SimpleExpr(buff);
					buff = scanner.getToken();
				}
			}
		}
		exitingDEBUG("primary");
		return e;
	}
}