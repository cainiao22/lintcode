package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018年1月8日 下午3:42:49
 * @description
 * 		找到单链表倒数第n个节点，保证链表中节点的最少数量为n
 * @example
 * 		给出链表 3->2->1->5->null和n = 2，返回倒数第二个节点的值1.
 *
 * @Solution 取两个指针。第一个指针先走，走到第n个后，第二个开始走。当第一个指针走到头，第二个指针走到的位置就是倒数第n个位置
 */
public class 链表倒数第n个节点 {
	
	public ListNode nthToLast(ListNode head, int n) {
        // write your code here
		ListNode result = head;
		while(n > 0) {
			head = head.next;
			n --;
		}
		while(head != null) {
			head = head.next;
			result = result.next;
		}
		
		return result;
    }

}
