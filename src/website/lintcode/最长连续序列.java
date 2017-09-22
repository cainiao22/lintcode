package website.lintcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2017��9��18�� ����6:25:50
 * @description ����һ��δ������������飬�ҳ���������еĳ��ȡ�Ҫ������㷨���Ӷ�ΪO(n)
 * 
 * @example ��������[100, 4, 200, 1, 3, 2]������������������ [1, 2, 3, 4]���������󳤶� 4
 *
 * @Solution ����hashSet,�ֱ���м���������չ�����hashset�а��������������ô�������ɾ���������ſ����ϸ�֤��ÿһ��Ԫ��
 * 			   �Ĳ����ǳ�����֮���Կ���ɾ��������Ϊ�������ֲ�������������һ����������һ�����ٴβ�����ʵ�����ظ��Ե��ˡ���Ϊ����
 * 			   һ��ȫ���ԵĲ���
 */
public class ��������� {
	
	public static int longestConsecutive(int[] num) {
        // write your code here
		Set<Integer> hashSet = new HashSet<>();
		for(int i=0; i<num.length; i++) {
			hashSet.add(num[i]);
		}
		int max = 1;
		for(int i=0; i<num.length; i++) {
			int down = num[i] - 1;
			while(hashSet.contains(down)) {
				hashSet.remove(down);
				down --;
			}
			int up = num[i] + 1;
			while(hashSet.contains(up)) {
				hashSet.remove(up);
				up ++;
			}
			
			max = Math.max(max, up - down - 1);
		}
		
		return max;
		
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {0,0,-1};
		int result = longestConsecutive(nums);
		System.out.println(result);
	}
}
