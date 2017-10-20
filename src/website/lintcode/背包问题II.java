package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月20日 下午3:15:24
 * @description
 * 		给出n个物品的体积A[i]和其价值V[i]，将他们装入一个大小为m的背包，最多能装入的总价值有多大？
 * 		O(n x m) memory is acceptable, can you do it in O(m) memory?
 * @example
 * 		对于物品体积[2, 3, 5, 7]和对应的价值[1, 5, 2, 4], 假设背包大小为10的话，最大能够装入的价值为9。
 *
 * @Solution 
 * 		如果使用二维数组 max[i][j] = max(max[i][j-1],max[i-A[j]][j-1] + v[j])
 * 		某种意义上来说，max[i][j-1],max[i-A[j]][j-1]是一列的数据。和他们的前一列没有关系。
 * 
 * 		也可以倒过来看 max[i][j] = max(max[i-1][j],max[i-1][j-weight[i]] + v[j])
 * 		在这里就要把它看成一行的数据了
 */
public class 背包问题II {
	
	public int backPackI(int m, int[] A, int[] V) {
        // write your code here
		int[][] max = new int[A.length][m + 1];
		for(int i=0; i<max.length; i++) {
			for(int j=0; j<A.length; j++) {
				max[i][j] = Math.max(max[i-1][j], max[i-i][j-A[i]] + V[i]);
			}
		}
		
		return max[A.length-1][m];
    }
	
	public int backPackII(int m, int[] A, int[] V) {
        // write your code here
		int[] max = new int[m + 1];
		for(int i=0; i<A.length; i++) {
			for(int j=max.length-1; j>=A[i]; j--) {
				//避免覆盖，倒着求
				max[j] = Math.max(max[j], max[j-A[i]] + V[i]);
			}
		}
		
		return max[m];
    }

}
