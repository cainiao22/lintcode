package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��11��3�� ����3:45:17
 * @description
 * 		����һ���ַ�������֤���Ƿ�Ϊ���֡�
 * @example
 * 
 * 		"0" => true
		" 0.1 " => true
		"abc" => false
		"1 a" => false
		"2e10" => true
 *
 * @Solution
 */
public class ��Ч���� {
	
	public boolean isNumber(String s) {
        // write your code here
		if(s == null || s.length() == 0) {
			return false;
		}
		boolean dot = false;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == '.') {
				if(dot) {
					return false;
				}
				dot = true;
			}
			if(s.charAt(i) == ' ') {
				continue;
			}
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				continue;
			}
			return false;
		}
		if(s.charAt(0) == '0') {
			return s.length() == 1 || s.charAt(1) == '.';
		}
		return true;
    }

}
