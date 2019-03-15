package website.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2018年1月18日 下午5:20:44
 * @description
 * 		给出一个链表，每个节点包含一个额外增加的随机指针可以指向链表中的任何节点或空的节点。
		回一个深拷贝的链表
		可否使用O(1)的空间
 * @example
 *
 * @Solution
 */
public class 复制带随机指针的链表 {
	
	private static class RandomListNode {
		int label;
		RandomListNode next, random;
		RandomListNode(int x) { this.label = x; }
		
		@Override
		public String toString() {
			return label + "";
		}
	}
	
	public static RandomListNode copyRandomList(RandomListNode head) {
		RandomListNode result = new RandomListNode(0);
		RandomListNode cur = result;
		result.next = cur;
       for(RandomListNode ptr=head; ptr != null; ptr = ptr.next) {
    	   cur.next = new RandomListNode(ptr.label);
    	   cur = cur.next;
       }
       cur = result;
       for(RandomListNode ptr=head; ptr != null; ptr = ptr.next) {
    	   cur = cur.next;
    	   if(ptr.random == null) continue;
    	   RandomListNode temp = result.next;
    	   for(RandomListNode r =head; r != ptr.random && r != null; r = r.next) {
    		   temp = temp.next;
    	   }
    	   cur.random = temp;
       }
       
       return result.next;
    }
	
	/**
	 * 用map记录新节点和旧节点的对应关系
	 * @param head
	 * @return
	 */
	public static RandomListNode copyRandomList2(RandomListNode head) {
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		RandomListNode result = new RandomListNode(0);
		RandomListNode cur = result;
		result.next = cur;
       for(RandomListNode ptr=head; ptr != null; ptr = ptr.next) {
    	   cur.next = new RandomListNode(ptr.label);
    	   map.put(ptr, cur.next);
    	   cur = cur.next;
       }
       cur = result.next;
       for(RandomListNode ptr=head; ptr != null; ptr = ptr.next) {
    	   if(map.get(ptr.random) != null) {
    		   cur.random = map.get(ptr.random);
    	   }
    	   cur = cur.next;
       }
       return result.next;
	}
	
	public static void main(String[] args) {
		//-1->8->7->-3->4->null, [4,-3,null,null,-1]
		RandomListNode head = new RandomListNode(-1);
		RandomListNode a1 = new RandomListNode(8);
		RandomListNode a2 = new RandomListNode(7);
		RandomListNode a3 = new RandomListNode(-3);
		RandomListNode a4 = new RandomListNode(4);
		head.next = a1;
		head.random = a4;
		a1.next = a2;
		a1.random = a3;
		
		a2.next = a3;
		a3.next = a4;
		
		a4.random = head;
		
		copyRandomList(head);
	}

}
