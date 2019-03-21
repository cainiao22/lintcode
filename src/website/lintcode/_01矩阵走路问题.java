package website.lintcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2019年3月19日 下午5:15:30
 * @description
 * 		给定一个大小为 n*m 的 01 矩阵 grid，1 是墙，0 是路，你现在可以把 grid 中的一个 1 变成 0，
 * 		请问从左上角走到右下角是否有路可走？如果有路可走，最少要走多少步？
​
​​
 * @example
 * 		给定 a = [[0,1,0,0,0],
 * 				[0,0,0,1,0],
 * 				[1,1,0,1,0],
 * 				[1,1,1,1,0]]，返回 7
		解释：
		将（0,1）处的 `1` 变成 `0`，最短路径如下：
		 (0,0)->(0,1)->(0,2)->(0,3)->(0,4)->(1,4)->(2,4)->(3,4) 其他长度为 `7` 的方案还有很多，这里不一一列举。
		给定 a = [[0,1,1],[1,1,0],[1,1,0]]，返回 -1
		
		解释：
		不管把哪个 `1` 变成 `0`，都没有可行的路径。
 *
 * @Solution
 */
public class _01矩阵走路问题 {
	
	int[] dx = {-1, 0, 1, 0};
	int[] dy = {0, 1, 0, -1};
	
