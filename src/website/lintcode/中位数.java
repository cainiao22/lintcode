package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��12��5�� ����5:13:53
 * @description
 * 		����һ��δ������������飬�ҵ�����λ����
		��λ���������������м�ֵ���������ĸ�����ż�������򷵻����������ĵ�N/2����
 * @example
 * 		��������[4, 5, 1, 2, 3]�� ���� 3
		��������[7, 9, 4, 5]������ 5
 *
 * @Solution
 */
public class ��λ�� extends HH {
	
	public int median(int[] nums) {
        // write your code here
		int mid = (nums.length-1)/2;
		return getKth(nums, 0, nums.length - 1, mid);
    }
	
	int getKth(int[] nums, int start, int end, int k) {
		int flag = nums[start];
		int i=start, j=end;
		while(i < j) {
			while(i < j && nums[i] <= flag) {
				i ++;
			}
			while(i < j && nums[j] > flag) {
				j --;
			}
			if(i < j) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				i ++;
				j --;
			}
		}
		if(flag > nums[j]) {
			nums[start] = nums[j];
			nums[j] = flag;
		}
		if(i == k) {
			return nums[j];
		}else if(i < k) {
			return getKth(nums, i+1, end, k);
		}else {
			return getKth(nums, start, j, k);
		}
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {7, 9, 4, 5};
		print(new ��λ��().median(nums));
	}

}
