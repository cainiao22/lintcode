package website.lintcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017年11月28日 下午2:25:04
 * @description
 * 		给一个字符串,你可以选择在一个字符或两个相邻字符之后拆分字符串,使字符串由仅一个字符或两个字符组成,输出所有可能的结果
 * @example
 * 		给一个字符串"123"
		返回[["1","2","3"],["12","3"],["1","23"]]
 *
 * @Solution 又是回溯、递归
 */
public class 分割字符串 extends HH {
	
	private static List<List<String>> result = new ArrayList<>();
	
	public static List<List<String>> splitString(String s) {
        // write your code here
		helper(s, 0, new ArrayList<String>());
		return result;
    }
	
	/**
	 * 递归方式
	 * @param s
	 * @param index
	 * @param item
	 */
	private static void helper(String s, int index, List<String> item) {
		if(index >= s.length()) {
			result.add(new ArrayList<>(item));
			return;
		}
		String a = String.valueOf(s.charAt(index));
		item.add(a);
		helper(s, index+1, item);
		item.remove(item.size()-1);
		if(index + 1 < s.length()) {
			a += s.charAt(index + 1);
			item.add(a);
			helper(s, index+2, item);
			item.remove(item.size() - 1);
		}
		/* 如果多了，可以这样
		 for (int i = index; i < index + 2 && i < s.length(); i++) {
	            String substring = s.substring(index, i + 1);
	            result.add(substring);
	            dfsHelper(results, result, i + 1, s);
	            result.remove(result.size() - 1);
	        }
	     */
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	//todo 回溯法非递归
	public static List<List<String>> splitStringIterator(String s) {
		Stack<String> stack = new Stack<>();
		for(int i=0;i<s.length(); i++) {
			stack.push(String.valueOf(s.charAt(i)));
		}
		result.add(new ArrayList<>(stack));
		stack.pop();
		while(true) {
			while(!stack.isEmpty() && stack.peek().length() != 2) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				break;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		splitString("123");
		for (int i = 0; i < result.size(); i++) {
			print(result.get(i));
		}
	}

}
