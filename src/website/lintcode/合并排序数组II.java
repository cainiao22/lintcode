package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月20日 下午6:10:42
 * @description 合并两个排序的整数数组A和B变成一个新的数组。
 * @example 给出 A = [1, 2, 3, empty, empty], B = [4, 5] 合并之后 A 将变成 [1,2,3,4,5]
 *
 * @Solution
 */
public class 合并排序数组II extends HH {

	public void mergeSortedArray(int[] A, int m, int[] B, int n) {
		// write your code here
		//i不一定从最后面开始，而是从m+n-1开始，也就是刚好可以装下两个数组的有效数字
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
		new 合并排序数组II().mergeSortedArray(A, 3, B, 2);
		print(A);
	}

}
