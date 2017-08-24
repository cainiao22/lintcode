package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��17�� ����4:54:48
 * @description ����һ���ַ����������ת�ַ����е�ÿ������ ���ʵĹ��ɣ��޿ո���ĸ����һ������
 *              �����ַ����Ƿ����ǰ������β��ո񣿿��԰��������Ƿ�ת����ַ����ܰ���
 *              ��δ����������ʼ�Ķ���ո��ڷ�ת�ַ����м�ո���ٵ�ֻ��һ��
 * @example
 *
 * @Solution �Ƚ��ַ��������ʷ�תһ�飬Ȼ�������巭ת����
 */
public class ��ת�ַ��� {

	public String reverseWords(String s) {
		// write your code
		String[] words = s.split(" ");
		String revertedWord = "";
		for (int i = words.length - 1; i >= 0; i--) {
			if (!"".equals(words[i])) {
				revertedWord += words[i];
				revertedWord += " ";
			}
		}
		String result = "";
		for (int j = revertedWord.length() - 2; j >= 0; j--) {
			result += revertedWord.charAt(j);
		}

		return result;
	}

	public String reverseWords2(String s) {

		if (s == null || s.length() == 0) {
			return "";
		}
		String[] array = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = array.length - 1; i >= 0; --i) {
			if (!array[i].equals("")) {
				sb.append(array[i]).append(" ");
			}
		}
		// remove the last " "
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);

	}

	public static void main(String[] args) {
		System.out.println(new ��ת�ַ���().reverseWords2(" hello world!  thank  you func "));
	}

}
