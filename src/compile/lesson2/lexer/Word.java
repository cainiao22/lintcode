package compile.lesson2.lexer;

public class Word extends Token {

	public final String lexeme;
	
	public Word(int tag, String lexeme) {
		super(tag);
		this.lexeme = lexeme;
	}

}
