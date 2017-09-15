package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��11�� ����4:12:40
 * @description ����һ�������Լ�����Ȩֵv1��v2������������ȨֵΪv1��v2���������ڵ㡣��֤�����нڵ�Ȩֵ������ͬ�����û���ҵ���Ӧ�ڵ㣬��ôʲôҲ��������
 * @example �������� 1->2->3->4->null ���Լ� v1 = 2 �� v2 = 4 ���ؽ�� 1->4->3->2->null��
 *
 * @Solution 1��Ͷ��ȡ�ɡ�ֱ�ӽ��������ڵ��ֵ
 * 			 2����������ֵ����pre,ֱ�ӽ�������Ҫע�������������ڵ��ǽ����ŵģ���Ҫ���⴦��
 * 			 3��ֻ��������pre,����cur�ڵ����ֱ��ͨ��pre.next�õ�,��������ԭ���2��ࡣ
 */
public class ���������������ڵ� {

	public static ListNode swapNodes(ListNode head, int v1, int v2) {
		// write your code here
		ListNode bak = new ListNode(-1);
		ListNode result = bak;
		bak.next = head;
		ListNode v1Node = null, v2Node = null;
		ListNode v1Pre = null, v2Pre = null;
		while (bak.next != null) {
			if (bak.next.val == v1) {
				v1Node = bak.next;
				v1Pre = bak;
			}
			if (bak.next.val == v2) {
				v2Node = bak.next;
				v2Pre = bak;
			}
			bak = bak.next;
		}

		if (v1Node != null && v2Node != null) {
			if (v1Node.next == v2Node) {
				ListNode temp = v2Node.next;
				v2Node.next = v1Node;
				v1Node.next = temp;
				v1Pre.next = v2Node;
			} else if (v2Node.next == v1Node) {
				ListNode temp = v1Node.next;
				v1Node.next = v2Node;
				v2Node.next = temp;
				v2Pre.next = v1Node;
			} else {
				if (v1Pre != null) {
					v1Pre.next = v2Node;
				}
				if (v2Pre != null) {
					v2Pre.next = v1Node;
				}
				ListNode temp = v1Node.next;
				v1Node.next = v2Node.next;
				v2Node.next = temp;
			}
		}

		return result.next;
	}
	
	public static ListNode swapNodesFromJiuzhang(ListNode head, int v1, int v2) {
		 ListNode dummy = new ListNode(0);
	        dummy.next = head;
	        
	        ListNode node1Prev = null, node2Prev = null;
	        ListNode cur = dummy;
	        while (cur.next != null) {
	            if (cur.next.val == v1) {
	                node1Prev = cur;
	            } else if (cur.next.val == v2) {
	                node2Prev = cur;
	            }
	            cur = cur.next;
	        }
	        
	        if (node1Prev == null || node2Prev == null) {
	            return head;
	        }
	        
	        //�����ߺϲ�Ϊһ�����
	        if (node2Prev.next == node1Prev) {
	            // make sure node2Prev.next is not node1Prev
	            ListNode t = node1Prev;
	            node1Prev = node2Prev;
	            node2Prev = t;
	        }
	        
	        ListNode node1 = node1Prev.next;
	        ListNode node2 = node2Prev.next;
	        ListNode node2Next = node2.next;
	        if (node1Prev.next == node2Prev) {
	            node1Prev.next = node2;
	            node2.next = node1;
	            node1.next = node2Next;
	        } else {
	            node1Prev.next = node2;
	            node2.next = node1.next;
	            
	            node2Prev.next = node1;
	            node1.next = node2Next;
	        }
	        
	        return dummy.next;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(10);
		head.next = new ListNode(8);
		head.next.next = new ListNode(7);
		head.next.next.next = new ListNode(6);
		head.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next = new ListNode(3);
		swapNodes(head, 8, 10);
	}
}
