/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package symTable;

import compilers.Symbol;
import java.util.Map;
import java.util.jar.Attributes;

/********************************************************************************************
 * class TCsymbol
 * 		An instance of a symbolic token in the abstract syntax tree.
 * @author shburns
 *********************************************************************************************/
public class TCsymbol implements Symbol{
	// Public Instance Variables
	public String name;
	public Object value;
	public int type;
	public int offset;
	
	// Constructors
	public TCsymbol() {
		
	}
	
	public TCsymbol(String n) {
		name = n;
	}
	
	public TCsymbol(String n,int o) {
		name = n;
		offset = o;
	}
	
	public TCsymbol(String n,int o, int t) {
		name = n;
		offset = o;
		type = t;
	}
	
	public String getId() {
		return name;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public int getType() {
		return type;
	}
	
	public void setValue(Object o) {
		value = o;
	}
	
	public Map getAttributes() {
		Attributes attr = new Attributes ();
		attr.put(name, value);
		return attr;
	}
	
	// Instance Methods
	public boolean equals(TCsymbol item) {
		return (name.equals(item.getId()) &&
				offset == item.getOffset() &&
				type == item.getType());
	}
	public String toString() {
		String s = name;
		s += " at " + offset + " of type " + type;
		return s;
	}
}