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
 * @date 2017年9月22日 下午4:28:54
 * @description 克隆一张无向图，图中的每个节点包含一个 label 和一个列表 neighbors。
 * 
 *              数据中如何表示一个无向图？http://www.lintcode.com/help/graph/
 * 
 *              你的程序需要返回一个经过深度拷贝的新图。这个新图和原图具有同样的结构，并且对新图的任何改动不会对原图造成任何影响
 * 
 * @example
 * 
 * 			比如，序列化图 {0,1,2#1,2#2,2} 共有三个节点, 因此包含两个个分隔符#。
 * 
 *          第一个节点label为0，存在边从节点0链接到节点1和节点2 第二个节点label为1，存在边从节点1连接到节点2
 *          第三个节点label为2，存在边从节点2连接到节点2(本身),从而形成自环。
 * 
 *          我们能看到如下的图：
 * 
 *          1 / \ / \ 0 --- 2 / \ \_/
 *
 * 
 * @Solution 1、递归吗，关键点是去除里面的环。找一个map存放node与copyNode之间的关系，
 *           2、递归的改版，迭代思路和递归类似
 */
public class 克隆图 {
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
	
	//非递归方式
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
