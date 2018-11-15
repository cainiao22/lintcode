package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018年4月27日 下午4:24:28
 * @description
 * 		给定 n k  求位数为n 且二进制中01出现次数相同前导不为0 对应10进制值能被k整除的数有多少
 * @example
 *
 * @Solution dp[z][o][m] 代表0的个数为z,1的个数为o,对k取余为m的组合个数，从0开始决策一直到第i位
 * 			如果第i+j位放0： dp[i][j][m] = dp[i-1][j][m];
 * 			如果第i+j位放1：dp[i][j][(m + 1 <<(i+j-1))%k] += dp[i][j-1][(m + 1 <<(i+j-1))%k]
 */
public class DP统计 {
	
	//TODO 有bug 未完成
	public static int solution(int n, int k) {
		
		int[][][] dp = new int[n+1][n+1][k];
		dp[0][0][0] = 1;
		for(int j=1; j<=n; j++) {
			dp[0][j][(1<<(j-1))%k] += 1;
		}
		for(int i=1; i<=n; i++) {
			dp[i][0][0] = 1;
		}
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				for(int m=0; m<k; m++) {
					dp[i][j][m] = dp[i-1][j][m];
				}
				
				for(int m=0; m<k; m++) {
					dp[i][j][(m + 1 <<(i+j-1))%k] += dp[i][j-1][(m + 1 <<(i+j-1))%k];
				}
			}
		}
		
		//假如现在有n个0和n-1个1
		for(int m=0; m<k; m++) {
			dp[n][n][(m + 1 <<(n + n -1))%k] = dp[n][n-1][(m + 1 <<(n + n -1))%k];
		}
		
		return dp[n][n][0];
	}
	
	public static void main(String[] args) {
		System.out.println(solution(3, 3));
		
		
	}
	

}
