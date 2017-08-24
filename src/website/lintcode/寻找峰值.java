package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月1日 上午11:23:22
 * 
 * @description 
 * 你给出一个整数数组(size为n)，其具有以下特点：

         相邻位置的数字是不同的
    A[0] < A[1] 并且 A[n - 2] > A[n - 1]

	假定P是峰值的位置则满足A[P] > A[P-1]且A[P] > A[P+1]，返回数组中任意一个峰值的位置。
	
 *
 * @example 给出数组[1, 2, 1, 3, 4, 5, 7, 6]返回1, 即数值 2 所在位置, 或者6, 即数值 7 所在位置.
 * 
 * @Solution 二分查找，跳出条件：A[middle] > A[middle - 1] && A[middle] > A[middle + 1]
 */
public class 寻找峰值 {
	
	 public int findPeak(int[] A) {
	    // write your code here
		int l = 1, r = A.length - 2;
		while(l <= r) {
			int middle = l + (r - l)/2;
			if(A[middle] > A[middle - 1] && A[middle] > A[middle + 1]) {
				return middle;
			} else if(A[middle] < A[middle + 1]) {
				r = middle - 1;
			} else {
				l = middle + 1;
			}
		}
		
		l = 1;
		r = A.length - 2;
		while(l <= r) {
			int middle = l + (r - l)/2;
			if(A[middle] > A[middle - 1] && A[middle] > A[middle + 1]) {
				return middle;
			} else if(A[middle] > A[middle - 1]) {
				l = middle + 1;
			} else {
				r = middle - 1;
			}
		}
		
		return - 1;
	 }
	 
	 public int find2(int[] A) {
		 int l = 1, r = A.length;
	        while (l <= r) {
	            int mid = (l + r) / 2;
	            if (A[mid] > A[mid-1] && A[mid] > A[mid+1])
	                return mid;
	            if (A[mid] > A[mid-1])
	                l = mid + 1;
	            else    
	                r = mid - 1;
	        }
	        return -1;

	 }
	 
	 public static void main(String[] args) {
		 int[] A = new int[] {683,800,926,1710,99,1939,1186,690,595,180,200,992,502,921,191,38,717,817,368,551,950,618,915,40,452,266,416,991,44,946,261,829,8,460,118,883,418,209,483,500,492,912,421,347,233,50,33,781,277,282,700,786,987,831,964,82,153,827,289,47,451,967,622,202,429,268,42,682,857,41,412,427,909,699,214,519,758,12,57,193,961,724,40,857,532,183,75,688,764,729,718,929,6,13,878,788,15,862,227,619,104};
		 int result = new 寻找峰值().find2(A);
		 System.out.println(result);
		 
		 int result2 = new 寻找峰值().findPeak(A);
		 System.out.println(result2);
	}
}
