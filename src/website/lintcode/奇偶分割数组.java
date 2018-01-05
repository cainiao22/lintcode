package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��12��29�� ����11:38:12
 * @description
 * 		�ָ�һ���������飬ʹ��������ǰż���ں���ԭ��������ɣ���ʹ�ö���ռ䡣
 * 
 * @example
 * 		���� [1, 2, 3, 4]������ [1, 3, 2, 4]��
 *
 * @Solution
 */
public class ��ż�ָ����� extends HH {
	
	public void partitionArray(int[] nums) {
        // write your code here
		int i = 0, j = nums.length - 1;
		while(i < j) {
			while(nums[i] % 2 == 1 && i < j) {
				i ++;
			}
			while(nums[j] % 2 == 0 && j > i) {
				j --;
			}
			
			if(i < j) {
				nums[i] = nums[i]^nums[j];
				nums[j] = nums[i]^nums[j];
				nums[i] = nums[i]^nums[j];
			}
		}
    }
	
	public static void main(String[] args) {
		int[] nums = {1};
		new ��ż�ָ�����().partitionArray(nums);
	}

}
