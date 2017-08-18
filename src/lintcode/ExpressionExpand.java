package lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年8月17日 下午5:14:41
 * @description Given an expression s includes numbers, letters and brackets. 
 * 				Number represents the number of repetitions inside the brackets(can be a string or another expression)．
 * 				Please expand expression to be a string
 * 
 * @example s = abc3[a] return abcaaa
s = 		3[abc] return abcabcabc
s = 		4[ac]dy, return acacacacdy
s = 		3[2[ad]3[pf]]xyz, return adadpfpfpfadadpfpfpfadadpfpfpfxyz
 *
 * @Solution 递归和迭代两种方式
 */
public class ExpressionExpand {
	
	public String expressionExpand(String s) {
        return dfs(s, 0, s.length());
    }
	
	/**
	 * 性能有问题 需要改进
	 * @param s
	 * @param left
	 * @param right
	 * @return
	 */
	private String dfs(String s, int left, int right) {
		StringBuffer sb = new StringBuffer();
		int repeat = 0;
		for(int i=left; i<right; i++) {
			char a = s.charAt(i);
			if(a >= '0' && a <= '9' ) {
				repeat = repeat * 10 + (a - '0');
			}else if(a == '[') {
				int j=i+1; 
				int begin = 1;
				while(begin != 0) {
					if(s.charAt(j) == '[') {
						begin ++;
					}else if(s.charAt(j) == ']') {
						begin -- ;
					}
					j ++;
				}
				String temp = dfs(s, i+1, j-1);
				while(repeat > 0) {
					sb.append(temp);
					repeat --;
				}
				i = j-1;
			}else {
				sb.append(a);
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String result = new ExpressionExpand().expressionExpand("3[2[ad]3[pf]]xyz");
		System.out.println(result.equals("adadpfpfpfadadpfpfpfadadpfpfpfxyz"));
		System.out.println(result);
	}

}
