package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月11日 下午6:35:17
 * @description 给出一个无序的正数数组，找出其中没有出现的最小正整数。 只允许时间复杂度O(n)的算法，并且只能使用常数级别的空间。
 * @example 如果给出 [1,2,0], return 3 如果给出 [3,4,-1,1], return 2
 *
 * @Solution 与前面的题类似，难点是{}，{0}，{1}，{-1}，{1,2,3},{1,1}这几种情况的适配
 * 			 想复杂了，难点就是{1,1}这种情况，也就是a[i]与他要交换的位置存放的数字一样，这样会陷入死循环·
 */
public class 丢失的第一个正整数 extends HH {

	public int firstMissingPositive(int[] A) {
		// write your code here
		for (int i = 0; i < A.length;) {
			if (A[i] > 0 && A[i] != i + 1 && A[i] < A.length) {
				int t = A[i];
				//避免死循环
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
				//为啥不判断是A.length?为了适配数组为空的情况，这时候进不来这个循环
				//这个时候外面的length变量就成了-1，结果输出的就变成了0
			}
		}
		return A.length + 1;
	}

	public static void main(String[] args) {
		int[] A = new int[] {3,4,-1,1};
		int res = new 丢失的第一个正整数().firstMissingPositive(A);
		System.out.println(res);
	}

}
