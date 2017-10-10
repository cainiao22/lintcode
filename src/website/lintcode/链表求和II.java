package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年10月9日 上午11:30:08
 * @description 假定用一个链表表示两个数，其中每个节点仅包含一个数字。假设这两个数的数字顺序排列，
 *              请设计一种方法将两个数相加，并将其结果表现为链表的形式
 * 
 * @example 给出 6->1->7 + 2->9->5。即，617 + 295。
 * 
 *          返回 9->1->2。即，912 。
 *
 * @Solution 先将两个链表翻转过来，再依照加法规则执行
 */
public class 链表求和II {

	public ListNode addLists2(ListNode l1, ListNode l2) {
		// write your code here
		l1 = revert(l1);
		l2 = revert(l2);
		int add = 0;//进位标志
		ListNode head = new ListNode(0);
		ListNode cur = head;
		while(l1 != null && l2 != null) {
			ListNode next = new ListNode(l1.val + l2.val + add);
			add = next.val/10;
			next.val = next.val%10;
			cur.next = next;
			cur = next;
			l1 = l1.next;
			l2 = l2.next;
		}
		while(l1 != null) {
			ListNode next = new ListNode(l1.val + add);
			add = next.val/10;
			next.val = next.val%10;
			cur.next = next;
			cur = next;
			l1 = l1.next;
		}
		while(l2 != null) {
			ListNode next = new ListNode(l2.val + add);
			add = next.val/10;
			next.val = next.val%10;
			cur.next = next;
			cur = next;
			l2 = l2.next;
		}
		while(add != 0) {
			ListNode next = new ListNode(add);
			add = next.val/10;
			next.val = next.val%10;
			cur.next = next;
			cur = next;
		}
		return revert(head.next);
	}

	private ListNode revert(ListNode head) {
		ListNode pre = null;
		ListNode temp = null;
		while (head != null) {
			temp = head.next;
			head.next = pre;
			pre = head;
			head = temp;
		}
		return pre;
	}
}
