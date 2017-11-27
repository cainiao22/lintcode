package website.lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017年11月21日 下午12:11:06
 * @description
 * 		实现一个带有取最小值min方法的栈，min方法将返回当前栈中的最小值。
		你实现的栈将支持push，pop 和 min 操作，所有操作要求都在O(1)时间内完成。
 * @example
 * 		如下操作：push(1)，pop()，push(2)，push(3)，min()， push(1)，min() 返回 1，2，1
 *
 * @Solution
 */
public class MinStack {
	
	private List<Integer> minValues = new ArrayList<>();
	private List<Integer> values = new ArrayList<>();
	
    public MinStack() {
        // do intialization if necessary
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        // write your code here
    	values.add(number);
    	if(minValues.isEmpty()) {
    		minValues.add(number);
    	}else {
    		int min = minValues.get(minValues.size() - 1);
    		minValues.add(Integer.min(min, number));
    	}
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
    	minValues.remove(minValues.size() - 1);
    	return values.remove(values.size() - 1);
    }

    /*
     * @return: An integer
     */
    public int min() {
        // write your code here
    	return minValues.get(minValues.size() - 1);
    }
}

class MinStack2 {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack2() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int number) {
        stack.push(number);
        if (minStack.empty() == true)
            minStack.push(number);
        else {
        // 这里考虑的相等的情况也会继续push
        //似乎和单调栈有点像
        if (minStack.peek() >= number)
            minStack.push(number);
        }
    }

    public int pop() {
        if (stack.peek().equals(minStack.peek()) ) 
            minStack.pop();
        return stack.pop();
    }

    public int min() {
        return minStack.peek();
    }
}
