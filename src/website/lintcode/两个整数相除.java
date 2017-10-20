package website.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yanpf
 * @date 2017年10月17日 下午6:46:44
 * @description 
 * 
		将两个整数相除，要求不使用乘法、除法和 mod 运算符。
		如果溢出，返回 2147483647 。

 * @example
 * 		给定被除数 = 100 ，除数 = 9，返回 11
 *
 * @Solution
 */
public class 两个整数相除 extends HH {
	
	//超时
	public int divide(int dividend, int divisor) {
        // write your code here
		if(dividend == -2147483648 || divisor == -2147483648) {
			return 2147483647;
		}
		int index = 0;
		//负数？
		boolean flagA = dividend < 0;
		boolean flagB = divisor < 0;
		if(flagA) {
			dividend = 0 - dividend;
		}
		if(flagB) {
			divisor = 0 - divisor;
		}
		
		while(dividend >= divisor) {
			int temp = divisor;
			int tmpIndex = 1;
			//这里可以改成位移
			while(temp + temp < dividend) {
				temp += temp;
				tmpIndex += tmpIndex;
			}
			index += tmpIndex;
			dividend -= temp;
		}
		//另一个比较好的方式 位移，和上面的效果类似
		/*while(a >= b){
            int shift = 0;
            while(a >= (b << shift)){
                shift++;
            }
            a -= b << (shift - 1);
            result += 1 << (shift - 1);
        }*/
		if(flagA == flagB) {
			return index;
		}else {
			return 0 - index;
		}
    }
	
	public int divideBetter(int dividend, int divisor) {
        // write your code here
		if(divisor == 0) {
			return Integer.MAX_VALUE;
		}
		//-2147483648除以-1，越界
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
		if(divisor == 1) {
			return dividend;
		}
		if(divisor == -1) {
			return 0 - dividend;
		}
		int index = 0;
		//负数？
		boolean flagA = dividend < 0;
		boolean flagB = divisor < 0;
		if(flagA) {
			dividend = 0 - dividend;
		}
		if(flagB) {
			divisor = 0 - divisor;
		}
		//将中间结果左一层缓存
		Map<Integer, Integer> cache = new HashMap<>();
		cache.put(1, divisor);
		if(dividend >= divisor) {
			int temp = divisor;
			int shift = 1;
			while(temp < dividend) {
				temp = divisor << shift;
				shift += 1;
				cache.put(shift, temp);
			}
			index += shift - 1;
			dividend -= (temp >> 1);
		}
		for(int i=index - 1; i>0; i--) {
			if(dividend - cache.get(i) >= 0) {
				index += i;
				dividend -= cache.get(i);
				if(dividend < divisor) {
					break;
				}
			}
		}
		if(flagA == flagB) {
			return index;
		}else {
			return 0 - index;
		}
    }
	
	public static void main(String[] args) {
		print(new 两个整数相除().divideBetter(-10, -3));
		int a = (int)2147483648L;
		System.out.println(a);
	}

}
