package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��24�� ����3:33:13
 * @description
 * 		
		����һ��ֻ������ĸ���ַ�����������Сд��ĸ���д��ĸ��˳���������
		ע������
		
		Сд��ĸ���ߴ�д��ĸ����֮�䲻һ��Ҫ������ԭʼ�ַ����е����λ�á�
		��ԭ��ɨ��һ�����
 * @example
 * 		����"abAcD"��һ�����ܵĴ�Ϊ"acbAD"
 *
 * @Solution
 * 		
 */
public class �ַ���Сд���� {
	
	 public void sortLetters(char[] chars) {
	        // write your code here
		 for(int i=0, j=chars.length-1; i<j; ) {
			 while(i < j && chars[i] >= 'a' && chars[i] <= 'z') {
				 i ++;
			 }
			 
			 while(i < j && chars[j] >= 'A' && chars[j] <= 'Z') {
				 j --;
			 }
			 
			 if(i < j) {
				 char temp = chars[i];
				 chars[i] = chars[j];
				 chars[j] = temp;
				 i ++;
				 j --;
			 }
		 }
	    }
	 
	 public static void main(String[] args) {
		char[] chars = "abAcD".toCharArray();
		new �ַ���Сд����().sortLetters(chars);
	}

}
