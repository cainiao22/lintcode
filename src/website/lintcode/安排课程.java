package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2017��10��17�� ����11:59:49
 * @description
 * 		
		����Ҫȥ��n�ž��µĿβ��ܻ��offer����Щ�α����Ϊ 0 �� n-1 ��
		��һЩ�γ���Ҫ��ǰ�ÿγ̡������������Ҫ�Ͽγ�0������Ҫ��ѧ�γ�1��������һ��ƥ������ʾ���ǣ� [0,1]
		
		����γ̵���������һЩǰ�ÿγ̵����󣬷�����Ϊ��ѧ�����пγ������ŵ�ѧϰ˳��
		
		���ܻ��ж����ȷ��˳����ֻҪ����һ�־Ϳ����ˡ����������������пγ̣�����һ�������顣

 * @example
 * 		���� n = 2, prerequisites = [[1,0]]
		���� [0,1]
		
		���� n = 4, prerequisites = [1,0],[2,0],[3,1],[3,2]]
		���� [0,1,2,3] or [0,2,1,3]
 *
 * @Solution
 */
public class ���ſγ� extends HH {
	
	//todo �Ż�Memory Limit Exceeded
	public int[] findOrderError(int numCourses, int[][] prerequisites) {
        // write your code here
		// key:�γ̣�value:������ǰ�ÿγ�
		Map<Integer, List<Integer>> tree = new HashMap<>();
		//key:�γ̣�value:�������ҿγ�
		Map<Integer, List<Integer>> dependency = new HashMap<>();
		int[] result = new int[numCourses];
		for(int i=0; i<prerequisites.length; i++) {
			if(tree.get(prerequisites[i][0]) == null) {
				tree.put(prerequisites[i][0], new ArrayList<Integer>());
			}
			tree.get(prerequisites[i][0]).add(prerequisites[i][1]);
			
			if(dependency.get(prerequisites[i][1]) == null) {
				dependency.put(prerequisites[i][1], new ArrayList<>());
			}
			dependency.get(prerequisites[i][1]).add(prerequisites[i][0]);
		}
		int index = 0;
		for(int i=0; i<numCourses; i++) {
			if(tree.containsKey(i)) continue;
			tree.put(i, new ArrayList<>());
		}  
		while(index < numCourses) {
			boolean flag = false;
			for(Map.Entry<Integer, List<Integer>> entry : tree.entrySet()) {
				if(entry.getValue() != null && entry.getValue().isEmpty()) {
					flag = true;
					result[index ++] = entry.getKey();
					tree.put(entry.getKey(), null);
					List<Integer> children = dependency.get(entry.getKey());
					if (children == null) {
						continue;
					}
					for (int child : children) {
						tree.get(child).remove((Integer) entry.getKey());
					}
				}
			}
			if(!flag) {
				return new int[0];
			}
		}
		
		return result;
		
    }
	
	//�Ż���: �������Ŀγ��б�ȥ�����ĳ��������Ŀγ�����
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] result = new int[numCourses];
		//��� ��������ǰ�ÿγ̵�����
		int[] inDegrees = new int[numCourses];
		//�����ҵĿγ��б�
		Map<Integer, List<Integer>> tree = new HashMap<>();
		for(int i=0;i<prerequisites.length; i++) {
			inDegrees[prerequisites[i][0]] ++;
			if(tree.get(prerequisites[i][1]) == null) {
				tree.put(prerequisites[i][1], new ArrayList<>());
			}
			tree.get(prerequisites[i][1]).add(prerequisites[i][0]);
		}
		
		int index = 0;
		List<Integer> buf = new ArrayList<>();
		for(int i=0; i<numCourses; i++) {
			if(inDegrees[i] == 0) {
				buf.add(i);
			}
		}
		List<Integer> buf2 = new ArrayList<>();
		while(!buf.isEmpty()) {
			buf2.clear();
			for(int i : buf) {
				result[index ++] = i;
				List<Integer> next = tree.get((Integer)i);
				if(next != null) {
					for(int j : next) {
						inDegrees[j] --;
						if(inDegrees[j] == 0) {
							buf2.add(j);
						}
					}
				}
			}
			List<Integer> temp = buf;
			buf = buf2;
			buf2 = temp;
		}
		if(index < numCourses) {
			return new int[0];
		}
		return result;
		
	}
	
	public static void main(String[] args) {
		int numCourses = 4;
		int[][] prerequisites = new int[][] {{1,0},{2,0},{3,1},{3,2},{0,3}};
		int[] result = new ���ſγ�().findOrder(numCourses, prerequisites);
		print(result);
	}

}
