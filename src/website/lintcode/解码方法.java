package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年10月10日 下午3:34:12
 * @description
 * 			
		有一个消息包含A-Z通过以下规则编码
		
		'A' -> 1
		'B' -> 2
		...
		'Z' -> 26
		
		现在给你一个加密过后的消息，问有几种解码的方式

 * @example
 * 		给你的消息为12，有两种方式解码 AB(12) 或者 L(12). 所以返回 2
 *
 * @Solution
 */
public class 解码方法 extends HH {
	
	private int count = 0;
	List<Integer> list = new ArrayList<>();
	int[] sum;
	/**
	 * 超时了 
	 * todo 打表优化,记忆化
	 * @param s
	 * @return
	 */
	public int numDecodings(String s) {
        // write your code here
		if(s == null || s.length() == 0) {
			return count;
		}
		sum = new int[s.length()];
		helper(s, 0);
		return count;
    }
	
	private void helper(String s, int index) {
		if(index >= s.length()) {
			count ++;
			return;
		}
		if(sum[index] != 0) {
			count += sum[index];
			return;
		}
		int a = s.charAt(index) - '0';
		if(a == 0) {
			return;
		}
		list.add(a);
		helper(s, index + 1);
		list.remove(list.size()-1);
		if(index + 1 < s.length()) {
			int b = s.charAt(index + 1) - '0';
			if(a*10 + b <=26) {
				list.add(a*10 + b);
				helper(s, index + 2);
				list.remove(list.size()-1);
			}
		}
		
		sum[index] = count;
		
	}
	
	public int numDecodingsWithDP(String s) {
		int[] sum = new int[s.length()];
		if(s == null || s.length() == 0) {
			return 0;
		}
		if(s.charAt(0) != '0') {
			sum[0] = 1;
		}
		for(int i=1; i<s.length(); i++) {
			int b = s.charAt(i) - '0';
			if(b != 0) {
				sum[i] = sum[i-1];
			}
			int a = s.charAt(i-1) - '0';
			if(a*10 + b <= 26 && a*10 + b >= 10) {
				if(i > 1) {
				sum[i] += sum[i-2];
				}else {
					sum[i] += 1;
				}
			}
		}
		
		return sum[sum.length-1];
	}
	
	public int numDecodingsFromJiu(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] nums = new int[s.length() + 1];
        nums[0] = 1;
        nums[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') {
                nums[i] = nums[i - 1];
            }
            
            int twoDigits = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
            if (twoDigits >= 10 && twoDigits <= 26) {
                nums[i] += nums[i - 2];
            }
        }
        return nums[s.length()];
    }
	
	public static void main(String[] args) {
		System.out.println(new 解码方法().numDecodings("192611"));
		
	}
}

// version: 高频题班
 class SolutionFromJiuZhang {
    /**
     * @param s a string,  encoded message
     * @return an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        // Write your code here
        int l = s.length();
        if (l == 0) {
            return 0;   // only for this problem, but the ans should be 1
        }
        int[] f = new int[l + 1];
        f[0] = 1;
        char sc[] = s.toCharArray();

        for (int i = 1; i <= l; i++) {
            if (sc[i - 1] != '0') {
                f[i] += f[i - 1];
            }
            if (i >= 2) {
                int val2 = (sc[i - 2] - '0') * 10 + sc[i - 1] - '0';
                if (10 <= val2 && val2 <= 26) {
                    f[i] += f[i - 2];
                }
            }
        }
        return f[l];
    }

}
