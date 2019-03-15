package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

/**
 * 
 * @author yanpf
 * @date 2017年9月28日 上午11:25:35
 * @description
 * 		
		There are a total of n courses you have to take, labeled from 0 to n - 1.
		
		Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
		
		Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 * @example
 * 		Given n = 2, prerequisites = [[1,0]]
		Return true
		
		Given n = 2, prerequisites = [[1,0],[0,1]]
		Return false
 *
 * @Solution 1、这是一个很典型的拓扑排序问题,将整个关系变成一个tuple结构，然后从入度为0顶点的开始减
 */
public class CourseSchedule {
	
	//有问题
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        // write your code here
		//Map<Integer, List<Integer>> map = new HashMap<>();
		List<List<Integer>> posts = new ArrayList<>(numCourses);
		for(int i=0; i<numCourses; i++) {
			posts.add(new LinkedList<>());
		}
		Queue<Integer> queue = new LinkedList<>();
		for(int i=0; i<prerequisites.length; i++) {
			posts.get(prerequisites[i][0]).add(prerequisites[i][1]);
			int val = prerequisites[i][1];
			int key = prerequisites[i][0];
			queue.add(val);
			while(!queue.isEmpty()) {
				key = queue.poll();
				if(posts.get(key) != null) {
					if(posts.get(key).contains(prerequisites[i][0])) {
						return false;
					}else {
						queue.addAll(posts.get(key));
					}
				}
			}
		}
		return true;
    }
	
	//todo 错的 他妹的
	public boolean canFinishLessMemory(int numCourses, int[][] prerequisites) {
		for(int i=0; i<prerequisites.length; i++) {
			Set<Integer> hashSet = new HashSet<>();
			boolean isCon = contains(hashSet, prerequisites, i);
			if(isCon) {
				return false;
			}
		}
		return true;
	}
	
	private boolean contains(Set<Integer> hashSet, int[][] prerequisites, int index) {
		if(hashSet.contains(prerequisites[index][1])) {
			return true;
		}
		hashSet.add(prerequisites[index][0]);
		hashSet.add(prerequisites[index][1]);
		for(int i=0; i<prerequisites.length; i++) {
			if(i!= index && prerequisites[i][0] == prerequisites[index][1]) {
				boolean result = contains(hashSet, prerequisites, i);
				if(result) {
					return true;
				}
			}
		}
		return false;
	}
	
	private int num;
	private boolean[] flag;
	
	public boolean canFinishLessTuple(int numCourses, int[][] prerequisites) {
		int[] indegrees = new int[numCourses];
		num = numCourses;
		flag=new boolean[numCourses];
		Queue<Integer> queue = new LinkedList<>();
		for(int i=0; i<prerequisites.length; i++) {
			indegrees[prerequisites[i][0]] ++;
		}
		/*for(int i=0; i<numCourses; i++) {
			if(indegrees[i] == 0 && !flag[i]) {
				queue.offer(i);
				process(queue, indegrees, prerequisites);
			}
		}*/
		for(int i=0; i<numCourses; i++) {
			if(indegrees[i] == 0 && !flag[i]) {
				queue.offer(i);
			}
		}
		
		process(queue, indegrees, prerequisites);
		
		return num == 0;
	}
	
	public void process(Queue<Integer> queue, int[] indegree, int[][] prerequisites) {
		while(!queue.isEmpty()) {
			int dep = queue.poll();
			flag[dep] = true;
			num --;
			for(int i=0; i<prerequisites.length; i++) {
				if(prerequisites[i][1] == dep) {
					if(--indegree[prerequisites[i][0]] == 0) {
						queue.add(prerequisites[i][0]);
					}
				}
			}
		}
	}
	
	public boolean canFinishAccepted(int numCourses, int[][] prerequisites) {  
        List<List<Integer>> posts = new ArrayList<List<Integer>>();  
        for (int i = 0; i < numCourses; i++) {  
            posts.add(new ArrayList<Integer>());  
        }  
          
        int[] preNums = new int[numCourses];  
        for (int i = 0; i < prerequisites.length; i++) {  
            posts.get(prerequisites[i][1]).add(prerequisites[i][0]);  
            preNums[prerequisites[i][0]]++;  
        }  
          
        Queue<Integer> queue = new LinkedList<Integer>();  
        for (int i = 0; i < numCourses; i++) {  
            if (preNums[i] == 0){  
                queue.offer(i);  
            }  
        }  
          
        int count = numCourses;  
        while (!queue.isEmpty()) {  
            int cur = queue.poll();  
            for (int i : posts.get(cur)) {  
                if (--preNums[i] == 0) {  
                    queue.offer(i);  
                }  
            }  
            count--;  
        }  
          
        return count == 0;  
    }  
	
	public static void main(String[] args) {
		int[][] prerequisites = new int[][] {
			//{5,8},{3,5},{1,9},{4,5},{0,2},{1,9},{7,8},{4,9}
			{1,0}//,{0,1}
		};
		boolean flag = new CourseSchedule().canFinishLessTuple(2, prerequisites);
		System.out.println(flag);
		boolean[] t = new boolean[2];
		System.out.println(t[0]);
	}

}
