package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��15�� ����11:29:38
 * @description ����һ���� n ��������ɵ������һ�������� s �����ҳ���������������� �� s ����С���������顣����޽⣬�򷵻� -1
 *              ������Ѿ������O(n)ʱ�临�Ӷȵı�̣��������� O(n log n)ʱ�临�Ӷȡ�
 * 
 * @example �������� [2,3,1,2,4,3] �� s = 7, ������ [4,3] �Ǹ������µ���С����������
 *
 * @Solution ��һ�֣�����ָ���ǰ������ɨ�裬j���ӵĲ�����i�����Ĳ�����min = j - i,������һ�����в��� 
 * 			  �ڶ��֣����������ǰ׺�ͣ������ң���sum[i]-sum[length-1]�ֲ��ҵ�һ�����ڵ���sum[i] + s��Ԫ�أ�
 * 			  �����и��Ż����������sum[i]-sum[length-1]û�д��ڵ���sum[i] + s��Ԫ�أ���ô�������ֱ�������ˡ�
 */
public class �ʹ���S����С������ {

	public int minimumSize(int[] nums, int s) {
		// write your code here
		if(nums.length == 0) return -1;
		int i = 0, j = 0;
		int sum = 0;
		int min = nums.length;
		while (j <= nums.length) {
			while (sum - nums[i] >= s) {
				sum = sum - nums[i];
				i++;
			}
			if (sum >= s && min > j - i) {
				min = j - i;
			}
			if (j == nums.length)
				break;
			sum += nums[j];
			j++;
		}
		if (sum >= s) {
			return min;
		} else {
			return -1;
		}
	}
	
	/**
	 * ���Ӽ���д����˼·�͵�һ������
	 * @param nums
	 * @param s
	 * @return
	 */
	public int minimumSizeAnother(int[] nums, int s) {
		if(nums.length == 0) return -1;
		int i = 0, j = 0;
		int sum = 0, min = Integer.MAX_VALUE;
		while(j < nums.length) {
			sum += nums[j++];
			while(sum >= s) {
				min = Math.min(j-i, min);
				sum -= nums[i++];
			}
		}
		
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	public int minimunSizeWithBinarySearch(int[] nums, int s) {
		int[] sum = new int[nums.length + 1];
		for (int i = 1; i < sum.length; i++)
			sum[i] = sum[i - 1] + nums[i - 1];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < sum.length; i++) {
			int target = sum[i] + s;
			int index = binarySearch(i, sum.length - 1, target, sum);
			//�������ɼ������binarySearch˵��
			if(index == sum.length) break;
			min = Math.min(min, index - i);
		}

		return min == Integer.MAX_VALUE ? -1 : min;

	}

	public int binarySearch(int low, int high, int target, int[] sum) {
		//�������һ�����ںţ�Ŀ����Ϊ��ʹ�������sum��û��targetʱ�򣬷���sum.length.
		//��ʱ��ʹ���sum��û����Ҫ���ҵ�Ŀ�꣬������Դ����溯������ѭ�����������ˡ�
		while (low <= high) {
			int mid = (low + high) / 2;
			if (target > sum[mid]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return low;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 2, 3, 1, 2, 4, 3 };
		System.out.println(new �ʹ���S����С������().minimunSizeWithBinarySearch(nums, 100));
	}

}
