package website.lintcode;

import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2019年1月23日 下午4:39:34
 * 
 * @description 一个花园有N个位置。每个位置上有一朵花。这N朵花会在N天内逐一盛开。每天都一定会有并且只有一朵花盛开，从这天起，
 * 				这朵花将一直处于盛开的状态。
 * 
 *              给定一个由数字1到N组成的数组flowers。数组中的每个数字表示那一天将会盛开的花的位置。
 * 
 *              例如，flowers[i] = x表示在位置x上的花会在第i天盛开，其中i和x都在1到N的范围内。
 * 
 *              还有，给出一个整数k，你需要返回，在哪一天，恰好有两朵花处于盛开的状态，并且两朵花之间恰好有k朵花没有盛开。
 * 
 *              如果不存在这样一天，那么返回-1。
 * 
 * 
 * @example 给定flowers = [1,3,2]，k = 1，返回 2.
 * 
 *          解释： 在第二天，第一朵和第三朵花会开。 给定flowers = [1,2,3]，k = 1，返回 -1.
 *
 * @Solution
 */
public class K个空的位置 {
	
	/**
	 * 暴力方式
	 * @param flowers
	 * @param k
	 * @return
	 */
	public static int kEmptySlots(int[] flowers, int k) {
		int[] real = new int[flowers.length];
		for(int i=0; i<flowers.length; i++) {
			real[flowers[i] - 1] = i + 1; 
		}
		
		return kEmptySlotsInner(real, k);
	}
	
	
	 public static int kEmptySlotsInner(int[] flowers, int k) {
	        // Write your code here
		 for(int i=1; i<=flowers.length; i++) {
			 for(int j=0; j<flowers.length; j++) {
				 if(flowers[j] == i) {
					 int x = j - 1;
					 while(x >= 0 && flowers[x] > i) {
						 x --;
					 }
					 if(x != -1 && j - x - 1 == k) {
						 return i;
					 }
					 
					 x = j + 1;
					 while(x < flowers.length && flowers[x] > i) {
						 x ++;
					 }
					 if(x != flowers.length &&  x - j - 1 == k) {
						 return i;
					 }
					 
					 break;
				 }
			 }
		 }
		 
		 return -1;
	 }
	 
	 /**
	  * 抽屉原理
	  * @param flowers
	  * @param k
	  * @return
	  */
	 public static int kEmptySlotsKbucket(int[] flowers, int k) {
		 int [][] kbucket = new int[(flowers.length + k)/(k+1)][2];
		 int bucketLength = kbucket.length;
		 for(int i=0; i<bucketLength; i++) {
			 kbucket[i][0] = flowers.length + k + 1;
			 kbucket[i][1] = -1;
		 }
		 for(int i=0; i<flowers.length; i++){
			 int day = i+1;
			 int index = (flowers[i] - 1) / (k+1);
			 if(kbucket[index][0] > flowers[i]) {
				 kbucket[index][0] = flowers[i];
			 }
			 if(kbucket[index][1] < flowers[i]) {
				 kbucket[index][1] = flowers[i];
			 }
			 
			 if(index > 0) {
				 if(kbucket[index][0] - kbucket[index-1][1] == k + 1) {
					 return day;
				 }
			 }
			 if(index < bucketLength - 1) {
				 if(kbucket[index + 1][0] - kbucket[index][1] == k + 1) {
					 return day;
				 }
			 }
		 }
		 
		 return -1;
	 }
	 
	 /**
	  * 单调栈方式  太太太牛逼了
	  * @param flowers
	  * @param k
	  * @return
	  */
	 public static int kEmptySlotsDanDiaoZhan(int[] flowers, int k) {
		 //real[i]=x 代表第i个位置上面的花朵第x天开
		 int[] real = new int[flowers.length];
			for(int i=0; i<flowers.length; i++) {
				real[flowers[i] - 1] = i + 1; 
			}
		 
		 int day = 0;
		 int ans = real.length + 1;
		 //存放的位置	
		 Stack<Integer> stack = new Stack<>();
		 for(int i=0; i<real.length; i++) {
			 //位置i上面的花比stack中的花开的早，但是stack中的花的位置在i的前面，所以位置i后面的花开放的时候，
			 //中间的k个位置一定不会接过i去,这一点是关键
			 while(!stack.isEmpty() && real[i] < real[stack.peek()]) {
				 int top = stack.pop();
				 if(i - top == k + 1) {
					 day = real[top];
					 ans = Integer.min(day, ans);
				 }
			 }
			 //走到这里 代表这里有一个位置开花时间比i早。但是他俩之间的位置都没有开花。所以可以在比较一下
			 if(!stack.isEmpty() && i - stack.peek() == k + 1) {
				 day = real[i];
				 ans = Integer.min(day, ans);
			 }
			 
			 stack.push(i);
		 }
		 
		 if(ans > real.length) {
			 return -1;
		 }
		 return ans;
	 }
	 
	 
	 public static void main(String[] args) {
		//int[] flowers = new int[] {6,5,8,9,7,1,10,2,3,4};
		int[] flowers = new int[] {9,1,4,2,8,7,5,3,6,10};
		int k = 3;
		int result = kEmptySlotsDanDiaoZhan(flowers, k);
		System.out.println(result);
	}

}
