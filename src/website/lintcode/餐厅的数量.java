package website.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019��1��10�� ����4:53:31
 * @description ����һ��List����������ݴ���ÿһ������������[x, y]���˿͵����괦��ԭ��[0,
 *              0]�����ҳ�n����˿�λ������Ĳ�����Ȼ��ȡ n ���ȳ�����List������˿͵ľ��벻���� n
 *              ����˿�����Ĳ���������롣������ n �Ҳ������������У�����������ԭʼ��˳��
 * @example
 * 			���� : n = 2 , List = [[0,0],[1,1],[2,2]]
			���� : [[0,0],[1,1]]
			���� : n = 3,List = [[0,1],[1,2],[2,1],[1,0]]
			���� :[[0,1],[1,2],[2,1]]
 *
 * @Solution ȡ��k�����a, Ȼ���ԭʼlist���ó�k��������a������
 */
public class ���������� extends HH {

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
    		//��Ϊarray[i]�ʼ������ߵ�Ԫ�أ��Ѿ������flag�����ˣ������ȼ���j,��������Ҫ��ľͿ���ֱ�Ӹ�ֵ��array[i]
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
    	}else { //���flag��λ�ò���k�������ҵ�k��ʱ����Ȼ�ǵ�k�� ��Ϊǰ��i+1����Ȼ��ǰ��û�иı�λ��
    		return getTheKNum(array, k, i + 1, right);
    	}
    }
    
    public static void main(String[] args) {
    	int array[] = new int[] {1};
    	for(int k = 1; k<= array.length; k++) {
			int res = new ����������().getTheKNum(array, k, 0, array.length - 1);
			System.out.println(res);
    	}
	}
    
}
