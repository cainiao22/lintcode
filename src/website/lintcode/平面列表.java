package website.lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2018��1��8�� ����3:27:25
 * @description
 * 		����һ���б����б��е�ÿ��Ҫ��Ҫô�Ǹ��б�Ҫô��������������һ��ֻ���������ļ��б�.
 * 		���÷ǵݹ鷽�����Խ�������
 * @example
 * 		���� [1,2,[1,2]]������ [1,2,1,2]��
		���� [4,[3,[2,[1]]]]������ [4,3,2,1]
 *
 * @Solution
 */
public class ƽ���б� extends HH {
	
	public class NestedInteger {

		private List<NestedInteger> list;
		private Integer num;

		// @return true if this NestedInteger holds a single integer,
		// rather than a nested list.
		public boolean isInteger() {
			return num != null;
		};

		// @return the single integer that this NestedInteger holds,
		// if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger() {
			return num;
		}

		// @return the nested list that this NestedInteger holds,
		// if it holds a nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList() {
			return list;
		}
	}

	public List<Integer> flatten(List<NestedInteger> nestedList) {
		// Write your code here
		List<Integer> result = new ArrayList<>();
		for(NestedInteger item : nestedList) {
			if(item.isInteger()) {
				result.add(item.getInteger());
			}else {
				result.addAll(flatten(item.getList()));
			}
		}
		
		return result;

	}
	
	public List<Integer> flattenIterator(List<NestedInteger> nestedList) {
		// Write your code here
		List<Integer> result = new ArrayList<>();
		Stack<NestedInteger> stack = new Stack<>();
		for(int i=nestedList.size() - 1; i>= 0 ; i--) {
			stack.push(nestedList.get(i));
		}
		
		while(!stack.isEmpty()) {
			NestedInteger top = stack.pop();
			if(top.isInteger()) {
				result.add(top.getInteger());
			}else {
				for(int i=top.getList().size() - 1; i>= 0 ; i--) {
					stack.push(top.getList().get(i));
				}
			}
		}
		
		return result;

	}
	
	/**
	 * ÿ�ζ����������������list�ľ�չ��һ�㡣�����ñ�ǣ�����������ܻ���list,��Ҫ�ٴα�����
	 * @param nestedList
	 * @return
	 */
	public List<Integer> flattenIteratorFromJiuZhang(List<NestedInteger> nestedList) {
        boolean isFlat = true;
        List<NestedInteger> ls = nestedList;
        while (isFlat) {
            isFlat = false;
            List<NestedInteger> newLs = new ArrayList<>();
            for (NestedInteger ni : ls) {
                if (ni.isInteger()) {
                    newLs.add(ni);
                } else {
                    newLs.addAll(ni.getList());
                    isFlat = true;
                }
            }
            ls = newLs;
        }
        List<Integer> r = new ArrayList<>();
        for (NestedInteger ni : ls) {
            r.add(ni.getInteger());
        }
        return r;
    }
	

}
