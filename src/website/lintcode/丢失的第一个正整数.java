package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��11�� ����6:35:17
 * @description ����һ��������������飬�ҳ�����û�г��ֵ���С�������� ֻ����ʱ�临�Ӷ�O(n)���㷨������ֻ��ʹ�ó�������Ŀռ䡣
 * @example ������� [1,2,0], return 3 ������� [3,4,-1,1], return 2
 *
 * @Solution ��ǰ��������ƣ��ѵ���{}��{0}��{1}��{-1}��{1,2,3},{1,1}�⼸�����������
 * 			 �븴���ˣ��ѵ����{1,1}���������Ҳ����a[i]����Ҫ������λ�ô�ŵ�����һ����������������ѭ����
 */
public class ��ʧ�ĵ�һ�������� extends HH {

	public int firstMissingPositive(int[] A) {
		// write your code here
		for (int i = 0; i < A.length;) {
			if (A[i] > 0 && A[i] != i + 1 && A[i] < A.length) {
				int t = A[i];
				//������ѭ��
				if(A[i] == A[t-1]) {
					i ++;
					continue;
				}
				A[i] = A[t - 1];
				A[t - 1] = t;
			} else {
				i++;
			}
		}
		for (int i = 0; i < A.length; i++) {
			if (A[i] != i+1) {
				return i+1;
				//Ϊɶ���ж���A.length?Ϊ����������Ϊ�յ��������ʱ����������ѭ��
				//���ʱ�������length�����ͳ���-1���������ľͱ����0
			}
		}
		return A.length + 1;
	}

	public static void main(String[] args) {
		int[] A = new int[] {3,4,-1,1};
		int res = new ��ʧ�ĵ�һ��������().firstMissingPositive(A);
		System.out.println(res);
	}

}
