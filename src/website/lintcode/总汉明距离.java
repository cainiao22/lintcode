package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年1月24日 下午6:42:10
 * @description	两个整数之间的汉明距离是相应位不同的位置数。
				现在你的工作是找到所有给定数字对之间的总汉明距离
 * @example
 * 
 * 			输入: 4, 14, 2

			输出: 6
			
			说明：在二进制表示中，4是0100,14是1110,2是0010（只是显示在这种情况下相关的四个位）。 所以答案是：
			汉明距离(4,14) + 汉明距离(4,2) + 汉明距离(14,2) = 2 + 2 + 2 = 6。
 *
 * @Solution
 */
public class 总汉明距离 {

	public int totalHammingDistance(int[] nums) {
        // Write your code here
		int sum = 0;
		for(int i=0; i<nums.length - 1; i++) {
			for(int j=i+1; j<nums.length; j++) {
				int r = nums[i] ^ nums[j];
				while(r != 0) {
					r = r >>> 1;
					if((r & 1 ) != 0) {
						sum += 1;
					}
				}
			}
		}
		
		return sum;
    }
}
