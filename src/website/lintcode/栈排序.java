package website.lintcode;

import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2019��3��15�� ����3:43:35
 * @description
 * 		�����һ�ַ�����һ��ջ������������ ���������������棩��
		�����ʹ������һ��ջ�����������������ɽ���Щ�����Ƶ�����һ�����ݽṹ�� ���磬���飩��
		
 * @example
 *		��һ��ջ��
		
		| |
		|3|
		|1|
		|2|
		|4|
		 -
		����֮��
		
		| |
		|4|
		|3|
		|2|
		|1|
		 -
		ջ�ᱻ���л�Ϊ[4,2,1,3]��Ҳ����˵���ұ���ջ����
 * @Solution
 */
public class ջ���� {
	
	public void stackSorting(Stack<Integer> stk) {
        // write your code here
		Stack<Integer> another = new Stack<>();
		while(!stk.isEmpty()) {
			int top = stk.pop();
			while(!another.isEmpty() && another.peek() < top) {
				stk.push(another.pop());
			}
			another.push(top);
		}
		
		while(!another.isEmpty()) {
			stk.push(another.pop());
		}
    }

}
