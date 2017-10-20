package website.lintcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * @author yanpf
 * @date 2017年10月18日 下午2:33:59
 * @description 给一个连续的数据流,写一个函数返回终止数字到达时的第一个唯一数字（包括终止数字）,
 *              如果在终止数字前无唯一数字或者找不到这个终止数字, 返回 -1
 * @example 给一个数据流 [1, 2, 2, 1, 3, 4, 4, 5, 6] 以及一个数字 5, 返回 3 给一个数据流 [1, 2, 2,
 *          1, 3, 4, 4, 5, 6] 以及一个数字 7, 返回 -1
 *
 * @Solution
 */
public class 数据流中第一个唯一的数字 extends HH {

	public int firstUniqueNumber(int[] nums, int number) {
		// Write your code here
		Queue<Integer> queue = new LinkedList<>();
		Map<Integer, Integer> times = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == number) {
				while (!queue.isEmpty()) {
					int result = queue.poll();
					if (times.get(result) == 1) {
						return result;
					}
				}
				return -1;
			} else {
				Integer time = times.get(nums[i]);
				if (time != null) {
					times.put(nums[i], time + 1);
				} else {
					times.put(nums[i], 1);
					queue.add(nums[i]);
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {1, 2, 2, 1, 3, 4, 4, 5, 6};
		int number = 5;
		int result = new 数据流中第一个唯一的数字().firstUniqueNumber(nums, number);
		print(result);
	}

}
