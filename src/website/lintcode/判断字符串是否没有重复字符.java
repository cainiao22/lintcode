package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��14�� ����6:43:50
 * @description  ʵ��һ���㷨ȷ���ַ����е��ַ��Ƿ��Ψһ����  �����ʹ�ö���Ĵ洢�ռ䣬����㷨����θı䣿
 * 
 * @example ����"abc"������ true  ����"aab"������ false
 *
 * @Solution  bitmap��ʽ����һ��int����ȥ��bitmap������128λ��boolean���飬
 * 			    ��һ����ȫ���ÿռ��˫��ѭ���໥���
 */
public class �ж��ַ����Ƿ�û���ظ��ַ� {
	
	public boolean isUnique(String str) {
        // write your code here
		int[] a = new int[4];
		for(int i = 0; i<str.length(); i++) {
			int index = str.charAt(i);
			int j = index / 32;
			index %= 32;
			if((a[j] & (1 << index)) != 0) {
				return false;
			}
			a[j] |= (1 << index);
		}
		
		return true;
    }
	
	 public boolean isUnique2(String str) {
	        // write your code here
	        boolean[] char_set = new boolean[256];
	        for (int i = 0; i < str.length(); i++) {
	        int val = str.charAt(i);
	        if (char_set[val]) return false;
	            char_set[val] = true;
	        }
	        return true;
	    }
	
	public static void main(String[] args) {
		boolean res = new �ж��ַ����Ƿ�û���ظ��ַ�().isUnique("helloworld");
		System.out.println(Integer.toBinaryString(1 << 31));
		System.out.println(1 << 31);
		System.out.println(res);
	}

}
