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
 * @date 2017年10月24日 下午3:40:38
 * @description 给定一个有向图，图节点的拓扑排序被定义为：
 * 
 *              对于每条有向边A--> B，则A必须排在B之前 拓扑排序的第一个节点可以是任何在图中没有其他节点指向它的节点
 * 
 *              找到给定图的任一拓扑排序 能否分别用BFS和DFS完成？
 *              
 *              注意:这里给的graph list是所有的node节点
 * @example
 *
 * @Solution
 * 		 计算所有节点的入度
		 选择一个入度为零的节点输出，并将其子节点的入度减1
		 循环直到所有节点输出
		 
		 dfs见最下面。
		 bfs只是将stack变成queue
 */
public class 拓扑排序 {

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
	
	//todo 错误的  为毛？
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
		//计算入度
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
