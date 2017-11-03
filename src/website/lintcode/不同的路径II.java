package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月31日 下午1:29:18
 * @description
 * 		
		"不同的路径" 的跟进问题：
		
		现在考虑网格中有障碍物，那样将会有多少条不同的路径？
		
		网格中的障碍和空位置分别用 1 和 0 来表示。
		注意事项
		
		m 和 n 均不超过100

 * @example
 * 		如下所示在3x3的网格中有一个障碍物：

		[
		  [0,0,0],
		  [0,1,0],
		  [0,0,0]
		]
		
		一共有2条不同的路径从左上角到右下角。
 *
 * @Solution
 */
public class 不同的路径II extends HH {
	
	private int[] dx = new int[] {1, 0};
	private int[] dy = new int[] {0, 1};
	private int count = 0;

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
		helper(obstacleGrid, 0, 0);
		return count;
    }
	
	/**
	 * 递归方式 会超时
	 * @param obstacleGrid
	 * @param x
	 * @param y
	 */
	private void helper(int[][] obstacleGrid, int x, int y) {
		if (x == obstacleGrid.length - 1 && y == obstacleGrid[0].length - 1) {
			count++;
			return;
		}
		//当位置从（0,0）开始的时候，这个情况会用到
		if(obstacleGrid[x][y] == 1) {
			return;
		}
		obstacleGrid[x][y] = 1;
		for (int i = 0; i < dx.length; i++) {
			if (x + dx[i] < obstacleGrid.length && y + dy[i] < obstacleGrid[0].length
					&& obstacleGrid[x + dx[i]][y + dy[i]] == 0) {
				helper(obstacleGrid, x + dx[i], y + dy[i]);
			}
		}
		obstacleGrid[x][y] = 0;
	}
	
	/**
	 * 动态规划
	 * @param obstacleGrid
	 */
	private int helper(int[][] obstacleGrid) {
		int[][] result = new int[obstacleGrid.length][obstacleGrid[0].length];
		for(int i=0; i<result.length; i++) {
			for(int j=0; j<result[0].length; j++) {
				if(obstacleGrid[i][j] == 1) {
					result[i][j] = 0;
				}else if(i == 0 && j == 0) {
					result[i][j] = 1;
				}else {
					if(i > 0) {
						result[i][j] += result[i-1][j];
					}
					if(j > 0) {
						result[i][j] += result[i][j-1];
					}
				}
			}
		}
		
		return result[obstacleGrid.length-1][obstacleGrid[0].length-1];
	}
	
	public static void main(String[] args) {
		int[][] obstacleGrid = new int[][]{{0,0,0},
				  {0,1,0},
				  {0,0,0}};
		print(new 不同的路径II().helper(obstacleGrid));
	}

}
