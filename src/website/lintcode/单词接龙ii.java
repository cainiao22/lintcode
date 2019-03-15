package website.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2017��9��6�� ����5:18:02
 * @description 
 * 			
		�����������ʣ�start��end����һ���ֵ䣬�ҳ����д�start��end�����ת������
		���磺
		
		    ÿ��ֻ�ܸı�һ����ĸ��
		    �任�����е��м䵥�ʱ������ֵ��г��֡�
		
		ע������
		
		    ���е��ʾ�����ͬ�ĳ��ȡ�
		    ���е��ʶ�ֻ����Сд��ĸ��


 * @example 
 *		 ����
		�����������£�
		start = "hit"
		end = "cog"
		dict = ["hot","dot","dog","lot","log"]
		����
		[
		    ["hit","hot","dot","dog","cog"],
		    ["hit","hot","lot","log","cog"]
		  ]
 * @Solution 1���͵�һ����������
 * 			 2��ȥ��Node,Ҫ�Ϻô����
 */
public class ���ʽ���ii {
	
	private static class Node {
		public Node parent;
		public String value;
		
		public Node(Node parent, String value) {
			super();
			this.parent = parent;
			this.value = value;
		}
	}
	
	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here  
		List<List<String>> result = new ArrayList<>();
		Queue<Node> queue = new LinkedList<>();
		Set<String> hash = new HashSet<>();
		dict.add(end);
		Node startNode = new Node(null, start);
		queue.offer(startNode);
		hash.add(start);
		boolean find = false;
		while(!queue.isEmpty() && !find) {
			int size = queue.size(); //���ﵥ���ó���������queue��̬����
			for(int i=0; i<size; i++) {
				Node current = queue.poll();
				for(String s : nextChars(current.value, dict)) {
					if(s.equals(end)) {
						find = true;
						List<String> item = new ArrayList<>();
						item.add(s);
						item.add(0, current.value);
						Node parent = current.parent;
						while(parent != null) {
							item.add(0, parent.value);
							parent = parent.parent;
						}
						result.add(item);
						break;
					}
					if(hash.contains(s)) {
						continue;
					}
					queue.offer(new Node(current, s));
				}
			}
		}
		
		return result;
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
	
	
	public List<List<String>> findLaddersFromJiuZhang(String start, String end, Set<String> dict) {
		//��Ž��
		List<List<String>> result = new ArrayList<>();
		Queue<String> queue = new LinkedList<>();
		//��Žڵ����
		Map<String, Integer> distance = new HashMap<>();
		//��ŵ�ǰ�ڵ��븸�ڵ�Ķ�Ӧ��ϵ
		Map<String, List<String>> map = new HashMap<>();
		distance.put(start, 0);
		dict.add(end);
		queue.add(start);
		bfs(dict, queue, map, distance);
		List<String> path = new ArrayList<>();
		path.add(end);
		dfs(result, map, distance, start, end, path);
		for(List<String> item : result) {
			Collections.reverse(item);
		}
		return result;
	}
	
	void bfs(Set<String> dict, Queue<String> queue, Map<String, List<String>> map, Map<String, Integer> distance) {
		while(!queue.isEmpty()) {
			String cur = queue.poll();
			for(String next : nextChars(cur, dict)) {
				if(!map.containsKey(next)) {
					map.put(next, new ArrayList<>());
				}
				map.get(next).add(cur);
				if(!distance.containsKey(next)) {
					distance.put(next, distance.get(cur) + 1);
					//distanceҲ������hash�ã���������ų���������ѭ��
					queue.offer(next);
				}
			}
		}
	}
	
	void dfs(List<List<String>> result, 
			Map<String, List<String>> map, 
			Map<String, Integer> distance, 
			String start, String cur,
			List<String> path) {
		if(cur.equals(start)) {
			result.add(new ArrayList<>(path));
			return;
		}
		for(String pre : map.get(cur)) {
			if(distance.get(pre) == distance.get(cur) - 1) {
				path.add(pre);
				dfs(result, map, distance, start, pre, path);
				path.remove(path.size() - 1);
			}
		}
	}
	
	public static void main(String[] args) {
		String[] dict = new String[] {"hot","dot","dog","lot","log"};
		Set<String> set = new HashSet<>();
		for (int i = 0; i < dict.length; i++) {
			set.add(dict[i]);
		}
		String start = "hit";
		String end = "cog";

		List<List<String>> result = new ���ʽ���ii().findLaddersFromJiuZhang(start, end, set);
		System.out.println("��֤�漣��ʱ�̣�");
		System.out.println(result);
	}
}
