package compile.lesson2;

import java.io.IOException;

public class Parser {
	
	static int lookahead;
	
	public Parser() throws IOException {
		lookahead = System.in.read();
	}
	
	public void expr() throws IOException {
		term();
		while (true) {
			if('+' == lookahead) {
				match('+');
				term();
				System.out.print('+');
			}else if('-' == lookahead) {
				match('-');
				term();
				System.out.print('-');
			}else {
				return;
			}
		}
	}
	
	public void term() throws IOException {
		if(Character.isDigit((char)lookahead)) {
			System.out.print((char)lookahead);
			match(lookahead);
		}else {
			throw new IOException("syntax error£¡");
		}
	}
	
	public void match(int t) throws IOException {
		if(lookahead == t){
			lookahead = System.in.read();
		}else {
			throw new IOException("syntax error£¡");
		}
	}

}
