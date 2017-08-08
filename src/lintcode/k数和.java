package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月8日 上午11:46:57
 * @description 给定n个不同的正整数，整数k（k < = n）以及一个目标数字。
 *              在这n个数里面找出K个数，使得这K个数的和等于目标数字，求问有多少种方案？
 * 
 * @example 给出[1,2,3,4]，k=2， target=5，[1,4] and [2,3]是2个符合要求的方案
 *
 * @Solution sum[target+1][k+1][length+1] 代表从A里面选取k个组成和为target的解决方案个数
 *           sum[i][j][m] = sum[i - A[m - 1]][j - 1][m - 1] + sum[i][j][m - 1],
 *           一般情况，sum[i][j][m]是算上第m个数和不算第m个数的两种情况的和 特殊情况是A[m - 1] ==
 *           i，如果此时只选择一个数的话，选择它就是一个解决方案，但是如果选择多个的话，
 *           那么选择第m-1个数无法成为一个解决方案，那么只能选择前面的那些
 */
public class k数和 {

	public int kSum(int A[], int k, int target) {
		// write your code here
		int length = A.length;
		int[][][] sum = new int[target + 1][k + 1][length + 1];
		for (int i = 0; i <= target; i++) {
			for (int j = 1; j <= k; j++) {
				for (int m = j; m <= length; m++) {
					if (A[m - 1] > i) {
						sum[i][j][m] = sum[i][j][m - 1];
					} else if (A[m - 1] == i) {
						if (j == 1) {
							sum[i][j][m] = 1 + sum[i][j][m - 1];
						} else {
							sum[i][j][m] = sum[i][j][m - 1];
						}
					} else {
						sum[i][j][m] = sum[i - A[m - 1]][j - 1][m - 1] + sum[i][j][m - 1];
					}
				}
			}
		}
		return sum[target][k][length];
	}
}
