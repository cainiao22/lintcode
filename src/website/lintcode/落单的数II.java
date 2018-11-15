package website.lintcode;


/**
 * 
 * @author yanpf
 * @date 2018年1月2日 下午5:12:19
 * @description
 * 		给出3*n + 1 个的数字，除其中一个数字之外其他每个数字均出现三次，找到这个数字。
 * 		一次遍历，常数级的额外空间复杂度
 * 
 * @example
 *		给出 [1,1,2,3,3,3,2,2,4,1] ，返回 4
 * @Solution int由32个字节组成。统计每个位置的字节出现次数。如果这个位置可以被3整除。则出现一次的那个数在这一位肯定不为1； 
 */
public class 落单的数II {
	
	public int singleNumberII(int[] A) {
        // write your code here
		int[] bits = new int[32];
		int ans = 0;
		
		//其实把两层循环倒过来。就是一次遍历的。
		for(int i=0; i<bits.length; i++) {
			for(int j=0;j<A.length; j++) {
				bits[i] += (A[j] >> i) & 1;
			}
			bits[i] = bits[i] % 3;
			ans |= bits[i] << i;
		}
		
		return ans;
    }

}
