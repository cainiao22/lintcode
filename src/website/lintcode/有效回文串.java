package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��11��1�� ����11:51:40
 * @description
 * 		
		����һ���ַ������ж����Ƿ�Ϊһ�����Ĵ���ֻ������ĸ�����֣����Դ�Сд��
		ע������
		
		���Ƿ��ǹ����ַ����п����ǿ��ַ������������Թ����У����Թٳ������ʵ����⡣
		
		�������Ŀ�У����ǽ����ַ����ж�Ϊ��Ч���ġ�
		������ʵ���������Ƿ�����������⣿ 
		
 * @example
 * 
 * 		"A man, a plan, a canal: Panama" ��һ�����ġ�
		"race a car" ����һ�����ġ�
 *
 * @Solution
 */
public class ��Ч���Ĵ� {
	
	public boolean isPalindrome(String s) {
        // write your code here
		int i =0, j = s.length()-1;
		while(i < j) {
			while(i < j && !isInRange(s.charAt(i))) {
				i ++;
			}
			while(i < j && !isInRange(s.charAt(j))) {
				j --;
			}
			if(i >= j) {
				break;
			}
			if(s.charAt(i) != s.charAt(j) && Math.abs(s.charAt(i) - s.charAt(j)) != ('a' - 'A')) {
				break;
			}
			i ++;
			j --;
		}
		//�������˼
		return j <= i;
    }
	
	private boolean isInRange(char a) {
		return (a >= '0' && a <= '9') || (a>= 'a' && a<= 'z') || (a >= 'A' && a<= 'Z');
	}
	
	public static void main(String[] args) {
		new ��Ч���Ĵ�().isPalindrome("aA");
	}

}
