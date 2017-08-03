package lintcode;

/**
 * 
 * @author yanpf
 * @description 翻转一个链表
 * @example 给出一个链表1->2->3->null，这个翻转后的链表为3->2->1->null
 * 
 * @Solution 有点类似于交换
 */
public class 翻转链表 {

	public ListNode reverse(ListNode head) {
        // write your code here
		if(head == null || head.next == null) {
			return head;
		}
		ListNode revert = head;
		ListNode temp = revert.next;
		ListNode tempNext = temp.next;
		revert.next = null;
		while(tempNext != null) {
			temp.next = revert;
			revert = temp;
			temp = tempNext;
			tempNext = temp.next;
		}
		temp.next = revert;
		return temp;
		
    }
	
	public ListNode reverse2(ListNode head) {
		ListNode pre = null;
		ListNode temp = null;
		while(head != null) {
			temp = head.next;
			head.next = pre;
			pre = head;
			head = temp;
		}
		return pre;
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		ListNode tail = head;
		for(int i=1; i<2; i++) {
			ListNode t = new ListNode(i);
			tail.next = t;
			tail = t;
		}
		ListNode revert = new 翻转链表().reverse(head);
		System.out.println(revert);
	}
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int val) {
	this.val = val;
	this.next = null;
	}
}

