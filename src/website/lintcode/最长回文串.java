package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��29�� ����4:28:25
 * @description ����һ��������Сд��ĸ���ַ������������Щ��ĸ���ɵ���Ļ��Ĵ��ĳ����Ƕ��١�
 * 				�����Ǵ�Сд���еģ�Ҳ����˵��"Aa" �����ᱻ��Ϊ��һ�����Ĵ���
 * 
 * @example ���� s = "abccccdd" ���� 7.һ�ֿ��Թ�������������Ĵ������� "dccaccd"��
 *
 * @Solution	1�������ⷨ����ֱ�۵ġ�����
 * 				2��ͳ��ÿ���ַ������˼��Σ�Ȼ��ÿ���ַ��Ĵ��������ż���ͱ�����������-1������Ǵ������������д�����ӣ�
 * 				      �����������+1�����Ǵ𰸰ɡ�����û�����������
 */
public class ����Ĵ� {
	
	public int longestPalindrome(String s) {
        int[] hash = new int[128];
		for(int i=0; i<s.length(); i++) {
			char a = s.charAt(i);
			hash[a] += 1;
		}
		int result = 0;
		int flag = 0;
		for(int i=0; i<hash.length; i++) {
			result += hash[i] / 2;
			if(hash[i] % 2 == 1) {
				flag = 1;
			}
		}
		
		return result*2 + flag;
    }

}
