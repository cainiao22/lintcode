package website.lintcode;

import java.util.HashSet;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019年3月21日 下午8:02:54
 * @description
 * 		如果一个子数组中出现的值不超过两个，则该子数组是有趣的。给一个数组a，求其中最长的一个有趣子数组。输出最大的长度。
 * @example
 *		a = [1,2,1,3]
		返回 3
		a=[1,1,1,1]
		返回 4
 * @Solution
 */
public class 有趣的子数组 {
	
	public int maxLen(int[] a) {
		// Write your code here
		int max = 0;
		for (int i = 0; i < a.length; i++) {
			HashSet<Integer> set = new HashSet<>();
			set.add(a[i]);
			for (int j = i + 1; j < a.length; j++) {
				max = Integer.max(max, j - i);
				set.add(a[j]);
				if (set.size() > 2) {
					break;
				}
			}
			if(set.size() <= 2) {
				max = Integer.max(max, a.length - i);
			}
		}

		return max;
	}
	
	/**
	 * 维持一个虚拟区间，区间开头是a,结尾是b,a和b是具体的数字，就是a[i],count_b代表的是b的数量，中间不能有a,
	 * 当出现第三个数时候，当前数组长度重新计算为count_b+1,因为这时候a就成了b,b就变成了新加进来的数
	 * @param a
	 * @return
	 */
	public int maxLen2(int[] arr) {
		int a = 0, b=0, max = 0, cur=0, count_b = 0;
		for(int num : arr) {
			if(num == a || num == b) {
				cur ++;
			}else {
				cur = count_b + 1;
			}
			if(num == b) {
				count_b ++;
			}else {
				count_b = 1;
				a = b;
				b = num;
			}
			
			max = Integer.max(max, cur);
		}
		
		return max;
	}
	
	/**
	 * 
	 * @param a
	 * @return
	 */
	int maxLen3(int[] a) {
		//一个数在区间内出现的次数
        int[] count = new int[1000];
        //这个是不同数字的个数
        int nums = 0;
        int len = 0;
        for(int i = 0, j = 0; i < a.length; i++) {
        	//原先这个位置为0，表示这个区间原来没有这个数
            if(0 == count[a[i]]++) 
            {
                nums++;
            }
            //这个表示区间内出现了三个数，需要去掉一个
            for(; nums > 2; j++) 
            {
                if(1 == count[a[j]] --) 
                {
                    nums--;
                }
            }
            len = Math.max(len, i - j + 1);
        }
        return len;
    }
	
	public static void main(String[] args) {
		int[] a = new int[] {1,2,1,2};
		int result = new 有趣的子数组().maxLen3(a);
		System.out.println(result);
	}

}
