package website.lintcode;

import java.awt.Image;

/**
 * 
 * @author yanpf
 * @date 2017��12��19�� ����2:47:10
 * @description
 * 		
		����һ���ַ��� A, ��ʾһ�� n λ������, ɾ������ k λ����, ʹ��ʣ���������Ȼ����ԭ����˳�����в���һ���µ���������
		
		�ҵ�ɾ�� k ������֮�����С��������
		
		N <= 240, k <= N

 * @example
 * 		����һ���ַ�������������� A ��һ������ k, ���� A = 178542, k = 4

		����һ���ַ��� "12"
 *
 * @Solution ������Ԫ��ǰ���ĳЩԪ�ش���������ôɾ����ǰ��������������Ԫ�أ��϶�������ֵ��С��
 * 			 ����������Ҫ��֤���е�˳��Ӹ�λ����λ�������Ǵӵ͵��ߵ�
 */
public class ɾ������ extends HH {
	
	public String DeleteDigits(String A, int l) {
        // write your code here
		char[] chars = A.toCharArray();
		int[] stack = new int[chars.length];
		stack[0] = 0;
		int top = 0, i = 1;
		while(l > 0 && i<chars.length) {
			while(top >= 0 && chars[i] < chars[stack[top]] && l > 0) {
				l --;
				chars[stack[top]] = ' ';
				top --;
			}
			top ++;
			stack[top] = i;
			i ++;
		}
		while(l > 0) {
			while(chars[i - 1] == ' ') {
				i --;
			}
			chars[i - 1] = ' ';
			l --;
		}
		
		StringBuffer sb = new StringBuffer();
		boolean flag = true;
		for(int j=0; j<chars.length; j++) {
			if(chars[j] != ' ') {
				if(chars[j] == '0' && flag) {
					continue;
				}else {
					flag = false;
				}
				sb.append(chars[j]);
			}
		}
		return sb.toString();
    }
	
	/**
	 * �����Ǹ��������Ż��汾
	 * @param A
	 * @param k
	 * @return
	 */
	public String DeleteDigitsFromJiuZhang(String A, int k) {
        // write your code here
        StringBuffer sb = new StringBuffer(A);
        int i, j;
        for (i = 0; i < k; i++) {//��֤��j���ȵ�j+1��С
            for (j = 0; j < sb.length() - 1 && sb.charAt(j) <= sb.charAt(j + 1); j++) {}
            	//�����һ�����Ҵ���ô����Ǹ��Ϳ��Ա�ɾ����
                sb.delete(j, j + 1);
        }
        //ȥ���ײ���0
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.delete(0, 1);
        }
        return sb.toString();
    }
	
	public static void main(String[] args) {
		print(new ɾ������().DeleteDigits("90249", 2));
	}

}
