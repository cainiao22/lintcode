package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��30�� ����9:46:45
 * @description
 * 
 * 				����һ��Ǹ������������������ǵ�˳����������һ������������ ע������
 * 
 *              ���Ľ�����ܴܺ��������Ƿ���һ���ַ������������������
 * 
 * @example ���� [1, 20, 23, 4, 8]�����������������ӦΪ8423201
 *
 * @Solution �������⡣�ؼ��ڱȽ�������������ֱ�λ�ֳ����飬�����е�ÿ��Ԫ�طֱ��Ӧ��ÿһλ�����֣���С�������С�
 * 			 �Ƚϵ�ʱ��λ�Ƚϣ��������ֵ��С�����򣬵��Ƕ���λ����ͬ��Ԫ����Ҫ�����⴦������������1��10�Լ�1��12��
 * 			 ��ʱ��λ���ٵ�Ԫ����Ҫ����ȫ��������ʽ���ǲ�ȫ��ʱ�򣬽�λ���ٵ����������ƣ�ֱ����������λ����ȡ�
 * 
 * 			 ����һ�ַ�ʽ����ֱ��ƴ�ӣ�����Ƚ���һ��Ҫ�򵥵Ķ࣬a,b����Ԫ�أ�ƴ�ӷ�ʽֻ�����֣�ab��ba,�Ƚ�������ƴ�����Ĵ�С
 * 			 ab�����a>b,ba�����b>a
 */
public class ����� extends HH {
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
	 * ��Ӧ��һ�ֽⷨ
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
	 * ��Ӧ�ڶ��ֽⷨ
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
		String result = new �����().largestNumber(nums);
		System.out.println(result);
	}

}
