package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��3��6�� ����6:03:19
 * @description
 * 			������������ num[0..k - 1] �� rem[0..k - 1]. ������num[0..k - 1]��, ���е�Ԫ�ض��ǻ��ʵ�( gcd Ϊ 1 ). ������Ҫ�ҵ�����������������С���� x:

		     x % num[0]    =  rem[0], 
		     x % num[1]    =  rem[1], 
		     .......................
		     x % num[k-1]  =  rem[k-1] 
 * @example
 * 			���� nums = [3,4,5], rems = [2,3,1], ���� 11
			11 ������������������Сֵ:
			- ���� 3, �õ����� 2.
			- ���� 4, �õ����� 3.
			- ���� 5, �õ����� 1.
 *
 * @Solution ��ʼ��num[0]+rems[0],���������ģ������������������һ�εĲ�������nums[i],����������һ����ֱ�����еĶ�����Ϊֹ
 */
public class �й�ʣ�ඨ�� extends HH {
	
	
	public int remainderTheorem(int[] num, int[] rem) {
        // write your code here
		int result = num[0] + rem[0];
		int step = num[0];
		for(int i=1; i<num.length;) {
			if(result % num[i] == rem[i] && step % num[i] != 0) {
				step *= num[i];
				i ++;
			}else {
				result += step;
			}
		}
		
		return result;
    }
	
	public static void main(String[] args) {
		int[] num = new int[] {3,4,5};
		int[] rem = new int[] {2,3,1};
		int result = new �й�ʣ�ඨ��().remainderTheorem(num, rem);
		System.out.println(result);
	}

}
