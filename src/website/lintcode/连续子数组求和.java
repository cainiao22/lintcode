package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2017年11月3日 下午4:28:37
 * @description
 * 		给定一个整数数组，请找出一个连续子数组，使得该子数组的和最大。输出答案时，
 * 		请分别返回第一个数字和最后一个数字的下标。（如果两个相同的答案，请返回其中任意一个）
 * @example
 * 
 * 		给定 [-3, 1, 3, -3, 4], 返回[1,4].
 *
 * @Solution
 */
public class 连续子数组求和 extends HH {
	
	public List<Integer> continuousSubarraySum(int[] A) {
        // write your code here
		int max = Integer.MIN_VALUE;
		int begin = 0, end = 0;
		int current = 0;
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<A.length; i++) {
			current += A[i];
			if(current > max) {
				end = i;
				max = current;
				//把当前的最大坐标拿出来，
				list.clear();
				list.add(begin);
				list.add(end);
			}
			if(current < 0) {
				begin = end = i + 1;
				current = 0;
			}
		}
		return list;
    }
	
	/**
	 * 和上面的效果一样
	 * @param A
	 * @return
	 */
	public ArrayList<Integer> continuousSubarraySumFromJiuZhang(int[] A) {
        // Write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(0);
        result.add(0);

        int len = A.length;
        int start = 0, end = 0;
        int sum = 0;
        int ans = -0x7fffffff;
        for (int i = 0; i < len; ++i) {
        	//这里比上面处理的好一些
            if (sum < 0) {
                sum = A[i];
                start = end = i;
            } else {
                sum += A[i];
                end = i;
            }
            if (sum >= ans) {
                ans = sum;
                result.set(0, start);
                result.set(1, end);
            }
        }
        return result;
    }
	
	public static void main(String[] args) {
		int[] A = new int[] {1,2,-2,-100,1,2,-2};
		print(new 连续子数组求和().continuousSubarraySum(A));
	}

}
