package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��31�� ����1:29:18
 * @description
 * 		
		"��ͬ��·��" �ĸ������⣺
		
		���ڿ������������ϰ�����������ж�������ͬ��·����
		
		�����е��ϰ��Ϳ�λ�÷ֱ��� 1 �� 0 ����ʾ��
		ע������
		
		m �� n ��������100

 * @example
 * 		������ʾ��3x3����������һ���ϰ��

		[
		  [0,0,0],
		  [0,1,0],
		  [0,0,0]
		]
		
		һ����2����ͬ��·�������Ͻǵ����½ǡ�
 *
 * @Solution
 */
public class ��ͬ��·��II extends HH {
	
	private int[] dx = new int[] {1, 0};
	private int[] dy = new int[] {0, 1};
	private int count = 0;

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
		helper(obstacleGrid, 0, 0);
		return count;
    }
	
	/**
	 * �ݹ鷽ʽ �ᳬʱ
	 * @param obstacleGrid
	 * @param x
	 * @param y
	 */
	private void helper(int[][] obstacleGrid, int x, int y) {
		if (x == obstacleGrid.length - 1 && y == obstacleGrid[0].length - 1) {
			count++;
			return;
		}
		//��λ�ôӣ�0,0����ʼ��ʱ�����������õ�
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
	 * ��̬�滮
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
		print(new ��ͬ��·��II().helper(obstacleGrid));
	}

}
