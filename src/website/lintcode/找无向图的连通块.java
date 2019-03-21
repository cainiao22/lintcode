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
 * @date 2019��3��20�� ����4:55:40
 * @description
 * 		�ҳ�����ͼ�����е���ͨ�顣
		ͼ�е�ÿ���ڵ����һ��label���Ժ�һ���ڽӵ���б�
		��һ������ͼ����ͨ����һ����ͼ������������������ͨ��·���������Ҳ�������ͼ�е�����������������
		����Ҫ���� label ���ϵ��б�.\\
		
		
 * @example
 * 		���� 1:

		����: {1,2,4#2,1,4#3,5#4,1,2#5,3}
		���: [[1,2,4],[3,5]]
		����: 
		
		  1------2  3
		   \     |  | 
		    \    |  |
		     \   |  |
		      \  |  |
		        4   5
		���� 2:
		
		����: {1,2#2,1}
		���: [[1,2]]
		����:
		
		  1--2

 *
 * @Solution
 */
public class ������ͼ����ͨ�� extends HH{
	

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
        //������һ��ȫ�ֵ� hashset,���ǰ���Ǹ���������ֻ��add��������ǰ���Ǹ�map��������add������ɾ��
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
