package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019年1月18日 下午2:51:16
 * @description 一种空间复杂度为O(1)的遍历方式
 *              空间复杂度O(1)的要求很严格。常规的递归实现是显然不能满足要求的[其空间复杂度是树的深度O(h) ]。本篇文章介绍著名的Morris遍历，该方法利用了二叉树结点中大量指向null的指针。
 * 
 *              常规的栈结构遍历方式，遍历到某个节点之后并不能回到上层的结点，这是由二叉树本身的结构所限制的，每个结点并没有指向父节点的指针，因此需要使用栈来完成回到上层结点的步骤。
 * 
 *              Morris遍历避免了使用栈结构，让下层有指向上层的指针，但并不是所有的下层结点都有指向上层的指针([这些指针也称为空闲指针])。
 * 
 *              空闲指针的分配规则如下：
 * 
 *              1. 当前子树的头结点为head，空闲指针由head的左子树中最右结点的右指针指向head结点。对head的左子树重复该步骤1，直到遍历至某个结点没有左子树，将该结点记
 *                 为node。进入步骤2。
 * 
 *              2. 从node结点开始通过每个结点的右指针进行移动，并打印结点的值。
 * 
 *                假设遍历到的当前结点为curNode，做如下判断：
 * 
 *              curNode结点的左子树中最右结点(记为lastRNode)是否指向curNode。
 * 
 *              A. 是 让lastRNode结点的右指针指向null，打印curNode的值。接着通过curNode的右指针遍历下一个结点，重复步骤2。
 * 
 *              B. 否 将curNode为头结点的子树重复步骤1。
 * @example
 *
 * @Solution
 */
public class Morris遍历二叉树 extends HH {

	static class TreeNode {
		public int val;
		public TreeNode left, right;

		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}

	public static List<Integer> midOrder(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		TreeNode p1 = root;
		TreeNode p2 = root.left;
		while (p1 != null) {
			p2 = p1.left;
			if (p2 == null) {
				result.add(p1.val);
				p1 = p1.right;
				continue;
			}
			while (p2.right != null && p2.right != p1) {
				p2 = p2.right;
			}
			if (p2.right == null) {
				p2.right = p1;
				p1 = p1.left;
			} else { //还原树结构
				p2.right = null;
				result.add(p1.val);
				p1 = p1.right;
			}

		}

		return result;
	}

	/**
	 * 稍微优化了一下代码结构
	 * @param root
	 * @return
	 */
	public static List<Integer> midOrderBetter(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		TreeNode p1 = root;
		TreeNode p2 = root.left;
		while (p1 != null) {
			p2 = p1.left;
			if (p2 != null) {
				while (p2.right != null && p2.right != p1) {
					p2 = p2.right;
				}
				if (p2.right == null) {
					p2.right = p1;
					p1 = p1.left;
					continue;
				} else { //还原树结构
					p2.right = null;
				}
			}
			result.add(p1.val);
			p1 = p1.right;
		}

		return result;
	}
	
	public static List<Integer> preOrder(TreeNode root){
		List<Integer> result = new ArrayList<>();
		TreeNode p1 = root;
		TreeNode p2 = root.left;
		while(p1 != null) {
			result.add(p1.val);
			p2 = p1.left;
			if(p2 != null) {
				while(p2.right != null && p2.right != p1.right) {
					p2 = p2.right;
				}
				if(p2.right == null) {
					p2.right = p1.right;
					p1 = p1.left;
					continue;
				}else {
					p2.right = null;
				}
			}
			p1 = p1.right;
		}
		
		return result;
	}
	
	public static List<Integer> preOrder2(TreeNode root){
		List<Integer> result = new ArrayList<>();
		TreeNode p1 = root;
		TreeNode p2 = root.left;
		while(p1 != null) {
			p2 = p1.left;
			if(p2 == null) {
				result.add(p1.val);
				p1 = p1.right;
				continue;
			}
			while(p2.right != null && p2.right != p1) {
				p2 = p2.right;
			}
			if(p2.right == null) {
				p2.right = p1;
				result.add(p1.val);
				p1 = p1.left;
				continue;
			}else{
				p2.right = null;
			}
			
		}
		
		return result;
	}
	
	public static TreeNode reverseEdge(TreeNode head) {
		TreeNode pre = null, next = null;
		while(head != null) {
			next = head.right;
			head.right = pre;
			pre = head;
			head = next;
		}
		
		return pre;
	}
	
	public static void printEdge(TreeNode node, List<Integer> result) {
		TreeNode last = reverseEdge(node);
		TreeNode current = last;
		while(current != null) {
			result.add(current.val);
			current = current.right;
		}
		reverseEdge(last);
	}
	
	public static List<Integer> postOrder(TreeNode root){
		TreeNode p1 = root;
		TreeNode p2 = null;
		List<Integer> result = new ArrayList<>();
		while(p1 != null) {
			p2 = p1.left;
			if(p2 != null) {
				while(p2.right != null && p2.right != p1) {
					p2 = p2.right;
				}
				if(p2.right == null) {
					p2.right = p1;
					p1 = p1.left;
					continue;
				}else {
					p2.right = null;
					printEdge(p1.left, result);
				}
			}
			
			p1 = p1.right;
		}
		
		printEdge(root, result);
		
		return result;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(20);
		root.left = new TreeNode(8);
		root.right = new TreeNode(22);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(12);

		List<Integer> list = postOrder(root);
		print(list);
	}

}
