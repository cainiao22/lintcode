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
 * @date 2017��9��6�� ����2:46:43
 * @description �����������ʣ�start��end����һ���ֵ䣬�ҵ���start��end�����ת������
 * 
 *              ���磺 ÿ��ֻ�ܸı�һ����ĸ�� �任�����е��м䵥�ʱ������ֵ��г��֡�
 * 
 *              ע������ ���û��ת�������򷵻�0�� ���е��ʾ�����ͬ�ĳ��ȡ� ���е��ʶ�ֻ����Сд��ĸ
 * 
 * @example �����������£� start = "hit" end = "cog" dict =
 *          ["hot","dot","dog","lot","log"] һ����̵ı任������ "hit" -> "hot" -> "dot"
 *          -> "dog" -> "cog"�� �������ĳ��� 5
 *
 * @Solution 1�����������������start��ʼ����ÿһ��λ��������ı�һ����ĸ�����õ��µ��ʣ�Ȼ�������µ�����ȥ��ѯ��
 * 				����鵽�ˣ���ô������ʾ�����һ����Ҫ������һ��Ԫ�أ��Դ����ơ�Ϊ�˱�����ѭ������Ҫһ������ȥ�洢�Ѿ�
 * 				�������ĵ��ʡ�
 */

//TODO �ύ��ͨ������Ҫ�ռ临�Ӷȵ��Ż�
public class ���ʽ��� extends HH {

	public int ladderLength(String start, String end, Set<String> dict) {
		// write your code here
		if (start.equals(end)) {
			return 0;
		}
		Queue<String> queue = new LinkedList<>();
		// ����Ѿ��������ĵ���
		Set<String> hash = new HashSet<>();
		queue.offer(start);
		dict.add(end);
		int length = 0;
		while (!queue.isEmpty()) {
			length++;
			//�����ǲ㼶��������ʱ���õ�������һ����������п��ܵĵ��ʡ�
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
					//�������hash�о���Ϊ��һ��ı���������������
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
				if (c == s.charAt(i)) { // ���������ͬ������Ҫ�滻
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
		int length = new ���ʽ���().ladderLength(start, end, set);
		System.out.println(length);
	}

}
