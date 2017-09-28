package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��26�� ����2:32:26
 * @description �������������A��B�ֱ���m��n�������ҵ����������������λ����Ҫ��ʱ�临�Ӷ�ӦΪO(log (m+n))��
 * 
 * @example ��������A = [1,2,3,4,5,6] B = [2,3,4,5]����λ��3.5 ��������A = [1,2,3] B =
 *          [4,5]����λ�� 3
 *
 * @Solution
 * 
 * 		    ����һ������Ϊn������������a����nΪ��������λ��Ϊa[n / 2 + 1] , 
		    ��nΪż��������λ��(a[n / 2] + a[n / 2 + 1]) / 2
		    ������ǿ��������������������KС��Ԫ�أ�����Խ��������
		    ����������AԪ�ظ���Ϊn������BԪ�ظ���Ϊm�����������������kСԪ��
		    ȡA[k / 2] B[k / 2] �Ƚϣ�
		    ��� A[k / 2] > B[k / 2] ��ô�������Ԫ�ر�Ȼ����B��ǰk / 2��Ԫ����(֤����֤��)
		    ��֮����Ȼ����A��ǰk / 2��Ԫ���У��������ǿ��Խ�A��B���е�ǰk / 2Ԫ��ɾȥ����ʣ���������е�
		    k - k / 2СԪ�أ����ǵõ������ݹ�ģ��С��ͬ�����⣬�ݹ���
		    ��� k / 2 ����ĳ���и���������Ԫ�ر�Ȼ������һ���е�ǰk / 2��Ԫ���У�ͬ�ϲ����ͺá�
 */
public class ���������������λ�� {

	//todo ����㷨�Ǵ��,�ο����´�
	public double findMedianSortedArraysError(int[] A, int[] B) {
		// write your code here
		int mid = (A.length + B.length) / 2;
		int startA = 0, endA = A.length - 1;
		while (startA < endA) {
			int midA = (startA + endA) / 2;
			int indexB = findFirstMax(A[midA], B);
			if (midA + indexB + 2 == mid) {
				return A[mid];
			} else if (midA + indexB + 2 < mid) {
				startA = midA + 1;
			} else {
				endA = midA;
			}
		}
		
		int startB = 0, endB = B.length - 1;
		while (startB < endB) {
			int midB = (startB + endB) / 2;
			int indexA = findFirstMax(B[midB], A);
			if (midB + indexA + 2 == mid) {
				return B[mid];
			} else if (midB + indexA + 2 < mid) {
				startB = midB + 1;
			} else {
				endB = midB;
			}
		}
		
		return 0;
	}

	private int findFirstMax(int target, int[] nums) {
		int start = 0, end = nums.length - 1;
		while (start < end) {
			int mid = start + (end - start) / 2;
			if (target == nums[mid]) {
				return mid - 1;
			} else if (target > nums[mid]) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return start - 1;

	}
	
	public double findMedianSortedArrays(int[] A, int[] B) {
		int length = A.length + B.length;
		if(length % 2 == 1) {
			return findKThArrays(A, 0, B, 0, length/2 + 1);
		}else {
			return (findKThArrays(A, 0, B, 0, length/2) + findKThArrays(A, 0, B, 0, length/2 + 1))/2;
		}
	}
	
	public double findKThArrays(int[] A, int startA, int[] B, int startB, int k) {
		if(startA >= A.length) {
			return B[startB + k -1];
		}
		if(startB >= B.length) {
			return A[startA + k - 1];
		}
		if(k == 1) {
			return Math.min(A[startA], B[startB]);
		}
		//��Ϊ�����õ�ʱ���ǵڣ�startA + k/2 - 1����Ԫ��
		if(A.length <= startA + k/2 - 1) {
			return findKThArrays(A, startA, B, startB + k/2, k - k/2);
		}
		if(B.length <= startB + k/2 - 1) {
			return findKThArrays(A, startA + k/2, B, startB, k - k/2);
		}
		
		if(A[startA + k/2-1] < B[startB + k/2-1]) {
			//����֮�����ü�������Ϊk�п��ܻ�������
			return findKThArrays(A, startA + k/2, B, startB, k - k/2);
		}else {
			return findKThArrays(A, startA, B, startB + k/2, k - k/2);
		}
	}
	
	
	public static void main(String[] args) {
		int[] A = new int[] {1,2,3,4};
		int[] B = new int[] {5,6,7,8,9};
		double mid = new ���������������λ��().findMedianSortedArrays(A, B);
		System.out.println(mid);
	}

}
