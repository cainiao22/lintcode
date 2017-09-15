package website.lintcode;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author yanpf
 * @date 2017年9月11日 下午5:26:29
 * @description 合并k个排序链表，并且返回合并后的排序链表。尝试分析和描述其复杂度。
 * @example 给出3个排序链表[2->4->null,null,-1->null]，返回 -1->2->4->null
 *
 * @Solution 1、优先对列，难点就是优先对列的构造上面。这里可以借助堆排序思想。用数组模拟二叉树
 * 			 2、归并排序. 本质就是将节点两两合并。（递归方式)
 * 			 3、归并排序（非递归方式)
 */
public class 合并k个排序链表 {
	
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
	 * 利用归并排序思想，将listNode分解成两个一组。然后两两合并
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
		//tail标识summary的最后一个元素
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
	 * 非递归方式，两两合并
	 * @param lists
	 * @return
	 */
	public ListNode mergeKListsWithMergeSortTwo(List<ListNode> lists) {
		if(lists == null || lists.isEmpty()) {
			return null;
		}
		while(lists.size() > 1) {
			List<ListNode> temp = new ArrayList<>();
			for(int i=0; i<lists.size()-1; i+=2) {//这个方式很有意思
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
	 * 求最后一个单词的长度
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
		new 合并k个排序链表().mergeKLists(list);
	}

}
