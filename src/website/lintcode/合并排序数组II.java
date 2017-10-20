package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��20�� ����6:10:42
 * @description �ϲ������������������A��B���һ���µ����顣
 * @example ���� A = [1, 2, 3, empty, empty], B = [4, 5] �ϲ�֮�� A ����� [1,2,3,4,5]
 *
 * @Solution
 */
public class �ϲ���������II extends HH {

	public void mergeSortedArray(int[] A, int m, int[] B, int n) {
		// write your code here
		//i��һ��������濪ʼ�����Ǵ�m+n-1��ʼ��Ҳ���Ǹպÿ���װ�������������Ч����
		int i = A.length - 1;
		m--;
		n--;
		while (m >= 0 && n >= 0) {
			if (A[m] >= B[n]) {
				A[i] = A[m];
				m--;
			} else {
				A[i] = B[n];
				n--;
			}
			i--;
		}

		while (n >= 0) {
			A[i--] = B[n--];
		}
		while (m != i && i < A.length - 1) {
			A[++m] = A[++i];
		}
	}

	public void mergeSortedArrayBetter(int[] A, int m, int[] B, int n) {
		int i = m - 1, j = n - 1, index = m + n - 1;
		while (i >= 0 && j >= 0) {
			if (A[i] > B[j]) {
				A[index--] = A[i--];
			} else {
				A[index--] = B[j--];
			}
		}
		/*while (i >= 0) {
			A[index--] = A[i--];
		}*/
		while (j >= 0) {
			A[index--] = B[j--];
		}
	}

	public static void main(String[] args) {
		int[] A = new int[] { 1, 2, 3, 0, 0, 0 };
		int[] B = new int[] { 4, 5 };
		new �ϲ���������II().mergeSortedArray(A, 3, B, 2);
		print(A);
	}

}
