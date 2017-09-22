package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��21�� ����4:41:52
 * @description
 * 
 * 				�����ַ���S���ַ���T������S�Ĳ�ͬ����������T���ֵĸ�����
 *              �������ַ�����ԭʼ�ַ���ͨ��ɾ��һЩ(�����)������һ���µ��ַ�����
 *              ���Ҷ�ʣ�µ��ַ������λ��û��Ӱ�졣(���磬��ACE���ǡ�ABCDE�����������ַ���,����AEC������)��
 * 
 *              Do it in O(n2) time and O(n) memory. O(n2) memory is also
 *              acceptable if you do not know how to optimize memory
 * 
 * @example ����S = "rabbbit", T = "rabbit"
 *
 * @Solution 1�����������Կ���������T���ַ���S���ַ��Ƿ�ƥ�䣬dp[i][j] = dp[i][j - 1].����˵������S�Ѿ�ƥ����j -
 *           1���ַ����õ�ƥ�����Ϊdp[i][j - 1].��������S[j]�ǲ��Ǻ�T[i]ƥ�䣬ƥ��ĸ���������dp[i][j -
 *           1]������֮�⣬��S[j]��T[i]���ʱ�����ǿ�����S[j]��T[i]ƥ�䣬Ȼ����S[j - 1]��T[i -
 *           1]ȥƥ�䡣���Ե��ƹ�ϵΪ��
 * 
 *           dp[0][0] = 1; // T��S���ǿմ�.
 * 
 *           dp[0][1 ... S.length() - 1] = 1; // T�ǿմ���Sֻ��һ��������ƥ�䡣
 * 
 *           dp[1 ... T.length() - 1][0] = 0; // S�ǿմ���T���ǿմ���Sû��������ƥ�䡣
 * 
 *           dp[i][j] = dp[i][j - 1] + (T[i - 1] == S[j - 1] ? dp[i - 1][j - 1]
 *           : 0).1 <= i <= T.length(), 1 <= j <= S.length()
 * 
 *           2���������Ҳ���Ի��ɵݹ鷽ʽ�����˼�������һ�� 3��������һ�ַ�ʽ��ֻ�����ǴӺ���ǰɨ�� 4���ݹ鷽ʽ������м���뱸��¼
 *           
 *           3���Ż��ݹ飬����¼
 *           4���Ż��ݹ飬���(T[i - 1] == S[j - 1]��ֻƥ����ȵĶ�ӦS�ĵ�һ��λ�õ���һ��
 */
public class ��ͬ�������� extends HH {

	public static int numDistinct(String S, String T) {
		// write your code here
		int M = T.length();
		int N = S.length();
		if (M > N) {
			return 0;
		}
		if (M == 0) {
			return 1;
		}
		int[][] matrix = new int[M][N];
		if (S.charAt(0) == T.charAt(0)) {
			matrix[0][0] = 1;
		}
		for (int i = 1; i < N; i++) {
			if (S.charAt(i) == T.charAt(0)) {
				matrix[0][i] = matrix[0][i - 1] + 1;
			} else {
				matrix[0][i] = matrix[0][i - 1];
			}
		}
		for (int i = 1; i < T.length(); i++) {
			for (int j = i; j < S.length(); j++) {
				if (T.charAt(i) == S.charAt(j)) {
					matrix[i][j] = matrix[i - 1][j - 1] + matrix[i][j - 1];
				} else {
					matrix[i][j] = matrix[i][j - 1];
				}
			}
		}
		print(matrix);
		return matrix[M - 1][N - 1];
	}

	public static int numDistinctRec(String S, String T) {
		if (S == null || T == null) {
			return 0;
		}

		if (S.length() < T.length()) {
			return 0;
		}

		return rec(S, T, 0, 0);
	}

	// ���ʹ�õ��ǴӺ���ǰ���Ƶ�˼��
	public static int rec(String S, String T, int indexS, int indexT) {
		if (indexT >= T.length()) {
			return 1;
		}
		if (indexS >= S.length()) {
			return 0;
		}
		int sum = 0;
		if (S.charAt(indexS) == T.charAt(indexT)) {
			sum += rec(S, T, indexS + 1, indexT + 1);
		}
		sum += rec(S, T, indexS + 1, indexT);

		return sum;
	}

	// ���뱸��¼
	public static int numDistinctRecWithMem(String S, String T) {
		if (S == null || T == null) {
			return 0;
		}

		if (S.length() < T.length()) {
			return 0;
		}
		int[][] memory = new int[S.length() + 1][T.length() + 1];
		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[0].length; j++) {
				memory[i][j] = -1;
			}
		}
		return recWithMem(S, T, 0, 0, memory);
	}

	public static int recWithMem(String S, String T, int indexS, int indexT, int[][] memory) {
		if (indexT >= T.length()) {
			return 1;
		}
		if (indexS >= S.length()) {
			return 0;
		}
		if (memory[indexS][indexT] != -1) {
			return memory[indexS][indexT];
		}
		int sum = 0;
		if (S.charAt(indexS) == T.charAt(indexT)) {
			sum += rec(S, T, indexS + 1, indexT + 1);
		}
		sum += rec(S, T, indexS + 1, indexT);
		memory[indexS][indexT] = sum;
		return sum;

	}

	public static int recWithMemBetter(String S, String T, int indexS, int indexT, int[][] memory) {
		if (indexT >= T.length()) {
			return 1;
		}
		if (indexS >= S.length()) {
			return 0;
		}
		if (memory[indexS][indexT] != -1) {
			return memory[indexS][indexT];
		}
		int sum = 0;
		//��������һ���Ż�����ǰ��������ʱ��ֻҪ��һ��������ȵ�
		for (int i = indexS; i < S.length(); i++) {
			if (S.charAt(i) == T.charAt(indexT)) {
				sum += rec(S, T, indexS + 1, indexT + 1);
				break;
			}
		}
		sum += rec(S, T, indexS + 1, indexT);
		memory[indexS][indexT] = sum;
		return sum;

	}

	public static void main(String[] args) {
		String S = "aaabbb"; // abbcabddefjge
		String T = "ab"; // abde
		int result = numDistinctRecWithMem(S, T);
		System.out.println(result);
	}

}
