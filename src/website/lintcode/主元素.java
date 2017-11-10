package website.lintcode;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017��11��10�� ����2:55:50
 * @description
 * 		����һ���������飬�ҳ���Ԫ�أ����������еĳ��ִ����ϸ��������Ԫ�ظ����Ķ���֮һ��
 * 		Ҫ��ʱ�临�Ӷ�ΪO(n)���ռ临�Ӷ�ΪO(1)
 * @example
 * 		��������[1,1,1,1,2,2,2]������ 1
 *
 * @Solution
 */
public class ��Ԫ�� extends HH {
	
	public int majorityNumber(List<Integer> nums) {
        // write your code here
		int count = 0;
		int current = 0;
		for(int i=0; i<nums.size(); i++) {
			if(count == 0) {
				current = nums.get(i);
				count ++;
			}else {
				if(current == nums.get(i)) {
					count ++;
				}else {
					count --;
				}
			}
			if(count > nums.size()/2) {
				break;
			}
		}
		return current;
    }
	
	public static void main(String[] args) {
		print(new ��Ԫ��().majorityNumber(Arrays.asList(1,1,1,1,2,2,2)));
	}
}
