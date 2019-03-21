package website.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019年3月20日 上午10:31:08
 * @description
 * 			实现一个数据结构，提供下面两个接口
			1.add(number) 添加一个元素
			2.topk() 返回前K大的数
 * @example
 *
 * @Solution
 */
public class 前K大数II {
	
	private int[] arr;
	private int current = 0;
	
	public 前K大数II (int k) {
        // do intialization if necessary
		this.arr = new int[k];
    }

    /*
     * @param num: Number to be added
     * @return: nothing
     */
    public void add(int num) {
        // write your code here(i*2+1, i*2+2)
    	if(current == arr.length) {
    		if(arr[0] < num) {
    			arr[0] = num;
    			int index = 0;
    			while(index * 2 + 1 < arr.length) {
    				int left = index * 2 + 1;
        			int right = index * 2 + 2;
        			int min = index;
        			if(left < arr.length && arr[min] > arr[left]) {
        				min = left;
        			}
        			
        			if(right < arr.length && arr[min] > arr[right]) {
        				min = right;
        			}
        			if(min == index) {
        				break;
        			}
        			int temp = arr[index];
        			arr[index] = arr[min];
        			arr[min] = temp;
        			index = min;
    			}
    			
    		}
    	}else {
    		arr[current] = num;
    		int index = current;
    		while((index - 1) / 2 >= 0) {
    			int parent = (index - 1) / 2;
    			if(arr[index] < arr[parent]) {
    				int temp = arr[index];
    				arr[index] = arr[parent];
    				arr[parent] = temp;
    				index = parent;
    			}else {
    				break;
    			}
    		}
    		
    		current ++;
    	}
    	
    	for(int i=0; i<current; i++) {
    		System.out.print(arr[i] + " ");
    	}
    	
    	System.out.println("++++++++++++++++");
    }

    /*
     * @return: Top k element
     */
    public List<Integer> topk() {
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<current; i++) {
        	result.add(arr[i]);
        }
       Collections.sort(result, new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	});
       return result;
        
    }
    
    public static void main(String[] args) {
    	前K大数II a = new 前K大数II(3);
    	a.add(3);
    	a.add(10);
    	a.add(100);
    	a.add(4);
    	
    	System.out.println(a.topk());
	}

}
