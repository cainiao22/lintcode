package website.lintcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * @author yanpf
 * @date 2017��10��18�� ����2:33:59
 * @description ��һ��������������,дһ������������ֹ���ֵ���ʱ�ĵ�һ��Ψһ���֣�������ֹ���֣�,
 *              �������ֹ����ǰ��Ψһ���ֻ����Ҳ��������ֹ����, ���� -1
 * @example ��һ�������� [1, 2, 2, 1, 3, 4, 4, 5, 6] �Լ�һ������ 5, ���� 3 ��һ�������� [1, 2, 2,
 *          1, 3, 4, 4, 5, 6] �Լ�һ������ 7, ���� -1
 *
 * @Solution
 */
public class �������е�һ��Ψһ������ extends HH {

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
		int result = new �������е�һ��Ψһ������().firstUniqueNumber(nums, number);
		print(result);
	}

}
