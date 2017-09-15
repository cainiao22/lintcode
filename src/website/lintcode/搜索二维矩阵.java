package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��14�� ����10:14:19
 * @description
 * 		
		д��һ����Ч���㷨������ m �� n�����е�ֵ��
		�����������������ԣ�
		    ÿ���е�����������������ġ�
		    ÿ�еĵ�һ����������һ�е����һ��������


 * @example	�������о���

			[
			  [1, 3, 5, 7],
			  [10, 11, 16, 20],
			  [23, 30, 34, 50]
			]
			
			���� target = 3������ true
 *
 * @Solution 1���������������Ȼ�ȡtarget�������ڵ��С���������Ҫ����һ�ζ��ֲ��ҡ�����Ȼ���������ٴν��ж�����ң�
 * 			 2����������ά��������һ��һά�������á����һά����ĳ��Ⱦ���m*n,���ҵ�midʱ��mid/n,�����У�mid%n������
 */
public class ������ά���� {
	
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix.length == 0) {
			return false;
		}
        // write your code here
		int m = matrix.length;
		int n = matrix[0].length;
		int mstart = 0, nstart = 0;
		int mend = m-1, nend = n - 1;
		
		//�����ж�0Ҳ���ԣ��ж�endҲ����
		while(mstart < mend) {
			int mid = mstart + (mend - mstart)/2;
			if(matrix[mid][nend] > target) {
				mend = mid;
			}else if(matrix[mid][nend] < target){
				mstart = mid + 1;
			}else {
				return true;
			}
		}
		/*  ������ж�0�ģ���Ҫ����󶵵�
		 while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        //���׹���
        if (matrix[end][0] <= target) {
            row = end;
        } else if (matrix[start][0] <= target) {
            row = start;
        } else {
            return false;
        }
		 */
		
		while(nstart < nend) {
			int mid = nstart + (nend - nstart)/2;
			if(matrix[mstart][mid] == target) {
				return true;
			}else if(matrix[mstart][mid] < target) {
				nstart = mid + 1;
			}else {
				nend = mid - 1;
			}
		}
		
		return false;
    }
	
	public boolean searchMatrix2(int[][] matrix, int target) {
		if(matrix.length == 0) {
			return false;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int end = m*n - 1;
		int start = 0;
		while(start <= end) {
			int mid = start + (end - start)/2;
			if(matrix[mid/n][mid%n] < target) {
				end = mid - 1;
			}else if(matrix[mid/n][mid%n] == target){
				return true;
			}else {
				start = mid + 1;
			}
		}
		return false;
	}
	
	
	
	public static void main(String[] args) {
		int[][] matrix = new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		new ������ά����().searchMatrix(matrix, 7);
	}

}
