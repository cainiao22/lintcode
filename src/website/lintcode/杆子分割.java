package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年11月9日 下午2:31:08
 * @description
 * 		给一个 n 英寸长的杆子和一个包含所有小于 n 的尺寸的价格. 确定通过切割杆并销售碎片可获得的最大值.
 * 		例如，如果棒的长度为8，并且不同长度部件的值如下，则最大可获得值为 22(通过切割两段长度 2 和 6 )
 * @example
 * 
 * 		长度    | 1   2   3   4   5   6   7   8  
		--------------------------------------------
		价格    | 1   5   8   9  10  17  17  20
		
		给出 price = [1, 5, 8, 9, 10, 17, 17, 20], n = 8 返回 22//切成长度为 2 和 6 的两段
		
		长度    | 1   2   3   4   5   6   7   8  
		--------------------------------------------
		价格    | 3   5   8   9  10  17  17  20
		
		给出 price = [3, 5, 8, 9, 10, 17, 17, 20], n = 8 返回 24//切成长度为 1 的 8 段
 *
 * @Solution
 */
public class 杆子分割 extends HH {
	
	public int cutting(int[] prices, int n) {
        // Write your code here
		int[] max = new int[n + 1];
		max[0] = 0;
		for(int i=1; i<=n; i++) {
			int price = max[i-1];
			/*for(int j=0; j<prices.length; j++) {
				if(i >= j + 1) {
					price = Math.max(price, max[i-j - 1] + prices[j]);
				}else{
					break;
				}
			}*/
			//优化：1） j不需要遍历到最后
			for(int j=0; j<=i; j++) { 
				price = Math.max(price, max[i-j - 1] + prices[j]);
			}
			max[i] = price;
		}
		return max[n];
    }
	
	public static void main(String[] args) {
		int[] prices = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
		print(new 杆子分割().cutting(prices, 8));
	}

}
