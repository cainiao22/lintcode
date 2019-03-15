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
 * @date 2017年12月26日 下午3:18:05
 * @description
 * 		假设有一棵有 N 个节点的无向树, 编号为 1 到 N, 每一个节点都有一个int类型的值，不同的节点可以有相同的值。
 * 		给一个长度为N的数组A，A[j]表示第j + 1个节点的值。再给一个长度为 (N - 1) * 2 的数组 E,对于任意的 0 <= j <= N - 2 都有 E[2 * j], 
 * 		E[2 * j + 1]表示节点 E[2 * j] 与节点 E[2 * j + 1]有边相连。返回具有相同值的节点构成的最长路劲的长度，路劲的长度为路径边的数量。
 * 
 * @example
 * 		给出 A = [1, 1, 1 ,2, 2] 和 E = [1, 2, 1, 3, 2, 4, 2, 5]
		描述了下面的这棵树:
		
		                   1 （value = 1）
		                 /   \
		    (value = 1) 2     3 (value = 1)
		               /  \
		 (value = 2)  4     5 (value = 2)
		
		你写的程序需要返回 2，因为最长路径为 2 -> 1 -> 3(所有节点的值均为1)。这条路径的边的数量为 2,所以答案是 2
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
	
	//TODO 有bug
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
			if(son != parent) { //去除他自己
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
