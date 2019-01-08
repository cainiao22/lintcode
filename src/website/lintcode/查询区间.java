package website.lintcode;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019年1月8日 下午4:11:46
 * @description  给定一个 List ，其中元素是 区间, 区间的长度是 1000, 例如 [500,1500], [2100,3100].给定一个 number 计算并确认是否在这些区间内.返回 True 或 False.
 * @example List = [[100,1100],[1000,2000],[5500,6500]] number = 6000  返回: True
 *
 * @Solution
 */
public class 查询区间 {
	
	public String isInterval(List<List<Integer>> intervalList, int number) {
        // Write your code here
		for (Iterator iterator = intervalList.iterator(); iterator.hasNext();) {
			List<Integer> list = (List<Integer>) iterator.next();
			if(number >= list.get(0) && number <= list.get(1)) {
				return "True";
			}
		}
		
		return "False";
    }

}
