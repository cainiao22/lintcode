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
 * @date 2019��3��13�� ����10:46:26
 * @description
 * 		����һ�����ظ�����������arr��ÿ���������ʹ��һ�Σ����������ظ��ĳ˻�����С��������

		2 <= |arr| <= 9
		2 <= arr[i] <= 23
 * @example
 * 		���� arr = [2,3], ���� [6]��

		���ͣ�
		2*3=6��
		���� arr = [2,3,5], ���� [6,10,15,30]��
		
		���ͣ�
		2*3=6, 2*5=10, 3*5=15, 2*3*5=30��
 *
 * @Solution
 */
public class �����˻� extends HH {
	
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
	  * TODO ������ 
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
	     //����˻�����     ��ʼֻ������
	     int count = 2;
	     while(!queue.isEmpty()) {
	    	 int len = queue.size();
	    	 int inner = 0;
	    	 int start = count + 0;
	    	 for(int i=0; i<len; i++) {
	    		 Integer top = queue.poll();
	    		 list.add(top);
	    		 //��һ������˻��Ŀ�ʼ�Ǵӵ�count����ʼ��
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
	 * ��arr��ǰ����Ԫ�س�ʼ��res��ÿ����һ��Ԫ��arr[i],��res�����arr��iλ��֮ǰ��Ԫ����arr[i]�ĳ˻��������ԭres������Ԫ����arr[i]�ĳ˻���
	 * ���� {2,3,5,7} 
	 * ��ʼres={2*3}={6} ���5��{2*5,3*5}={10,15},{6*5}={30}
	 * res={6,10,15,30}
	 * ���7��{2*7,3*7,5*7}={14,21,35},{6*7,10*7,15*7,30*7}={42,70,105,210}
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
	  * ʹ�õݹ鷽ʽ
	  * 
	  * �������Ƿ��������Ŀ������ǽ��ҳ���Щ����Ȼ�������������������ǿ�һ������ҳ���Щ�������ǿ��Կ�����Щ������������������������������
	  * һֱ�����е�����Ȼ����ǽ�����N��������������ҳ���������ط������õ��������ѣ�dfs��,ͨ�������˳�����ҳ��� 
		���磺 
		arr=[2,3,5,7]�����ҵ�2������������ϡ������ҵ�2��Ȼ��ڶ�����3��5��7������3����ڶ�������5��7��
		�����5����ڶ�������7�����͵õ���������������ϡ�
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
		 if(count == 0) { //��ǰ��û����
			 helper(arr, current + 1, count + 1, fac * arr[current], list);
			 helper(arr, current + 1, count, fac, list);
		 }else {
			 list.add(fac * arr[current]);
			 //����ǰ��������˻�
			 helper(arr, current + 1, count + 1, fac * arr[current], list);
			 //������ǰ��������˻�
			 helper(arr, current + 1, count, fac, list);
		 }
	 }
	 
	 
	 
	 public static void main(String[] args) {
		 int[] arr = new int[] {2,3,5};
		 int[] result1 = new �����˻�().getPrimeProduct(arr);
		 int[] result2 = new �����˻�().getPrimeProduct4(arr);
		 print(result1);
		 print(result2);
	}

}
