package website.lintcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2019年2月21日 下午5:54:13
 * @description
 * 		您将获得一个使用这三个可能值初始化的 m×n 2D 网格。
		-1 - 墙壁或障碍物。
		0 - 门。
		INF - Infinity是一个空房间。我们使用值 2 ^ 31 - 1 = 2147483647 来表示INF，您可以假设到门的距离小于 2147483647。
		在代表每个空房间的网格中填入到距离最近门的距离。如果不可能到达门口，则应填入 INF
 * @example
 *		解释：
		2D网络为：
		INF  -1  0  INF
		INF INF INF  -1
		INF  -1 INF  -1
		  0  -1 INF INF
		答案为：
		  3  -1   0   1
		  2   2   1  -1
		  1  -1   2  -1
		  0  -1   3   4
 * @Solution
 */
public class 墙和门 extends HH {
	
	private int[][] dt = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	 public void wallsAndGates(int[][] rooms) {
	     for(int i=0; i<rooms.length; i++) {
	    	 for(int j=0; j<rooms[0].length; j++) {
	    		 if(rooms[i][j] == Integer.MAX_VALUE) {
	    			 int distance = helper(i, j, rooms);
	    			 rooms[i][j] = distance;
	    		 }
	    	 }
	     }
	 }
	 
	 /**
	  * dp方式求解
	  * @param rooms
	  */
	 private void wallsAndGatesDP(int[][] rooms) {
		 Queue<Integer[]> queue = new LinkedList<>();
		 for(int i=0; i<rooms.length; i++) {
	    	 for(int j=0; j<rooms[0].length; j++) {
	    		 if(rooms[i][j] == 0) {
	    			 queue.add(new Integer[] {i, j});
	    		 }
	    	 }
	     }
		 
		 while(!queue.isEmpty()) {
			 Integer[] top = queue.poll();
			 int x = top[0];
			 int y = top[1];
			 for(int i=0; i<dt.length; i++) {
				 if(x +dt[i][0] < 0 
						 || x + dt[i][0] >= rooms.length 
						 || y + dt[i][1] < 0 
						 || y + dt[i][1] >= rooms[0].length) {
					 continue;
				 }
				 
				 if(rooms[x + dt[i][0]][y+dt[i][1]] == -1) {
					 continue;
				 }
				 if(rooms[x][y] + 1 < rooms[x + dt[i][0]][y+dt[i][1]]) {
					 rooms[x + dt[i][0]][y+dt[i][1]] = rooms[x][y] + 1;
					 queue.add(new Integer[]{x + dt[i][0], y+dt[i][1]});
				 }
			 }
		 }
	 }
	 
	 /**
	  * 递归方式求解
	  * @param x
	  * @param y
	  * @param rooms
	  * @return
	  */
	 private int helper(int x, int y, int[][] rooms) {
		 if(rooms[x][y] == -1) {
			 return Integer.MAX_VALUE;
		 }
		 if(rooms[x][y] != Integer.MAX_VALUE) {
			 return rooms[x][y];
		 }
		 int temp = rooms[x][y];
		 rooms[x][y] = -1;
		 int result = Integer.MAX_VALUE;
		 for(int i=0; i<dt.length; i++) {
			 if(x +dt[i][0] < 0 
					 || x + dt[i][0] >= rooms.length 
					 || y + dt[i][1] < 0 
					 || y + dt[i][1] >= rooms[0].length) {
				 continue;
			 }
			 int distance = helper(x + dt[i][0], y + dt[i][1], rooms);
			 if(distance != Integer.MAX_VALUE) {
				 result = Integer.min(result, distance + 1);
			 }
		 }
		 rooms[x][y] = temp;
		 return result;
	 }
	 
	 /**
	  * 另一种思路，从0开始遍历所有子节点
	  * @param rooms
	  */
	 public void wallsAndGatesFromJiuZhang(int[][] rooms) {
	        // write your code here
	        if (rooms.length == 0 || rooms[0].length == 0) {
	            return;
	        }
	        
	        int m = rooms.length;
	        int n = rooms[0].length;
	        for (int i = 0; i < m; i++) {
	            for (int j = 0; j < n; j++) {
	                if (rooms[i][j] == 0) {
	                    dfs(rooms, i, j, 0);
	                }
	            }
	        }
	    }
	    
	    private void dfs(int[][] rooms, int x, int y, int distance) {
	        if (!inBound(rooms, x, y) || rooms[x][y] == -1) {
	            return;
	        }
	        if (rooms[x][y] > distance || distance == 0) {
	            rooms[x][y] = distance;
	            dfs(rooms, x + 1, y, distance + 1);
	            dfs(rooms, x, y + 1, distance + 1);
	            dfs(rooms, x - 1, y, distance + 1);
	            dfs(rooms, x, y - 1, distance + 1);
	        }
	    }
	    
	    private boolean inBound(int[][] rooms, int x, int y) {
	        return x >= 0 && x < rooms.length && y >=0 && y < rooms[0].length;
	    }
	 
	 public static void main(String[] args) {
		int INF = Integer.MAX_VALUE;
		int[][] rooms = new int[][] {{INF, -1, 0, INF}, {INF,INF,INF, -1}, {INF, -1,INF, -1}, {0, -1,INF,INF}};
		new 墙和门().wallsAndGatesDP(rooms);
		print(rooms);
	}

}
