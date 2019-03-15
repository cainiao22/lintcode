package website.lintcode;

import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2019年3月15日 下午3:43:35
 * @description
 * 		请设计一种方法将一个栈进行升序排列 （最大的数在最上面）。
		你可以使用另外一个栈来辅助操作，但不可将这些数复制到另外一个数据结构中 （如，数组）。
		
 * @example
 *		给一个栈：
		
		| |
		|3|
		|1|
		|2|
		|4|
		 -
		排序之后：
		
		| |
		|4|
		|3|
		|2|
		|1|
		 -
		栈会被序列化为[4,2,1,3]，也就是说最右边是栈顶。
 * @Solution
 */
public class 栈排序 {
	
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
