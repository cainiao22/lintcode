package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��3��13�� ����6:09:10
 * @description
 * 		������1��01��str�����������Ѱ�ҵ����01�����Ӵ���0��1������֣�����010��10101��01��
 * 		Ȼ������������lintcode��ѧԱ��˵����Щ���ڼ��ˡ���ô���ڣ�����Խ���һЩ������ʹ��01�����Ӵ������ܵĳ���
 * 		������ָ�������ѡ��һ��λ�ã����ַ����Ͽ�����������ַ�����Ȼ��ÿ���ַ�����ת�������ԭ����˳��ƴ����һ��
 * 		����Խ���0�λ��������Ĳ����������������ܹ���õ����01�����Ӵ��ĳ��ȡ�
 * 
 * 
 * @example
 * 		����str="100010010"������5��

		����Խ������·ָ�10|0010010�����߷�ת�󣬱����01|0100100��
		��010100100��ѡ��λ��1��λ��5��01010������Ϊ5��
		����str="1001"������2��
		
		������ηָת��������ʹ�ô𰸱������10��Ϊ���01�����Ӵ�������2��
 *
 * @Solution
 */
public class Ѱ���01�Ӵ� {

	/**
	 * �����ⷨ   ��ʱ��
	 * @param str
	 * @return
	 */
	public int askingForTheLongest01Substring(String str) {
		int max = 0;
       for(int i=0; i<str.length(); i++) {
    	   int next = str.charAt(i) - '0';
    	   int count = 0;
    	   for(int j=0; j<str.length(); j++) {
    		   int a = str.charAt((i+j) % str.length()) - '0';
    		   if(a == next) {
    			   next = 1 ^ next;
    			   count ++;
    		   }else {
    			   break;
    		   }
    	   }
    	   
    	   max = Integer.max(count, max);
       }
       
       return max;
    }
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public int askingForTheLongest01SubstringDP(String str) {
		//dp[i]��ʾ��iΪ��β�ķ���Ҫ����ַ�������
		int[] dp = new int[str.length()];
		dp[0] = 1;
		for(int i=1; i<str.length(); i++) {
			if(str.charAt(i-1) != str.charAt(i)) {
				dp[i] = dp[i-1] + 1;
			}else{
				dp[i] = 1;
			}
		}
		
		int max = 0;
		for(int i=0; i<dp.length; i++) {
			int item = dp[i];
			if(item == i+1 && str.charAt(0) != str.charAt(dp.length - 1)) {
				item += dp[dp.length - 1];
			}
			
			max = Integer.max(max, item);
		}
		
		if(max > dp.length) {
			max = dp.length;
		}
		
		return max;
		
	}
	
	/**
	 * ��һ��˼·��dp�ⷨ
	 * @param str
	 * @return
	 */
	public int askingForTheLongest01SubstringDP2(String str) {
		int[] dp = new int[str.length() * 2];
		String str2 = str + str;
		dp[0] = 1;
		int max = 0;
		for(int i=1; i<str2.length(); i++) {
			if(str2.charAt(i-1) != str2.charAt(i)) {
				dp[i] = dp[i-1] + 1;
			}else{
				dp[i] = 1;
			}
			if(dp[i] >= dp.length / 2) {
				return dp[i];
			}
			if(dp[i] > max) {
				max = dp[i];
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		String str = "010101";
		System.out.println(new Ѱ���01�Ӵ�().askingForTheLongest01SubstringDP(str));
	}
}
