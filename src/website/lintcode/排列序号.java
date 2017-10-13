package website.lintcode;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017��10��13�� ����11:43:13
 * @description
 * 		����һ�������ظ����ֵ����У�����Щ���ֵ��������а��ֵ������������еı�š����У���Ŵ�1��ʼ��
 * @example
 * 		���磬���� [1,2,4] �ǵ� 1 ������
 *
 * @Solution
 * 
 * 		1����n�����ظ����ָղŵ������� n! �֣�

		2�����������д��ҵ���(���ӵ�λ����λ)���з������±�i��0��ʼ�� 
		      ��iλ�����ֺ����ұߵ�ĳһ�����ֽ����󣬶����µ�i-1,i-2,...0λ�ϵ����ֿ������i���ֲ�ͬ�����С�
		      �������Խ�n����Ϊ��nλ��Ȩֵ��

		3������� i λ���ұ���low������С������
		 ��ô����low�����͵� i λ��������֮��õ������У�i,i-1,...0�����ֵ����еı�ſ϶��Ƚ���֮ǰ��ҪС��
		 �ֽ���֮��Ĳ�ͬ������ i ���֣�����ڵ�iλ�����ŵ�����Ϊ i ! * low��

		�ܽ�һ�£��ӵ�λ����λ��
		��������ÿһλ�������ұߵĵ�λ����ɵ������н��и�λ�����λĳһλ��������õ������п��ܵı�ű���С��������Ŀ��
		Ҫ�õ���ű���С�����У�����ֻ������һλ���������λ�ϵģ�����С�ģ����õ�����������ʹ�ö�ÿһλ��������ʱ��
		���ǲ���صģ���û���ظ����г��֡���ÿһλ�������õ��������ۼӿɵõ�������ű���������С����Ŀ���ټ�һ���ɵõ����
 */
public class ������� extends HH {
	
	public long permutationIndexTimeLimt(int[] A) {
        // write your code here
		helper(A, 0);
		Comparator<Integer[]> comparator = new Comparator<Integer[]>() {

			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				for(int i=0; i<o1.length; i++) {
					if(o1[i].intValue() == o2[i].intValue()) {
						continue;
					}else {
						return o1[i] - o2[i];
					}
				}
				return 0;
			}
		};
		Collections.sort(result, comparator);
		Integer[] ax =new Integer[A.length];
		for(int i=0; i<A.length; i++) {
			ax[i] = A[i];
		}
		int i = 0;
		for(int j=0; j<result.size(); j++) {
			i++;
			if(comparator.compare(result.get(j), ax) == 0) {
				break;
			}
		}
		
		return i;
    }
	
	private List<Integer[]> result = new ArrayList<>();
	
	private void helper(int[] A, int index) {
		if(index >= A.length) {
			Integer[] copy = new Integer[A.length];
			for(int j=0; j<A.length; j++) {
				copy[j] = A[j];
			}
			print(A);
			result.add(copy);
			return;
		}
		//��������ʼ��
		for(int i=index; i<A.length; i++) {
			swap(A, index, i);
			helper(A, index+1);
			swap(A, index, i);
		}
	}
	
	/**
	 * ֱ�Ӽ��� 
	 * @param A
	 * @return
	 *  
	 * �Ӻ���ǰ���㣬����������е�ǰ���ж��ٸ�С������
	 * 
	 */
	public long permutationIndex(int[] A) {
		long sum = 1;//���1�������Լ�
		for(int i=A.length-1; i>=0; i--) {
			long weight = 0;
			for(int j=i+1; j<A.length; j++) {
				if(A[i] > A[j]) {
					weight ++;
				}
			}
			//����������ü��仯����
			sum += weight * fac(A.length - i - 1);
		}
		return sum;
	}
	
	
	int[] AX;
	private int getAx(int x) {
		if(x == 0 || x == 1) {
			return 1;
		}
		if(AX[x] != 0) {
			return AX[x];
		}
		
		int a = x * getAx(x-1);
		AX[x] = a;
		return a;
	}
	//�������Ǹ����㷽ʽһ�����������ô���С��0�������
	long fac(int numerator) {
		long now = 1;
		for (int i = 1; i <= numerator; i++) {
			now *= (long) i;
		}
		return now;
	}
	
	public static void main(String[] args) {
		int[] A = new int[] {4,2,1};
		new �������().helper(A, 0);
		print(new �������().permutationIndex(A));
	}

}
