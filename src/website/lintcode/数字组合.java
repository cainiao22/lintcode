package website.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;import javax.swing.text.AbstractDocument.LeafElement;

/**
 * 
 * @author yanpf
 * @date 2017��12��14�� ����6:03:48
 * @description
 * 		����һ����ѡ���ֵ�set(C)��Ŀ������(T),�ҵ�C�����е���ϣ�ʹ�ҳ������ֺ�ΪT��C�е����ֿ����������ظ���ѡȡ��
		����,������ѡ����[2,3,6,7]��Ŀ������7������Ľ�Ϊ��
		[7]��
		[2,2,3]
		
 * @example
 * 		������ѡset[2,3,6,7]��Ŀ������7
		���� [[7],[2,2,3]]
 *
 * @Solution
 */
public class ������� extends HH {
	
	private static List<List<Integer>> ans = new ArrayList<>();
	
	/**
	 * �ݹ����
	 * @param candidates
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
		Arrays.sort(candidates);
		List<Integer> item = new ArrayList<>();
		helper(candidates, 0, target, item);
		return ans;
    }
	
	private static void helper(int [] candidates, int index, int target, List<Integer> item) {
		if(target == 0) {
			ans.add(new ArrayList<Integer>(item));
			return;
		}
		if(index >= candidates.length || candidates[index] > target) {
			return;
		}
		
		for(int i=index; i<candidates.length && candidates[i] <= target; i++) {
			item.add(candidates[i]);
			helper(candidates, i, target - candidates[i], item);
			item.remove(item.size() - 1);
		}

	}
	
	/**
	 * �ǵݹ�
	 * @param candidates
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

		Stack<Integer> stack = new Stack<>();
		Arrays.sort(candidates);
		if(candidates[0] <= target) {
			stack.push(0);
		}
		int current = target - candidates[0];
		while(!stack.isEmpty()) {
			if(current == 0) {
				addAns(stack, candidates);
				current += candidates[stack.pop()];
				if(!stack.isEmpty()) {
					int next = stack.pop() + 1;
					current += candidates[next - 1];
					while(next >= candidates.length || candidates[next] > current) {
						if(stack.isEmpty()) {
							break;
						}
						next = stack.pop() + 1;
						current += candidates[next - 1];
					}
					if(next < candidates.length && candidates[next] <= current) {
						stack.push(next);
						current -= candidates[next];
					}
				}
			}else { //current ����0,���м������
				int next = stack.peek();
				if(candidates[next] <= current) {
					current -= candidates[next];
					stack.push(next);
				}else {//next��ʼ����current��
					next = stack.pop() + 1;
					current += candidates[next - 1];
					while(next >= candidates.length || candidates[next] > current) {
						if(stack.isEmpty()) {
							break;
						}
						next = stack.pop() + 1;
						current += candidates[next - 1];
					}
					if(next < candidates.length && candidates[next] <= current) {
						stack.push(next);
						current -= candidates[next];
					}
					
				}
				
			}
		}
		return ans;
	}
	
	/**
	 * ����2���Ż���
	 * @param candidates
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> combinationSum3(int[] candidates, int target) {

		Stack<Integer> stack = new Stack<>();
		Arrays.sort(candidates);
		if(candidates[0] <= target) {
			stack.push(0);
		}
		int current = target - candidates[0];
		int next = 0;
		while(!stack.isEmpty()) {
			if(current == 0) {
				addAns(stack, candidates);
				current += candidates[stack.pop()];
				if(!stack.isEmpty()) {
					next = stack.pop() + 1;
					current += candidates[next - 1];
				}
			}else { //current ����0,���м������
				next = stack.peek();
				if(candidates[next] > current) {
					next = stack.pop() + 1;
				}
			}
			while(next >= candidates.length || candidates[next] > current) {
				if(stack.isEmpty()) {
					break;
				}
				next = stack.pop() + 1;
				current += candidates[next - 1];
			}
			if(next < candidates.length && candidates[next] <= current) {
				stack.push(next);
				current -= candidates[next];
			}
		}
		return ans;
	}
	
	private static void addAns(Stack<Integer> stack, int[] candidates) {
		List<Integer> item = new ArrayList<>(stack);
		List<Integer> an = new ArrayList<>();
		for (Iterator<Integer> iterator = item.iterator(); iterator.hasNext();) {
			Integer index = iterator.next();
			an.add(candidates[index]);
		}
		ans.add(an);
	}
	
	/**
	 * ɾ�������е��ظ�Ԫ��
	 * @param candidates
	 * @return
	 */
	 public int[] removeDuplicates(int[] candidates) {
	        Arrays.sort(candidates);
	        
	        int index = 0;
	        for (int i = 0; i < candidates.length; i++) {
	        	//�൱��˫ָ�����
	            if (candidates[i] != candidates[index]) {
	                candidates[++index] = candidates[i];
	            }
	        }
	        
	        int[] nums = new int[index + 1];
	        for (int i = 0; i < index + 1; i++) {
	            nums[i] = candidates[i];
	        }
	        
	        return nums;
	   }
	 
	 /**
	  * ֱ��ȥ��
	  * @param candidates
	  * @param target
	  * @return
	  */
	 public  List<List<Integer>> combinationSum4(int[] candidates, int target) {
	        List<List<Integer>> result = new ArrayList<>();
	        if (candidates == null) {
	            return result;
	        }

	        List<Integer> combination = new ArrayList<>();
	        Arrays.sort(candidates);
	        helper(candidates, 0, target, combination, result);

	        return result;
	    }

	     void helper(int[] candidates,
	                 int index,
	                 int target,
	                 List<Integer> combination,
	                 List<List<Integer>> result) {
	        if (target == 0) {
	            result.add(new ArrayList<Integer>(combination));
	            return;
	        }

	        for (int i = index; i < candidates.length; i++) {
	            if (candidates[i] > target) {
	                break;
	            }
	            //ȥ�ز���
	            if (i != index && candidates[i] == candidates[i - 1]) {
	                continue;
	            }

	            combination.add(candidates[i]);
	            helper(candidates, i, target - candidates[i], combination, result);
	            combination.remove(combination.size() - 1);
	        }
	    }
	
	public static void main(String[] args) {
		int[] candidates = new int[] {2,3,6,7};
		combinationSum2(candidates, 7);
		println(ans);
	}
}