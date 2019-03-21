package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019��3��16�� ����10:52:40
 * @description
 * 		һ����ά���飬ÿһ�ж�ֻ��0��1��ǰ�沿����0����һ������1���ҵ���������������������ߵ�1���ڵ�������
 * @example
 * 		���� arr = [[0,0,0,1],[1,1,1,1]], ���� 0��

		���ͣ�
		arr[1][0]Ϊ������������ߵ�1�������ڵ���Ϊ0��
		���� arr = [[0,0,0,1],[0,1,1,1]], ���� 1��
		
		���ͣ�
		arr[1][1]Ϊ������������ߵ�1�������ڵ���Ϊ1��
 *
 * @Solution
 */
public class �����1 {
	
	public int getColumn(int[][] arr) {
        // Write your code here
		int result = 0;
		for(int i=0; i<arr[0].length; i++) {
			if(arr[0][i] == 1) {
				result = i;
				break;
			}
		}
		if(result == 0) {
			return 0;
		}
		outter:
		for(int i=1; i<arr.length; i++) {
			if (arr[i][result - 1] == 0) {
                continue;
            }
			for(int j=0; j<result; j++) {
				if(arr[i][j] == 1) {
					result = j;
					if(result == 0) {
						return result;
					}
					continue outter;
				}
			}
		}
		
		return result;
    }
	
	/**
	 * ���Ľⷨ 66666666
	 * @param arr
	 * @return
	 */
	public int getColumn2(int[][] arr) {
        // Write your code here
        int res = arr[0].length - 1;
        
        for (int i = 0; i < arr.length; i++) {
            while (res - 1 >= 0 && arr[i][res - 1] == 1) {
                res--;
            }
        }
        return res;
    }

}
