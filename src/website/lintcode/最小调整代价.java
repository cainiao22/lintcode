package website.lintcode;

import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017��11��8�� ����5:22:37
 * @description
 * 		��һ���������飬����ÿ�����Ĵ�С��ʹ�����ڵ��������Ĳ����һ������������target������ÿ�����Ĵ���Ϊ����ǰ��Ĳ�ľ���ֵ�����������֮����С�Ƕ��١�
		ע������
		����Լ���������ÿ��������������������С�ڵ���100
		
 * @example
 * 		��������[1, 4, 2, 3]��target=1����С�ĵ��������ǵ���Ϊ[2, 3, 2, 3]����������֮����2������2��
 *
 * @Solution û˼·�����տ������ϴ𰸡�ö�١���Ϊ�������ֶ�С��100.������ô���������Ҳ��100�п��ܡ�
 *           ���ƹ�ʽ cost[i][j]����A[i]����Ϊj�����ܵõ�����Сֵ��
 *           cost[i][j] = Math.min(cost[i-1][k] + abs(A[i]-j]),k=1,2,3.....
 */
public class ��С�������� {
	
	public int MinAdjustmentCost(List<Integer> A, int target) {
        // write your code here
		int[][] cost = new int[A.size()][101];
		for(int i=1; i<=100; i++) {
			cost[0][i] = Math.abs(A.get(0) - i);
		}
		for(int i=1; i<A.size(); i++) {
			for(int j=1; j<=100; j++) {
				int min = Integer.MAX_VALUE;
				for(int k=1; k<=100; k++) {
					if(Math.abs(j - k) <= target) {
						min = Math.min(cost[i-1][k] + Math.abs(A.get(i) - j), min);
					}
					cost[i][j] = min;
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=1; i<=100; i++) {
			min = Math.min(min, cost[A.size()-1][i]);
		}
		return min;
    }

}
