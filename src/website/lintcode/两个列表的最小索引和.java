package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2019年1月8日 下午4:56:24
 * @description 假设Andy和Doris想要选择一家餐馆吃晚餐，他们都有一个各自最喜爱的餐馆列表。
 * 
 *              你需要用最少的列表索引总和来帮助他们找出他们的共同兴趣。 如果答案之间存在选择关系，则输出所有答案并且没有顺序要求。
 *              你可以假设总有一个答案。
 * 
 *              1.两个列表的长度都将在[1,1000]的范围内。 2.两个列表中的字符串长度将在[1,30]范围内。
 *              3.索引从0开始直到到列表长度减1。 4.两个列表中都没有重复。
 * @example
 * 
 * 			案例 1: 输入: 
 *          ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 *          ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse",
 *          "Shogun"] 输出: ["Shogun"] 解释: 唯一一个两个人都喜欢的餐厅是 "Shogun". 
 *          
 *          案例 2: 输入:
 *          ["Shogun", "Tapioca Express", "Burger King", "KFC"] ["KFC",
 *          "Shogun", "Burger King"] 输出: ["Shogun"] 解释: 两个人都喜欢的、并且索引和最小的餐厅是
 *          "Shogun"，和为 1 (0+1).
 *
 * @Solution
 */
public class 两个列表的最小索引和 {

	public String[] findRestaurant(String[] list1, String[] list2) {
        // Write your code here
		int min = list1.length + list2.length;
		Map<Integer, List<String>> map = new HashMap<>();
		for(int i=0; i<list1.length; i++) {
			for(int j=0; j<list2.length; j++) {
				if(list1[i].equals(list2[j])) {
					if(min >= i + j) {
						min = i + j;
						if(map.get(min) == null) {
							map.put(min, new ArrayList<String>());
						}
						map.get(min).add(list1[i]);
					}
				}
			}
		}
		
		if(map.get(min) != null) {
			String res[] = new String[map.get(min).size()];
			int i = 0;
			for (Iterator iterator = map.get(min).iterator(); iterator.hasNext();) {
				String type = (String) iterator.next();
				res[i] = type;
				i ++;
			}
			
			return res;
		}
		
		return new String[] {};
    }
}
