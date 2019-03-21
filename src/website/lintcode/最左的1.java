package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2019年3月16日 上午10:52:40
 * @description
 * 		一个二维数组，每一行都只有0和1，前面部分是0，后一部分是1，找到数组里面所有行中最左边的1所在的列数。
 * @example
 * 		给出 arr = [[0,0,0,1],[1,1,1,1]], 返回 0。

		解释：
		arr[1][0]为所有行中最左边的1，其所在的列为0。
		给出 arr = [[0,0,0,1],[0,1,1,1]], 返回 1。
		
		解释：
		arr[1][1]为所有行中最左边的1，其所在的列为1。
 *
 * @Solution
 */
public class 最左的1 {
	
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
	 * 最精简的解法 66666666
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
