package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月16日 下午4:48:58
 * @description 在一个二维01矩阵中找到全为1的最大正方形
 * 
 * @example 样例
			1 0 1 0 0
			1 0 1 1 1
			1 1 1 1 1
			1 0 0 1 0
			返回 4
 *
 * @Solution dp 假设a[i][j]代表以matrix[i][j]为右下角的矩阵的最大长度，
 * 				那么，a[i][j] = matrix == 0 ? 0 : min(a[i-1][j], a[i][j-1], a[i-1][j-1]) + 1
 * 			  单调栈
 * 			  暴力 简单改一下，遍历k改成二分k，对比判断改成计算矩阵和来判断，复杂度从n^5降到n^2logn（这样似乎不行)
 */
public class 最大正方形 {
	
	public int maxSquareDP(int[][] matrix) {
        // write your code here
		int[][] a = new int[matrix.length][matrix[0].length];
		int max = 0;
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				if(i == 0 || j == 0 || matrix[i][j] == 0) {
					a[i][j] = matrix[i][j];
				}else {
					a[i][j] = Math.min(a[i][j-1], a[i-1][j]) + 1;
					a[i][j] = Math.min(a[i][j], a[i-1][j-1] + 1);
				}
				max = Math.max(max, a[i][j]);
			}
		}
		
		return max * max;
    }
	
	/**
	 * 
	 * 暴力解法
	 * @param matrix
	 * @return
	 */
	//todo 太麻烦了，暂时放弃
	public int maxSquare(int[][] matrix) {
        // write your code here
		int[][] a = new int[matrix.length][matrix[0].length];
		int[][] sumi = new int[matrix.length+1][matrix[0].length+1];
		int[][] sumj = new int[matrix.length + 1][matrix[0].length + 1];
		for(int i=1; i<matrix.length; i++) {
			for(int j=1; j<matrix[0].length; j++) {
				sumi[i][j] = sumi[i][j-1] + matrix[i-1][j-1];
				sumj[j][i] = sumj[j][i-1] + matrix[i-1][j-1];
			}
		}
		
		int max = 0;
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				if(i == 0 || j == 0 || matrix[i][j] == 0) {
					a[i][j] = matrix[i][j];
				}else {
					a[i][j] = Math.min(a[i][j-1], a[i-1][j]) + 1;
					a[i][j] = Math.min(a[i][j], a[i-1][j-1] + 1);
				}
				max = Math.max(max, a[i][j]);
			}
		}
		
		return max * max;
    }
	
	public static void main(String[] args) {
		//new 最大正方形().maxSquare(matrix);
	}

}
