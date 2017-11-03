package website.lintcode;

import java.util.Arrays;

/**
 * 
 * @author yanpf
 * @date 2017年11月1日 下午12:26:30
 * @description
 * 		给定两个整数数组（第一个是数组 A，第二个是数组 B），在数组 A 中取 A[i]，
 * 		数组 B 中取 B[j]，A[i] 和 B[j]两者的差越小越好(|A[i] - B[j]|)。返回最小差。
 * 		时间复杂度 O(n log n)
 * @example
 * 		给定数组 A = [3,4,6,7]， B = [2,3,8,9]，返回 0。
 *
 * @Solution  有点类似归并排序
 */
public class 最小差 extends HH {
	
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
        //这个更加精简
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
		print(new 最小差().smallestDifference(A, B));
	}

}
