package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2017��9��27�� ����10:10:47
 * @description ����һ���������飬�ҵ���Ϊ��������顣��Ĵ���Ӧ�÷�������Ҫ������������ʼλ�úͽ���λ��
 * @example ���� [-3, 1, 2, -3, 4]������[0, 2] ���� [1, 3].
 *
 * @Solution ǰi������Ƚϣ�����˫ѭ��Ҳ������hashMap
 */
public class ������֮�� extends HH {

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
	 * ��hashMap��һ����ǰi���һ�����Ƚ�
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
		List<Integer> list = new ������֮��().subarraySum(nums);
		print(list);
	}
}
