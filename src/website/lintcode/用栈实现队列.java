package website.lintcode;

import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017��9��18�� ����5:29:15
 * @description
 * 		�����������������Ҫʹ������ջ��ʵ�ֶ��е�һЩ������

		����Ӧ֧��push(element)��pop() �� top()������pop�ǵ��������еĵ�һ��(��ǰ���)Ԫ�ء�
		
		pop��top������Ӧ�÷��ص�һ��Ԫ�ص�ֵ��
		
 * @example
 * 		����push(1), pop(), push(2), push(3), top(), pop()����Ӧ�÷���1��2��2
 *
 * @Solution
 */
public class ��ջʵ�ֶ��� {
	
	private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public ��ջʵ�ֶ���() {
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
     * ��from����
     * @author yanpf
     * @date 2017��9��18�� ����5:53:23
     * @description
     * @example
     *
     * @Solution �����ʽ�Ƚ���һ�����ԣ�����ʵ�����������صĹ��ܡ�
     * 			 �ӽ���������������stack2,ֻҪû��pop����peek�����ݾ�һֱ����stack2�С�
     * 			 ����һ��������pop����peek������֮���stack2��ת����stack1�У�
     *           �ص���������ٴε���pushʱ��stack1������������Ȼ����stack2��ֱ��
     *           stack1�е����ݹ��ˡ��ŵ�stack2��ȥ��ת
     *           ��������Ŀ���������̯ΪO(1)
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
