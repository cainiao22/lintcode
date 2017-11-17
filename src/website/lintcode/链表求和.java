package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年11月13日 下午4:21:24
 * @description
 * 		你有两个用链表代表的整数，其中每个节点包含一个数字。数字存储按照在原来整数中相反的顺序，
 * 		使得第一个数字位于链表的开头。写出一个函数将两个整数相加，用链表形式返回和
 * @example
 * 		给出两个链表 3->1->5->null 和 5->9->2->null，返回 8->0->8->null
 *
 * @Solution 最后千万不要忘了进位标志是否还存在
 */
public class 链表求和 extends HH {
	
	public ListNode addLists(ListNode l1, ListNode l2) {
        // write your code here
		ListNode head = new ListNode(0);
		int inc = 0;
		while(l1 != null && l2 != null) {
			int sum = l1.val + l2.val + inc;
			inc = sum / 10;
			sum = sum % 10;

			ListNode item =new ListNode(sum);
			item.next = head.next;
			head.next = item;
			l1 = l1.next;
			l2 = l2.next;
		}
		while(l1 != null) {
			int sum = l1.val + inc;
			inc = sum / 10;
			sum = sum % 10;
			ListNode item =new ListNode(sum);
			item.next = head.next;
			head.next = item;
			l1 = l1.next;
		}
		
		while(l2 != null) {
			int sum = l2.val + inc;
			inc = sum / 10;
			sum = sum % 10;
			ListNode item =new ListNode(sum);
			item.next = head.next;
			head.next = item;
			l2 = l2.next;
		}
		while(inc != 0) {
			int sum = inc;
			inc = sum / 10;
			sum = sum % 10;
			ListNode item =new ListNode(sum);
			item.next = head.next;
			head.next = item;
			l2 = l2.next;
		}
		return head.next;
    }
	
	/**
	 * 这个方式更加简洁
	 * @param l1
	 * @param l2
	 * @return
	 */
	 public ListNode addListsFromJiuZhang(ListNode l1, ListNode l2) {
	        // write your code here
	        ListNode dummy = new ListNode(0);
	        ListNode tail = dummy;

	        int carry = 0;
	        //for还可以这样用
	        for (ListNode i = l1, j = l2; i != null || j != null; ) {
	            int sum = carry;
	            sum += (i != null) ? i.val : 0;
	            sum += (j != null) ? j.val : 0;

	            tail.next = new ListNode(sum % 10);
	            tail = tail.next;

	            carry = sum / 10;
	            i = (i == null) ? i : i.next;
	            j = (j == null) ? j : j.next;
	        }

	        if (carry != 0) {
	            tail.next = new ListNode(carry);
	        }
	        return dummy.next;
	    }
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(3);
		l1.next = new ListNode(1);
		l1.next.next = new ListNode(2);
		
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(9);
		l2.next.next = new ListNode(5);
		l2.next.next.next = new ListNode(1);
		new 链表求和().addLists(l1, l2);
	}

}
