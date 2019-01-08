package website.lintcode;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019��1��8�� ����4:11:46
 * @description  ����һ�� List ������Ԫ���� ����, ����ĳ����� 1000, ���� [500,1500], [2100,3100].����һ�� number ���㲢ȷ���Ƿ�����Щ������.���� True �� False.
 * @example List = [[100,1100],[1000,2000],[5500,6500]] number = 6000  ����: True
 *
 * @Solution
 */
public class ��ѯ���� {
	
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
