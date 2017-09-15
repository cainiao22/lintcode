package website.lintcode;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author yanpf
 * @date 2017��9��11�� ����5:26:29
 * @description �ϲ�k�������������ҷ��غϲ���������������Է����������临�Ӷȡ�
 * @example ����3����������[2->4->null,null,-1->null]������ -1->2->4->null
 *
 * @Solution 1�����ȶ��У��ѵ�������ȶ��еĹ������档������Խ���������˼�롣������ģ�������
 * 			 2���鲢����. ���ʾ��ǽ��ڵ������ϲ������ݹ鷽ʽ)
 * 			 3���鲢���򣨷ǵݹ鷽ʽ)
 */
public class �ϲ�k���������� {
	
	public ListNode mergeKLists(List<ListNode> lists) {
        // write your code here
		if(lists == null || lists.isEmpty()) {
			return null;
		}
		if(lists.size() == 1) {
			return lists.get(0);
		}
		ListNode[] stack = new ListNode[lists.size()];
		ListNode result = new ListNode(-1);
		ListNode tail = result;
		int i=0;
		for(ListNode node : lists) {
			if(node != null) {
			stack[i] = node;
			i++;
			}
		}
		for(int j=(i-1)/2; j>=0; j--) {
			rebalance(stack, j, i-1);
		}
		while(stack[0] != null) {
			tail.next = stack[0];
			tail = tail.next;
			stack[0] = stack[0].next;
			i=stack.length-1;
			if(stack[0] == null) {
				for(; i>0&&stack[i]==null; i--);
				if(i == 0) {
					break;
				}
				ListNode temp = stack[0];
				stack[0] = stack[i];
				stack[i] = temp;
			}
			rebalance(stack, 0, i);
		}
		
		return result.next;
    }
	
	private void rebalance(ListNode[] stack, int index, int len) {
		if(index > len/2) {
			return;
		}
		int min = index;
		if(index*2+1 <= len && stack[index*2+1] != null && stack[index*2+1].val < stack[min].val) {
			min = index*2 +1;
		}
		if(index*2+2 <= len && stack[index*2+2] != null && stack[index*2+2].val < stack[min].val) {
			min = index*2 + 2;
		}
		if(min != index) {
			ListNode temp = stack[index];
			stack[index] = stack[min];
			stack[min] = temp;
			rebalance(stack, min, len);
		}
	}
	
	/**
	 * ���ù鲢����˼�룬��listNode�ֽ������һ�顣Ȼ�������ϲ�
	 * @param lists
	 * @return
	 */
	public ListNode mergeKListsWithMergeSort(List<ListNode> lists) {
		if(lists == null) {
			return null;
		}
		return mergeSortHelper(lists, 0, lists.size()-1);
	}
	
	private ListNode mergeSortHelper(List<ListNode> lists, int start, int end) {
		if(start == end) {
			return lists.get(start);
		}
		int mid = start + (end - start)/2;
		ListNode first = mergeSortHelper(lists, start, mid);
		ListNode second = mergeSortHelper(lists, mid+1, end);
		return mergeTwoList(first, second);
	}
	
	private ListNode mergeTwoList(ListNode first, ListNode second) {
		ListNode summary = new ListNode(-1);
		//tail��ʶsummary�����һ��Ԫ��
		ListNode tail = summary;
		while(first != null && second != null) {
			if(first.val <= second.val) {
				tail.next = first;
				tail = first;
				first = first.next;
			}else {
				tail.next = second;
				tail = second;
				second = second.next;
			}
		}
		
		if(first != null) {
			summary.next = first;
		}
		if(second != null) {
			summary.next = second;
		}
		return summary.next;
	}
	
	/**
	 * �ǵݹ鷽ʽ�������ϲ�
	 * @param lists
	 * @return
	 */
	public ListNode mergeKListsWithMergeSortTwo(List<ListNode> lists) {
		if(lists == null || lists.isEmpty()) {
			return null;
		}
		while(lists.size() > 1) {
			List<ListNode> temp = new ArrayList<>();
			for(int i=0; i<lists.size()-1; i+=2) {//�����ʽ������˼
				ListNode listNode = mergeTwoList(lists.get(i), lists.get(i+1));
				temp.add(listNode);
			}
			if(lists.size()%2 == 1) {
				temp.add(lists.get(lists.size()-1));
			}
			lists = temp;
		}
		
		return lists.get(0);
	}
	
	/**
	 * �����һ�����ʵĳ���
	 * @param s
	 * @return
	 */
	public int lengthOfLastWord(String s) {
        // write your code here
		if(s == null || s.length() == 0) {
			return 0;
		}
		int j = 0;
		int length = s.length() - 1;
		while(s.charAt(length) == ' ') {
			length --;
		}
		for(int i=length; i>=0; i--) {
			if(s.charAt(i) != ' ') {
				j ++;
			}else {
				break;
			}
		}
		return j;
    }
	
	public static void main(String[] args) {
		ListNode first = new ListNode(2);
		ListNode second = new ListNode(1);
		List<ListNode> list = new ArrayList<>();
		list.add(first);
		list.add(null);
		list.add(second);
		new �ϲ�k����������().mergeKLists(list);
	}

}
