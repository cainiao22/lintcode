package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018��4��27�� ����4:24:28
 * @description
 * 		���� n k  ��λ��Ϊn �Ҷ�������01���ִ�����ͬǰ����Ϊ0 ��Ӧ10����ֵ�ܱ�k���������ж���
 * @example
 *
 * @Solution dp[z][o][m] ����0�ĸ���Ϊz,1�ĸ���Ϊo,��kȡ��Ϊm����ϸ�������0��ʼ����һֱ����iλ
 * 			�����i+jλ��0�� dp[i][j][m] = dp[i-1][j][m];
 * 			�����i+jλ��1��dp[i][j][(m + 1 <<(i+j-1))%k] += dp[i][j-1][(m + 1 <<(i+j-1))%k]
 */
public class DPͳ�� {
	
	//TODO ��bug δ���
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
		
		//����������n��0��n-1��1
		for(int m=0; m<k; m++) {
			dp[n][n][(m + 1 <<(n + n -1))%k] = dp[n][n-1][(m + 1 <<(n + n -1))%k];
		}
		
		return dp[n][n][0];
	}
	
	public static void main(String[] args) {
		System.out.println(solution(3, 3));
		
		
	}
	

}
