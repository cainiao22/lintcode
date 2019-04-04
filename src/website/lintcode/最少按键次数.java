package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年3月28日 下午4:56:33
 * @description
 * 		给定一个只包含大小写字母的英文单词，问最少需要按键几次才能将单词输入（可以按caps lock以及shift键，一开始默认输入小写字母）
 * @example
 *		Example 1:
		Input: Hadoop
		Output:7
		Explanation:
		Hold down the Shift key and then h to enter H, then press adoop in turn to enter.
		Example2：
		
		Input：HADOOp
		Output：8
		Explanation:
		First press caps lock, press hadoo, then caps lock, and finally press p.
 * @Solution
 */
public class 最少按键次数 {

	int MAX_VALUE = Integer.MAX_VALUE - 100;
	
	public int getAns(String s) {
        // Write your code here 0 小写, 1 shift, 2 capslock
		int[][] dp = new int[s.length()][3];
		char first = s.charAt(0);
		if(first >= 'a' && first <= 'z') {
			dp[0][0] = 1;
			dp[0][1] = dp[0][2] = MAX_VALUE;
		}else {
			dp[0][0] = MAX_VALUE;
			//先按shift或capslock键 在按字母键
			dp[0][1] = dp[0][2] = 2;
		}
		
		for(int i=1; i<s.length(); i++) {
			char c = s.charAt(i);
			if(c >= 'a' && c <= 'z') { //小写
				dp[i][0] = Math.min(dp[i-1][0] + 1, dp[i-1][1] + 1);
				dp[i][0] = Math.min(dp[i][0], dp[i-1][2] + 2);
				dp[i][1] = dp[i][2] = MAX_VALUE;
				//如果前面是个大写capslock  那么我这里也可以shift然后在按字母，之后的状态其实是capslock
				dp[i][2] = Math.min(dp[i-1][2] + 2, dp[i][1]);
			}else { //大写 需要按下shift
				dp[i][0] = MAX_VALUE;
				dp[i][1] = Math.min(dp[i-1][0] + 2, dp[i-1][1] + 2);
				dp[i][1] = Math.min(dp[i][1], dp[i-1][2] + 3);
				
				dp[i][2] = Math.min(dp[i-1][0] + 2, dp[i-1][1] + 2);
				dp[i][2] = Math.min(dp[i][2], dp[i-1][2] + 1);
				
			}
		}
		
		int max = Math.min(dp[s.length() - 1][0], dp[s.length()-1][1]);
		max = Math.min(dp[s.length()-1][2], max);
		return max;
    }
	
	public int getAns2(String s) {
        if(s == null || s.isEmpty())
        {
            return 0;
        }
        //代表当前状态，lows表示当前状态最终是小写，ups表示当前状态最终是大写
        int[] lows = new int[s.length()];
        int[] ups = new int[s.length()];
        int len = s.length();
        char last = s.charAt(len - 1);
        if(isLow(last))
        {
            lows[len - 1] = 1;
            ups[len - 1] = 2;
        }
        else 
        {
            lows[len - 1] = 2;
            ups[len - 1] = 1;
        }
        for(int i = len - 2;i > -1;i --)
        {
            char ch = s.charAt(i);
            if(isLow(ch))
            {	//当前状态最终是小写，那就直接加1
                lows[i] = 1 + lows[i + 1];
                //当前状态最终是大写，需要先capslock 在输入字母
                ups[i] = 2 + Math.min(lows[i + 1],ups[i + 1]);
            }
            else 
            {
                ups[i]=1 + ups[i + 1];
                lows[i] = 2 + Math.min(lows[i + 1],ups[i + 1]);
            }
        }
        //因为第一个字母状态是小写
        return lows[0];
    }
    private boolean isLow(char ch)
    {
        return 'a' <= ch && ch <= 'z';
    }
	
	public static void main(String[] args) {
		String s = "DCcDCcdDcGvTvEEdddEEddEdEdAs";
		int result = new 最少按键次数().getAns2(s);
		System.out.println(result);
	}
}
