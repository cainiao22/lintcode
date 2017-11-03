package website.lintcode;

import java.util.ArrayList;

import java.util.List;


/**
 * 
 * @author yanpf
 * @date 2017年11月3日 下午4:09:56
 * @description
 * 		给一个词典，找出其中所有最长的单词。
 * @example
 * 
 * 		在词典
		{
		  "dog",
		  "google",
		  "facebook",
		  "internationalization",
		  "blabla"
		}
		
		中, 最长的单词集合为 ["internationalization"]
		
		在词典
		
		{
		  "like",
		  "love",
		  "hate",
		  "yes"
		}
		
		中，最长的单词集合为 ["like", "love", "hate"]
 *
 * @Solution
 */
public class 最长单词 {
	
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
