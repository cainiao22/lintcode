package website.lintcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author yanpf
 * @date 2019��3��20�� ����6:36:32
 * @description
 * 		������ʿ�������ϵ� ��ʼ λ��(һ��2���ƾ��� 0 ��ʾ�� 1 ��ʾ���ϰ���)���ҵ����� �յ� �����·�ߣ�����·�ߵĳ��ȡ������ʿ���ܵ����򷵻� -1 ��
 * 		�����յ�ض�Ϊ��.
		��ʿ���ܴ����ϰ���.
		
		�����ʿ��λ��Ϊ (x,y)������һ�����Ե���������Щλ��:
		(x + 1, y + 2)
		(x + 1, y - 2)
		(x - 1, y + 2)
		(x - 1, y - 2)
		(x + 2, y + 1)
		(x + 2, y - 1)
		(x - 2, y + 1)
		(x - 2, y - 1)
 * @example
 *
		
		��1:

		����:
		[[0,0,0],
		 [0,0,0],
		 [0,0,0]]
		source = [2, 0] destination = [2, 2] 
		���: 2
		����:
		[2,0]->[0,1]->[2,2]
		��2:
		
		����:
		[[0,1,0],
		 [0,0,1],
		 [0,0,0]]
		source = [2, 0] destination = [2, 2] 
		���:-1
 *
 * @Solution
 */
public class ��ʿ�����·�� {
	
	 class Point {
		      int x;
		      int y;
		      Point() { x = 0; y = 0; }
		      Point(int a, int b) { x = a; y = b; }
		  }
	
	 int MAX_VALUE = Integer.MAX_VALUE - 1;
	 
	public int shortestPath(boolean[][] grid, Point source, Point destination) {
        // write your code here
		int[] dx = new int[] {1,1,-1,-1,2,2,-2,-2};
		int[] dy = new int[] {2,-2,2,-2,1,-1,1,-1};
		int[][] dp = new int[grid.length][grid[0].length];
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				dp[i][j] = MAX_VALUE;
			}
		}
		Queue<Point> queue = new LinkedList<>();
		queue.add(source);
		dp[source.x][source.y] = 0;
		while(!queue.isEmpty()) {
			Point top = queue.poll();
			for(int i=0; i<dx.length; i++) {
				if(top.x + dx[i] < 0 || top.x + dx[i] >= dp.length || top.y + dy[i] < 0 || top.y + dy[i] >= dp[0].length) {
					continue;
				}
				if(!grid[top.x + dx[i]][top.y + dy[i]] && dp[top.x + dx[i]][top.y + dy[i]] > dp[top.x][top.y] + 1) {
					dp[top.x + dx[i]][top.y + dy[i]] = dp[top.x][top.y] + 1;
					queue.add(new Point(top.x + dx[i], top.y + dy[i]));
				}
			}
		}
		
		return dp[destination.x][destination.y] == MAX_VALUE ? -1 : dp[destination.x][destination.y];
    }
	
	/**
	 * ��������㷨 ����һ�δﵽĿ�ĵص�·���������·��
	 * @param grid
	 * @param source
	 * @param destination
	 * @return
	 */
	public int shortestPath2(boolean[][] grid, Point source, Point destination) {
		int[] dx = new int[] {1,1,-1,-1,2,2,-2,-2};
		int[] dy = new int[] {2,-2,2,-2,1,-1,1,-1};
		Queue<Point> queue = new LinkedList<>();
		queue.add(source);
		int path = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int index=0; index<size; index++) {
				Point top = queue.poll();
				if(destination.x == top.x && destination.y == top.y) {
					return path;
				}
				for(int i=0; i<dx.length; i++) {
					if(top.x + dx[i] < 0 || top.x + dx[i] >= grid.length || top.y + dy[i] < 0 || top.y + dy[i] >= grid[0].length) {
						continue;
					}
					if(!grid[top.x + dx[i]][top.y + dy[i]]) {
						queue.add(new Point(top.x + dx[i], top.y + dy[i]));
						grid[top.x + dx[i]][top.y + dy[i]] = true;
					}
				}
			}
			path ++;
		}
		
		return -1;
	}

}