	int MAX_VALUE = Integer.MAX_VALUE - 1;
	/**
	 * 中规中矩的算法 空间复杂度超出
	 * @param grid
	 * @return
	 */
	public int getBestRoad(int[][] grid) {
        // Write your code here
		int best = MAX_VALUE;
		int m = grid.length, n = grid[0].length;
		int[][] dp = new int[m][n];
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j] == 1) {
					grid[i][j] = 0;
					int item = getBestRoadDp(grid, dp);
					best = Integer.min(best, item);
					grid[i][j] = 1;
				}
			}
		}
		return best == MAX_VALUE ? -1 : best;
    }
	
	public int getBestRoadDp(int[][] grid, int[][] dp) {
		int m = grid.length, n = grid[0].length;
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				dp[i][j] = MAX_VALUE;
			}
		}
		if(grid[0][0] == 1 || grid[m-1][n-1] == 1) {
			return MAX_VALUE;
		}
		dp[0][0] = 0;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {0, 0});
		while(!queue.isEmpty()) {
			int[] top = queue.poll();
			int x = top[0];
			int y = top[1];
			for(int i=0; i<dx.length; i++) {
				if(x + dx[i] < 0 || x + dx[i] >= m || y + dy[i] < 0 || y + dy[i] >= n) {
					continue;
				}
				if(grid[x+dx[i]][y + dy[i]] == 1) {
					continue;
				}
				if(dp[x+dx[i]][y + dy[i]] > dp[x][y] + 1) {
					dp[x+dx[i]][y + dy[i]] = dp[x][y] + 1;
					queue.add(new int[] {x+dx[i], y + dy[i]});
				}
			}
		}
		
		return dp[m-1][n-1];
	}
	
	
	public int getBestRoad2(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		//表示从(0, 0) -> (i,j) 将k个1变成0所得到的最短距离
		int count = 2;
		int[][][] dp = new int[m][n][count];
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<2; k++) {
					dp[i][j][k] = MAX_VALUE;
				}
			}
		}
		if(grid[0][0] == 0) {
			dp[0][0][0] = 0;
		}
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {0, 0, 0});
		while(!queue.isEmpty()) {
			int[] top = queue.poll();
			int x = top[0];
			int y = top[1];
			int index = top[2];
			for(int i=0; i<dx.length; i++) {
				if(x + dx[i] < 0 || x + dx[i] >= m || y + dy[i] < 0 || y + dy[i] >= n) {
					continue;
				}
				for(int j=index; j<count; j++) {
					if(j == 0) {
						if(grid[x + dx[i]][y + dy[i]] == 0) {
							if(dp[x + dx[i]][y + dy[i]][j] > dp[x][y][0] + 1){
								dp[x + dx[i]][y + dy[i]][j] = dp[x][y][0] + 1;
								queue.add(new int[] {x + dx[i], y + dy[i], j});
							}
						}
					}else {
						if(grid[x + dx[i]][y + dy[i]] == 1) {
							if(dp[x + dx[i]][y + dy[i]][j] > dp[x][y][j-1] + 1) {
								dp[x + dx[i]][y + dy[i]][j] = dp[x][y][j-1] + 1;
								queue.add(new int[] {x + dx[i], y + dy[i], j});
							}
						}else {
							if(dp[x + dx[i]][y + dy[i]][j] > dp[x][y][j] + 1) {
								dp[x + dx[i]][y + dy[i]][j] = dp[x][y][j] + 1;
								queue.add(new int[] {x + dx[i], y + dy[i], j});
							}
						}
					}
				}
				
				
			}
		}
		int result = MAX_VALUE;
		for(int k=0; k<count; k++) {
			result = Integer.min(dp[m-1][n-1][k], result);
		}
		
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				int a = dp[i][j][0];
				if(a == MAX_VALUE) {
					a = -1;
				}
				System.out.print(a + " ");
			}
			System.out.println();
		}
		
		return result == MAX_VALUE ? -1 : result;
	}
	
	
	public class Point {
        int x;
        int y;
        int dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
	
	/**
	 * 从左到右分别做dp,然后算和
	 */
    int[][] dir = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
    public int getBestRoad3(int[][] grid) {
        // Write your code here
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][]distLeft = new int[m][n];
        int[][]distRight = new int[m][n];
        Queue<Point> list = new LinkedList<Point>();
        Point start = new Point(0,0,0);
        list.offer(start);
        visited[0][0] = true;
        while(!list.isEmpty()) {
            Point p = list.poll();
            for(int i=0; i<4; i++) {
                int newX = p.x + dir[i][0];
                int newY = p.y + dir[i][1];
                if(newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    distLeft[newX][newY] = p.dist+ 1;
                    if(grid[newX][newY] == 0) {
                        list.offer(new Point(newX,newY,p.dist+ 1));
                    }
                    
                }
            }
        }
        visited = new boolean[m][n];
        start = new Point(m-1,n-1,0);
        list.offer(start);
        visited[m-1][n-1] = true;
        while(!list.isEmpty()) {
            Point p = list.poll();
            for(int i=0; i<4; i++) {
                int newX = p.x + dir[i][0];
                int newY = p.y + dir[i][1];
                if(newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    distRight[newX][newY] = p.dist+ 1;
                    if(grid[newX][newY] == 0) {
                        list.offer(new Point(newX,newY,p.dist+ 1));
                    }
                }
            }
        }
        int minDist = Integer.MAX_VALUE;
        if(distRight[0][0] > 0) {
            minDist = Math.min(minDist, distRight[0][0]);
        }
        if(distLeft[m-1][n-1] > 0) {
            minDist = Math.min(minDist, distLeft[m-1][n-1]);
        }
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i == 0 && j == 0 || i == m-1 && j == n-1) {
                    continue;
                }
                if(distRight[i][j] > 0 && distLeft[i][j] > 0) {
                    minDist = Math.min(minDist, distRight[i][j] + distLeft[i][j]);
                }
            }
        }
        if(minDist == Integer.MAX_VALUE) {
            return -1;
        }
        return minDist;
    }
	
	public static void main(String[] args) {
		int[][] grid = new int[][] {{0,1,0,0,0},
									{0,0,0,1,0},
									{1,1,1,1,0},
									{1,1,1,1,1},
									{1,1,1,0,0}};
		int result = new _01矩阵走路问题().getBestRoad(grid);
		int result2 = new _01矩阵走路问题().getBestRoad2(grid);
		System.out.println(result + "==" + result2);
	}

}
