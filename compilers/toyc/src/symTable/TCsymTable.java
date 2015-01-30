/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package symTable;

import java.util.ArrayList;
import compilers.SymbolTable;
import compilers.Symbol;

public class TCsymTable implements SymbolTable{
	
	private static ArrayList<TCsymbol> syms = new ArrayList<TCsymbol>();
	
	public TCsymTable(){
		
	}
	// Instance Methods
	public Symbol add(String id,int type) {
		int offset = syms.size();
		boolean exists = false;
		TCsymbol sym = null;
		for (int i = 0; i < syms.size(); i ++) {
			if (syms.get(i).getId().equals(id)) {
				exists = true;
			}
		}
		if (!exists) {
			sym = new TCsymbol(id, offset, type);
			syms.add(sym);
		}
		
		return sym;
	}
	
	public Symbol add(String id) {
		boolean exists = false;
		TCsymbol sym = null;
		for (int i = 0; i < syms.size(); i ++) {
			if (syms.get(i).getId().equals(id)) {
				exists = true;
			}
		}
		if (!exists) {
			sym = new TCsymbol(id);
			syms.add(sym);
		}

		return sym;
	}
	
	public Symbol find(String id) {
		for (int i = 0; i < syms.size(); i ++) {
			if (syms.get(i).getId().equals(id)) {
				return syms.get(i);
			}
		}
		return null;
	}
	public String toString() {
		String s = "[SYMBOL TABLE]:";
		for (int i = 0; i < syms.size(); i ++) {
			s += syms.get(i).toString();
		}
		return s;
	}
}