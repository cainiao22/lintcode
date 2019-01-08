package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��1��7��
 * @description ����һ���ǿ��ַ������ж����ܷ�ͨ���ظ�����ĳһ���Ӵ����ɴΣ����μ����ϣ��õ����ַ�����Сд��ĸ��ɣ��������ĳ��Ȳ��ᳬ��10000
 * 
 * @example ����1��
 * 
 *          ���룺"abab"
 * 
 *          �����True
 * 
 *          ˵���������������Ӵ�"ab"�ظ����εõ��� ����2��
 * 
 *          ���룺"aba"
 * 
 *          �����False ����3��
 * 
 *          ���룺"abcabcabcabc"
 * 
 *          �����True
 * 
 *          ˵���������������Ӵ�"abc"�ظ��Ĵεõ���ͬʱҲ������"abcabc"�ظ����Σ���
 * 
 * 
 * @Solution �����Ƿ��ܲ��n���ظ����Ӵ�����ô��Ȼ�ܲ�ֳɶ���Ӵ�����ôÿ���Ӵ��ĳ��ȿ϶����ܴ���ԭ�ַ������ȵ�һ�룬��ô���ǿ��Դ�ԭ�ַ������ȵ�һ�������1��
 *           �����ǰ�����ܱ��ܳ���������˵�����Էֳ����ɸ����ַ��������ǽ���Щ���ַ���ƴ����������ԭ�ַ����Ƿ���ȡ�
 *           ��������˶�����ȣ�����false��
 */
public class �ظ����Ӵ�ģʽ {

	public boolean repeatedSubstringPattern(String s) {
		// write your code here
		for (int n = s.length() / 2; n > 0; n--) {
			if (s.length() % n == 0) {
				int repeat = s.length() / n;
				StringBuffer item = new StringBuffer();
				for (int i = 0; i < n; i++) {
					char a = s.charAt(i);
					item.append(a);
				}
				String a = item.toString();
				for (int i = 1; i < repeat; i++) {
					item.append(a);
				}

				if (s.equals(item.toString())) {
					return true;
				}
			}
		}

		return false;
	}
	
	public boolean repeatedSubstringPatternKMP(String s) {
		// write your code here
		for (int n = s.length() / 2; n > 0; n--) {
			if (s.length() % n == 0) {
				int repeat = s.length() / n;
				StringBuffer item = new StringBuffer();
				for (int i = 0; i < n; i++) {
					char a = s.charAt(i);
					item.append(a);
				}
				String a = item.toString();
				for (int i = 1; i < repeat; i++) {
					item.append(a);
				}

				if (s.equals(item.toString())) {
					return true;
				}
			}
		}

		return false;
	}

}
