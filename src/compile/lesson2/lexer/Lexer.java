package compile.lesson2.lexer;

import java.io.IOException;
import java.util.Hashtable;

public class Lexer {

	public int line = 1;
	private char peek = ' ';
	private Hashtable<String, Word> words = new Hashtable<>();

	public void reserve(Word t) {
		words.put(t.lexeme, t);
	}

	public Lexer() {
		reserve(new Word(Tag.TRUE, "true"));
		reserve(new Word(Tag.FALSE, "false"));
	}

	public Token scan() throws IOException {
		for (;; peek = (char) System.in.read()) {
			if (peek == '\t' || peek == ' ') {
				continue;
			} else if (peek == '\n') {
				line += 1;
			} else {
				break;
			}
		}

		if (Character.isDigit(peek)) {
			int v = 0;
			do {
				v = v * 10 + Character.digit(peek, 10);
				peek = (char) System.in.read();
			} while (Character.isDigit(peek));
			
			return new Num(v);
		}
		
		if(Character.isLetter(peek)) {
			StringBuffer sb = new StringBuffer();
			do {
				sb.append(peek);
				peek = (char) System.in.read();
			}while(Character.isLetter(peek));
			
			String s = sb.toString();
			Word word = words.get(s);
			if(word == null) {
				word = new Word(Tag.ID, s);
				words.put(s, word);
			}
			return word;
		}
		
		Token token = new Token(peek);
		peek = ' ';
		return token;
	}

}
