package website.lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017年11月10日 下午3:15:18
 * @description
 * 		给定一个仅包含数字 0 - 9 的字符串和一个目标值，
 * 		返回在数字之间添加了 二元 运算符(不是一元)+, - 或 * 之后所有能得到目标值的情况。
 * 
 * @example
 * 		"123", 6 -> ["1+2+3", "1*2*3"] 
		"232", 8 -> ["2*3+2", "2+3*2"]
		"105", 5 -> ["1*0+5","10-5"]
		"00", 0 -> ["0+0", "0-0", "0*0"]
		"3456237490", 9191 -> []
 *
 * @Solution
 */
public class 添加运算符 extends HH {
	
	private List<String> result = new ArrayList<>();
	private List<Object> expression = new ArrayList<>();
	private Stack<Integer> numbers = new Stack<>();
	private Stack<String> operators = new Stack<>();
	private String[] operas = new String[] {"+", "-","*"};
	
	public List<String> addOperators(String num, int target) {
        // write your code here
		helper(target, 0, num, "", 0, 0);
		return result;
    }
	
	/**
	 * 太耗时了，简直就是暴力解法
	 * @param num
	 * @param index
	 * @param target
	 */
	private void helper(String num, int index, int target) {
		if(index >= num.length()) {
			StringBuffer sb = new StringBuffer();
			for(Object item : expression) {
				sb.append(item.toString());
				if(item instanceof Integer) {
					numbers.push((Integer)item);
				}else {
					if(operators.isEmpty()) {
						operators.push(item.toString());
					}else {
						boolean flag = compare(operators.peek(), item.toString());
						if(flag) {
							int b = numbers.pop();
							int a = numbers.pop();
							String op = operators.pop();
							numbers.push(operate(a, b, op));
						}
						operators.push(item.toString());
					}
				}
			}
			while(!operators.isEmpty()) {
				int b = numbers.pop();
				int a = numbers.pop();
				String op = operators.pop();
				numbers.push(operate(a, b, op));
			}
			if(numbers.pop() == target) { 
				result.add(sb.toString());
			}
			return;
		}
		Integer head = 0;
		for(int i=index; i<num.length(); i++) {
			head = head * 10 + (num.charAt(i) - '0');
			if(index == 0) {
				expression.add(head);
				helper(num, i+1, target);
				expression.remove(expression.size()-1);
			}else {
				for(int j = 0; j<operas.length; j++) {
					expression.add(operas[j]);
					expression.add(head);
					helper(num, i+1, target);
					expression.remove(expression.size()-1);
					expression.remove(expression.size()-1);
				}
				if(head != 0) {
					expression.add("/");
					expression.add(head);
					helper(num, i+1, target);
					expression.remove(expression.size()-1);
					expression.remove(expression.size()-1);
				}
			}
			if(head == 0) {
				break;
			}
		}
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
	
	private boolean compare(String o1, String o2) {
		if(o1.equals("+") || o1.equals("-")) {
			return o2.equals("+") || o2.equals("-");
		}else {
			return true;
		}
	}
	
	/**
	 * 因为要输出所有可能的情况，必定是用深度优先搜索。问题在于如何将问题拆分成多次搜索。加减法很好处理，每当我们截出一段数字时，将之前计算的结果加上或者减去这个数，就可以将剩余的数字字符串和新的计算结果代入下一次搜索中了，直到我们的计算结果和目标一样，就完成了一次搜索。然而，乘法如何处理呢？这里我们需要用一个变量记录乘法当前累乘的值，直到累乘完了，遇到下一个加号或减号再将其算入计算结果中。这里有两种情况:

    乘号之前是加号或减号，例如2+3*4，我们在2那里算出来的结果，到3的时候会加上3，计算结果变为5。在到4的时候，因为4之前我们选择的是乘号，这里3就应该和4相乘，而不是和2相加，所以在计算结果时，要将5先减去刚才加的3得到2，然后再加上3乘以4，得到2+12=14，这样14就是到4为止时的计算结果。

    另外一种情况是乘号之前也是乘号，如果2+3*4*5，这里我们到4为止计算的结果是14了，然后我们到5的时候又是乘号，这时候我们要把刚才加的3*4给去掉，然后再加上3*4*5，也就是14-3*4+3*4*5=62。这样5的计算结果就是62。

因为要解决上述几种情况，我们需要这么几个变量，一个是记录上次的计算结果currRes，一个是记录上次被加或者被减的数prevNum，一个是当前准备处理的数currNum。当下一轮搜索是加减法时，prevNum就是简单换成currNum，当下一轮搜索是乘法时，prevNum是prevNum乘以currNum。
	 * @param target
	 * @param index
	 * @param num
	 * @param expression
	 * @param current
	 * @param pre
	 * 
	 */
	private void helper(int target, int index, String num, String expression, int current, int pre) {
		if(current == target && index >= num.length()) {
			result.add(expression);
		}
		int head = 0;
		for(int i=index; i<num.length(); i++) {
			head = head * 10 + (num.charAt(i) - '0');
			if(index == 0) { //这里的pre必须是head
				helper(target, i + 1, num, expression + head, head, head);
			}else {
				helper(target, i + 1, num, expression + "+" + head, current + head, head);
				helper(target, i + 1, num, expression + "-" + head, current - head, -head);
				helper(target, i + 1, num, expression + "*" + head, current- pre + pre * head, pre * head);
			}
			if(head == 0) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		print(new 添加运算符().addOperators("123", 6));
		//print(34/5+62*37*4+9+0);
	}

}
