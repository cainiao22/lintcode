package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017��11��3�� ����4:28:37
 * @description
 * 		����һ���������飬���ҳ�һ�����������飬ʹ�ø�������ĺ���������ʱ��
 * 		��ֱ𷵻ص�һ�����ֺ����һ�����ֵ��±ꡣ�����������ͬ�Ĵ𰸣��뷵����������һ����
 * @example
 * 
 * 		���� [-3, 1, 3, -3, 4], ����[1,4].
 *
 * @Solution
 */
public class ������������� extends HH {
	
	public List<Integer> continuousSubarraySum(int[] A) {
        // write your code here
		int max = Integer.MIN_VALUE;
		int begin = 0, end = 0;
		int current = 0;
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<A.length; i++) {
			current += A[i];
			if(current > max) {
				end = i;
				max = current;
				//�ѵ�ǰ����������ó�����
				list.clear();
				list.add(begin);
				list.add(end);
			}
			if(current < 0) {
				begin = end = i + 1;
				current = 0;
			}
		}
		return list;
    }
	
	/**
	 * �������Ч��һ��
	 * @param A
	 * @return
	 */
	public ArrayList<Integer> continuousSubarraySumFromJiuZhang(int[] A) {
        // Write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(0);
        result.add(0);

        int len = A.length;
        int start = 0, end = 0;
        int sum = 0;
        int ans = -0x7fffffff;
        for (int i = 0; i < len; ++i) {
        	//��������洦��ĺ�һЩ
            if (sum < 0) {
                sum = A[i];
                start = end = i;
            } else {
                sum += A[i];
                end = i;
            }
            if (sum >= ans) {
                ans = sum;
                result.set(0, start);
                result.set(1, end);
            }
        }
        return result;
    }
	
	public static void main(String[] args) {
		int[] A = new int[] {1,2,-2,-100,1,2,-2};
		print(new �������������().continuousSubarraySum(A));
	}

}
