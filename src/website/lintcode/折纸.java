package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��3��11�� ����9:52:47
 * @description
 * 		��ֽ��ÿ�ζ��ǽ�ֽ����������ۣ�����Ϊ 0��͹��Ϊ 1������ n �κ󣬽�ֽչ�������ۺ���ɵ� 01 ���С�
 * @example
 *		����: 1
		���: "0"
		
		����: 2
		���: "001"
 * @Solution
 */
public class ��ֽ {
	
	private StringBuffer sb = new StringBuffer();

	public String getString(int n) {
        // Write your code here
		if(n == 0) {
			return "";
		}
		String left = getString(n - 1);
		sb = new StringBuffer(left);
		sb.append('0');
		for(int i=left.length() - 1; i>=0; i--) {
			sb.append(('1' - left.charAt(i)));
		}
		
		return sb.toString();
    }
	
	public String getStringIterator(int n) {
		if(n == 0) {
			return sb.toString();
		}
		for(int i=1; i<=n; i++) {
			sb.append(0);	
			for(int j=sb.length() - 2; j >= 0; j--) {
				sb.append('1' - sb.charAt(j));
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String result = new ��ֽ().getStringIterator(3);
		System.out.println(result);
	}
}
