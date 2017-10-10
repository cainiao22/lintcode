package website.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年10月9日 下午6:16:08
 * @description 
 * 			Given a set of distinct positive integers, 
 * 			find the largest subset such that every pair (Si, Sj) 
 * 			of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * @example
 * 
 * 		Given nums = [1,2,3], return [1,2] or [1,3]

		Given nums = [1,2,4,8], return [1,2,4,8]
 *
 * @Solution
 */
public class LargestDivisibleSubset extends HH {
	
	public List<Integer> largestDivisibleSubset(int[] nums) {
        // write your code here
		int[] max = new int[nums.length];
		int[] pre = new int[nums.length];
		max[0] = 1;
		Arrays.sort(nums);
		for(int i=1; i<nums.length; i++) {
			int m = 0;
			int p = i;
			for(int j=i-1; j>=0; j--) {
				if(nums[i] % nums[j] == 0 && max[j] > m) {
					m = max[j];
					p = j;
				}
			}
			max[i] = m + 1;
			pre[i] = p;
		}
		int m = 0;
		for(int i=1; i<max.length; i++) {
			if(max[m] < max[i]) {
				m = i;
			}
		}
		List<Integer> result = new ArrayList<>();
		result.add(nums[m]);
		while(pre[m] != m) {
			m = pre[m];
			result.add(nums[m]);
		}
		
		return result;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {1,2,3,4,8,9,12,13,14,18,36};
		List<Integer> result = new LargestDivisibleSubset().largestDivisibleSubset(nums);
		print(result);
	}

}
