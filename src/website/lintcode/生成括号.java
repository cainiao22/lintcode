package website.lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * 
 * @date 2017��10��25�� ����2:18:24
 * 
 * @description ���� n �����ţ���дһ�������Խ��������µ�������ϣ�������������Ͻ����
 * 
 * @example ���� n = 3, �����ɵ��������: "((()))", "(()())", "(())()", "()(())", "()()()"
 *
 * @Solution ����ȫ���еĽ���������ڱ���������ȥ�ж��Ƿ��������
 * 			   �Ľ�:���ж��Ǹ�����ʡ�ԡ�����˵����ȫ���еĹ����С�ֻҪ��֤�����ŵ�ʣ���������������š���{@link website.lintcode.��������#generateParenthesisFromJiuZhang}
 */
public class �������� extends HH {

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
        //����Ƚ���Ҫ
        if (right > 0 && left < right) {
		    helper(result, paren + ")", left, right - 1);
        }
	}
	
	public static void main(String[] args) {
		List<String> result = new ��������().generateParenthesis(3);
		println(result);
	}

}
