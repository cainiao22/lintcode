package website.lintcode;

/**
 * 
 * @author yanpf
 * 
 * @date 2019年1月11日 上午10:40:22
 * 
 * @description  给出一个二进制数组，在最多翻转一位0的情况下，找到这个数组里最长的连续的1的个数
 * 
 * @example 给定输入数组 = [1,0,1,1,0]，返回4
 *
 * @Solution dp dp[i]： 以i为结尾的连续的1的个数
 */
public class 最长连续的1II extends HH {
	
	public int findMaxConsecutiveOnes(int[] nums) {
        // write your code here
		int dp[] = new int[nums.length];
		dp[0] = 1;
		int max = dp[0];
		int lastZero = -1;
		if(nums[0] == 0) {
			lastZero = 0;
		}
		for(int i=1; i<nums.length; i++) {
			if(nums[i] == 0) { //必须把i变为1
				if(nums[i-1] == 0) { //前一个元素为0，把我变成1，长度就是1
					dp[i] = 1;
				}else { //我不是0,距离我最近的那个0的位置就是分割点
					dp[i] = i - lastZero;
				}
				lastZero = i;
			}else { //i本身就是1，改变它前面的离我最近的那个0
				if(lastZero == -1) {
					dp[i] = i - lastZero;
				}else {
					dp[i] = dp[lastZero] + i - lastZero;
				}
				
			}
			max = Math.max(dp[i], max);
		}
		print(dp);
		return max;
    }
	
	/**
	 * 精简解法
	 * @param nums
	 * @return
	 */
	public int findMaxConsecutiveOnesBetter(int[] nums) {
		int flip = 0;
		int noFlip = 0;
		int max = 0;
		for(int i=0; i<nums.length; i++) {
			if(nums[i] == 1) {
				flip += 1;
				noFlip += 1;
			}else {
				flip = noFlip + 1;
				noFlip = 0;
			}
			max = Math.max(max, flip);
			
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		int nums[] = new int[] {1,0,1,1,0};
		int max = new 最长连续的1II().findMaxConsecutiveOnes(nums);
		System.out.println(max);
	}

}
