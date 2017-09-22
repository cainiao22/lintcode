package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月21日 下午4:41:52
 * @description
 * 
 * 				给出字符串S和字符串T，计算S的不同的子序列中T出现的个数。
 *              子序列字符串是原始字符串通过删除一些(或零个)产生的一个新的字符串，
 *              并且对剩下的字符的相对位置没有影响。(比如，“ACE”是“ABCDE”的子序列字符串,而“AEC”不是)。
 * 
 *              Do it in O(n2) time and O(n) memory. O(n2) memory is also
 *              acceptable if you do not know how to optimize memory
 * 
 * @example 给出S = "rabbbit", T = "rabbit"
 *
 * @Solution 1、从这个表可以看出，无论T的字符与S的字符是否匹配，dp[i][j] = dp[i][j - 1].就是说，假设S已经匹配了j -
 *           1个字符，得到匹配个数为dp[i][j - 1].现在无论S[j]是不是和T[i]匹配，匹配的个数至少是dp[i][j -
 *           1]。除此之外，当S[j]和T[i]相等时，我们可以让S[j]和T[i]匹配，然后让S[j - 1]和T[i -
 *           1]去匹配。所以递推关系为：
 * 
 *           dp[0][0] = 1; // T和S都是空串.
 * 
 *           dp[0][1 ... S.length() - 1] = 1; // T是空串，S只有一种子序列匹配。
 * 
 *           dp[1 ... T.length() - 1][0] = 0; // S是空串，T不是空串，S没有子序列匹配。
 * 
 *           dp[i][j] = dp[i][j - 1] + (T[i - 1] == S[j - 1] ? dp[i - 1][j - 1]
 *           : 0).1 <= i <= T.length(), 1 <= j <= S.length()
 * 
 *           2、这个东西也可以换成递归方式解决。思想和上面一样 3、延续第一种方式，只不过是从后向前扫描 4、递归方式解决。中间加入备忘录
 *           
 *           3、优化递归，备忘录
 *           4、优化递归，针对(T[i - 1] == S[j - 1]，只匹配相等的对应S的第一个位置的那一对
 */
public class 不同的子序列 extends HH {

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

	// 这个使用的是从后向前地推的思想
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

	// 加入备忘录
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
		//这里做了一个优化，加前面的情况的时候只要第一个两边相等的
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
