package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��10�� ����12:05:51
 * @description
 * 
 * 		
	Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
	prove that at least one duplicate number must exist. Assume that there is only one duplicate number, 
	find the duplicate one.
	ע������

    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n^2).
    There is only one duplicate number in the array, but it could be repeated more than once.


 * @example
 * 
 * 		Given nums = [5,5,4,3,2,1] return 5
		Given nums = [5,4,4,3,2,1] return 4
 *
 * @Solution  ����ǲ��������еĻ��ı�����
 * 
 * 		������ô�ٶ�����������㵽������㣬��ξ����Ϊa�����ĳ��ȳ�Ϊc����һ������λ�þ໷��������Ϊp��
 * 		����p1��p2׷��ʱ��һ��û������������������Ϊʲô����Ҳ����˵0<p<c.���Դӳ�����������p1�߹��ľ���Ϊa+p��
 * 		p2�߹��ľ���Ϊa+p+nc��nΪ��������������Ϊp2�ٶ�Ϊp1������������

		2a+2p=a+p+nc��������a+p=nc��������ָ������ڻ�����p��λ���ϣ�����a�����뼴�ɻص�������㡣
		��aǡ����������㵽�������ľ��룬��������������һָ��ص�������㣬��һָ������ԭ�أ�ͬʱ���ٶ�1ǰ����
		�ٴ�����һ�����ڻ�������ˡ�
 */
public class FindtheDuplicateNumber {
	


	public int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
         do{
        	slow = nums[slow];
        	fast = nums[nums[fast]];
        }while(fast != slow);
        fast = 0;
        while(fast != slow) {
        	slow = nums[slow];
        	fast = nums[fast];
        }
        
        return fast;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {5,5,4,3,2,1};
		System.out.println(new FindtheDuplicateNumber().findDuplicate(nums));
	}

}
