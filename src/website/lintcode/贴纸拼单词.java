package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019��2��14�� ����5:18:23
 * 
 * @description	����N�ֲ�ͬ���͵���ֽ�� ÿ����ֽ�϶�д��һ��СдӢ�ĵ��ʡ�
				ͨ���ü���ֽ�ϵ�������ĸ����������ƴ���ַ���target��
				ÿ����ֽ����ʹ�ö�Σ��ٶ�ÿ����ֽ�������ޡ�
				ƴ��target������Ҫ��������ֽ�����������ƴ�ɣ��򷵻�-1��
 * @example		
 * 				���룺
				["with", "example", "science"], "thehat"
				�����
				3
				���ͣ�
				ʹ������"with"��һ��"example"�������ü���������ĸ�����Եõ�"thehat"����Ҳ������Ҫ��������ֽ����
				���� 2:
				
				���룺
				["notice", "possible"], "basicbasic"
				�����
				-1
				���ͣ�
				�޷�ƴ��"basicbasic"��
 *
 * @Solution ѹ״dp + dfs
 */
public class ��ֽƴ���� {
	
	private static List<String> list = new ArrayList<>();
	
	/**
	 * ��ʱ�ˣ����Ż�
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
					//ÿ�����ʱ������õ�ȫ������
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
