package compile.lesson2;

import java.io.IOException;

public class Postfix {
	
	public static void main(String[] args) throws IOException {
		Parser parser = new Parser();
		parser.expr();
	}

}
