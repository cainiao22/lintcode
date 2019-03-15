package website.lintcode;

/**
 * 
 * @author yanpf
 * 
 * @date 2019��1��11�� ����10:40:22
 * 
 * @description  ����һ�����������飬����෭תһλ0������£��ҵ�������������������1�ĸ���
 * 
 * @example ������������ = [1,0,1,1,0]������4
 *
 * @Solution dp dp[i]�� ��iΪ��β��������1�ĸ���
 */
public class �������1II extends HH {
	
	public int findMaxConsecutiveOnes(int[] nums) {
        // write your code here
		int dp[] = new int[nums.length];
		dp[0] = 1;
		int max = dp[0];
		int lastZero = -1;
		if(nums[0] == 0) {
			lastZero = 0;
		}
		for(int i=1; i<nums.length; i++) {
			if(nums[i] == 0) { //�����i��Ϊ1
				if(nums[i-1] == 0) { //ǰһ��Ԫ��Ϊ0�����ұ��1�����Ⱦ���1
					dp[i] = 1;
				}else { //�Ҳ���0,������������Ǹ�0��λ�þ��Ƿָ��
					dp[i] = i - lastZero;
				}
				lastZero = i;
			}else { //i�������1���ı���ǰ�������������Ǹ�0
				if(lastZero == -1) {
					dp[i] = i - lastZero;
				}else {
					dp[i] = dp[lastZero] + i - lastZero;
				}
				
			}
			max = Math.max(dp[i], max);
		}
		print(dp);
		return max;
    }
	
	/**
	 * ����ⷨ
	 * @param nums
	 * @return
	 */
	public int findMaxConsecutiveOnesBetter(int[] nums) {
		int flip = 0;
		int noFlip = 0;
		int max = 0;
		for(int i=0; i<nums.length; i++) {
			if(nums[i] == 1) {
				flip += 1;
				noFlip += 1;
			}else {
				flip = noFlip + 1;
				noFlip = 0;
			}
			max = Math.max(max, flip);
			
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		int nums[] = new int[] {1,0,1,1,0};
		int max = new �������1II().findMaxConsecutiveOnes(nums);
		System.out.println(max);
	}

}
