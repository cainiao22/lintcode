package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��1�� ����12:03:33
 * @description �ò����������������
 * @example Given 1->3->2->0->null, return 0->1->2->3->null
 *
 * @Solution
 */
public class ����������� {

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
			temp.next.next = tail; ����дҲ��*/
			
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

		ListNode result = new �����������().insertionSortList(head);
		while (result != null) {
			System.out.println(result.val);
			result = result.next;
		}
	}
}
