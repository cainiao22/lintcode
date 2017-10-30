package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017��10��24�� ����3:40:38
 * @description ����һ������ͼ��ͼ�ڵ���������򱻶���Ϊ��
 * 
 *              ����ÿ�������A--> B����A��������B֮ǰ ��������ĵ�һ���ڵ�������κ���ͼ��û�������ڵ�ָ�����Ľڵ�
 * 
 *              �ҵ�����ͼ����һ�������� �ܷ�ֱ���BFS��DFS��ɣ�
 *              
 *              ע��:�������graph list�����е�node�ڵ�
 * @example
 *
 * @Solution
 * 		 �������нڵ�����
		 ѡ��һ�����Ϊ��Ľڵ�������������ӽڵ����ȼ�1
		 ѭ��ֱ�����нڵ����
		 
		 dfs�������档
		 bfsֻ�ǽ�stack���queue
 */
public class �������� {

	public static class DirectedGraphNode {
		int label;
		ArrayList<DirectedGraphNode> neighbors;

		DirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<DirectedGraphNode>();
		}
	}

	public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		// write your code here
		return dfs(graph);
	}
	
	//todo �����  Ϊë��
	public ArrayList<DirectedGraphNode> dfsError(ArrayList<DirectedGraphNode> graph) {
		ArrayList<DirectedGraphNode> result = new ArrayList<>();
		Stack<DirectedGraphNode> stack = new Stack<>();
		Set<DirectedGraphNode> set = new HashSet<>();
		if (graph != null) {
			for (DirectedGraphNode node : graph) {
				if (node != null && !set.contains(node)) {
					stack.push(node);
					set.add(node);
				}
			}
		}

		while (!stack.isEmpty()) {
			DirectedGraphNode top = stack.pop();
			result.add(top);
			if (top.neighbors != null) {
				for (DirectedGraphNode node : top.neighbors) {
					if (node != null && !set.contains(node)) {
						stack.push(node);
						set.add(node);
					}
				}
			}
		}

		return result;
	}
	
	private ArrayList<DirectedGraphNode> dfs(ArrayList<DirectedGraphNode> graph) {
		//�������
		Map<DirectedGraphNode, Integer> inDegree = new HashMap<>();
		for(DirectedGraphNode item : graph) {
			for(DirectedGraphNode node : item.neighbors) {
				if(inDegree.containsKey(node)) {
					inDegree.put(node, inDegree.get(node) + 1);
				}else {
					inDegree.put(node, 1);
				}
			}
		}
		
		Stack<DirectedGraphNode> stack = new Stack<>();
		for(DirectedGraphNode node : graph) {
			if(!inDegree.containsKey(node)) {
				stack.push(node);
			}
		}
		
		ArrayList<DirectedGraphNode> result = new ArrayList<>();
		while(!stack.isEmpty()) {
			DirectedGraphNode current = stack.pop();
			result.add(current);
			for(DirectedGraphNode n : current.neighbors) {
				inDegree.put(n, inDegree.get(n) - 1);
				if(inDegree.get(n) == 0) {
					stack.push(n);
				}
			}
		}
		
		return result;
	}

}
