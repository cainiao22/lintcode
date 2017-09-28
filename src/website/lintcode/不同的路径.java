package website.lintcode;

import java.awt.Image;
import java.math.BigDecimal;

/**
 * 
 * @author yanpf
 * @date 2017年9月25日 下午12:06:45
 * @description  有一个机器人的位于一个 m × n 个网格左上角。
				   机器人每一时刻只能向下或者向右移动一步。机器人试图达到网格的右下角。
                                         问有多少条不同的路径？
 * @example
 *
 * @Solution 1、直接求，C^(m+n-2),(m-1),这里涉及到了约分。使用那个约分算法
 *           2、dp matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
 */
public class 不同的路径 {
	
	public long uniquePaths(int m, int n) {
        // write your code here
		long x = cBetter(m + n - 2, m < n ? m-1: n-1);
		return x;
    }
	
	public int c(int m, int n) {
		BigDecimal mresult = new BigDecimal(1), nresult = new BigDecimal(1);
		for(int i=0; i< n; i++) {
			mresult = mresult.multiply(new BigDecimal(m-i));
			nresult = nresult.multiply(new BigDecimal(n - i));
		}
		
		return mresult.divide(nresult).intValue();
	}
	
	public int cBetter(int m, int n) {
		int mresult = 1, nresult=1;
		for(int i=0; i<n; i++) {
			mresult *= (m-i);
			nresult *= (n-i);
			int gongyueshu = getGongYueShu(mresult, nresult);
			System.out.println("mresult:" + mresult + ", nresult:" + nresult + ",公约数:" + gongyueshu);
			mresult /= gongyueshu;
			nresult /= gongyueshu;
		}
		System.out.println("mresult:" + mresult + ", nresult:" + nresult);
		return mresult;
	}
	
	private int getGongYueShu(int m, int n) {
		m = m % n;
		if(m == 0) {
			return n;
		}
		return getGongYueShu(n, m);
	}
	
	public int uniquePathsDP(int m, int n) {
        // write your code here
		int[][] matrix = new int[m][n];
		for(int i=0; i<m; i++) {
			matrix[i][0] = 1;
		}
		for(int i=0; i<n; i++) {
			matrix[0][i] = 1;
		}
		for(int i=1; i<m; i++) {
			for(int j=1; j<n; j++) {
				matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
			}
		}
		
		return matrix[m-1][n-1];
    }
	
	public static void main(String[] args) {
		long res = new 不同的路径().uniquePaths(17, 18);
		System.out.println(res);
		int res2 = new 不同的路径().uniquePathsDP(17,18);
		System.out.println(res2);
		int a = 1166803110;
		System.out.println(a);
	}

}
