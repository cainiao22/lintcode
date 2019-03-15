package website.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019年1月10日 下午4:53:31
 * @description 给出一个List，里面的数据代表每一个餐厅的坐标[x, y]。顾客的坐标处于原点[0,
 *              0]。先找出n家离顾客位置最近的餐厅，然后取 n 家先出现在List中且与顾客的距离不超过 n
 *              家离顾客最近的餐厅的最长距离。返回这 n 家餐厅的坐标序列，按输入数据原始的顺序。
 * @example
 * 			给定 : n = 2 , List = [[0,0],[1,1],[2,2]]
			返回 : [[0,0],[1,1]]
			给定 : n = 3,List = [[0,1],[1,2],[2,1],[1,0]]
			返回 :[[0,1],[1,2],[2,1]]
 *
 * @Solution 取第k大的数a, 然后从原始list中拿出k个不大于a的坐标
 */
public class 餐厅的数量 extends HH {

    public List<List<Integer>> nearestRestaurant(List<List<Integer>> restaurant, int n) {
    	if(restaurant == null || restaurant.size() == 0) {
    		return new ArrayList<>();
    	}
    	if(restaurant.size() < n) {
    		return restaurant;
    	}
        // Write your code here
    	int array[] = new int[restaurant.size()];
    	for(int i=0; i<restaurant.size(); i++) {
    		List<Integer> item = restaurant.get(i);
    		array[i] = item.get(0)*item.get(0) + item.get(1)* item.get(1);
    	}
    	
    	int k = getTheKNum(array, n, 0, restaurant.size() - 1);   
    	List<List<Integer>> result = new ArrayList<>();
    	for(int i=0; i<restaurant.size(); i++) {
    		int j = 0;
    		List<Integer> item = restaurant.get(i);
    		int distance = item.get(0)*item.get(0) + item.get(1)* item.get(1);
    		if(distance <= k) {
    			result.add(item);
    			j ++;
    		}
    		if(j == n) {
    			break;
    		}
    	}
    	
    	return result;
    }
    public int getTheKNum(int array[], int k, int left, int right) {
    	int flag = array[left];
    	int i, j;
    	for(i=left, j=right; i<j; ) {
    		//因为array[i]最开始是最左边的元素，已经存放在flag里面了，所以先检验j,看到不合要求的就可以直接赋值给array[i]
    		while(array[j] > flag && i < j) {
    			j --;
    		}
    		if(array[j] < flag) {
    			array[i ++] = array[j];
    		}
    		while(array[i] <= flag && i < j) {
    			i ++;
    		}
    		//
    		if(array[i] >= flag) {
    			array[j --] = array[i];
    		}
    	}
    	array[i] = flag;
    	if(i + 1 == k) {
    		return flag;
    	}else if(i + 1 > k) {
    		return getTheKNum(array, k, left, i - 1);
    	}else { //如果flag的位置不到k，后面找第k的时候仍然是第k个 因为前面i+1个仍然在前面没有改变位置
    		return getTheKNum(array, k, i + 1, right);
    	}
    }
    
    public static void main(String[] args) {
    	int array[] = new int[] {1};
    	for(int k = 1; k<= array.length; k++) {
			int res = new 餐厅的数量().getTheKNum(array, k, 0, array.length - 1);
			System.out.println(res);
    	}
	}
    
}
