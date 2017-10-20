package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��16�� ����4:44:55
 * @description
 * 		����һ���ַ�����һ��ƫ����������ƫ������ת�ַ���(����������ת)
 * @example
 * 		�����ַ��� "abcdefg".

		offset=0 => "abcdefg"
		offset=1 => "gabcdef"
		offset=2 => "fgabcde"
		offset=3 => "efgabcd"
		
	

 *
 * @Solution  �����㷨
 */
public class ��ת�ַ��� {
	
	//todo ���
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
	
	//�����ڴ���������
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
		new ��ת�ַ���().rotateString("abcdefg".toCharArray(), 2);
		for(int i=0; i<=3; i++) {
			new ��ת�ַ���().rotateString2("abcdefg".toCharArray(), i);
		}
	}

}
