package website.lintcode;

import java.util.ArrayList;

import java.util.List;


/**
 * 
 * @author yanpf
 * @date 2017��11��3�� ����4:09:56
 * @description
 * 		��һ���ʵ䣬�ҳ�����������ĵ��ʡ�
 * @example
 * 
 * 		�ڴʵ�
		{
		  "dog",
		  "google",
		  "facebook",
		  "internationalization",
		  "blabla"
		}
		
		��, ��ĵ��ʼ���Ϊ ["internationalization"]
		
		�ڴʵ�
		
		{
		  "like",
		  "love",
		  "hate",
		  "yes"
		}
		
		�У���ĵ��ʼ���Ϊ ["like", "love", "hate"]
 *
 * @Solution
 */
public class ����� {
	
	public List<String> longestWords(String[] dictionary) {
        // write your code here
		ArrayList<String> list = new ArrayList<>();
		int max = 0;
		for(String item : dictionary) {
			if(item.length() > max) {
				list.clear();
				list.add(item);
				max = item.length();
			}else if(item.length() == max) {
				list.add(item);
			}
		}
		return list;
    }

}
