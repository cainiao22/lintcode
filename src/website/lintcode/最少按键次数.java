package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��3��28�� ����4:56:33
 * @description
 * 		����һ��ֻ������Сд��ĸ��Ӣ�ĵ��ʣ���������Ҫ�������β��ܽ��������루���԰�caps lock�Լ�shift����һ��ʼĬ������Сд��ĸ��
 * @example
 *		Example 1:
		Input: Hadoop
		Output:7
		Explanation:
		Hold down the Shift key and then h to enter H, then press adoop in turn to enter.
		Example2��
		
		Input��HADOOp
		Output��8
		Explanation:
		First press caps lock, press hadoo, then caps lock, and finally press p.
 * @Solution
 */
public class ���ٰ������� {

	int MAX_VALUE = Integer.MAX_VALUE - 100;
	
	public int getAns(String s) {
        // Write your code here 0 Сд, 1 shift, 2 capslock
		int[][] dp = new int[s.length()][3];
		char first = s.charAt(0);
		if(first >= 'a' && first <= 'z') {
			dp[0][0] = 1;
			dp[0][1] = dp[0][2] = MAX_VALUE;
		}else {
			dp[0][0] = MAX_VALUE;
			//�Ȱ�shift��capslock�� �ڰ���ĸ��
			dp[0][1] = dp[0][2] = 2;
		}
		
		for(int i=1; i<s.length(); i++) {
			char c = s.charAt(i);
			if(c >= 'a' && c <= 'z') { //Сд
				dp[i][0] = Math.min(dp[i-1][0] + 1, dp[i-1][1] + 1);
				dp[i][0] = Math.min(dp[i][0], dp[i-1][2] + 2);
				dp[i][1] = dp[i][2] = MAX_VALUE;
				//���ǰ���Ǹ���дcapslock  ��ô������Ҳ����shiftȻ���ڰ���ĸ��֮���״̬��ʵ��capslock
				dp[i][2] = Math.min(dp[i-1][2] + 2, dp[i][1]);
			}else { //��д ��Ҫ����shift
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
        //����ǰ״̬��lows��ʾ��ǰ״̬������Сд��ups��ʾ��ǰ״̬�����Ǵ�д
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
            {	//��ǰ״̬������Сд���Ǿ�ֱ�Ӽ�1
                lows[i] = 1 + lows[i + 1];
                //��ǰ״̬�����Ǵ�д����Ҫ��capslock ��������ĸ
                ups[i] = 2 + Math.min(lows[i + 1],ups[i + 1]);
            }
            else 
            {
                ups[i]=1 + ups[i + 1];
                lows[i] = 2 + Math.min(lows[i + 1],ups[i + 1]);
            }
        }
        //��Ϊ��һ����ĸ״̬��Сд
        return lows[0];
    }
    private boolean isLow(char ch)
    {
        return 'a' <= ch && ch <= 'z';
    }
	
	public static void main(String[] args) {
		String s = "DCcDCcdDcGvTvEEdddEEddEdEdAs";
		int result = new ���ٰ�������().getAns2(s);
		System.out.println(result);
	}
}
