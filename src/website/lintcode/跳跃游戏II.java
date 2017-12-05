package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��11��30�� ����2:38:36
 * @description
 * 		
		����һ���Ǹ��������飬�������λ������ĵ�һ��λ�á�
		�����е�ÿ��Ԫ�ش��������Ǹ�λ�ÿ�����Ծ����󳤶ȡ�������
		���Ŀ����ʹ�����ٵ���Ծ����������������һ��λ�á�

 * @example
 * 		��������A = [2,3,1,1,4]�����ٵ����������һ��λ�õ���Ծ������2(�������±�0��һ���������±�1��Ȼ����3������������һ��λ�ã�һ����Ծ2��)
 *
 * @Solution
 */
public class ��Ծ��ϷII {
	
	//��̰��
	public int jump(int[] A) {
        // write your code here
		int[] steps = new int[A.length];
		for(int i=1; i<steps.length; i++) {
			steps[i] = Integer.MAX_VALUE;
		}
		for(int i=1; i<A.length; i++) {
			for(int j=0; j<i; j++) {
				if(j + A[j] >= i) {
					steps[i] = Integer.min(steps[i], steps[j] + 1);
				}
			}
		}
		return steps[A.length - 1];
		
    }
	
	/**
	 * ̰���㷨
	 * @param A
	 * @return
	 */
	public int jump2FromJiuzhang(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0, end = 0, jumps = 0;
        while (end < A.length - 1) {
            jumps++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (A[i] + i > farthest) {
                    farthest = A[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
        }
        return jumps;
    }

}
