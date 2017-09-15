package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月11日 下午4:12:40
 * @description 给你一个链表以及两个权值v1和v2，交换链表中权值为v1和v2的这两个节点。保证链表中节点权值各不相同，如果没有找到对应节点，那么什么也不用做。
 * @example 给出链表 1->2->3->4->null ，以及 v1 = 2 ， v2 = 4 返回结果 1->4->3->2->null。
 *
 * @Solution 1、投机取巧。直接交换两个节点的值
 * 			 2、定义两个值两个pre,直接交换，需要注意的是如果两个节点是紧挨着的，需要特殊处理
 * 			 3、只定义两个pre,两个cur节点可以直接通过pre.next拿到,但是最终原理和2差不多。
 */
public class 交换链表当中两个节点 {

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
	        
	        //将两者合并为一种情况
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
