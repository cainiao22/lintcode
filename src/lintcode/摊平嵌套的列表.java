package lintcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017年8月16日 下午3:56:16
 * @description 给你一个嵌套的列表，实现一个迭代器将其摊平。一个列表的每个元素可能是整数或者一个列表。
 * 
 * @example 给出列表 [[1,1],2,[1,1]]，经过迭代器之后返回 [1,1,2,1,1]。给出列表
 *          [1,[4,[6]]]，经过迭代器之后返回 [1,4,6]
 *
 * @Solution 递归，无非是初始化的时候就摊平还是获取的时候摊平，初始化摊平。读取较快，获取的时候摊平，添加较快
 */
public class 摊平嵌套的列表 {

}

/**
 * 添加的时候较慢，获取的时候较快
 * @author yanpf
 * @date 2017年8月16日 下午4:43:50
 * @description
 * @example
 *
 * @Solution
 */
class NestedIterator implements Iterator<Integer> {

	private Integer next;

	private List<NestedInteger> it = new ArrayList<>();

	/** 这个是一进来就直接摊平 **/
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
 * 这种是放到堆栈中，迭代过程中摊平，获取的时候较慢，但是添加的时候较快
 * @author yanpf
 * @date 2017年8月16日 下午4:40:10
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
	/** 这种是在遍历过程中去摊平 **/
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
