package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2018年3月9日 下午4:18:57
 * @description
 * 		给定一个单链表和数值x，划分链表使得所有小于x的节点排在大于等于x的节点之前。
		你应该保留两部分内链表节点原有的相对顺序。
 * @example
 * 		给定链表 1->4->3->2->5->2->null，并且 x=3
		返回 1->2->2->4->3->5->null
 *
 * @Solution
 */
public class 链表划分 {
	
	public ListNode partition(ListNode head, int x) {
        ListNode h = new ListNode(0);
        ListNode cur = h;
        ListNode t = new ListNode(0);
        ListNode tail = t;
        for(ListNode ptr = head; ptr != null; ptr = ptr.next){
        	if(ptr.val < x) {
        		cur.next = ptr;
        		cur = cur.next;
        	}else {
        		tail.next = ptr;
        		tail = tail.next;
        	}
        }
        cur.next = t.next;
        //将最后一个元素的下一位置空，防止死循环
        tail.next = null;
        return h.next;
    }

}
