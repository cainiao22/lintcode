package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年3月6日 下午6:03:19
 * @description
 * 			给出两个数组 num[0..k - 1] 和 rem[0..k - 1]. 在数组num[0..k - 1]中, 所有的元素都是互质的( gcd 为 1 ). 我们需要找到满足下列条件的最小正数 x:

		     x % num[0]    =  rem[0], 
		     x % num[1]    =  rem[1], 
		     .......................
		     x % num[k-1]  =  rem[k-1] 
 * @example
 * 			给出 nums = [3,4,5], rems = [2,3,1], 返回 11
			11 是满足以下条件的最小值:
			- 除以 3, 得到余数 2.
			- 除以 4, 得到余数 3.
			- 除以 5, 得到余数 1.
 *
 * @Solution 初始化num[0]+rems[0],遍历其他的，如果符合条件，那下一次的步长乘以nums[i],继续遍历下一个，直到所有的都满足为止
 */
public class 中国剩余定理 extends HH {
	
	
	public int remainderTheorem(int[] num, int[] rem) {
        // write your code here
		int result = num[0] + rem[0];
		int step = num[0];
		for(int i=1; i<num.length;) {
			if(result % num[i] == rem[i] && step % num[i] != 0) {
				step *= num[i];
				i ++;
			}else {
				result += step;
			}
		}
		
		return result;
    }
	
	public static void main(String[] args) {
		int[] num = new int[] {3,4,5};
		int[] rem = new int[] {2,3,1};
		int result = new 中国剩余定理().remainderTheorem(num, rem);
		System.out.println(result);
	}

}
