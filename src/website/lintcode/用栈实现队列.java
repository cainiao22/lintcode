package website.lintcode;

import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017年9月18日 下午5:29:15
 * @description
 * 		正如标题所述，你需要使用两个栈来实现队列的一些操作。

		队列应支持push(element)，pop() 和 top()，其中pop是弹出队列中的第一个(最前面的)元素。
		
		pop和top方法都应该返回第一个元素的值。
		
 * @example
 * 		比如push(1), pop(), push(2), push(3), top(), pop()，你应该返回1，2和2
 *
 * @Solution
 */
public class 用栈实现队列 {
	
	private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public 用栈实现队列() {
        // do intialization if necessary
    	stack1 = new Stack<>();
    	stack2 = new Stack<>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        // write your code here
    	while(!stack2.isEmpty()) {
    		stack1.push(stack2.pop());
    	}
    	stack1.push(element);
    	while(!stack1.isEmpty()) {
    		stack2.push(stack1.pop());
    	}
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
    	return stack2.pop();
    }

    /*
     * @return: An integer
     */
    public int top() {
        // write your code here
    	return stack2.peek();
    }
    
    /**
     * 答案from九章
     * @author yanpf
     * @date 2017年9月18日 下午5:53:23
     * @description
     * @example
     *
     * @Solution 这个方式比较上一个而言，可以实现类似懒加载的功能。
     * 			 加进来的数据首先入stack2,只要没有pop或者peek，数据就一直放在stack2中。
     * 			 但是一旦调用了pop或者peek，数据之间从stack2翻转到了stack1中，
     *           重点是这里，当再次调用push时候，stack1不动。数据仍然放入stack2，直到
     *           stack1中的数据光了。才到stack2中去翻转
     *           这样还真的可以做到均摊为O(1)
     */
    public class MyQueue {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        public MyQueue() {
           // do initialization if necessary
           stack1 = new Stack<Integer>();
           stack2 = new Stack<Integer>();
        }
        
        private void stack2ToStack1(){
            while(! stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
        }
        
        public void push(int element) {
            // write your code here
            stack2.push(element);
        }

        public int pop() {
            // write your code here
            if(stack1.empty() == true){
                this.stack2ToStack1();
            }
            return stack1.pop();
        }

        public int top() {
            // write your code here
            if(stack1.empty() == true){
                this.stack2ToStack1();
            }
            return stack1.peek();
        }
    }

}
