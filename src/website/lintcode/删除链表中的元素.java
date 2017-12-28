package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年12月26日 下午3:05:33
 * @description
 * 			删除链表中等于给定值val的所有节点。
 * @example
 *			给出链表 1->2->3->3->4->5->3, 和 val = 3, 你需要返回删除3之后的链表：1->2->4->5。
 * @Solution
 */
public class 删除链表中的元素 {

	public ListNode removeElements(ListNode head, int val) {
        ListNode result = new ListNode(0);
        ListNode current = head, tail = result;
        while(current != null) {
        	if(current.val != val) {
        		tail.next = current;
        		tail = current;
        	}
        	current = current.next;
        }
        //这里是一个小细节，假如tail不是最尾部那个。后面的非法数就会被他带出来
        tail.next = null;
        return result.next;
    }
	
	/**
	 * 直接判断next的值。不合法就删除
	 * @param head
	 * @param val
	 * @return
	 */
	public ListNode removeElementsBetter(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        while (head.next != null) {
            if (head.next.val == val) { //这里假如是最后一个元素的话，它的next就是null,没必要再手动设置
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        
        return dummy.next;
    }
}
