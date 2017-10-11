package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��11�� ����11:50:13
 * @description
 * 		
		�� n ����ͬ��ֵ��Ӳ���ų�һ���ߡ���������������������������� 1 �� 2 ��Ӳ�ң�ֱ��û��Ӳ��Ϊֹ��
		���������˷ֱ��õ���Ӳ���ܼ�ֵ����ֵ�ߵ��˻�ʤ��
		���ж� ��һ����� ���仹��Ӯ��

 * @example
 * 
 * 		�������� A = [1,2,2], ���� true.
		�������� A = [1,2,4], ���� false
 *
 * @Solution min-max����+���仯��max��helper���ء�min:suffix-helper
 */
public class Ӳ���ų���II extends HH {
	
	private int[] global;
	
	public boolean firstWillWin(int[] values) {
        // write your code here
		//��׺��
		int[] suffix = new int[values.length];
		global = new int[values.length];
		suffix[values.length-1] = values[values.length-1];
		for(int i=values.length-2; i>= 0; i--) {
			suffix[i] = values[i] + suffix[i+1];
		}
		
		int max = helper(values, 0, suffix);
		return max > suffix[0] - max;
		
    }
	
	public int helper(int[] values, int index, int[] suffix) {
		if(values.length - index <= 2) {
			return suffix[index];
		}
		if(global[index] != 0) {
			return global[index];
		}
		int max = Math.max(values[index] + suffix[index+1] - helper(values, index+1, suffix), 
				values[index] + values[index + 1] + suffix[index+2] - helper(values, index+2, suffix));
		
		global[index] = max;
		
		return max;
	}
	
	/**
	 * �������һ����˭�ã�����˭�ã������뱣֤ǰ�����λ�õ�����С���Դ˵��ƣ��Ƶ���0������Ϊ��0��������
	 * @param values
	 * @return
	 */
	public boolean firstWillWinDP(int[] values) {
		if(values.length <= 2) {
			return true;
		}
		int[] suffix = new int[values.length];
		suffix[values.length-1] = values[values.length-1];
		for(int i=values.length-2; i>= 0; i--) {
			suffix[i] = values[i] + suffix[i+1];
		}
		int[] max = new int[values.length];
		max[values.length-1] = suffix[values.length-1];
		max[values.length-2] = suffix[values.length-2];
		for(int i=values.length-3; i>=0; i--) {
			max[i] = Math.max(values[i] + (suffix[i+1] - max[i+1]),
					values[i]+values[i+1] + suffix[i+2] - max[i+2]);
		}
		
		return max[0] > suffix[0]/2;
		
	}
	
	public static void main(String[] args) {
		int[] values = new int[] {16,27,25,23,25,16,12,9,1,2,7,20,19,23,16,0,6,22,16,11,8,27,9,2,20,2,13,7,25,29,12,12,18,29,27,13,16,1,22,9,3,21,29,14,7,8,14,5,0,23,16,1,20};
		System.out.println(new Ӳ���ų���II().firstWillWinDP(new int[] {10000}));
	}

}
