package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��11��20�� ����12:06:48
 * @description ����һ��δ����������飬���ҳ������������������Ҫ�ص�����ࡣ ��������е�Ҫ������ 2 �����뷵�� 0. 
 * 				�ܷ�ʹ�����Ե�ʱ��Ϳռ临�Ӷȵķ������������⡣
 * @example �������� [1, 9, 2, 5]���������Ϊ [1, 2, 5, 9]�������ļ������ 5 �� 9 ֮�䣬= 4.
 *
 * @Solution ��Ͱ����
 */
public class ����� extends HH {

	public int maximumGap(int[] nums) {
		// write your code here
		if(nums.length <= 1) {
			return 0;
		}
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for(int i=0; i<nums.length; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if(max == min) {
			return 0;
		}
		double average = (max - min)*1.0 / (nums.length - 1);
		int[] localMins = new int[nums.length];
		int[] localMaxs = new int[nums.length];
		for(int i=0; i<localMaxs.length; i++) {
			localMaxs[i] = Integer.MIN_VALUE;
			localMins[i] = -1;
		}
		for(int i=0; i<nums.length; i++) {
			int index = (int)((nums[i] - min)/average);
			localMins[index] = Math.min(localMins[index], nums[i]);
			if(localMins[index] == -1) {
				localMins[index] = nums[i];
			}
			localMaxs[index] = Math.max(localMaxs[index], nums[i]);
		}
		int right = 1, left = 0;
		int ans = Integer.MIN_VALUE;
		while(right < localMaxs.length) {
			while(right < localMaxs.length && localMins[right] == -1) right ++;
			if(right == localMaxs.length) break;
			ans = Math.max(ans, localMins[right] - localMaxs[left]);
			left = right;
			right ++;
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {0, Integer.MAX_VALUE};
		print(new �����().maximumGap(nums));
	}

}
