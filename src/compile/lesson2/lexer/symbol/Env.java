package compile.lesson2.lexer.symbol;

import java.util.Hashtable;

public class Env {
	
	private Hashtable<String, Symbol> table;
	
	private Env prev;
	
	public Env(Env prev) {
		this.prev = prev;
		table = new Hashtable<>();
	}
	
	public Symbol get(String s) {
		for(Env e = this; e != null; e = e.prev) {
			Symbol found = table.get(s);
			if(found != null) {
				return found;
			}
		}
		
		return null;
	}
	
	public void put(String key, Symbol s) {
		this.table.put(key, s);
	}
	

}
