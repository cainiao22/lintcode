package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月11日 下午12:01:53
 * @description 给一个由 1 - n 的整数随机组成的一个字符串序列，其中丢失了一个整数，请找到它。
 * @example 给出 n = 20, str = 19201234567891011121314151618,丢失的数是 17 ，返回这个数。
 *
 * @Solution 1、打表方式 ，用一个数组记录这个字符串中所有0～9的字符个数，对N以内的各个数位出现的次数做一次统计计算
 * 				然后与数组中的出现次数做一次对比，把不对的拉出来，
 * 
 * 			 2、dsf 反正只有两位数，无非就是前一个数是不是与后一个数结合
 */			
public class 寻找丢失的数II {
	
	private int miss = 0;
	private boolean find = false;
	boolean[] hash;
	
	public int findMissing2(int n, String str) {
        // Write your code here
		hash = new boolean[n+1];
		dfsBetter(0, str, n);
		if(!find) {
		}
		return miss;
    }
	
	private void dfs(int index, String str, int n) {
		if(index >= str.length()) {
			for(int i=1; i<n + 1; i++) {
				if(!hash[i]) {
					System.out.println("miss=" + i);
					miss = i;
					find = true;
				}
			}
			return;
		}
		int a = str.charAt(index) - '0';
		if(a != 0 && !hash[a]) {
			hash[a] = true;
			dfs(index+1, str, n);
			hash[a] = false;
		}
		//如果判断出a==0了那么两位数肯定也不可能的
		if(find || a == 0) return;
		//这里有问题，这种写法只适合两位数，需要改进
		if(index + 1 < str.length()) {
			int b = str.charAt(index + 1) - '0';
			a = a * 10 + b;
			if(a > n) return;
			if(!hash[a]) {
				hash[a] = true;
				dfs(index + 2, str, n);
				hash[a] = false;
			}
		}
	}
	
	private void dfsBetter(int index, String str, int n) {
		if(index >= str.length()) {
			for(int i=1; i<n + 1; i++) {
				if(!hash[i]) {
					System.out.println("miss=" + i);
					miss = i;
					find = true;
				}
			}
			return;
		}
		int a = str.charAt(index) - '0';
		if(a == 0) return;
		int j = index;
		//这种写法更具有通用性，不再局限于N的位数
		while(a <= n && !find) {
			if(!hash[a]) {
				hash[a] = true;
				dfs(j + 1, str, n);
				hash[a] = false;
			}
			j++;
			if(j < str.length()) {
				a = a * 10 + (str.charAt(j) - '0');
			}
		}
	}
	
	public static void main(String[] args) {
		int miss = new 寻找丢失的数II().findMissing2(11, "111098765432");
		System.out.println();
		System.out.println(miss);
	}

}
