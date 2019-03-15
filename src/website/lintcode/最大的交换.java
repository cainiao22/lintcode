package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��3��8�� ����11:17:02
 * @description
 * 		����һ���Ǹ�����������Խ���������λ����һ����������ĺϷ��������������ĺϷ������ܹ���õ�����
 * @example
 *		����1:

		����: 2736
		���: 7236
		����: ��������2������7.
		����2:
		
		����: 9973
		���: 9973
		����: ���ý���.
 * @Solution
 */
public class ���Ľ��� {
	
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
	
	//TODO ��ʱ��һ�ߣ�����vip��
	public int maximumSwap2(int num) {
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(new ���Ľ���().maximumSwap(9973));
	}

}
