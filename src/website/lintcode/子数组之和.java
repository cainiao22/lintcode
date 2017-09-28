package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2017年9月27日 上午10:10:47
 * @description 给定一个整数数组，找到和为零的子数组。你的代码应该返回满足要求的子数组的起始位置和结束位置
 * @example 给出 [-3, 1, 2, -3, 4]，返回[0, 2] 或者 [1, 3].
 *
 * @Solution 前i项和做比较，可以双循环也可以用hashMap
 */
public class 子数组之和 extends HH {

	public List<Integer> subarraySum(int[] nums) {
        // write your code here
		int[] sum = new int[nums.length+1];
		sum[0] = 0;
		for(int i=0; i<nums.length; i++) {
			sum[i+1] = sum[i] + nums[i];
		}
		List<Integer> list = new ArrayList<>();
label:	for(int i=0; i<sum.length-1; i++) {
			for(int j=i+1; j<sum.length; j++) {
				if(sum[i] == sum[j]) {
					list.add(i);
					list.add(j-1);
					break label;
				}
			}
		}
		
		return list;
    }
	
	/**
	 * 用hashMap。一面求前i项和一面做比较
	 * @param nums
	 * @return
	 */
	public List<Integer> subarraySumWithHashMap(int[] nums) {
		Map<Integer, Integer> hashMap = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		hashMap.put(0, -1);
		int sum = 0;
		for(int i=0; i<nums.length; i++) {
			sum += nums[i];
			if(hashMap.containsKey(sum)) {
				list.add(hashMap.get(sum) + 1);
				list.add(i);
				return list;
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {3,1,-3,-1};
		List<Integer> list = new 子数组之和().subarraySum(nums);
		print(list);
	}
}
