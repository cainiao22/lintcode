package website.lintcode;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2017年9月6日 下午2:46:43
 * @description 给出两个单词（start和end）和一个字典，找到从start到end的最短转换序列
 * 
 *              比如： 每次只能改变一个字母。 变换过程中的中间单词必须在字典中出现。
 * 
 *              注意事项 如果没有转换序列则返回0。 所有单词具有相同的长度。 所有单词都只包含小写字母
 * 
 * @example 给出数据如下： start = "hit" end = "cog" dict =
 *          ["hot","dot","dog","lot","log"] 一个最短的变换序列是 "hit" -> "hot" -> "dot"
 *          -> "dog" -> "cog"， 返回它的长度 5
 *
 * @Solution 1、宽度优先搜索，从start开始，对每一个位置穷举它改变一个字母后所得的新单词，然后拿着新单词中去查询。
 * 				如果查到了，那么这个单词就是下一层需要遍历的一个元素；以此类推。为了避免死循环，需要一个集合去存储已经
 * 				遍历过的单词。
 */

//TODO 提交不通过。需要空间复杂度的优化
public class 单词接龙 extends HH {

	public int ladderLength(String start, String end, Set<String> dict) {
		// write your code here
		if (start.equals(end)) {
			return 0;
		}
		Queue<String> queue = new LinkedList<>();
		// 存放已经遍历过的单词
		Set<String> hash = new HashSet<>();
		queue.offer(start);
		dict.add(end);
		int length = 0;
		while (!queue.isEmpty()) {
			length++;
			//这里是层级遍历，这时候拿到的是这一层下面的所有可能的单词。
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String current = queue.poll();
				if (end.equals(current)) {
					return length;
				}
				for (String s : nextChars(current, dict)) {
					if(hash.contains(s)) {
						continue;
					}
					//如果不在hash中就作为下一层的遍历对象放入队列中
					queue.offer(s);
				}

			}
		}
		
		return 0;
	}

	private String replaceChar(String s, int index, char c) {
		char[] arr = s.toCharArray();
		arr[index] = c;
		return new String(arr);
	}

	private List<String> nextChars(String s, Set<String> dict) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			for (char c = 'a'; c <= 'z'; c++) {
				if (c == s.charAt(i)) { // 这里如果相同，不需要替换
					continue;
				}
				String newChar = replaceChar(s, i, c);
				if (dict.contains(newChar)) {
					list.add(newChar);
				}
			}
		}

		return list;
	}
	
	public static void main(String[] args) {
		String[] dict = new String[] {"hot","dot","dog","lot","log"};
		Set<String> set = new HashSet<>();
		for (int i = 0; i < dict.length; i++) {
			set.add(dict[i]);
		}
		String start = "hit";
		String end = "cog";
		int length = new 单词接龙().ladderLength(start, end, set);
		System.out.println(length);
	}

}
