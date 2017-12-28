package website.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;import javax.swing.text.AbstractDocument.LeafElement;

/**
 * 
 * @author yanpf
 * @date 2017年12月14日 下午6:03:48
 * @description
 * 		给出一个候选数字的set(C)和目标数字(T),找到C中所有的组合，使找出的数字和为T。C中的数字可以无限制重复被选取。
		例如,给出候选数组[2,3,6,7]和目标数字7，所求的解为：
		[7]，
		[2,2,3]
		
 * @example
 * 		给出候选set[2,3,6,7]和目标数字7
		返回 [[7],[2,2,3]]
 *
 * @Solution
 */
public class 数字组合 extends HH {
	
	private static List<List<Integer>> ans = new ArrayList<>();
	
	/**
	 * 递归求解
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
	 * 非递归
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
			}else { //current 大于0,还有减的余地
				int next = stack.peek();
				if(candidates[next] <= current) {
					current -= candidates[next];
					stack.push(next);
				}else {//next开始大于current了
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
	 * 方法2的优化版
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
			}else { //current 大于0,还有减的余地
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
	 * 删除数组中的重复元素
	 * @param candidates
	 * @return
	 */
	 public int[] removeDuplicates(int[] candidates) {
	        Arrays.sort(candidates);
	        
	        int index = 0;
	        for (int i = 0; i < candidates.length; i++) {
	        	//相当于双指针操作
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
	  * 直接去重
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
	            //去重操作
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