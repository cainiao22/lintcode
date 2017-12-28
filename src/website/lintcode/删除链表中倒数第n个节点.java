package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年12月14日 下午2:21:54
 * @description
 * 		给定一个链表，删除链表中倒数第n个节点，返回链表的头节点。O(n)时间复杂度
 * @example
 * 		给出链表1->2->3->4->5->null和 n = 2.

		删除倒数第二个节点之后，这个链表将变成1->2->3->5->null.
 *
 * @Solution
 */
public class 删除链表中倒数第n个节点 extends HH {

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		List<ListNode> indexs = new ArrayList<>();
		int i = 0;
		ListNode cur = head;
		while(cur != null) {
			if(i % 2 == 0) {
				indexs.add(cur);
			}
			i ++;
			cur = cur.next;
		}
		//拿取被删除元素前面那个元素。
		if(i == n) {
			return head.next;
		}
		//这就是它前面那个元素
		i = i - n - 1;
		cur = indexs.get(i/2);
		if(i%2 == 1) {
			cur = cur.next;
		}
		cur.next = cur.next.next;
		return head;
    }
	
	//借鉴那个环的思路。首先，让当前节点走到第n的位置。那么剩下的未走的数量是length-n,也就是说如果再来一个节点从头节点开始走，跟着前面那个，当前面那个节点走到尾部时候
	//当前这个节点的位置就是length-n,就是倒数第n个节点，把它删掉就可以了
	public static ListNode removeNthFromEndFromJiuZhang(ListNode head, int n) {
		ListNode h = new ListNode(0);
		h.next = head;
		ListNode cur = h, dummy = h;
		int i = 0;
		while(i < n) {
			i ++;
			//n 大于listNode的长度
			if(cur == null) {
				return head;
			}
			cur = cur.next;
		}
		while(cur.next != null) {
			dummy = dummy.next;
			cur = cur.next;
		}
		dummy.next = dummy.next.next;
		return h.next;
		
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		ListNode a = head;
		for(int i=1; i<=5; i++) {
			ListNode cur = new ListNode(i);
			a.next = cur;
			a = a.next;
		}
		removeNthFromEndFromJiuZhang(head.next, 2);
	}
}
