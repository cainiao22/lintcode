package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年3月11日 上午9:52:47
 * @description
 * 		折纸，每次都是将纸从右向左对折，凹痕为 0，凸痕为 1，求折 n 次后，将纸展开所得折痕组成的 01 序列。
 * @example
 *		输入: 1
		输出: "0"
		
		输入: 2
		输出: "001"
 * @Solution
 */
public class 折纸 {
	
	private StringBuffer sb = new StringBuffer();

	public String getString(int n) {
        // Write your code here
		if(n == 0) {
			return "";
		}
		String left = getString(n - 1);
		sb = new StringBuffer(left);
		sb.append('0');
		for(int i=left.length() - 1; i>=0; i--) {
			sb.append(('1' - left.charAt(i)));
		}
		
		return sb.toString();
    }
	
	public String getStringIterator(int n) {
		if(n == 0) {
			return sb.toString();
		}
		for(int i=1; i<=n; i++) {
			sb.append(0);	
			for(int j=sb.length() - 2; j >= 0; j--) {
				sb.append('1' - sb.charAt(j));
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String result = new 折纸().getStringIterator(3);
		System.out.println(result);
	}
}
