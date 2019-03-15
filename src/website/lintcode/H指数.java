package website.lintcode;

import java.util.Arrays;

/**
 * 
 * @author yanpf
 * @date 2019��1��29�� ����10:55:17
 * @description	����һ���о�Ա�����������飨ÿ������������һ���Ǹ����������������о�Ա��Hָ����

				һ���о��ߵ�Hָ��Ϊh����ζ�����������У���hƪ��������h����������
				
				
 * @example		��������ֵcitations = [3, 0, 6, 1, 5]�������о�����5ƪ���ģ�ÿһƪ��������Ϊ3��0��6��1��5��
 * 				��Ϊ�о�������ƪ����������3���������ϣ���ʣ����ƪ��û�е�������������Hָ��Ϊ3��
 *
 * @Solution
 */
public class Hָ�� {
	
	public int hIndex(int[] citations) {
        // write your code here
		Arrays.sort(citations);
		int result = 0;
		for(int i=0; i<citations.length; i++) {
			if(citations[i] >= citations.length - i) {
				result = citations.length - i;
				break;
			}
		}
		
		return result;
    }
	
	public static void main(String[] args) {
		int[] citations = new int[] {3, 0, 6, 1, 5};
		int result = new Hָ��().hIndex(citations);
		System.out.println(result);
	}

}
