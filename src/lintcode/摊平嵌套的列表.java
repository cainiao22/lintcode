package lintcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017��8��16�� ����3:56:16
 * @description ����һ��Ƕ�׵��б�ʵ��һ������������̯ƽ��һ���б��ÿ��Ԫ�ؿ�������������һ���б�
 * 
 * @example �����б� [[1,1],2,[1,1]]������������֮�󷵻� [1,1,2,1,1]�������б�
 *          [1,[4,[6]]]������������֮�󷵻� [1,4,6]
 *
 * @Solution �ݹ飬�޷��ǳ�ʼ����ʱ���̯ƽ���ǻ�ȡ��ʱ��̯ƽ����ʼ��̯ƽ����ȡ�Ͽ죬��ȡ��ʱ��̯ƽ����ӽϿ�
 */
public class ̯ƽǶ�׵��б� {

}

/**
 * ��ӵ�ʱ���������ȡ��ʱ��Ͽ�
 * @author yanpf
 * @date 2017��8��16�� ����4:43:50
 * @description
 * @example
 *
 * @Solution
 */
class NestedIterator implements Iterator<Integer> {

	private Integer next;

	private List<NestedInteger> it = new ArrayList<>();

	/** �����һ������ֱ��̯ƽ **/
	public NestedIterator(List<NestedInteger> nestedList) {
		// Initialize your data structure here.
		if (nestedList != null && !nestedList.isEmpty()) {
			while (!nestedList.isEmpty()) {
				NestedInteger nestedInteger = nestedList.remove(0);
				dfs(nestedInteger);
			}
		}
	}

	private void dfs(NestedInteger nestedInteger) {
		if (nestedInteger != null && nestedInteger.isInteger()) {
			it.add(nestedInteger);
		} else {
			List<NestedInteger> list = nestedInteger.getList();
			while (list != null && !list.isEmpty()) {
				NestedInteger temp = list.remove(0);
				dfs(temp);
			}
		}
	}

	// @return {int} the next element in the iteration
	@Override
	public Integer next() {
		// Write your code here
		int temp = next;
		next = null;
		return temp;
	}

	// @return {boolean} true if the iteration has more element or false
	@Override
	public boolean hasNext() {
		if (it.isEmpty()) {
			return false;
		}
		next = it.remove(0).getInteger();
		return true;
	}

	@Override
	public void remove() {
	}
}
/**
 * �����Ƿŵ���ջ�У�����������̯ƽ����ȡ��ʱ�������������ӵ�ʱ��Ͽ�
 * @author yanpf
 * @date 2017��8��16�� ����4:40:10
 * @description
 * @example
 *
 * @Solution
 */
class NestedIterator2 implements Iterator<Integer> {

	private Stack<NestedInteger> stack;

	private void pushListToStack(List<NestedInteger> nestedList) {

		Stack<NestedInteger> temp = new Stack<>();

		for (NestedInteger nested : nestedList) {

			temp.push(nested);

		}

		while (!temp.isEmpty()) {

			stack.push(temp.pop());

		}

	}

	public NestedIterator2(List<NestedInteger> nestedList) {

		stack = new Stack<>();

		pushListToStack(nestedList);

	}

	// @return {int} the next element in the iteration

	@Override

	public Integer next() {

		if (!hasNext()) {

			return null;

		}

		return stack.pop().getInteger();

	}

	// @return {boolean} true if the iteration has more element or false

	@Override
	/** �������ڱ���������ȥ̯ƽ **/
	public boolean hasNext() {

		while (!stack.isEmpty() && !stack.peek().isInteger()) {

			pushListToStack(stack.pop().getList());

		}

		return !stack.isEmpty();

	}

	@Override

	public void remove() {
	}

}

interface NestedInteger {
	// @return true if this NestedInteger holds a single integer,
	// rather than a nested list.
	public boolean isInteger();

	// @return the single integer that this NestedInteger holds,
	// if it holds a single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger();

	// @return the nested list that this NestedInteger holds,
	// if it holds a nested list
	// Return null if this NestedInteger holds a single integer
	public List<NestedInteger> getList();
}
