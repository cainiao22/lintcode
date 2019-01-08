package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2017年10月17日 上午11:59:49
 * @description
 * 		
		你需要去上n门九章的课才能获得offer，这些课被标号为 0 到 n-1 。
		有一些课程需要“前置课程”，比如如果你要上课程0，你需要先学课程1，我们用一个匹配来表示他们： [0,1]
		
		给你课程的总数量和一些前置课程的需求，返回你为了学完所有课程所安排的学习顺序。
		
		可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。

 * @example
 * 		给定 n = 2, prerequisites = [[1,0]]
		返回 [0,1]
		
		给定 n = 4, prerequisites = [1,0],[2,0],[3,1],[3,2]]
		返回 [0,1,2,3] or [0,2,1,3]
 *
 * @Solution
 */
public class 安排课程 extends HH {
	
	//todo 优化Memory Limit Exceeded
	public int[] findOrderError(int numCourses, int[][] prerequisites) {
        // write your code here
		// key:课程，value:依赖的前置课程
		Map<Integer, List<Integer>> tree = new HashMap<>();
		//key:课程，value:依赖的我课程
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
	
	//优化点: 我依赖的课程列表去掉，改成我依赖的课程数量
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] result = new int[numCourses];
		//入度 我依赖的前置课程的数量
		int[] inDegrees = new int[numCourses];
		//依赖我的课程列表
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
		int[] result = new 安排课程().findOrder(numCourses, prerequisites);
		print(result);
	}

}
