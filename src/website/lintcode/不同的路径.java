package website.lintcode;

import java.awt.Image;
import java.math.BigDecimal;

/**
 * 
 * @author yanpf
 * @date 2017��9��25�� ����12:06:45
 * @description  ��һ�������˵�λ��һ�� m �� n ���������Ͻǡ�
				   ������ÿһʱ��ֻ�����»��������ƶ�һ������������ͼ�ﵽ��������½ǡ�
                                         ���ж�������ͬ��·����
 * @example
 *
 * @Solution 1��ֱ����C^(m+n-2),(m-1),�����漰����Լ�֡�ʹ���Ǹ�Լ���㷨
 *           2��dp matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
 */
public class ��ͬ��·�� {
	
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
			System.out.println("mresult:" + mresult + ", nresult:" + nresult + ",��Լ��:" + gongyueshu);
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
		long res = new ��ͬ��·��().uniquePaths(17, 18);
		System.out.println(res);
		int res2 = new ��ͬ��·��().uniquePathsDP(17,18);
		System.out.println(res2);
		int a = 1166803110;
		System.out.println(a);
	}

}
