package website.lintcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * 
 * @author yanpf
 * @date 2017年9月27日 上午10:37:42
 * @description
 * 
 * 				为最近最少使用（LRU）缓存策略设计一个数据结构，它应该支持以下操作：获取数据（get）和写入数据（set）。
 * 
 *              获取数据get(key)：如果缓存中存在key，则获取其数据值（通常是正数），否则返回-1。
 * 
 *              写入数据set(key,
 *              value)：如果key还没有在缓存中，则写入其数据值。当缓存达到上限，它应该在写入新数据之前删除最近最少使用的数据用来腾出空闲位置。
 * 
 * 
 * @example
 *
 * @Solution
 */
public class LRU缓存策略 {
	public static void main(String[] args) {
		LRUCacheFromJiuZhang lruCache = new LRUCacheFromJiuZhang(3);
		lruCache.set(1,1);
		lruCache.set(2,2);
		lruCache.set(3,3);
		lruCache.set(4,4);
		System.out.println(lruCache.get(4));
		System.out.println(lruCache.get(3));
		System.out.println(lruCache.get(2));
		System.out.println(lruCache.get(1));
		lruCache.set(5, 5);
		System.out.println(lruCache.get(1));
		System.out.println(lruCache.get(2));
		System.out.println(lruCache.get(3));
		System.out.println(lruCache.get(4));
		System.out.println(lruCache.get(5));
		
		
		//lruCache.set(2,2),set(3,3),set(4,4),get(4),get(3),get(2),get(1),set(5,5),get(1),get(2),get(3),get(4),get(5)
	}
}

class LRUCache {
	
	private static class Node{
		int times;
		long timestamp;
	}
	
	private Map<Integer, Integer> map;
	private Map<Integer, Node> times;
	private int capacity;

	/*
	 * @param capacity: An integer
	 */public LRUCache(int capacity) {
		map = new HashMap<>();
		times = new HashMap<>();
		this.capacity = capacity;
	}

	/*
	 * @param key: An integer
	 * 
	 * @return: An integer
	 */
	public int get(int key) {
		// write your code here
		if (map.get(key) != null) {
			Node t = times.get(key);
			t.times += 1;
			t.timestamp = System.currentTimeMillis();
			return map.get(key);
		}
		return -1;
	}

	/*
	 * @param key: An integer
	 * 
	 * @param value: An integer
	 * 
	 * @return: nothing
	 */
	public void set(int key, int value) {
		// write your code here
		if (map.size() >= capacity) {
			Node min = new Node();
			min.times = Integer.MAX_VALUE;
			min.timestamp = System.currentTimeMillis();
			int remove = 0;
			for(Map.Entry<Integer, Node> t : times.entrySet()) {
				if(min.times > t.getValue().times) {
					min = t.getValue();
					remove = t.getKey();
				}else if(min.times == t.getValue().times) {
					if(min.timestamp > t.getValue().timestamp) {
						min = t.getValue();
						remove = t.getKey();
					}
				}
			}
			map.remove(remove);
			times.remove(remove);
			capacity = map.size();
		}
		map.put(key, value);
		Node t = new Node();
		t.times = 0;
		t.timestamp = System.currentTimeMillis();
		times.put(key, t);
	}
}

class LRUCacheFromJiuZhang {
	private class Node {
		int key, value;
		Node pre, next;
		
		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private Map<Integer, Node> map;
	private int capacity;
	private Node head, tail;
	
	public LRUCacheFromJiuZhang(int capacity) {
		map = new HashMap<>();
		this.capacity = capacity;
		head = new Node(-1, -1);
		tail = new Node(-1, -1);
		head.next = tail;
		tail.pre = head;
	}

	/*
	 * @param key: An integer
	 * 
	 * @return: An integer
	 */
	public int get(int key) {
		// write your code here
		if(map.containsKey(key)) {
			Node currentNode = map.get(key);
			currentNode.pre.next = currentNode.next;
			currentNode.next.pre = currentNode.pre;
			moveToTail(currentNode);
			return currentNode.value;
		}
		return -1;
	}

	/*
	 * @param key: An integer
	 * 
	 * @param value: An integer
	 * 
	 * @return: nothing
	 */
	public void set(int key, int value) {
		// write your code here
		if(map.containsKey(key)) {
			map.get(key).value = value;
			return;
		}
		if(map.size() == capacity) {
			Node remove = head.next;
			head.next = head.next.next;
			head.next.pre = head;
			map.remove(remove.key);
		}
		Node current = new Node(key, value);
		moveToTail(current);
		map.put(key, current);
	}
	
	private void moveToTail(Node currentNode) {
		tail.pre.next = currentNode;
		currentNode.next = tail;
		currentNode.pre = tail.pre;
		tail.pre = currentNode;
	}
	
}
