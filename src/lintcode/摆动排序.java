package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��9�� ����6:04:18
 * @description ����һ��û����������飬�뽫ԭ����͵��������������������� nums[0] <= nums[1] >= nums[2] <=
 *              nums[3].... ��͵��������飬Ҳ���ǲ���Ҫ��������
 * 
 * @example ��������Ϊ nums = [3, 5, 2, 1, 6, 4] һ���������Ϊ [1, 6, 2, 5, 3, 4]
 *
 * @Solution 1����������һ�飬Ȼ��������м�ֿ��������������˳�����β��뵽ǰ������������ֱ�䡣����������Ǹ�����һ��
 * 
 * 			 2����������һ�飬������ÿ��һ�������������Ľ���һ�Ρ�[1,2,3,4,5,6] [1,3,2,5,4,6]
 * 
 * 			 3����������ֱ�ӻ����ӵ�һ��λ�ÿ�ʼ��
 * 				�±�Ϊ���������������Ӧ�ô������ߵ����������С��ǰ���������ô�ͺ�ǰ�����������
 * 				�±�Ϊż�������������Ӧ��С�����ߵ��������������ǰ���������ô�ͺ�ǰ�����������
 *             ��������һ�ײ���֮���м���Ǹ�����λ��һ���Ǵ������ߵ�ż��λ�õģ�ԭ�������������������
 *             ʣ�µľ��ǣ����������νӵĵط�������һ��һż�������ֻص����������棬������Ӧ�ô������ߵ�����
 *             �����С��ǰ���������һ�ν�������Ϊǰ����Ǹ�ż��һ����С�����ż����Ӧ��ǰ���Ǹ������ģ�������С��ǰ���Ǹ�ż����
 *             ���Խ���֮������ȻС��ǰ��ż����Ӧ����������Ӱ��ǰ��������λ�õı˴�˳�������������ν����ˡ�
 *             
 */
public class �ڶ����� {

	public void wiggleSort(int[] nums) {
		// Write your code here
		if(nums.length <= 1) return;
		heapSort(nums);
		for(int i=1; i<nums.length-1; i+=2) {
			swap(nums, i, i+1);
		}
	}

	public void heapSort(int[] nums) {
		int length = nums.length;
		for (int i = (length - 1) / 2; i >= 0; i--) {
			int j = i;
			do {
				if ((j*2 + 1 < length) && (nums[j] < nums[j * 2 + 1]) || (j * 2 + 2 < length && nums[j] < nums[j * 2 + 2])) {
					int max = j * 2 + 1;
					if (max + 1 < length && nums[max] < nums[max + 1]) {
						max = max + 1;
					}
					swap(nums, j, max);
					j = max;
				} else {
					break;
				}
			} while (j <= (length - 1) / 2);
		}

		for (int i = length - 1; i > 0; i--) {
			swap(nums, 0, i);
			int j = 0;
			do {
				if ((nums[j] < nums[j * 2 + 1] && (j*2 + 1 < i)) || (j * 2 + 2 < i && nums[j] < nums[j * 2 + 2])) {
					int max = j * 2 + 1;
					if (max + 1 < i && nums[max] < nums[max + 1]) {
						max = max + 1;
					}
					swap(nums, j, max);
					j = max;
				} else {
					break;
				}
			} while (j < (i-1)/ 2);
		}
	}

	private void swap(int[] nums, int i, int j) {
		if (nums[i] != nums[j]) {
			nums[i] = nums[i] ^ nums[j];
			nums[j] = nums[i] ^ nums[j];
			nums[i] = nums[i] ^ nums[j];
		}
	}
	
	public void wiggleSortBetter(int[] nums) {
		for(int i=1; i<nums.length; i++) {
			if((i %2 == 1 && nums[i] < nums[i-1]) 
					|| (i % 2 == 0 && nums[i] > nums[i-1])){
				swap(nums, i, i-1);
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 2,1};
		new �ڶ�����().wiggleSort(nums);
	}

}
