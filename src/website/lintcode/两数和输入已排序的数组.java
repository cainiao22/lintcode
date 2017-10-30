package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��25�� ����5:17:17
 * @description
 * 		����һ���Ѿ����������е����飬�ҵ�������ʹ���Ǽ������ĺ͵����ض�����
		����Ӧ�÷��������������±꣬index1����С��index2��ע�ⷵ�ص�ֵ���� 0-based��
 * @example
 * 		��������Ϊ [2,7,11,15] ��target = 9
		���� [1,2]
 *
 * @Solution  1������ѭ�����ж��������ӿ���һЩ i < nums.length && nums[i] <= target - nums[i]
 * 			  2��˫ָ�룬һ����ǰɨ��һ���Ӻ�ɨ��
 * 				�����������������ڸ���������βָ��Ӧ����ǰ�ƶ�����С��ʱͷָ������ƶ������ھͿ������������������
 */
public class ��������������������� extends HH {
	
	public int[] twoSum(int[] nums, int target) {
        // write your code here
		int[] result = new int[2];
		for(int i=0; i < nums.length && nums[i] <= target - nums[i]; i++) {
			for(int j=i+1; j<nums.length; j++) {
				if(nums[i] + nums[j] == target) {
					result[0] = i+1;
					result[1] = j+1;
					return result;
				}else if(nums[i] + nums[j] < target) {
					continue;
				}else {
					break;
				}
			}
		}
		
		return result;
    }
	
	public int[] twoSum2(int[] nums, int target) {
		int[] result = new int[2];
		int i=0, j=nums.length-1;
		while(i < j) {
			if(nums[i] + nums[j] > target) {
				j --;
			}else if(nums[i] + nums[j] < target) {
				i++;
			}else {
				result[0] = i+1;
				result[1] = j + 1;
				break;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {2,7,11,15};
		print(new ���������������������().twoSum(nums, 9));
	}

}
