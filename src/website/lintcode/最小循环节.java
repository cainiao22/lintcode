package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��4��1�� ����5:57:39
 * @description
 * 		����һ��int������ array, ������������Сѭ���ڵĳ���
 * @example
 *		���� array = [1,2,1,2,1,2], ���� 2��

		���ͣ�
		��Сѭ����Ϊ[1,2]������Ϊ2��
		���� array = [1,2,1,2,1], ���� 2��
		
		���ͣ�
		��Сѭ����Ϊ[1,2]������Ϊ2�����һ��2��Ȼû�и������������ǻ�����Ϊѭ��[1,2]��
		���� array = [1,2,1,2,1,4], ���� 6��
		
		���ͣ�
		��Сѭ����Ϊ[1,2,1,2,1,4]������Ϊ6��
 * @Solution
 * 		���Կ���ʹ��˫ָ��(L,R)������һ��������¼len��ǰ��ѭ���ڣ�����ѭ���ڿ϶��Ǵӵ�һλ��ʼ��������L��¼��ǰƥ�䵽��λ�ã�
 * 		Rһֱ�����ƶ��������ǰλ��ƥ�䣬��ôL�����ã�len�ͼ�1,
 * 		����Ĳ�����kmp�㷨��nextһ����ѭ���ھ��� (i - 1) - ( next[i] - 1 ) = i - next[i]�����帴�Ӷ�O(n) ��
 * 		ʹ��δ�Ż�����nextֵ�ķ���
 */
public class ��Сѭ���� extends HH {
	
	/**
	 * kmp�㷨�ı���
	 * @param array
	 * @return
	 */
	public int minimumCycleSection(int[] array) {
		int[] next = new int[array.length + 1];
		next[0] = -1;
		int i = 0, j = -1;
		while(i < array.length) {
			if(j == -1 || array[i] == array[j]) {
				i ++;
				j ++;
				next[i] = j;
			}else {
				j = next[j];
			}
		}
        return i - next[i];
    }

	/**
	 * kmp�㷨��ȡnext����
	 * @param array
	 * @return
	 */
	private int[] getNext(int[] array) {
		int[] next = new int[array.length];
		next[0] = -1;
		int i=0, j = -1;
		while(i < next.length - 1) {
			if(j == -1 || array[i] == array[j]) {
				i ++;
				j ++;
				if(array[i] != array[j]) {
					next[i] = j;
				}else {
					next[i] = next[j];
				}
			}else {
				j = next[j];
			}
		}
		
		return next;
	}
	
	public static void main(String[] args) {
		int next = new ��Сѭ����().minimumCycleSection(new int[] {1,2,1,2,1});
		print(next);
	}
}
