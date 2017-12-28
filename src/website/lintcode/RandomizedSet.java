package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author yanpf
 * @date 2017��12��11�� ����5:40:06
 * @description
 * 		
		���һ�����ݽṹʵ����ƽ�� O(1) �ĸ��Ӷ���ִ���������еĲ�����
		
		    insert(val): ������Ԫ�ز���set�У�����롣
		
		    remove(val): ������Ԫ����set�У����set���Ƴ���
		
		    getRandom: �����set�з���һ��Ԫ�ء�ÿһ��Ԫ�ط��صĿ����Ա�����ͬ��


 * @example
 *
 * @Solution
 */
public class RandomizedSet {
	
	private Random random;
	
	//���ÿ��ֵ���ڵ�λ��
	private Map<Integer, Integer> map;
	
	//��ž����ֵ
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
