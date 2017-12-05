package website.lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017��11��27�� ����5:11:01
 * @description
 * 		�������������n��k�����ش�1......n��ѡ����k��������ϡ�
 * @example
 * 		���� n = 4 �� k = 2

		���صĽ�Ϊ��
		
		[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
 *
 * @Solution 1���ݹ�
 * 			 2������
 */
public class ��� extends HH {
	
	private static List<List<Integer>> result = new ArrayList<>();
	private static List<Integer> item = new ArrayList<>();
	
	/**
	 * �ݹ�
	 * @param n
	 * @param k
	 * @return
	 */
	public static List<List<Integer>> combine(int n, int k) {
        // write your code here
		if(k == 0) {
			List<Integer> list = new ArrayList<>(item);
			result.add(list);
			return result;
		}
		for(int i=n; i>=k; i--) {
			item.add(i);
			combine(i-1, k-1);
			item.remove(item.size() - 1);
		}
		return result;
    }
	
	/**
	 * ����
	 * @param n
	 * @param k
	 * @return
	 */
	public static List<List<Integer>> combineIterator(int n, int k) {
		Stack<Integer> stack = new Stack<>();
		for(int i=1; i<=k;i++) {
			stack.push(i);
		}
		result.add(new ArrayList<>(stack));
		while(!stack.isEmpty()) {
			while(!stack.isEmpty() && (n - stack.peek() <= k - stack.size())) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				break;
			}
			int head = stack.pop();
			stack.push(head + 1);
			while(stack.size() < k) {
				stack.push(stack.peek() + 1);
			}
			result.add(new ArrayList<>(stack));
		}
		return result;
	}
	
	public static void main(String[] args) {
		combineIterator(4, 2);
		for(List<Integer> item : result) {
			print(item);
		}
	}

}
