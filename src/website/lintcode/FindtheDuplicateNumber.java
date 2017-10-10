package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月10日 下午12:05:51
 * @description
 * 
 * 		
	Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
	prove that at least one duplicate number must exist. Assume that there is only one duplicate number, 
	find the duplicate one.
	注意事项

    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n^2).
    There is only one duplicate number in the array, but it could be repeated more than once.


 * @example
 * 
 * 		Given nums = [5,5,4,3,2,1] return 5
		Given nums = [5,4,4,3,2,1] return 4
 *
 * @Solution  这个是查找链表中的环的变形题
 * 
 * 		可以这么假定，从链的起点到环的起点，这段距离称为a。环的长度称为c，第一次相遇位置距环的起点距离为p。
 * 		首先p1被p2追上时它一定没有走完整个环（想想为什么？）也就是说0<p<c.所以从出发到相遇，p1走过的距离为a+p，
 * 		p2走过的距离为a+p+nc（n为正整数）。又因为p2速度为p1两倍，所以有

		2a+2p=a+p+nc，所以有a+p=nc。现在两指针均处在环起点过p的位置上，再走a个距离即可回到环的起点。
		而a恰好是链的起点到环的起点的距离，所以我们另其中一指针回到链的起点，另一指针仍在原地，同时以速度1前进，
		再次相遇一定是在环的起点了。
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
