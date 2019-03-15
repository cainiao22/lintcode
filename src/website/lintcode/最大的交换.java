package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年3月8日 上午11:17:02
 * @description
 * 		给定一个非负整数，你可以交换两个数位至多一次来获得最大的合法的数。返回最大的合法的你能够获得的数。
 * @example
 *		样例1:

		输入: 2736
		输出: 7236
		解释: 交换数字2和数字7.
		样例2:
		
		输入: 9973
		输出: 9973
		解释: 不用交换.
 * @Solution
 */
public class 最大的交换 {
	
	public int maximumSwap(int num) {
        int[] arr = new int[10];
        int index = 0;
        int pre = num;
        boolean replaced = false;
        while(num != 0) {
        	arr[index ++] = num % 10;
        	num /= 10;
        }
        for(int i=index - 1; i> 0; i--) {
        	int max = 0;
        	int indexJ = 0;
        	for(int j=0; j<i; j++) {
        		if(arr[j] > max) {
        			max = arr[j];
        			indexJ = j;
        		}
        	}
        	int temp = arr[indexJ];
        	if(temp > arr[i]) {
	        	arr[indexJ] = arr[i];
	        	arr[i] = temp;
	        	replaced = true;
	        	break;
        	}
        }
        
        if(!replaced) {
        	return pre;
        }
        for(int i=index - 1; i>=0; i--) {
        	num = num * 10 + arr[i];
        }
        return num;
    }
	
	//TODO 暂时放一边，先做vip的
	public int maximumSwap2(int num) {
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(new 最大的交换().maximumSwap(9973));
	}

}
