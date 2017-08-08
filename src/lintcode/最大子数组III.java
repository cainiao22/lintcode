package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��3�� ����1:38:23
 * @description ����һ�����������һ������ k���ҳ� k �����ص�������ʹ�����ǵĺ����
 *              ÿ��������������������е�λ��Ӧ���������ġ��������ĺ͡����������ٰ���һ����
 * 
 * @example �������� [-1,4,-2,3,-2,3] �Լ� k = 2������ 8
 *
 * @Solution ��localMax[i][j]����ǰj������ѡ��i������������ֵ,���е�j��Ϊ��ѡ����ѡ��֤j>=i,�������ֲ��ܺϷ�
 *           globalMax��ʾǰj������ѡ��i���������ȫ�����ֵ��j��һ����ѡ localMax[i][j] =
 *           Math.max(globalMax[i-1][j-1], localMax[i][j-1]) + nums[j]��
 *           ���������Ҫ��Ȼ���Ǻ�ǰ������һ�������Ǹ�����һ����Ҫ��Ȼ���ǵ�����Ϊһ���������(��������ǰ����Ǹ����ų����Ϳ���)��
 */
public class ���������III {

	public int maxSubArray(int[] nums, int k) {
		// write your code here
		int length = nums.length;
		int[][] localMax = new int[length + 1][length + 1];
		int[][] globalMax = new int[length + 1][length + 1];
		for (int i = 0; i < length; i++) {
			localMax[0][i] = 0;
			if (i == 0) {
				localMax[1][0] = nums[0];
				globalMax[1][0] = nums[0];
			} else {
				localMax[1][i] = Math.max(nums[i], localMax[1][i - 1] + nums[i]);
				globalMax[1][i] = Math.max(globalMax[1][i - 1], localMax[1][i]);
			}
		}
		for (int i = 2; i <= length; i++) {
			for (int j = i - 1; j < length; j++) {
				if (j == i - 1) {
					localMax[i][j] = localMax[i - 1][j - 1] + nums[j];
					globalMax[i][j] = localMax[i][j];
				} else {
					// Ϊʲôֱ�Ӻ�globalMax��Ӳ��У�����ȱ����һ�����������nums[j]����ǰ������Ǹ��������ӵĻ����ã���Ϊ��������ζ�Ŵ�ǰ��ȡi-1�����飬
					// ����j�����Ϊһ��������ڣ��������j��ǰ�����һ��������Ļ����Ǿ���ζ��ǰ��ȡ��i-1�����飬��jǿ�Ʊ������ֿ��ˡ�ȱ����һ�����������
					// ��j�������һ�����������Ӳ���ֱ�Ӵ�ǰ��j-1����ȡ��i������
					// ����localMax[i][j-1]+ nums[j]�����j�������һ�����������Ӳ���ֱ�Ӵ�ǰ��j-1����ȡ��i����������
					localMax[i][j] = Math.max(globalMax[i - 1][j - 1], localMax[i][j - 1]) + nums[j];
					globalMax[i][j] = Math.max(localMax[i][j], globalMax[i][j - 1]);
				}
			}
		}

		return globalMax[k][length - 1];
	}

	/**
	 * �Ż����� 
	 * 1����Ȼ�ҵ���ѡ��k�����飬��ôû��Ҫ��ѡ��length����������������� 
	 * 2����취��ǰ���Ǹ����������鲢��һ�����ȥ
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int maxSubArrayBetter(int[] nums, int k) {
		int length = nums.length;
		int[][] localMax = new int[length + 1][length + 1];
		int[][] globalMax = new int[length + 1][length + 1];
		for (int i = 1; i <= k; i++) {
			localMax[i][i-1] = Integer.MIN_VALUE;
			for (int j = i; j <= length; j++) {
				localMax[i][j] = Math.max(globalMax[i-1][j-1], localMax[i][j-1]) + nums[j-1];
				if(i == j) {
					//globalMax[i][j] = localMax[i][j];Ҳ����
					globalMax[i][j] = globalMax[i-1][j-1] + nums[j-1];
				}else {
					globalMax[i][j] = Math.max(globalMax[i][j-1], localMax[i][j]);
				}
			}
		}
		
		return globalMax[k][length];
	}
	
	//todo ��һ���������������  http://www.jiuzhang.com/solution/maximum-subarray-iii
	public int maxSubArrayBetterSecond(int[] nums, int k) {
		return 0;
	}
	public static void main(String[] args) {
		int[] nums = new int[] { -1, -2, -3, -100, -1, -50 };
		int result = new ���������III().maxSubArray(nums, 2);
		System.out.println(result);
	}

}
