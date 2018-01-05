package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018年1月2日 下午5:17:42
 * @description
 * 		给出2*n + 1 个的数字，除其中一个数字之外其他每个数字均出现两次，找到这个数字。一次遍历，常数级的额外空间复杂度
 * 
 * @example
 *		给出 [1,2,2,1,3,4,3]，返回 4
 * @Solution
 */
public class 落单的数 {
	
	public int singleNumber(int[] A) {
        // write your code here
		int ans = A[0];
		for(int i=1; i<A.length; i++) {
			ans = ans ^ A[i];
		}
		
		return ans;
    }

}
