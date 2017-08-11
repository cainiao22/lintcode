package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��11�� ����10:29:20
 * @description ������һ�����飬���ĵ�i��Ԫ����һ֧�����Ĺ�Ʊ�ڵ�i��ļ۸�
 * 				��������ֻ�������һ�ν���(����,һ��������Ʊ),
 * 				���һ���㷨���ҳ��������
 * 
 * @example ����һ���������� [3,2,3,1,2], ���� 1 
 *
 * @Solution ���⣬�͵���Ǯ�����޵ģ�ֻ����һ��
 */
public class ������Ʊ�����ʱ�� {
	
	public int maxProfit(int[] prices) {
		 // write your code here
		if(prices.length == 0)
			return 0;
		int max = Integer.MIN_VALUE;
		int res = Integer.MAX_VALUE;
		for(int i=0; i<prices.length; i++) {
			res = Math.min(prices[i], res);
			max = Math.max(prices[i] - res, max);
		}
		
		return max;
    }

}
