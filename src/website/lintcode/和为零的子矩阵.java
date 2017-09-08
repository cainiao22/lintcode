package website.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2017年9月1日 下午12:32:15
 * @description 给定一个整数矩阵，请找出一个子矩阵，使得其数字之和等于0.输出答案时，请返回左上数字和右下数字的坐标。
 * 
 * @example 给定矩阵

			[
			  [1 ,5 ,7],
			  [3 ,7 ,-8],
			  [4 ,-8 ,9],
			]
			
			返回 [(1,1), (2,2)]
 *
 * @Solution 1、暴力。创建四维数组。i和j为左上角元素下标，k和l为右下角元素下标，遍历整个情况。空间复杂度达到O(n^4),时间复杂度高达O(n^5).太傻了
 * 			 2、sum[i][j]代表的是从(0,0)到（i,j)的矩阵和
 *	  			对于(i,j)(k,l)所代表的区间和为0的话，那么sum(i,l)-sum(i,j) = sum(k,l)-sum(k,j)
 *	 			也就是说，这个矩阵对于(i,j)和（k,j）组成的线段的右边所有元素所组成的矩阵和+(i,j)(k,l)所代表的区间=这个矩阵对于(i,j)和（k,j）组成的线段的右边所有元素所组成的矩阵和
 */
public class 和为零的子矩阵 {
	
	/**
	 * 纯暴力
	 * @param matrix
	 * @return
	 */
	public int[][] submatrixSum(int[][] matrix) {
        // Write your code here“”
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] sumPrefix = new int[m][n+1];
		for(int i=0; i<m; i++) {
			for(int j=1; j<=n; j++) {
				sumPrefix[i][j] = sumPrefix[i][j-1] + matrix[i][j-1];
			}
		}
		int[][][][] sum = new int[m][n][m][n];
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				for(int k = i; k<m; k++) {
					for(int l = j; l<n; l++) {
						if(k==i && l == j) {
							sum[i][j][k][l] = matrix[i][j];
						}else if(k == i) {
							sum[i][j][k][l] = sum[i][j][k][l-1] + matrix[k][l];
						}else if(l == j) {
							sum[i][j][k][l] = sum[i][j][k-1][l] + matrix[k][l];
						}else {
							int item = 0;
							for(int y=i; y<=k; y++) {
								item += sumPrefix[y][l+1] - sumPrefix[y][j];
							}
							sum[i][j][k][l] = item;
						}
						
						if(sum[i][j][k][l] == 0) {
							return new int[][] {{i,j},{k,l}};
						}
					}
				}
			}
		}
		
		return null;
    }
	
	/**
	 * sum[i][j]代表的是从(0,0)到（i,j)的矩阵和
	 * 对于(i,j)(k,l)所代表的区间和为0的话，那么sum(i,l)-sum(i,j) = sum(k,l)-sum(k,j)
	 * 也就是说，这个矩阵对于(i,j)和（k,j）组成的线段的右边所有元素所组成的矩阵和+(i,j)(k,l)所代表的区间=这个矩阵对于(i,j)和（k,j）组成的线段的右边所有元素所组成的矩阵和
	 * 
	 * @param matrix
	 * @return
	 */
	//TODO bug未解决完
	public int[][] submatrixSumFromJiuZhang(int[][] matrix){
		int[][] result = new int[2][2];
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] sum = new int[m+1][n+1];
		//这里还可以以matrix为基准，倒着来，
		/*for (int i=0; i<M; ++i) {
            for (int j=0; j<N; ++j)
                sum[i+1][j+1] = matrix[i][j] + sum[i+1][j] + sum[i][j+1] - sum[i][j];
        }*/
		for(int i=0;i<=m; i++) {
			for(int j=0; j<= n; j++) {
				if(i==0 || j == 0) {
					sum[i][j] = 0;
				}else {
					sum[i][j] = matrix[i-1][j-1] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];
				}
			}
		}
		
		for(int i=0; i<=m; i++) { 
			Map<Integer, Integer> map = new HashMap<>();
			//对于同一列的不同行，做遍历，map中存放的是(i,j)(k,j)左侧的矩阵和作为key,j作为value
			for(int j=1; j<=n; j++) { //j代表列，i和k分别代表行
				for(int k=i+1;k<=m; k++) {
					int diff = sum[k][j] - sum[i][j];
					if(map.containsKey(diff)) {
						int l = map.get(diff);
						result[0][0] = i;
						result[0][1] = l;
						result[1][0] = k-1;
						result[1][1] = j-1;
						return result;
					}else {
						map.put(diff, j);
					}
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[][] nums = new int[][] {
			
			  /*{1 ,5 ,7},
			  {3 ,7 ,-8},
			  {4 ,-8 ,9},*/
			{0,4},
			{-4,0}
			
		};
		int[][] aa = new 和为零的子矩阵().submatrixSumFromJiuZhang(nums);
		
		for(int i=0; i<aa.length; i++) {
			for(int j=0; j<aa[0].length; j++) {
				System.out.print(aa[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
