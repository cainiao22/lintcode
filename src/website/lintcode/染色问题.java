package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年3月14日 上午10:17:12
 * @description
 * 			有一个圆形，分成n个扇形，用m种颜色给每个扇形染色，相邻扇形颜色不能相同。求方案总数。

			不考虑对称性。
			由于这个数可能很大，因此只需返回方案数模1e9 + 7。
			1 \leq n \leq 10^51≤n≤10
			​5
			​​ 
			1 \leq m \leq 10^51≤m≤10
			​5
​​
 * @example
 * 			给定n = 2，m = 3，返回6。

			解释：
			一个圆划分为 2 个扇形，用 3 种颜色上色方案有“黑红，黑白，白红，白黑，红白，红黑”6 种。
			给定 n = 3，m = 2，返回 0。
			
			解释：
			一个圆划分为 3 个扇形，用 2 种颜色上色，无论怎么上色，都没法保证相邻的颜色不同。
 *
 * @Solution
 */
public class 染色问题 {
	
	/**
	 *  dp[i]表示i个扇形m种配色的上色方案数，n个扇形的染色问题，可以转换为在n-1个扇形中插入一个扇形，有两种情况： 
		i）第1个扇形和第n-1个扇形的颜色不同，有dp[n-1] (由于两个扇形的颜色不同，上色方案是n-1个扇形m中配色)种情况，由于此扇形的颜色不能和另外两个相同，有m-2种颜色； 
		ii）第1个扇形和第n-1个扇形的颜色相同，有dp[n-2] (由于两个扇形的颜色相同，只需考虑n-2个扇形)种情况，由于此扇形的颜色不能和另外两个相同，有m-1种颜色 
		dp[n]=dp[n−1]∗(m−2)+dp[n−2]∗(m−1)dp[n]=dp[n−1]∗(m−2)+dp[n−2]∗(m−1) 
		经过推倒最终结果可表示为 

		an=(m−1)^n+(−1)^n−2∗(m−1) 
		可以使用动态规划，也可以直接求解
		
	 * @param n
	 * @param m
	 * @return
	 */
	public int getCount(int n, int m) {
		int f = 1000000007;
        long[] dp = new long[n + 3];
        long m1 = m;
        dp[0] = m1 % f;
        //如果m直接来的话是两个整数相乘，得出的数也会认为是整数，这样很可能会出现值溢出
        dp[1] = m1*(m1-1) % f;
        //对于3个节点的情况。只有dp[n]=dp[n−1]∗(m−2)一种情况，因为dp[i-1]和 dp[i-2] 是挨着的的，他俩的颜色一定不相同
        dp[2] = dp[1] * (m1-2) % f;
        for(int i=3; i<n; i++) {
        	dp[i] = (dp[i-1] * (m1-2) % f + dp[i-2] * (m1-1) % f) % f;
        }
        
        return (int) (dp[n-1] % f);
    }
	
	public int getCount2(int n, int m) {
		int f = 1000000007;
		long res = myPower(m - 1, n) % f + myPower(-1, n - 2)*(m - 1);
		return (int) res;
	}
	
	//二分法求指数
	long myPower(long n, long m) {
		int f = 1000000007;
		if(n == 0) {
			return 0;
		}
		if(m == 0) {
			return 1;
		}
		
		long res = myPower(n, m >> 1) % f;
		res = res * res % f;
		if((m & 1) == 1) {
			res = res * n % f;
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		int result = new 染色问题().getCount(1000, 1800);
		System.out.println(result);
	}

}
