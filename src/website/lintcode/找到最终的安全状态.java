package website.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019��1��23�� ����2:15:07
 * @description
 * 
 * 				��һ������ͼ�У����Ǵ�ĳ���ڵ㿪ʼ��ÿ������ͼ��������ߡ� ������ǵ���һ���ն˽ڵ㣨Ҳ����˵����û��ָ������ıߣ�����ֹͣ��
 * 
 *              ���ڣ�������ǵ���ʼ�ڵ������ǰ�ȫ�ģ����ҽ������������ߵ��ն˽ڵ㡣
 *              �������˵��������Ȼ��K�������κ����ߵ�·�ߣ����Ǳ���������K���������ͣ���ն˽ڵ㡣
 * 
 *              ��Щ�ڵ������հ�ȫ�ģ� �����Ǽ������������������
 * 
 *              ����ͼ����N���ڵ㣬���ǩΪ0, 1, ..., N-1������N��ͼ�ĳ��ȡ� ��ͼ���������ʽ������ graph
 *              [i]�Ǵ�i������ͨ����(i, j)�������ܹ�����Ľڵ�j��ɵ�����
 * @example
 * 
 * 			����: graph = [[1,2],[2,3],[5],[0],[5],[],[]] 
 * 			���: [2,4,5,6]
 *
 * @Solution
 */
public class �ҵ����յİ�ȫ״̬ extends HH {
	
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
	 * �ռ临�Ӷȹ���
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
		List<Integer> result = new �ҵ����յİ�ȫ״̬().eventualSafeNodes2(graph);
		print(result);
	}
}
