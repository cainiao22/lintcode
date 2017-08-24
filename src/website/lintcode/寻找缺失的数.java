package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��11�� ����10:53:49
 * @description ����һ������ 0 .. N �� N ���������У��ҳ�0 .. N ��û�г����������е��Ǹ�����
 * 				��������ԭ����ɣ�ʹ��O(1)�Ķ���ռ��O(N)��ʱ�䡣
 * 
 * @example N = 4 ������Ϊ [0, 1, 3] ʱ��ȱʧ����Ϊ2
 *
 * @Solution 1���ֱ�������͡�0-N��ͣ�Ȼ�����
 * 			 2��������hash��������,��0-N�����һ���ź�������飬
 * 				�������numsҲ����0-N�Ļ������Խ�nums[i]��ֵ��Ӧ����i��λ������
 */
public class Ѱ��ȱʧ���� {

	public int findMissing(int[] nums) {
        // write your code here
		int sum1 = 0, sum2 = 0;
		for(int i=0; i<nums.length; i++) {
			sum1 += i;
			sum2 += nums[i];
		}
		return sum1 + nums.length - sum2;
    }
	
	public int findMissingBetter(int[] nums) {
        // write your code here
		int res = nums.length;
		for(int i=0; i<nums.length; i++) {
			res = res + i - nums[i];
		}
		return res;
    }
	
	public static int findMissingSecond(int[] nums) {
        // write your code here
		int n = nums.length;
		for(int i = 0; i<n; ) {
			//����λ�ò���Ӧ�������ŵ��Լ���Ӧ��λ������ȥ
			//����������ȱʧ������С��N�ģ���ô���ڵ�N�������޷��ҵ�����λ�õģ�
			//���մ�λ���Ǹ�i��λ�ô�ŵ���ʵ����N
			if(nums[i] != i && nums[i] < n) { 
				int t = nums[i];
				nums[i] = nums[t];
				nums[t] = t;
			}else { //��֤��i��λ�ô�ŵ���i�������ֵ��ʱ��ŷ���
				i++;
			}
		}
		for(int i=0; i<n; i++) {
			//����һ�����飬���λ�ú����ֲ���Ӧ����ô������λ�ã���ʵ�����Ĳ���Ӧ�Ļ������λ�ô�ŵ�����Ӧ�þ���N
			if(nums[i] != i) {
				return i;
			}
		}
		//���ȫ����Ӧ������Ȼȱ�ٵľ���N��
		return nums.length;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {9,8,7,6,2,0,1,5,4};
		System.out.println(findMissingSecond(nums));
	}
}
