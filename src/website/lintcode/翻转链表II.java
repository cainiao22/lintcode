package website.lintcode;

/**
 * 
 * @author yanpf
 * @description 翻转链表中第m个节点到第n个节点的部分 ( m，n满足1 ≤ m ≤ n ≤ 链表长度 )
 * @example 给出链表1->2->3->4->5->null， m = 2 和n = 4，返回1->4->3->2->5->null
 * 
 * @Soultion 和第一种情况类似，但是要保存m和n位置的节点，最后要分别与两个节点各自的前驱和后驱要做衔接
 */
public class 翻转链表II {

	public ListNode reverseBetween(ListNode head, int m, int n) {
		// write your code
		ListNode pre = null, result = head;
		for (int i = 0; i < m - 1; i++) {
			pre = head;
			head = head.next;
		}
		ListNode temp = null, tempM = head, tempPre = pre;
		for (int i = m; i <= n; i++) {
			temp = head.next;
			head.next = pre;
			pre = head;
			head = temp;
		}
		if(m == 1) {
			result = pre;
		}else {
			tempPre.next = pre;
		}
		tempM.next = temp;
		return result;
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode tail = head;
		for(int i=2; i<6; i++) {
			ListNode t = new ListNode(i);
			tail.next = t;
			tail = t;
		}
		ListNode revert = new 翻转链表II().reverseBetween(head, 1, 4);
		System.out.println(revert);
	}

}
