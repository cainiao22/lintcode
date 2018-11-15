package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年12月29日 上午11:46:17
 * @description
 * 		假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。设计一个算法来找到最大的利润。你最多可以完成两笔交易。
 * @example
 *		给出一个样例数组 [4,4,6,1,1,4,2,5], 返回 6
 * @Solution global[i][j] 前i天不超过j次交易所能得到的最大利润。local[i][j] 前i天不超过j次交易而且最后一次卖出是第i天的最大利润
 * 			 global[i][j] = max(global[i-1][j], local[i][j])
 * 			 local[i][j] = 
 */

//TODO 看不懂答案
public class 买卖股票的最佳时机III {
	
	public int maxProfit(int[] prices) {
        // write your code here
		return 0;
    }
	
}
