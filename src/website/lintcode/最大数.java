package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月30日 上午9:46:45
 * @description
 * 
 * 				给出一组非负整数，重新排列他们的顺序把他们组成一个最大的整数。 注意事项
 * 
 *              最后的结果可能很大，所以我们返回一个字符串来代替这个整数。
 * 
 * @example 给出 [1, 20, 23, 4, 8]，返回组合最大的整数应为8423201
 *
 * @Solution 排序问题。关键在比较那里，将两个数分别按位分成数组，数组中的每个元素分别对应着每一位的数字，从小到大排列。
 * 			 比较的时候按位比较，类似于字典大小的排序，但是对于位数不同的元素需要做特殊处理。就是类似于1和10以及1和12，
 * 			 这时候，位数少的元素需要做补全处理，处理方式就是补全的时候，将位数少的数组做复制，直到两个数组位数相等。
 * 
 * 			 还有一种方式就是直接拼接，这个比较上一种要简单的多，a,b两个元素，拼接方式只有两种，ab和ba,比较这两个拼接数的大小
 * 			 ab大就是a>b,ba大就是b>a
 */
public class 最大数 extends HH {
	int[] va = new int[10];
	int[] vb = new int[10];
	static final int N = 10;

	public String largestNumber(int[] nums) {
		shellSort(nums);
		print(nums);
		StringBuilder sb = new StringBuilder();
		if (nums.length == 0 || nums[0] == 0) {
			return "0";
		}
		for (int i = 0; i < nums.length; i++) {
			sb.append(nums[i]);
		}

		return sb.toString();
	}
	
	/**
	 * 对应第一种解法
	 * @param a
	 * @param b
	 * @return
	 */
	public int compare(int a, int b) {
		int ai = 0, bi = 0;
		while (a > 0) {
			va[ai++] = a % 10;
			a = a / 10;
		}
		while (b > 0) {
			vb[bi++] = b % 10;
			b = b / 10;
		}
		int i = ai - 1, j = bi - 1;
		while (i >= 0 && j >= 0) {
			for (; i >= 0 && j >= 0; i--, j--) {
				if (va[i] > vb[j]) {
					return 1;
				} else if (va[i] < vb[j]) {
					return -1;
				}
			}
			if (i >= 0 || j >= 0) {
				if (i < 0) {
					i = ai - 1;
				}
				if (j < 0) {
					j = bi - 1;
				}
			}
		}

		return 0;
	}
	
	/**
	 * 对应第二种解法
	 * @param a
	 * @param b
	 * @return
	 */
	public int compare2(int a, int b) {
		String ab = a + "" + b;
		String ba = b + "" + a;
		return ab.compareTo(ba);
	}

	public void shellSort(int[] a) {
		int gap = a.length / 2;
		while (gap > 0) {
			for (int i = gap; i < a.length; i++) {
				for (int j = i - gap; j >= 0 && compare2(a[j], a[j + gap]) < 0; j -= gap) {
					a[j] = a[j] ^ a[j + gap];
					a[j + gap] = a[j] ^ a[j + gap];
					a[j] = a[j] ^ a[j + gap];
				}
			}

			gap /= 2;
		}

	}

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		String result = new 最大数().largestNumber(nums);
		System.out.println(result);
	}

}
