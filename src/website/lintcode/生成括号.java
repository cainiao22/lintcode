package website.lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * 
 * @date 2017年10月25日 下午2:18:24
 * 
 * @description 给定 n 对括号，请写一个函数以将其生成新的括号组合，并返回所有组合结果。
 * 
 * @example 给定 n = 3, 可生成的组合如下: "((()))", "(()())", "(())()", "()(())", "()()()"
 *
 * @Solution 类似全排列的解决方案。在遍历过程中去判断是否符合条件
 * 			   改进:将判断那个步骤省略。或者说放入全排列的过程中。只要保证左括号的剩余数量大于右括号。见{@link website.lintcode.生成括号#generateParenthesisFromJiuZhang}
 */
public class 生成括号 extends HH {

	private List<String> result = new ArrayList<>();
	private int leftCount = 0;
	private int rightCount = 0;

	public List<String> generateParenthesis(int n) {
		// write your code here
		leftCount = rightCount = n;
		StringBuffer item = new StringBuffer();
		helper(0, n*2, item);
		return result;
	}

	private void helper(int index, int n, StringBuffer item) {
		if (index == n) {
			if(check(item)) {
				result.add(item.toString());
			}
		}
		if (leftCount != 0) {
			leftCount--;
			item.append("(");
			helper(index + 1, n, item);
			leftCount++;
			item.setLength(item.length() - 1);
		}
		if (rightCount != 0) {
			rightCount--;
			item.append(")");
			helper(index + 1, n, item);
			rightCount++;
			item.setLength(item.length() - 1);
		}

	}
	
	private Stack<Integer> stack = new Stack<>();
	private boolean check(StringBuffer item) {
		stack.clear();
		for(int i=0; i<item.length(); i++) {
			if(item.charAt(i) == '(') {
				stack.push(1);
			}else {
				if(stack.isEmpty()) {
					return false;
				}
				stack.pop();
			}
		}
		return stack.isEmpty();
	}
	
	public ArrayList<String> generateParenthesisFromJiuZhang(int n) {
        ArrayList<String> result = new ArrayList<String>();
        if (n <= 0) {
            return result;
        }
        helper(result, "", n, n);
        return result;
    }
    
	public void helper(ArrayList<String> result,
	                   String paren, // current paren
	                   int left,     // how many left paren we need to add
	                   int right) {  // how many right paren we need to add
		if (left == 0 && right == 0) {
			result.add(paren);
			return;
		}

        if (left > 0) {
		    helper(result, paren + "(", left - 1, right);
        }
        //这里比较重要
        if (right > 0 && left < right) {
		    helper(result, paren + ")", left, right - 1);
        }
	}
	
	public static void main(String[] args) {
		List<String> result = new 生成括号().generateParenthesis(3);
		println(result);
	}

}
