package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author yanpf
 * @date 2017年12月11日 下午5:40:06
 * @description
 * 		
		设计一个数据结构实现在平均 O(1) 的复杂度下执行以下所有的操作。
		
		    insert(val): 如果这个元素不在set中，则插入。
		
		    remove(val): 如果这个元素在set中，则从set中移除。
		
		    getRandom: 随机从set中返回一个元素。每一个元素返回的可能性必须相同。


 * @example
 *
 * @Solution
 */
public class RandomizedSet {
	
	private Random random;
	
	//存放每个值所在的位置
	private Map<Integer, Integer> map;
	
	//存放具体的值
	private List<Integer> values;
	
	public RandomizedSet() {
        // do intialization if necessary
		map = new HashMap<>();
		values = new ArrayList<>();
		random = new Random();
    }

    /*
     * @param val: a value to the set
     * @return: true if the set did not already contain the specified element or false
     */
    public boolean insert(int val) {
        // write your code here
    	if(map.containsKey(val)) {
    		return false;
    	} 

    	map.put(val, values.size());
    	values.add(val);
    	return true;
    }

    /*
     * @param val: a value from the set
     * @return: true if the set contained the specified element or false
     */
    public boolean remove(int val) {
        // write your code here
    	if(map.containsKey(val)) {
    		int index = map.get(val);
    		if(index != values.size() - 1) {
	    		values.set(index, values.get(values.size() - 1));
	    		map.put(values.get(index), index);
    		}
    		values.remove(values.size() - 1);
    		map.remove(val);
    		return true;
    	}
    	return false;
    }

    /*
     * @return: Get a random element from the set
     */
    public int getRandom() {
        // write your code here
    	return values.get(random.nextInt(values.size()));
    }

}
