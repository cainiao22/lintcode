package website.lintcode;

import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2019��1��23�� ����4:39:34
 * 
 * @description һ����԰��N��λ�á�ÿ��λ������һ�仨����N�仨����N������һʢ����ÿ�춼һ�����в���ֻ��һ�仨ʢ������������
 * 				��仨��һֱ����ʢ����״̬��
 * 
 *              ����һ��������1��N��ɵ�����flowers�������е�ÿ�����ֱ�ʾ��һ�콫��ʢ���Ļ���λ�á�
 * 
 *              ���磬flowers[i] = x��ʾ��λ��x�ϵĻ����ڵ�i��ʢ��������i��x����1��N�ķ�Χ�ڡ�
 * 
 *              ���У�����һ������k������Ҫ���أ�����һ�죬ǡ�������仨����ʢ����״̬���������仨֮��ǡ����k�仨û��ʢ����
 * 
 *              �������������һ�죬��ô����-1��
 * 
 * 
 * @example ����flowers = [1,3,2]��k = 1������ 2.
 * 
 *          ���ͣ� �ڵڶ��죬��һ��͵����仨�Ὺ�� ����flowers = [1,2,3]��k = 1������ -1.
 *
 * @Solution
 */
public class K���յ�λ�� {
	
	/**
	 * ������ʽ
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
	  * ����ԭ��
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
	  * ����ջ��ʽ  ̫̫̫ţ����
	  * @param flowers
	  * @param k
	  * @return
	  */
	 public static int kEmptySlotsDanDiaoZhan(int[] flowers, int k) {
		 //real[i]=x �����i��λ������Ļ����x�쿪
		 int[] real = new int[flowers.length];
			for(int i=0; i<flowers.length; i++) {
				real[flowers[i] - 1] = i + 1; 
			}
		 
		 int day = 0;
		 int ans = real.length + 1;
		 //��ŵ�λ��	
		 Stack<Integer> stack = new Stack<>();
		 for(int i=0; i<real.length; i++) {
			 //λ��i����Ļ���stack�еĻ������磬����stack�еĻ���λ����i��ǰ�棬����λ��i����Ļ����ŵ�ʱ��
			 //�м��k��λ��һ������ӹ�iȥ,��һ���ǹؼ�
			 while(!stack.isEmpty() && real[i] < real[stack.peek()]) {
				 int top = stack.pop();
				 if(i - top == k + 1) {
					 day = real[top];
					 ans = Integer.min(day, ans);
				 }
			 }
			 //�ߵ����� ����������һ��λ�ÿ���ʱ���i�硣��������֮���λ�ö�û�п��������Կ����ڱȽ�һ��
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
