package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月26日 下午2:32:26
 * @description 两个排序的数组A和B分别含有m和n个数，找到两个排序数组的中位数，要求时间复杂度应为O(log (m+n))。
 * 
 * @example 给出数组A = [1,2,3,4,5,6] B = [2,3,4,5]，中位数3.5 给出数组A = [1,2,3] B =
 *          [4,5]，中位数 3
 *
 * @Solution
 * 
 * 		    对于一个长度为n的已排序数列a，若n为奇数，中位数为a[n / 2 + 1] , 
		    若n为偶数，则中位数(a[n / 2] + a[n / 2 + 1]) / 2
		    如果我们可以在两个数列中求出第K小的元素，便可以解决该问题
		    不妨设数列A元素个数为n，数列B元素个数为m，各自升序排序，求第k小元素
		    取A[k / 2] B[k / 2] 比较，
		    如果 A[k / 2] > B[k / 2] 那么，所求的元素必然不在B的前k / 2个元素中(证明反证法)
		    反之，必然不在A的前k / 2个元素中，于是我们可以将A或B数列的前k / 2元素删去，求剩下两个数列的
		    k - k / 2小元素，于是得到了数据规模变小的同类问题，递归解决
		    如果 k / 2 大于某数列个数，所求元素必然不在另一数列的前k / 2个元素中，同上操作就好。
 */
public class 两个排序数组的中位数 {

	//todo 这个算法是错的,参考九章答案
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
		//因为真正拿的时候是第（startA + k/2 - 1）个元素
		if(A.length <= startA + k/2 - 1) {
			return findKThArrays(A, startA, B, startB + k/2, k - k/2);
		}
		if(B.length <= startB + k/2 - 1) {
			return findKThArrays(A, startA + k/2, B, startB, k - k/2);
		}
		
		if(A[startA + k/2-1] < B[startB + k/2-1]) {
			//后面之所以用减法。因为k有可能会是奇数
			return findKThArrays(A, startA + k/2, B, startB, k - k/2);
		}else {
			return findKThArrays(A, startA, B, startB + k/2, k - k/2);
		}
	}
	
	
	public static void main(String[] args) {
		int[] A = new int[] {1,2,3,4};
		int[] B = new int[] {5,6,7,8,9};
		double mid = new 两个排序数组的中位数().findMedianSortedArrays(A, B);
		System.out.println(mid);
	}

}
