package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月11日 上午10:29:20
 * @description 假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
 * 				如果你最多只允许完成一次交易(例如,一次买卖股票),
 * 				设计一个算法来找出最大利润
 * 
 * @example 给出一个数组样例 [3,2,3,1,2], 返回 1 
 *
 * @Solution 烂题，就当是钱是无限的，只能买一股
 */
public class 买卖股票的最佳时机 {
	
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
