package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��1��24�� ����6:42:10
 * @description	��������֮��ĺ�����������Ӧλ��ͬ��λ������
				������Ĺ������ҵ����и������ֶ�֮����ܺ�������
 * @example
 * 
 * 			����: 4, 14, 2

			���: 6
			
			˵�����ڶ����Ʊ�ʾ�У�4��0100,14��1110,2��0010��ֻ����ʾ�������������ص��ĸ�λ���� ���Դ��ǣ�
			��������(4,14) + ��������(4,2) + ��������(14,2) = 2 + 2 + 2 = 6��
 *
 * @Solution
 */
public class �ܺ������� {

	public int totalHammingDistance(int[] nums) {
        // Write your code here
		int sum = 0;
		for(int i=0; i<nums.length - 1; i++) {
			for(int j=i+1; j<nums.length; j++) {
				int r = nums[i] ^ nums[j];
				while(r != 0) {
					r = r >>> 1;
					if((r & 1 ) != 0) {
						sum += 1;
					}
				}
			}
		}
		
		return sum;
    }
}
