package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年12月14日 下午2:17:00
 * @description
 * @example
 *
 * @Solution
 */
public class 左填充 {

	static public String leftPad(String originalStr, int size) {
        // Write your code here
		return leftPad(originalStr, size, ' ');
    }

    /*
     * @param originalStr: the string we want to append to
     * @param size: the target length of the string
     * @param padChar: the character to pad to the left side of the string
     * @return: A string
     */
    public static String leftPad(String originalStr, int size, char padChar) {
        // write your code here
    	if(originalStr.length() >= size) {
    		return originalStr;
    	}
		String result = "";
		for(int i=0; i<size - originalStr.length(); i++) {
			result += padChar;
		}
		result += originalStr;
		return result;
    }
    
    public static void main(String[] args) {
		System.out.println(leftPad("1", 2, '0'));
	}
}
