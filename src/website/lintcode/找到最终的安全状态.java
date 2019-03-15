package website.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019年1月23日 下午2:15:07
 * @description
 * 
 * 				在一个有向图中，我们从某个节点开始，每次沿着图的有向边走。 如果我们到达一个终端节点（也就是说，它没有指向外面的边），就停止。
 * 
 *              现在，如果我们的起始节点最终是安全的，当且仅当我们最终走到终端节点。
 *              更具体地说，存在自然数K，对于任何行走的路线，我们必须在少于K步的情况下停在终端节点。
 * 
 *              哪些节点是最终安全的？ 把他们加入数组中排序输出。
 * 
 *              有向图具有N个节点，其标签为0, 1, ..., N-1，其中N是图的长度。 该图以下面的形式给出： graph
 *              [i]是从i出发，通过边(i, j)，所有能够到达的节点j组成的链表。
 * @example
 * 
 * 			输入: graph = [[1,2],[2,3],[5],[0],[5],[],[]] 
 * 			输出: [2,4,5,6]
 *
 * @Solution
 */
public class 找到最终的安全状态 extends HH {
	
	public List<Integer> eventualSafeNodes(int[][] graph) {
		List<Integer> result = new ArrayList<>();
        for(int i=0; i<graph.length; i++) {
        	List<Integer> path = new ArrayList<>();
        	path.add(i);
        	if(isSafe(i, graph, path)) {
        		result.add(i);
        	}
        }
        
        return result;
    }
	
	/**
	 * 空间复杂度过大
	 * @param graph
	 * @return
	 */
	boolean isSafe(int node, int[][] graph, List<Integer> path) {
		for(int i=0; i<graph[node].length; i++) {
			if(path.contains(graph[node][i])){
				return false;
			}
			path.add(graph[node][i]);
			if(!isSafe(graph[node][i], graph, path)) {
				return false;
			}
			path.remove(Integer.valueOf(graph[node][i]));
		}
		
		return true;
	}
	
	public List<Integer> eventualSafeNodes2(int[][] graph){
		List<Integer> result = new ArrayList<>();
		boolean flag = true;
		for(int i=0; i<graph.length; i++) {
			if(graph[i].length == 0) {
				result.add(i);
			}
		}
        while(flag) {
        	flag = false;
	        for(int i=0; i<graph.length; i++) {
	           	boolean contains = true;
            	for(int j = 0; j<graph[i].length; j++) {
            		if(!result.contains(graph[i][j])){
            			contains = false;
            			break;
            		}
            	}
            	if(contains && !result.contains(i)) {
            		flag = true;
            		result.add(i);
            	}
	         }
        }
        
        Collections.sort(result);
        return result;
        
	}
	
	
	
	public static void main(String[] args) {
		int[][] graph = new int[][]{{4,9},{3,5,7},{0,3,4,5,6,8},{7,8,9},{5,6,7,8},{6,7,8,9},{7,9},{8,9},{9},{}};
		List<Integer> result = new 找到最终的安全状态().eventualSafeNodes2(graph);
		print(result);
	}
}
