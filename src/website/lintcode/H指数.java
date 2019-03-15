package website.lintcode;

import java.util.Arrays;

/**
 * 
 * @author yanpf
 * @date 2019年1月29日 上午10:55:17
 * @description	给定一个研究员的引用量数组（每个引用量都是一个非负整数）。请计算该研究员的H指数。

				一个研究者的H指数为h，意味着他的论文中，有h篇有至少有h个引用量。
				
				
 * @example		给定引用值citations = [3, 0, 6, 1, 5]，代表研究者有5篇论文，每一篇的引用量为3，0，6，1，5。
 * 				因为研究者有三篇论文至少有3个引用以上，而剩下两篇则没有到三，所以他的H指数为3。
 *
 * @Solution
 */
public class H指数 {
	
	public int hIndex(int[] citations) {
        // write your code here
		Arrays.sort(citations);
		int result = 0;
		for(int i=0; i<citations.length; i++) {
			if(citations[i] >= citations.length - i) {
				result = citations.length - i;
				break;
			}
		}
		
		return result;
    }
	
	public static void main(String[] args) {
		int[] citations = new int[] {3, 0, 6, 1, 5};
		int result = new H指数().hIndex(citations);
		System.out.println(result);
	}

}
