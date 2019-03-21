package website.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * @author yanpf
 * @date 2019年3月20日 下午4:55:40
 * @description
 * 		找出无向图中所有的连通块。
		图中的每个节点包含一个label属性和一个邻接点的列表。
		（一个无向图的连通块是一个子图，其中任意两个顶点通过路径相连，且不与整个图中的其它顶点相连。）
		你需要返回 label 集合的列表.\\
		
		
 * @example
 * 		样例 1:

		输入: {1,2,4#2,1,4#3,5#4,1,2#5,3}
		输出: [[1,2,4],[3,5]]
		解释: 
		
		  1------2  3
		   \     |  | 
		    \    |  |
		     \   |  |
		      \  |  |
		        4   5
		样例 2:
		
		输入: {1,2#2,1}
		输出: [[1,2]]
		解释:
		
		  1--2

 *
 * @Solution
 */
public class 找无向图的连通块 extends HH{
	

	 class UndirectedGraphNode {
	     int label;
	     ArrayList<UndirectedGraphNode> neighbors;
	     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	 };

	
	public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, UndirectedGraphNode> map = new HashMap<>();
		for(UndirectedGraphNode item : nodes) {
			map.put(item.label, item);
		}
		while(!map.isEmpty()) {
			List<Integer> item = new ArrayList<>();
			Queue<UndirectedGraphNode> queue = new LinkedList<>();
			queue.add(map.values().iterator().next());
			while(!queue.isEmpty()) {
				UndirectedGraphNode node = queue.poll();
				map.remove(node.label);
				item.add(node.label);
				if(node.neighbors != null) {
					for(UndirectedGraphNode neighbor : node.neighbors) {
						if(map.containsKey(neighbor.label)) {
							queue.add(neighbor);
							map.remove(neighbor.label);
						}
					}
				}
			}
			Collections.sort(item);
			result.add(item);
		}
		return result;
    }
	
	public List<List<Integer>> connectedSet2(ArrayList<UndirectedGraphNode> nodes) {
        // Write your code here
        List<List<Integer>> results = new ArrayList<>();
        if (nodes == null || nodes.size() == 0) {
            return results;
        }
        //这里是一个全局的 hashset,针对前面那个方法，他只做add操作，而前面那个map不仅做了add还做了删除
        HashSet<UndirectedGraphNode> set = new HashSet<>();
        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        for (UndirectedGraphNode node : nodes) {
            if (set.contains(node)) {
                continue;
            }
            queue.offer(node);
            set.add(node);
            ArrayList<Integer> result = new ArrayList<>();
            while (!queue.isEmpty()) {
                UndirectedGraphNode anode = queue.poll();
                result.add(anode.label);
                for (UndirectedGraphNode neighbor : anode.neighbors) {
                    if (!set.contains(neighbor)) {
                        set.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
            if (result.size() > 1) {
                Collections.sort(result);
            }
            results.add(result);
        }
        return results;
    }
	
	public static void main(String[] args) {
		int[] arr = new int[] {-1,0,1,-11,8,-15,3,-3,4,-13,-8,13,-14,-2,-6,2,-12,-10,10,-7,9,-4,6,11,5,14,-5};
		Arrays.sort(arr);
		print(arr);
	}

}
