package website.lintcode;

import java.util.Arrays;

/**
 * 
 * @author yanpf
 * @date 2017��11��1�� ����12:26:30
 * @description
 * 		���������������飨��һ�������� A���ڶ��������� B���������� A ��ȡ A[i]��
 * 		���� B ��ȡ B[j]��A[i] �� B[j]���ߵĲ�ԽСԽ��(|A[i] - B[j]|)��������С�
 * 		ʱ�临�Ӷ� O(n log n)
 * @example
 * 		�������� A = [3,4,6,7]�� B = [2,3,8,9]������ 0��
 *
 * @Solution  �е����ƹ鲢����
 */
public class ��С�� extends HH {
	
	public int smallestDifference(int[] A, int[] B) {
        // write your code here
		Arrays.sort(A);
		Arrays.sort(B);
		int min = Integer.MAX_VALUE;
		int i=0, j=0;
		while(i < A.length && j < B.length) {
			while(i < A.length && A[i] < B[j]) {
				i++;
			}
			if(i == A.length) {
				return Math.min(min,B[j] - A[i-1]);
			}
			min = Math.min(min, A[i] - B[j]);
			if(i > 0) {
				min = Math.min(min, B[j] - A[i-1]);
			}
			
			while(j < B.length && B[j] < A[i]) {
				j ++;
			}
			if(j == B.length) {
				return Math.min(min,A[i] - B[j-1]);
			}
			
			min = Math.min(min, B[j] - A[i]);
			if(j > 0) {
				min = Math.min(min, A[i] - B[j-1]);
			}
			if(min == 0){break;};
		}
		
		return min;
    }
	
	public int smallestDifferenceFromJiuZhang(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 0;
        }
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int ai = 0, bi = 0;
        int min = Integer.MAX_VALUE;
        //������Ӿ���
        while (ai < A.length && bi < B.length) {
            min = Math.min(min, Math.abs(A[ai] - B[bi]));
            if (A[ai] < B[bi]) {
                ai++;
            } else {
                bi++;
            }
        }
        return min;
    }
	
	public static void main(String[] args) {
		int[] A = new int[] {10,25,55,34};
		int[] B = new int[] {18,47,46,42};
		print(new ��С��().smallestDifference(A, B));
	}

}
