package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��30�� ����12:07:42
 * @description 
 * 			����һ����ת�������������ʼλ����δ֪�ģ�����0 1 2 4 5 6 7 ���ܱ����4 5 6 7 0 1 2����

			����Ҫ�ҵ�������С��Ԫ�ء�

			����Լ��������в������ظ���Ԫ�ء�
			
 * @example ����[4,5,6,7,0,1,2]  ���� 0
 *
 * @Solution ���ַ����ҡ�������Сλ��Ϊx����ôx�����һ��λ�õ�Ԫ���ǵ����ģ���0~x-1��Ԫ���ǵݼ��ġ�
 * 			 �����һ��λ�õ�Ԫ��С��0~x-1���κ�һ��Ԫ�ء��㷨�������һ��Ԫ��targetΪ��׼�����ҵ�һ��С������Ԫ��λ�ã�
 * 			 ���λ�þ�����СԪ�����ڵ�λ�á�0~length-1ȡ�м�mid,���midԪ��С��target,��ô��СԪ������ߣ�
 * 			midԪ�ش���target����ô��СԪ�����ұߡ��Դ˹��������ȥ��
 */
public class Ѱ����ת���������е���Сֵ {
	
	public int findMin(int[] nums) {
        // write your code here
		int start = 0,end = nums.length - 1;
		int target = nums[nums.length - 1];
		while(start < end - 1) {
			int mid = start + (end - start) /2;
			if(nums[mid] > target) {
				start = mid;
			}else {
				end = mid;
			}
		}
		
		if(nums[start] < target) {
			return nums[start];
		}else {
			return nums[end];
		}
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {4,5,6,7,0,1,2};
		nums = new int[] {4,3,2,1,0,7,6,5};
		int res = new Ѱ����ת���������е���Сֵ().findMin(nums);
		System.out.println(res);
	}

}
