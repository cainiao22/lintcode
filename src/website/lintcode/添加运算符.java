package website.lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017��11��10�� ����3:15:18
 * @description
 * 		����һ������������ 0 - 9 ���ַ�����һ��Ŀ��ֵ��
 * 		����������֮������� ��Ԫ �����(����һԪ)+, - �� * ֮�������ܵõ�Ŀ��ֵ�������
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
public class �������� extends HH {
	
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
	 * ̫��ʱ�ˣ���ֱ���Ǳ����ⷨ
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
	 * ��ΪҪ������п��ܵ�������ض����������������������������ν������ֳɶ���������Ӽ����ܺô���ÿ�����ǽس�һ������ʱ����֮ǰ����Ľ�����ϻ��߼�ȥ��������Ϳ��Խ�ʣ��������ַ������µļ�����������һ���������ˣ�ֱ�����ǵļ�������Ŀ��һ�����������һ��������Ȼ�����˷���δ����أ�����������Ҫ��һ��������¼�˷���ǰ�۳˵�ֵ��ֱ���۳����ˣ�������һ���ӺŻ�����ٽ�������������С��������������:

    �˺�֮ǰ�ǼӺŻ���ţ�����2+3*4��������2����������Ľ������3��ʱ������3����������Ϊ5���ڵ�4��ʱ����Ϊ4֮ǰ����ѡ����ǳ˺ţ�����3��Ӧ�ú�4��ˣ������Ǻ�2��ӣ������ڼ�����ʱ��Ҫ��5�ȼ�ȥ�ղżӵ�3�õ�2��Ȼ���ټ���3����4���õ�2+12=14������14���ǵ�4Ϊֹʱ�ļ�������

    ����һ������ǳ˺�֮ǰҲ�ǳ˺ţ����2+3*4*5���������ǵ�4Ϊֹ����Ľ����14�ˣ�Ȼ�����ǵ�5��ʱ�����ǳ˺ţ���ʱ������Ҫ�Ѹղżӵ�3*4��ȥ����Ȼ���ټ���3*4*5��Ҳ����14-3*4+3*4*5=62������5�ļ���������62��

��ΪҪ����������������������Ҫ��ô����������һ���Ǽ�¼�ϴεļ�����currRes��һ���Ǽ�¼�ϴα��ӻ��߱�������prevNum��һ���ǵ�ǰ׼���������currNum������һ�������ǼӼ���ʱ��prevNum���Ǽ򵥻���currNum������һ�������ǳ˷�ʱ��prevNum��prevNum����currNum��
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
			if(index == 0) { //�����pre������head
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
		print(new ��������().addOperators("123", 6));
		//print(34/5+62*37*4+9+0);
	}

}
