package website.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2019��1��8�� ����4:56:24
 * @description ����Andy��Doris��Ҫѡ��һ�Ҳ͹ݳ���ͣ����Ƕ���һ��������ϲ���Ĳ͹��б�
 * 
 *              ����Ҫ�����ٵ��б������ܺ������������ҳ����ǵĹ�ͬ��Ȥ�� �����֮�����ѡ���ϵ����������д𰸲���û��˳��Ҫ��
 *              ����Լ�������һ���𰸡�
 * 
 *              1.�����б�ĳ��ȶ�����[1,1000]�ķ�Χ�ڡ� 2.�����б��е��ַ������Ƚ���[1,30]��Χ�ڡ�
 *              3.������0��ʼֱ�����б��ȼ�1�� 4.�����б��ж�û���ظ���
 * @example
 * 
 * 			���� 1: ����: 
 *          ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 *          ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse",
 *          "Shogun"] ���: ["Shogun"] ����: Ψһһ�������˶�ϲ���Ĳ����� "Shogun". 
 *          
 *          ���� 2: ����:
 *          ["Shogun", "Tapioca Express", "Burger King", "KFC"] ["KFC",
 *          "Shogun", "Burger King"] ���: ["Shogun"] ����: �����˶�ϲ���ġ�������������С�Ĳ�����
 *          "Shogun"����Ϊ 1 (0+1).
 *
 * @Solution
 */
public class �����б����С������ {

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
