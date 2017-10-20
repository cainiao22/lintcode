package website.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2017年10月16日 下午4:04:42
 * @description 给一个数组和一个滑动窗口的大小, 求每一个窗口内唯一元素的个数和
 * @example 给一个数组 nums = [1, 2, 1, 3, 3] 和 k = 3 第一个窗口为 [1, 2, 1], 只有 2 是唯一的,
 *          计数为 1. 第二个窗口为 [2, 1, 3], 所有的元素都是唯一的, 计数为 3. 第三个窗口为 [1, 3, 3], 只有 1
 *          是唯一的, 计数为 1. 总数为 1 + 3 + 1 = 5 返回 5
 *
 * @Solution
 */
public class 滑动窗口内唯一元素数量和 extends HH {

	public static int slidingWindowUniqueElementsSum(int[] nums, int k) {
		// write your code here
		Map<Integer, Integer> map = new HashMap<>();
		int result = 0;
		int count = 0;
		for(int i=0; i<Math.min(k, nums.length);i++) {
			Integer n = map.get(nums[i]);
			System.out.println(nums[i] + " " + n);
			if(n != null) {
				map.put(nums[i], n+1);
			}else {
				map.put(nums[i], 1);
			}
			System.out.println("===" + map.get(nums[i]) + "===");
		}
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if(entry.getValue() == 1) {
				System.out.println(entry.getKey() + "->" + entry.getValue());
				count ++;
			}
		}
		result += count;
		if(k >= nums.length) {
			return result;
		}
		System.out.println(count);
		for(int i=k; i<nums.length; i++) {
			if(nums[i] == nums[i-k]) {
				result += count;
				continue;
			}
			int tempCount = map.get(nums[i-k]);
			if(tempCount == 1) {
				count --;
				map.remove(nums[i-k]);
			}else {
				if(tempCount == 2) {
					count ++;
				}
				map.put(nums[i-k], tempCount-1);
			}
			if(map.get(nums[i]) == null) {
				count ++;
				map.put(nums[i], 1);
			}else {
				if(map.get(nums[i]) == 1) {
					count --;
				}
				map.put(nums[i], map.get(nums[i]) + 1);
			}
			System.out.println(count);
			result += count;
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = new int[] {1, 2, 1, 3, 3};
		print(slidingWindowUniqueElementsSum(nums, 6));
	}

}
