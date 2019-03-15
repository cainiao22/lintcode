package website.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * @author yanpf
 * @date 2017��12��26�� ����3:18:05
 * @description
 * 		������һ���� N ���ڵ��������, ���Ϊ 1 �� N, ÿһ���ڵ㶼��һ��int���͵�ֵ����ͬ�Ľڵ��������ͬ��ֵ��
 * 		��һ������ΪN������A��A[j]��ʾ��j + 1���ڵ��ֵ���ٸ�һ������Ϊ (N - 1) * 2 ������ E,��������� 0 <= j <= N - 2 ���� E[2 * j], 
 * 		E[2 * j + 1]��ʾ�ڵ� E[2 * j] ��ڵ� E[2 * j + 1]�б����������ؾ�����ֵͬ�Ľڵ㹹�ɵ��·���ĳ��ȣ�·���ĳ���Ϊ·���ߵ�������
 * 
 * @example
 * 		���� A = [1, 1, 1 ,2, 2] �� E = [1, 2, 1, 3, 2, 4, 2, 5]
		����������������:
		
		                   1 ��value = 1��
		                 /   \
		    (value = 1) 2     3 (value = 1)
		               /  \
		 (value = 2)  4     5 (value = 2)
		
		��д�ĳ�����Ҫ���� 2����Ϊ�·��Ϊ 2 -> 1 -> 3(���нڵ��ֵ��Ϊ1)������·���ıߵ�����Ϊ 2,���Դ��� 2
 *
 * @Solution
 */
public class TreeLongestPathWithSameValue extends HH {
	
	static class Node {
		int value;
		List<Node> children = new ArrayList<>();
		int path;
		
		public Node(int value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return value + "";
		}
	}
	
	//TODO ��bug
	public int LongestPathWithSameValue(int[] A, int[] E) {
        // write your code here
		Map<Integer, Node> map = new HashMap<>();
		for(int i = 0; i<A.length; i++) {
			Node node = new Node(A[i]);
			map.put(i+1, node);
		}
		
		for(int i=0; i<A.length; i++) {
			if(i*2+1 < E.length) {
				map.get(E[i*2]).children.add(map.get(E[i*2+1]));
			}
		}
		
		Node root = map.get(1);
		helper(root);
		
		int max = 0;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			max = Integer.max(max, current.path);
			queue.addAll(current.children);
		}
		
		return max;	
    }

	private int helper(Node root) {
		if(root == null) {
			return 0;
		}
		int max = 0, second = 0;
		for(Node child : root.children) {
			if(child.value == root.value) {
				int current = helper(child) + 1;
				if(current > max) {
					second = max;
					max = current;
				}else if(current > second) {
					second = current;
				}
			}
		}
		
		root.path = max + second;
		
		return max;
	}
	
	/**
	 * 
	 * @param A
	 * @param E
	 * @return
	 */
	
	private int ans = 0;
	public int LongestPathWithSameValueFromJiuZhang(int[] A, int[] E) {
		List<List<Integer>> ch = new ArrayList<>();
		for(int i=0; i<=A.length; i++) {
			ch.add(new ArrayList<>());
		}
		for(int i=0; i<A.length - 1; i++) {
			ch.get(E[i*2]).add(E[i*2+1]);
			ch.get(E[i*2 + 1]).add(E[i*2]);
		}
		
		int temp = dfs(1, 0, A, ch);
		ans = Math.max(temp, ans);
		return ans - 1;
		
	}
	
	private int dfs(int index, int parent, int[] a, List<List<Integer>> ch) {
		List<Integer> v = new ArrayList<>();
		for(int son : ch.get(index)) {
			if(son != parent) { //ȥ�����Լ�
				if(a[son-1] == a[index-1]) {
					v.add(dfs(son, index, a, ch));
				}else {
					dfs(son, index, a, ch);
				}
			}
		}
		
		v.add(0);
		v.add(0);
		Collections.sort(v);
		Collections.reverse(v);
		ans = Math.max(ans, v.get(0) + v.get(1) + 1);
		return v.get(0) + 1;
	}
	
	public static void main(String[] args) {
		int[] A = {1,1,1,1,1};
		int[] E = {1, 2, 1, 3, 2, 4, 2, 5};
		
		print(new TreeLongestPathWithSameValue().LongestPathWithSameValueFromJiuZhang(A, E));
	}
}
