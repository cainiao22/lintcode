package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月16日 下午4:44:55
 * @description
 * 		给定一个字符串和一个偏移量，根据偏移量旋转字符串(从左向右旋转)
 * @example
 * 		对于字符串 "abcdefg".

		offset=0 => "abcdefg"
		offset=1 => "gabcdef"
		offset=2 => "fgabcde"
		offset=3 => "efgabcd"
		
	

 *
 * @Solution  反手算法
 */
public class 旋转字符串 {
	
	//todo 错的
	public void rotateStringError(char[] str, int offset) {
        // write your code here
		for(int i=0; i<offset; i++) {
			int preIndex = (i + str.length - offset)%str.length;
			char temp = str[i];
			while((preIndex + str.length - offset)%str.length != i) {
				str[(preIndex + offset)%str.length] = str[preIndex];
				preIndex = (preIndex + str.length - offset)%str.length;
			}
			str[preIndex] = temp;
		}
		System.out.println(str);
    }
	
	//这是在从右向左倒腾
	public void rotateString(char[] str, int offset) {
		offset = offset %str.length;
        reverse(str, 0, offset-1);
        reverse(str, offset, str.length-1);
        reverse(str, 0, str.length-1);
		System.out.println(str);
    }
	
	public void rotateString2(char[] str, int offset) {
		offset = offset %str.length;
        reverse(str, 0, str.length - offset-1);
        reverse(str, str.length - offset, str.length-1);
        reverse(str, 0, str.length-1);
		System.out.println(str);
    }
	
	private void reverse(char[] str, int start, int end) {
		for(int i=start, j=end; i<j; i++,j--) {
			char temp = str[i];
			str[i] = str[j];
			str[j] = temp;
		}
	}
	
	public static void main(String[] args) {
		new 旋转字符串().rotateString("abcdefg".toCharArray(), 2);
		for(int i=0; i<=3; i++) {
			new 旋转字符串().rotateString2("abcdefg".toCharArray(), i);
		}
	}

}
