package website.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2017��9��1�� ����12:32:15
 * @description ����һ�������������ҳ�һ���Ӿ���ʹ��������֮�͵���0.�����ʱ���뷵���������ֺ��������ֵ����ꡣ
 * 
 * @example ��������

			[
			  [1 ,5 ,7],
			  [3 ,7 ,-8],
			  [4 ,-8 ,9],
			]
			
			���� [(1,1), (2,2)]
 *
 * @Solution 1��������������ά���顣i��jΪ���Ͻ�Ԫ���±꣬k��lΪ���½�Ԫ���±꣬��������������ռ临�ӶȴﵽO(n^4),ʱ�临�Ӷȸߴ�O(n^5).̫ɵ��
 * 			 2��sum[i][j]������Ǵ�(0,0)����i,j)�ľ����
 *	  			����(i,j)(k,l)������������Ϊ0�Ļ�����ôsum(i,l)-sum(i,j) = sum(k,l)-sum(k,j)
 *	 			Ҳ����˵������������(i,j)�ͣ�k,j����ɵ��߶ε��ұ�����Ԫ������ɵľ����+(i,j)(k,l)�����������=����������(i,j)�ͣ�k,j����ɵ��߶ε��ұ�����Ԫ������ɵľ����
 */
public class ��Ϊ����Ӿ��� {
	
	/**
	 * ������
	 * @param matrix
	 * @return
	 */
	public int[][] submatrixSum(int[][] matrix) {
        // Write your code here����
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
	 * sum[i][j]������Ǵ�(0,0)����i,j)�ľ����
	 * ����(i,j)(k,l)������������Ϊ0�Ļ�����ôsum(i,l)-sum(i,j) = sum(k,l)-sum(k,j)
	 * Ҳ����˵������������(i,j)�ͣ�k,j����ɵ��߶ε��ұ�����Ԫ������ɵľ����+(i,j)(k,l)�����������=����������(i,j)�ͣ�k,j����ɵ��߶ε��ұ�����Ԫ������ɵľ����
	 * 
	 * @param matrix
	 * @return
	 */
	//TODO bugδ�����
	public int[][] submatrixSumFromJiuZhang(int[][] matrix){
		int[][] result = new int[2][2];
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] sum = new int[m+1][n+1];
		//���ﻹ������matrixΪ��׼����������
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
			//����ͬһ�еĲ�ͬ�У���������map�д�ŵ���(i,j)(k,j)���ľ������Ϊkey,j��Ϊvalue
			for(int j=1; j<=n; j++) { //j�����У�i��k�ֱ������
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
		int[][] aa = new ��Ϊ����Ӿ���().submatrixSumFromJiuZhang(nums);
		
		for(int i=0; i<aa.length; i++) {
			for(int j=0; j<aa[0].length; j++) {
				System.out.print(aa[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
