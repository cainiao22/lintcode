package website.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author yanpf
 * @date 2019年3月13日 上午10:46:26
 * @description
 * 		给定一个无重复的质数数组arr，每个质数最多使用一次，求所有无重复的乘积并从小到大排序。

		2 <= |arr| <= 9
		2 <= arr[i] <= 23
 * @example
 * 		给出 arr = [2,3], 返回 [6]。

		解释：
		2*3=6。
		给出 arr = [2,3,5], 返回 [6,10,15,30]。
		
		解释：
		2*3=6, 2*5=10, 3*5=15, 2*3*5=30。
 *
 * @Solution
 */
public class 质数乘积 extends HH {
	
	private List<Integer> list = new ArrayList<>();
	
	 public int[] getPrimeProduct(int[] arr) {
	     int max = 1 << arr.length;
	     for(int i=1; i<max; i++) {
	    	 if((i & (i - 1)) == 0) {
	    		 continue;
	    	 }
	    	 int item = 1;
	    	 for(int j=0; j<arr.length; j++) {
	    		 if((i & (1 << j)) != 0) {
	    			 item *= arr[j];
	    		 }
	    	 }
	    	 
	    	 list.add(item);
	     }
	    
	     int[] result = new int[list.size()];
	     for(int i=0; i<result.length; i++) {
	    	 result[i] = list.get(i);
	     }
	     Arrays.sort(result);
	     return result;
	 }
	 
	 /**
	  * TODO 有问题 
	  * @param arr
	  * @return
	  */
	 public int[] getPrimeProduct2(int[] arr) {
	     Queue<Integer> queue = new LinkedList<>();
	     for(int i=0; i<arr.length - 1; i++) {
	    	 for(int j=i+1; j<arr.length; j++) {
	    		 queue.add(arr[i] * arr[j]);
	    	 }
	     }
	     List<Integer> list = new ArrayList<>();
	     //参与乘积的数     开始只有两个
	     int count = 2;
	     while(!queue.isEmpty()) {
	    	 int len = queue.size();
	    	 int inner = 0;
	    	 int start = count + 0;
	    	 for(int i=0; i<len; i++) {
	    		 Integer top = queue.poll();
	    		 list.add(top);
	    		 //下一个参与乘积的开始是从第count个开始的
    			 for(int j=start; j<arr.length; j++) {
    				 queue.add(top * arr[j]);
    			 }
    			 if(start >= arr.length) {
    				 inner ++;
    				 start = count + inner;
    			 }else {
    				 start ++;
    			 }
    			 
	    	 }
	    	 
	    	 count ++;
	     }
	     
	     int[] result = new int[list.size()];
	     for(int i=0; i<result.length; i++) {
	    	 result[i] = list.get(i);
	     }
	     Arrays.sort(result);
	     return result;
	 }
	 
	/**
	 * 用arr的前两个元素初始化res，每增加一个元素arr[i],在res中添加arr中i位置之前的元素与arr[i]的乘积，再添加原res中所有元素与arr[i]的乘积。
	 * 例如 {2,3,5,7} 
	 * 开始res={2*3}={6} 添加5，{2*5,3*5}={10,15},{6*5}={30}
	 * res={6,10,15,30}
	 * 添加7，{2*7,3*7,5*7}={14,21,35},{6*7,10*7,15*7,30*7}={42,70,105,210}
	 * res={6,10,14,15,21,30,35,42,70,105,210}
	 * 
	 * @param arr
	 * @return
	 */
	 public int[] getPrimeProduct3(int[] arr) {
		List<Integer> result = new ArrayList<>();
		result.add(arr[0] * arr[1]);
		for(int i=2; i<arr.length; i++) {
			int length = result.size();
			for(int j=0; j<length; j++) {
				result.add(result.get(j) * arr[i]);
			}
			for(int j=0; j<i; j++) {
				result.add(arr[i]*arr[j]);
			}
		}
		
		Collections.sort(result);
		int[] resultArr = new int[result.size()];
	     for(int i=0; i<resultArr.length; i++) {
	    	 resultArr[i] = result.get(i);
	     }
	     
	     return resultArr;
	 }
	 
	 /**
	  * 使用递归方式
	  * 
	  * 首先我们分析这个题目，其就是将找出这些数，然后对其进行排序，首先我们看一下如何找出这些数，我们可以看出这些数都是任意两个数、任意三个数、
	  * 一直到所有的数。然后就是将任意N个数的所有组合找出来，这个地方我们用到的是深搜（dfs）,通过数组的顺序将其找出， 
		例如： 
		arr=[2,3,5,7]我们找到2个数的任意组合。首先找到2，然后第二数是3，5，7接着是3，其第二个数是5，7、
		最后是5，其第二个数是7这样就得到了两个的所有组合。
--
	  * @param arr
	  * @return
	  */
	 public int[] getPrimeProduct4(int[] arr) {
		 List<Integer> list = new ArrayList<>();
		 helper(arr, 0, 0, 1, list);
		 
		 int[] result = new int[list.size()];
	     for(int i=0; i<result.length; i++) {
	    	 result[i] = list.get(i);
	     }
	     Arrays.sort(result);
	     return result;
	 }
	 
	 private void helper(int[] arr, int current, int count, int fac, List<Integer> list) {
		 if(current == arr.length) {
			 return;
		 }
		 if(count == 0) { //当前还没有数
			 helper(arr, current + 1, count + 1, fac * arr[current], list);
			 helper(arr, current + 1, count, fac, list);
		 }else {
			 list.add(fac * arr[current]);
			 //将当前数据算入乘积
			 helper(arr, current + 1, count + 1, fac * arr[current], list);
			 //不将当前数据算入乘积
			 helper(arr, current + 1, count, fac, list);
		 }
	 }
	 
	 
	 
	 public static void main(String[] args) {
		 int[] arr = new int[] {2,3,5};
		 int[] result1 = new 质数乘积().getPrimeProduct(arr);
		 int[] result2 = new 质数乘积().getPrimeProduct4(arr);
		 print(result1);
		 print(result2);
	}

}
