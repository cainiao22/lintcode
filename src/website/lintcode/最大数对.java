package website.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019年3月15日 下午3:55:29
 * @description
 * 		给两个数组，给一个最大值，在这两个数组里各找一个组成一对，求其和最接近最大值，但不大于最大值的所有数对。
		两个数组的长度不超过100000100000.
		每个元素不超过10000000001000000000.
 * @example
 * 		给定 a=[2,3,4,5,6], b=[4,5,7], x=8', 返回 [[3,5],[4,4]].

		解释:
		[3,5] 及 [4,4]的和是8，不超过8. 
		给定 a=[2,3,4,5,6], b=[4,5,7], x=10', 返回 [[3,7],[5,5],[6,4]].
		
		解释:
		[3,7],[5,5],[6,4]的和是9, 不超过10. 
 *
 * @Solution
 */
public class 最大数对 extends HH {
	
	/**
	 * 比较笨的方法 超时了
	 * @param a
	 * @param b
	 * @param x
	 * @return
	 */
	public int[][] getAns(int[] a, int[] b, int x) {
		int max = 0;
		Arrays.sort(a);
		Arrays.sort(b);
		List<int[]> list = new ArrayList<>();
        for(int i=0; i<a.length; i++) {
        	if(i > 0 && a[i] == a[i-1]) {
        		continue;
        	}
        	for(int j=0; j<b.length; j++) {
        		if(j >0 && b[j] == b[j-1]) {
        			continue;
        		}
        		if(a[i] + b[j] > x) {
        			break;
        		}
        		
        		if(a[i] + b[j] > max) {
        			max = a[i] + b[j];
        			list.clear();
        			list.add(new int[] {a[i],b[j]});
        		}else if(a[i] + b[j] == max) {
        			list.add(new int[] {a[i],b[j]});
        		}
        	}
        }
        
        int[][] result = new int[list.size()][2];
        for(int i=0; i<list.size(); i++) {
        	result[i] = list.get(i);
        }
        
        return result;
    }
	
	/**
	 * 内部二叉查找方式
	 * @param a
	 * @param b
	 * @param x
	 * @return
	 */
	public int[][] getAns2(int[] a, int[] b, int x) {
		int max = 0;
		Arrays.sort(a);
		Arrays.sort(b);
		int right = 0;
		for(int i=0; i<b.length; i++) {
			if(b[i] != b[right]) {
				right ++;
				b[right] = b[i];
			}
		}
		List<int[]> list = new ArrayList<>();
        for(int i=0; i<a.length; i++) {
        	if(i > 0 && a[i] == a[i-1]) {
        		continue;
        	}
        	if(a[i] <= x) {
        		int target = binarySearch(b, x - a[i], right);
        		if(target == -1) {
        			break;
        		}
        		if(target + a[i] < max) {
        			continue;
        		}
        		if(target + a[i] > max) {
        			list.clear();
        			max = target + a[i];
        		}
        		list.add(new int[] {a[i], target});
        	}else {
        		break;
        	}
        	
        }
        
        int[][] result = new int[list.size()][2];
        for(int i=0; i<list.size(); i++) {
        	result[i] = list.get(i);
        }
        
        return result;
	}
	
	private int binarySearch(int[] arr, int target, int right) {
		if(arr[0] > target) {
			return -1;
		}
		int left = 0;
		while(left <= right) {
			System.out.println(left + "," + right + "," + target);
			int mid = left + (right - left) / 2;
			if(arr[mid] == target) {
				return arr[mid];
			}else if(arr[mid] < target) {
				left = mid + 1;
			}else {
				right = mid - 1;
			}
		}
		return arr[left - 1];
	}
	
	/**
	 * lintcode top1
	 * @param a
	 * @param b
	 * @param x
	 * @return
	 */
	public int[][] getAns3(int[] a, int[] b, int x) {
		int max = 0;
		Arrays.sort(a);
		Arrays.sort(b);
		List<int[]> list = new ArrayList<>();
		int i=0, j=b.length - 1;
		while(i < a.length && j >= 0) {
			while(i > 0 && a[i] == a[i-1] && i < a.length) {
				i++;
			}
			while(j < b.length - 1 && b[j] == b[j + 1] && j >= 0) {
				j --;
			}
			if(i == a.length || j < 0) {
				break;
			}
			int item = a[i] + b[j];
			if(item <= x) {
				if(item > max) {
					max = item;
					list.clear();
					list.add(new int[] {a[i], b[j]});
				}else if(item == max) {
					list.add(new int[] {a[i], b[j]});
				}
				i ++;
			}else if(item > x) {
				j --;
			}
		}
		
		int[][] result = new int[list.size()][2];
        for(i=0; i<list.size(); i++) {
        	result[i] = list.get(i);
        }
        
        return result;
	}
	
	public int[][] getAns4(int[] a, int[] b, int x) {
        // Write your code here.
         Arrays.sort(a);
        Arrays.sort(b);
        List<int[]> result = new ArrayList<>();
        int curMax = Integer.MIN_VALUE;
        int index1 = 0, index2 = b.length - 1;
        while (index1 < a.length && index2 >= 0) {
            if (index1 != 0 && a[index1] == a[index1 - 1]) {
                index1++;
                continue;
            }
            if (index2 != b.length - 1 && b[index2] == b[index2 + 1]) {
                index2--;
                continue;
            }
            int sum = a[index1] + b[index2];
            if (sum > x) {
                index2--;
            } else {
                if (sum > curMax) {
                    result = new ArrayList<int[]>();
                    result.add(new int[]{a[index1], b[index2]});
                    curMax = sum;
                } else if (sum == curMax) {
                    result.add(new int[]{a[index1], b[index2]});
                    //index2--;
                }
                index1++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }
	
	public static void main(String[] args) {
		//[4,8,5,9,2,10,23]
		//[12,42,23,34,31,29]
		int[] a = new int[] {6,5,4,5,6,7,8};
		int[] b = new int[] {4,3,4,2,1,9,1,3};
		int x = 11;
		int[][] result = new 最大数对().getAns3(a, b, x);
		print(result);
	}

}
