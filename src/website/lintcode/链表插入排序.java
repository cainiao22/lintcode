package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月1日 下午12:03:33
 * @description 用插入排序对链表排序
 * @example Given 1->3->2->0->null, return 0->1->2->3->null
 *
 * @Solution
 */
public class 链表插入排序 {

	public ListNode insertionSortList(ListNode head) {
		// write your code here
		if (head == null)
			return null;
		ListNode result = new ListNode(-1);
		while(head != null) {
			ListNode temp = result;
			while(temp.next != null && temp.next.val <= head.val) {
				temp = temp.next;
			}
			ListNode tail = temp.next;
			temp.next = head;
			/*head = head.next; 
			temp.next.next = tail; 这样写也对*/
			
			temp = head.next;
			head.next = tail;
			head = temp;
		}

		return result.next;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(3);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(0);

		ListNode result = new 链表插入排序().insertionSortList(head);
		while (result != null) {
			System.out.println(result.val);
			result = result.next;
		}
	}
}
