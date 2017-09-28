package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��26�� ����11:19:40
 * @description 
 * 		����һ���������飬��ԭ������ɾ���ظ����ֵ����֣�ʹ��ÿ��Ԫ��ֻ����һ�Σ����ҷ����µ�����ĳ��ȡ�

		��Ҫʹ�ö��������ռ䣬������ԭ��û�ж���ռ�����������
 * @example
 * 		��������A =[1,1,2]����ĺ���Ӧ�÷��س���2����ʱA=[1,2]
 *
 * @Solution
 */
public class ɾ�����������е��ظ����� extends HH {
	
	public int removeDuplicates(int[] nums) {
        // write your code here
		if(nums.length <= 1) {
			return nums.length;
		}
		int i=0, j=0;
		for(; i<nums.length; i++) {
			if(nums[j] != nums[i]) {
				j ++;
				nums[j] = nums[i];
			}
		}
		
		return j + 1;
    }

	public static void main(String[] args) {
		int[] nums = new int[] {1,2,3,4,5};
		int j = new ɾ�����������е��ظ�����().removeDuplicates(nums);
		System.out.println(j);
		print(nums);
	}
}
