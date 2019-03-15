package website.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2018��1��18�� ����5:20:44
 * @description
 * 		����һ������ÿ���ڵ����һ���������ӵ����ָ�����ָ�������е��κνڵ��յĽڵ㡣
		��һ�����������
		�ɷ�ʹ��O(1)�Ŀռ�
 * @example
 *
 * @Solution
 */
public class ���ƴ����ָ������� {
	
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
	 * ��map��¼�½ڵ�;ɽڵ�Ķ�Ӧ��ϵ
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
