package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018��1��8�� ����3:42:49
 * @description
 * 		�ҵ�����������n���ڵ㣬��֤�����нڵ����������Ϊn
 * @example
 * 		�������� 3->2->1->5->null��n = 2�����ص����ڶ����ڵ��ֵ1.
 *
 * @Solution ȡ����ָ�롣��һ��ָ�����ߣ��ߵ���n���󣬵ڶ�����ʼ�ߡ�����һ��ָ���ߵ�ͷ���ڶ���ָ���ߵ���λ�þ��ǵ�����n��λ��
 */
public class ��������n���ڵ� {
	
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
