package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019年2月14日 下午5:18:23
 * 
 * @description	给出N种不同类型的贴纸。 每个贴纸上都写有一个小写英文单词。
				通过裁剪贴纸上的所有字母并重排序来拼出字符串target。
				每种贴纸可以使用多次，假定每种贴纸数量无限。
				拼出target最少需要多少张贴纸？如果不可能拼成，则返回-1。
 * @example		
 * 				输入：
				["with", "example", "science"], "thehat"
				输出：
				3
				解释：
				使用两张"with"和一张"example"，经过裁剪和重排字母，可以得到"thehat"。这也是所需要的最少贴纸数。
				样例 2:
				
				输入：
				["notice", "possible"], "basicbasic"
				输出：
				-1
				解释：
				无法拼成"basicbasic"。
 *
 * @Solution 压状dp + dfs
 */
public class 贴纸拼单词 {
	
	private static List<String> list = new ArrayList<>();
	
	/**
	 * 超时了，待优化
	 * @param stickers
	 * @param target
	 * @return
	 */
	public static int minStickers(String[] stickers, String target) {
        // Write your code here
		if(target == null || target.equals("")) {
			return 0;
		}
		int[][] all = new int[stickers.length][26];
		for(int i=0; i<stickers.length; i++) {
			for(char c : stickers[i].toCharArray()) {
				all[i][c - 'a'] ++;
			}
		}
		int[] tmp = new int[26];
		for(char c : target.toCharArray()) {
			tmp[c - 'a'] ++;
		}
		int ans = Integer.MAX_VALUE;
		for(int i=0; i<all.length; i++) {
			if(all[i][target.charAt(0) - 'a'] == 0) {
				continue;
			}
			StringBuffer sb = new StringBuffer();
			for(int j=0; j<26; j++) {
				for(int k=0; k < tmp[j] - all[i][j]; k++) {
					sb.append((char)('a' + j));
				}
			}
			
			int tempAns = minStickers(stickers, sb.toString());
			if(tempAns != -1) {
				ans = Math.min(ans, tempAns + 1);
			}
		}
		
		return ans == Integer.MAX_VALUE ? -1 : ans;
    }
	
	public static int minStickers2(String[] stickers, String target) {
		char[] map = target.toCharArray();
		int n = target.length();
		int[] dp = new int[1 << n];
		for(int i=0; i<dp.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		dp[0] = 0;
		for(int i=0; i<dp.length; i++) {
			if(dp[i] == Integer.MAX_VALUE) {
				continue;
			}
			for(int j=0; j<stickers.length; j++) {
				int cur = i;
				for(char c : stickers[j].toCharArray()) {
					//每个单词必须有用的全部用上
					for(int k=0; k<map.length; k++) {
						if(map[k] == c && ((cur >> k) & 1) == 0) {
							cur |= (1 << k);
							break;
						}
					}
				}
				dp[cur] = Math.min(dp[cur], dp[i] + 1);
			}
		}
		
		return dp[dp.length - 1] == Integer.MAX_VALUE ? -1 :dp[dp.length - 1];
	}
	
	public static void main(String[] args) {
		String[] stickers = new String[] {"fly","me","charge","mind","bottom"};
		String target = "centorder";
		int ans = minStickers2(stickers, target);
		System.out.println(ans);
	}

}
