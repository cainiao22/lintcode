package website.lintcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017��11��21�� ����12:22:58
 * @description
 * 		
		�������һ���������Ľṹ�����ں����ض��ı����б������Ҷ�Ӷ���һ�������ַ���ֵ�������б�����ķ�Ҷ�Ӷ�����һ�������ַ���ֵ��
		����һ��������飬�빹��ñ��ı�����������ظñ�����ĸ���

 * @example
 * 		���� (2*6-(23+7)/(1+2)) �ı��ɱ�ʾΪ ["2" "*" "6" "-" "(" "23" "+" "7" ")" "/" "(" "1" "+" "2" ")"]).
		���������£�
		
		                 [ - ]
		             /          \
		        [ * ]              [ / ]
		      /     \           /         \
		    [ 2 ]  [ 6 ]      [ + ]        [ + ]
		                     /    \       /      \
		                   [ 23 ][ 7 ] [ 1 ]   [ 2 ] .
		
		�ڹ���ñ��������ֻ�践�ظ��ڵ�[-]��
 *
 * @Solution
 */
public class ��������� extends HH {
	
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
	 * �����㷨�Ľ���˼·�����������������һͬ����
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
		System.out.println(new ���������().build(expression));
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
