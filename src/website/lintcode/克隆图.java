package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2017��9��22�� ����4:28:54
 * @description ��¡һ������ͼ��ͼ�е�ÿ���ڵ����һ�� label ��һ���б� neighbors��
 * 
 *              ��������α�ʾһ������ͼ��http://www.lintcode.com/help/graph/
 * 
 *              ��ĳ�����Ҫ����һ��������ȿ�������ͼ�������ͼ��ԭͼ����ͬ���Ľṹ�����Ҷ���ͼ���κθĶ������ԭͼ����κ�Ӱ��
 * 
 * @example
 * 
 * 			���磬���л�ͼ {0,1,2#1,2#2,2} ���������ڵ�, ��˰����������ָ���#��
 * 
 *          ��һ���ڵ�labelΪ0�����ڱߴӽڵ�0���ӵ��ڵ�1�ͽڵ�2 �ڶ����ڵ�labelΪ1�����ڱߴӽڵ�1���ӵ��ڵ�2
 *          �������ڵ�labelΪ2�����ڱߴӽڵ�2���ӵ��ڵ�2(����),�Ӷ��γ��Ի���
 * 
 *          �����ܿ������µ�ͼ��
 * 
 *          1 / \ / \ 0 --- 2 / \ \_/
 *
 * 
 * @Solution 1���ݹ��𣬹ؼ�����ȥ������Ļ�����һ��map���node��copyNode֮��Ĺ�ϵ��
 *           2���ݹ�ĸİ棬����˼·�͵ݹ�����
 */
public class ��¡ͼ {
	static Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
	public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		// write your code here
		if(node == null) {
			return null;
		}
		UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
		map.put(node, copy);
		for (UndirectedGraphNode item : node.neighbors) {
			if (map.get(item) != null) {
				copy.neighbors.add(map.get(item));
			} else {
				copy.neighbors.add(cloneGraph(item));
			}
		}

		return copy;

	}
	
	//�ǵݹ鷽ʽ
	public static UndirectedGraphNode cloneGraphIter(UndirectedGraphNode node) {
		if(node == null) {
			return null;
		}
		List<UndirectedGraphNode> nodes = getNodes(node);
		for(UndirectedGraphNode item : nodes) {
			map.put(item, new UndirectedGraphNode(item.label));
		}
		
		for(UndirectedGraphNode item : nodes) {
			for(UndirectedGraphNode neighbor : item.neighbors) {
				map.get(item).neighbors.add(map.get(neighbor));
			}
		}
		
		return map.get(node);
		
	}
	
	private static List<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		Set<UndirectedGraphNode> hashSet = new HashSet<>();
		queue.offer(node);
		hashSet.add(node);
		while(!queue.isEmpty()) {
			UndirectedGraphNode top = queue.poll();
			for(UndirectedGraphNode item : top.neighbors) {
				if(!hashSet.contains(item)) {
					queue.offer(item);
					hashSet.add(item);
				}
			}
		}
		return new ArrayList<>(hashSet);
	}

	public static class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}
	
	public static void main(String[] args) {
		//{0,1,5#1,2,5#2,3#3,4,4#4,5,5#5}
		UndirectedGraphNode a0 = new UndirectedGraphNode(0);
		UndirectedGraphNode a1 = new UndirectedGraphNode(1);
		UndirectedGraphNode a2 = new UndirectedGraphNode(2);
		UndirectedGraphNode a3 = new UndirectedGraphNode(3);
		UndirectedGraphNode a4 = new UndirectedGraphNode(4);
		UndirectedGraphNode a5 = new UndirectedGraphNode(5);
		
		a0.neighbors.add(a1);
		a0.neighbors.add(a5);
		a1.neighbors.add(a2);
		a1.neighbors.add(a5);
		a2.neighbors.add(a3);
		a3.neighbors.add(a4);
		a3.neighbors.add(a4);
		a4.neighbors.add(a5);
		a4.neighbors.add(a5);
		
		cloneGraph(a0);
		
	}

}
