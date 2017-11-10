package website.lintcode;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年11月10日 下午2:55:50
 * @description
 * 		给定一个整型数组，找出主元素，它在数组中的出现次数严格大于数组元素个数的二分之一。
 * 		要求时间复杂度为O(n)，空间复杂度为O(1)
 * @example
 * 		给出数组[1,1,1,1,2,2,2]，返回 1
 *
 * @Solution
 */
public class 主元素 extends HH {
	
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
		print(new 主元素().majorityNumber(Arrays.asList(1,1,1,1,2,2,2)));
	}
}
