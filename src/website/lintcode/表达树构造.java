package website.lintcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017年11月21日 下午12:22:58
 * @description
 * 		
		表达树是一个二叉树的结构，用于衡量特定的表达。所有表达树的叶子都有一个数字字符串值。而所有表达树的非叶子都有另一个操作字符串值。
		给定一个表达数组，请构造该表达的表达树，并返回该表达树的根。

 * @example
 * 		对于 (2*6-(23+7)/(1+2)) 的表达（可表示为 ["2" "*" "6" "-" "(" "23" "+" "7" ")" "/" "(" "1" "+" "2" ")"]).
		其表达树如下：
		
		                 [ - ]
		             /          \
		        [ * ]              [ / ]
		      /     \           /         \
		    [ 2 ]  [ 6 ]      [ + ]        [ + ]
		                     /    \       /      \
		                   [ 23 ][ 7 ] [ 1 ]   [ 2 ] .
		
		在构造该表达树后，你只需返回根节点[-]。
 *
 * @Solution
 */
public class 表达树构造 extends HH {
	
	public ExpressionTreeNode build(String[] expression) {
		Stack<String> operators = new Stack<>();
		Stack<ExpressionTreeNode> numbers = new Stack<>();
		for(String item : expression) {
			if(isNumberic(item)) {
				numbers.push(new ExpressionTreeNode(item));
			}else if(item.equals("(")) {
				operators.push(item);
			}else if(item.equals(")")){
				String op = operators.pop();
				while(op != "(") {
					ExpressionTreeNode b = numbers.pop();
					ExpressionTreeNode a = numbers.pop();
					ExpressionTreeNode c = new ExpressionTreeNode(op);
					c.left = a;
					c.right = b;
					numbers.push(c);
					op = operators.pop();
				}
			}else {
				if(operators.isEmpty()) {
					operators.push(item);
				}else {
					while(!operators.isEmpty() && compare(operators.peek(), item) >= 0) {
						ExpressionTreeNode b = numbers.pop();
						ExpressionTreeNode a = null;
						if(!numbers.isEmpty()) {
							a = numbers.pop();
						}
						ExpressionTreeNode c = new ExpressionTreeNode(operators.pop());
						c.left = a;
						c.right = b;
						numbers.push(c);
					}
					operators.push(item);
				}
			}
		}
		while(!operators.isEmpty()) {
			ExpressionTreeNode b = numbers.pop();
			ExpressionTreeNode a = numbers.pop();
			ExpressionTreeNode c = new ExpressionTreeNode(operators.pop());
			c.left = a;
			c.right = b;
			numbers.push(c);
			
		}
		return numbers.pop();
	}
	
	private Map<String, Integer> map = new HashMap<String, Integer>() {
		{
			put("(", 0);
			put("+", 1);
			put("-", 1);
			put("*", 2);
			put("/", 2);
		}
	};
	private int compare(String symbol, String item) {
		return map.get(symbol) - map.get(item);
	}

	private boolean isNumberic(String item) {
		return item.charAt(0) <= '9' && item.charAt(0) >= '0';
	}
	
	/**
	 * 九章算法的解题思路，将数字与操作符号一同处理
	 * @param expression
	 * @return
	 */
	public ExpressionTreeNode buildFromJiuZhang(String[] expression) {
		int weight = 0;
		Stack<TreeNode> stack = new Stack<>();
		for(String op : expression) {
			if(op.equals("(")) {
				weight += 10;
				continue;
			}
			if(op.equals(")")) {
				weight -= 10;
				continue;
			}
			int val = getWeight(op, weight);
			TreeNode node = new TreeNode(op, val);
			while(!stack.isEmpty() && stack.peek().val >= val) {
				node.eNode.left = stack.pop().eNode;
			}
			if(!stack.isEmpty()) {
				stack.peek().eNode.right = node.eNode;
			}
		}
		ExpressionTreeNode result = null;
		while(!stack.isEmpty()) {
			result = stack.pop().eNode;
		}
		return result;
	}
	
	private int getWeight(String op, int weight) {
		if(op.equals("+") || op.equals("-")) {
			return weight + 1;
		}
		if(op.equals("*") || op.equals("/")) {
			return weight + 2;
		}
		return Integer.MAX_VALUE;
	}

	public static void main(String[] args) {
		String[] expression = new String[] {"2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"};
		System.out.println(new 表达树构造().build(expression));
	}
	

}

class ExpressionTreeNode {
	public String symbol;
	public ExpressionTreeNode left, right;

	public ExpressionTreeNode(String symbol) {
		this.symbol = symbol;
		this.left = this.right = null;
	}
	@Override
	public String toString() {
		StringBuffer sBuffer = new StringBuffer();
		if(left != null) {
			sBuffer.append("[").append(left.toString()).append("]");
		}
		sBuffer.append(symbol);
		if(right != null) {
			sBuffer.append("[").append(right.toString()).append("]");
		}
		return sBuffer.toString();
	}
}

class TreeNode {
	public ExpressionTreeNode eNode;
	public int val;
	
	public TreeNode(String symbol, int val) {
		super();
		this.eNode = new ExpressionTreeNode(symbol);
		this.val = val;
	}
	
	@Override
	public String toString() {
		return "val:" + val + "," + eNode.toString();
	}
	
}
