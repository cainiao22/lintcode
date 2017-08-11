package lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2017��8��10�� ����3:17:14
 * @description ������������Ľ�,ÿ��Ԫ�س��ִ����ú���������һ��,�𰸿���������˳�����
 * 
 * @example nums1 = [1, 2, 2, 1], nums2 = [2, 2], ���� [2, 2]
 *
 * @Solution 1���鲢����˼�� 2��hash˼��
 */
public class ������Ľ�II {

	public int[] intersection(int[] nums1, int[] nums2) {
		shellSort(nums1);
		shellSort(nums2);
		int i = 0, j = 0, k = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] == nums2[j]) {
				// ����ȥ�أ�������ĿҪ����Ҫ
				if (k == 0 || nums1[k - 1] != nums1[i]) {
					nums1[k] = nums1[i];
					k++;
				}
				i++;
				j++;
			} else if (nums1[i] < nums2[j]) {
				i++;
			} else {
				j++;
			}
		}
		int[] res = new int[k];
		for (i = 0; i < k; i++) {
			res[i] = nums1[i];
		}
		return res;

	}

	private void shellSort(int[] nums) {
		for (int gap = nums.length / 2; gap > 0; gap /= 2) {
			for (int i = 0; i <= gap; i++) {
				for (int j = i + gap; j < nums.length; j += gap) {
					if (nums[j - gap] > nums[j]) {
						int temp = nums[j];
						int k = j - gap;
						while (k >= 0 && nums[k] > temp) {
							nums[k + gap] = nums[k];
							k -= gap;
						}
						nums[k + gap] = temp;
					}
				}
			}
		}
	}

	private void shellSort2(int[] nums) {
		for (int gap = nums.length / 2; gap > 0; gap /= 2) {
			for (int j = gap; j < nums.length; j++) {
				if (nums[j] < nums[j - gap]) {
					int temp = nums[j];
					int k = j - gap;
					while (k >= 0 && nums[k] > temp) {
						nums[k + gap] = nums[k];
						k -= gap;
					}
					nums[k + gap] = temp;
				}
			}
		}
	}

	/** hash���� ��nums1ӳ�䵽map���棬 ��nums2ȥ��Ӧ **/
	public int[] intersectionSecond(int[] nums1, int[] nums2) {

		// Write your code here

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums1.length; ++i) {

			if (map.containsKey(nums1[i]))

				map.put(nums1[i], map.get(nums1[i]) + 1);

			else

				map.put(nums1[i], 1);

		}

		List<Integer> results = new ArrayList<Integer>();

		for (int i = 0; i < nums2.length; ++i)

			if (map.containsKey(nums2[i]) &&

					map.get(nums2[i]) > 0) {

				results.add(nums2[i]);

				map.put(nums2[i], map.get(nums2[i]) - 1);

			}

		int result[] = new int[results.size()];

		for (int i = 0; i < results.size(); ++i)

			result[i] = results.get(i);

		return result;

	}

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 2, 2, 4, 2, 3, 6, 5, 3, 2, 1, 7, 4, 3, 6, 8, 9, 5, 3, 1 };
		int[] res = new ������Ľ�II().intersection(nums, nums);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + "\t");
		}
	}

}
