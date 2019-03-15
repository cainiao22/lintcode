package website.lintcode;
/**
 * 
 * @author yanpf
 * @date 2019年3月11日 下午1:48:59
 * @description	
 * 		给定一个正整数，找出其用二进制表示拥有同样1的数量的比它小的最大正数和比它大的最小正数。
		如果找不到则输出-1，这里的正整数都是指32位带符号正整数。
 * @example
 * 		输入: n=5
		输出: Smaller: 3 ,Larger : 6
		输入: n=6
		输出: Smaller: 3 ,Larger : 9
 *
 * @Solution
 */
public class 拥有同样多的1的下一个数 {
	
	 public int getPrev(int n) {
		 int temp = n;
		System.out.println(Integer.toBinaryString(n));
		 int min = n;
		 while((min & n) != 0 || (min ==0 && temp > 0)) {
			 min = ((temp - 1)^temp) + 1 >>> 1;
			 temp = temp - min;
			 min = min >>> 1;
		 }
		 System.out.println(Integer.toBinaryString(min));
		 if(temp <= 0 && min == 0) {
			 return -1;
		 }
		 n = n - min;
		
		 int sub = n & (min - 1);
		 
		
		 //保留n的高位
		 n = (((1 << 31) - 1) - min + 1) & n;
		 System.out.println(Integer.toBinaryString(n));
		 int count = 0;
		 temp = sub;
		 while(sub != 0) {
			 sub = (sub - 1) & sub;
			 count ++;
		 }
		 int bitCount = 0;
		 temp = min >> 1;
		 while(temp != 0) {
			 temp = temp >> 1;
		 	 bitCount ++;
		 }
		 for(int i=1; i<=count; i++) {
			 sub += (1 << (bitCount - i));
		 }
		 System.out.println(Integer.toBinaryString(sub));
		 System.out.println(Integer.toBinaryString(n + sub));
		 return n + sub;
	 }
	
	 public int getNext(int n) {
		 int min0 = ((n - 1)^n) + 1;
		 int temp = min0;
		 System.out.println(Integer.toBinaryString(n));
		 while((min0 & n) == min0 && min0 < n) {
			 min0 = min0 << 1;
		 }
		 System.out.println(Integer.toBinaryString(min0));
		 if(min0 < 0) {
			 return -1;
		 }
		 if(min0 < n) {
			 n = n + (min0 >> 1);
			 min0 = (min0 >> 1) - 1;
			 int bitmap = (1 << 31) - min0 - 1;
			 temp= n & bitmap;
			 n = n & min0;
			 int sub = 0;
			 while(n != 0) {
				 sub = (sub << 1) + 1;
				 n = (n -1) & n;
			 }
			 System.out.println(Integer.toBinaryString(temp + sub));
			 return temp + sub;
		 }
		 
		 temp = n - (min0 >> 1);
		 while(temp > 0 && (temp & 1) == 0) {
			 temp = temp >> 1;
		 }
		 return min0 + temp;
	 }
	 
	 
	    // @param n a positive integer
	    // @return a positive integer or -1
	    public int getPrev2(int n) {
	        // Write your code here
	        int temp = n;
	        int c0 = 0;
	        int c1 = 0;
	        while ((temp & 1) == 1) {
	            c1++;
	            temp >>= 1;
	        }
	        if (temp == 0) return -1;

	        while (((temp & 1) == 0) && (temp != 0)) {
	            c0++;
	            temp >>= 1;
	        }
	        //首先n - (1 << c1) 其实是把后一位为0的那位1抹去并将其与的多位全部变成了1，剩下的工作就是去除c0个1
	        //(1 << (c0 - 1)) - 1 意味着c0个1       66666666666666666
	        return n - (1 << c1) - (1 << (c0 - 1)) + 1;
	    }

	    // @param n a positive integer
	    // @return a positive integer or -1
	    public int getNext2(int n) {
	        // Write your code here
	        int temp = n;
	        int c0 = 0;
	        int c1 = 0;
	        
	        while (((temp & 1) == 0) && (temp != 0)) {
	            c0++;
	            temp >>= 1;
	        }

	        while ((temp & 1) == 1) {
	            c1++;
	            temp >>= 1;
	        }
	        //这个思路和上面类似  n + (1 << c0)是将后一位为0的那个1111..1..000..0全部置0，剩下的就是在加上c1个1了
	        //(1 << (c1 - 1)) - 1 代表c1个1
	        int result = n + (1 << c0) + (1 << (c1 - 1)) - 1;
	        if (result < 0)
	            return -1;
	        return n + (1 << c0) + (1 << (c1 - 1)) - 1;
	    }
	 
	 public static void main(String[] args) {
		 
		//System.out.println(Integer.toBinaryString((1 << 31) - 1));
		int next = new 拥有同样多的1的下一个数().getPrev(1073741824);
		System.out.println(next);
	}

}
