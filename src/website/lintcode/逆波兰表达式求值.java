package website.lintcode;

import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017年11月9日 上午11:43:07
 * @description
 * 		
	求逆波兰表达式的值。
	
	在逆波兰表达法中，其有效的运算符号包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰计数表达。

 * @example
 * 		["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
		["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 *
 * @Solution
 */
public class 逆波兰表达式求值 extends HH {
	
	public int evalRPN(String[] tokens) {
        // write your code here
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<tokens.length; i++) {
			if(stack.isEmpty() || isNumberic(tokens[i])) {
				stack.push(getIntFromStr(tokens[i]));
			}else {
				int a = stack.pop();
				int b = stack.pop();
				//这个操作是到这来的，栈顶的是被操作数
				int c = operate(b, a, tokens[i]);
				stack.push(c);
			}
		}
		return stack.pop();
    }
	
	private Integer getIntFromStr(String s) {
		int result = 0;
		int index = 0;
		if(s.charAt(0) == '-') {
			index = 1;
		}
		for(int i=index; i<s.length(); i++) {
			result = result * 10 + s.charAt(i) - '0';
		}
		return result * (index == 1 ? -1 : 1);
	}
	
	private int operate(int a, int b, String s) {
		if(s.equals("+")) {
			return a + b;
		}
		if(s.equals("-")) {
			return a-b;
		}
		if(s.equals("*")) {
			return a*b;
		}
		return a/b;
	}
	
	private boolean isNumberic(String s) {
		String operators = "+-*/";
		return !operators.contains(s);
		//return !s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/");
	}
	
	public static void main(String[] args) {
		print(new 逆波兰表达式求值().getIntFromStr("18"));
	}

}
