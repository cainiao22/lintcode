package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017��12��14�� ����2:21:54
 * @description
 * 		����һ������ɾ�������е�����n���ڵ㣬���������ͷ�ڵ㡣O(n)ʱ�临�Ӷ�
 * @example
 * 		��������1->2->3->4->5->null�� n = 2.

		ɾ�������ڶ����ڵ�֮������������1->2->3->5->null.
 *
 * @Solution
 */
public class ɾ�������е�����n���ڵ� extends HH {

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
		//��ȡ��ɾ��Ԫ��ǰ���Ǹ�Ԫ�ء�
		if(i == n) {
			return head.next;
		}
		//�������ǰ���Ǹ�Ԫ��
		i = i - n - 1;
		cur = indexs.get(i/2);
		if(i%2 == 1) {
			cur = cur.next;
		}
		cur.next = cur.next.next;
		return head;
    }
	
	//����Ǹ�����˼·�����ȣ��õ�ǰ�ڵ��ߵ���n��λ�á���ôʣ�µ�δ�ߵ�������length-n,Ҳ����˵�������һ���ڵ��ͷ�ڵ㿪ʼ�ߣ�����ǰ���Ǹ�����ǰ���Ǹ��ڵ��ߵ�β��ʱ��
	//��ǰ����ڵ��λ�þ���length-n,���ǵ�����n���ڵ㣬����ɾ���Ϳ�����
	public static ListNode removeNthFromEndFromJiuZhang(ListNode head, int n) {
		ListNode h = new ListNode(0);
		h.next = head;
		ListNode cur = h, dummy = h;
		int i = 0;
		while(i < n) {
			i ++;
			//n ����listNode�ĳ���
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
