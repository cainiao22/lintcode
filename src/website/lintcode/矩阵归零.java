package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��9��20�� ����11:54:28
 * @description	
 * 			
		����һ��m��n�������һ��Ԫ����0�����������к���ȫ��Ԫ�ر��0��
		
		��Ҫ��ԭ��������ɲ�����
		������ʵ���������Ƿ�����������⣿ 
		���Ƿ�ʹ���˶���Ŀռ䣿
		һ��ֱ�ӵĽ��������ʹ�� O(MN) �Ķ���ռ䣬���Ⲣ����һ���õķ�����
		һ���򵥵ĸĽ�������ʹ�� O(M + N) �Ķ���ռ䣬������Ȼ������õĽ��������
		�������һ�������ռ�Ľ��������
 * @example
 *
 * @Solution 1.��ȫcopy��ʱ�临�Ӷ� O(MN) ����
 * 			 2.��������һά����M,N���ֱ���Ϊ0Ԫ�ص����С�����һ��matrix���ҳ�Ϊ0��Ԫ�أ�������M��N��Ӧλ����Ϊ1��
 * 			       �ٴα������飬����M��N��Ӧλ���Ƿ�Ϊ1�ж����λ���Ƿ�Ϊ0
 *           3��˼����ǰ����ͬ��ֻ������Ҫ��M��N���������滻Ϊmatrix[i][j]Ϊ0���Ǹ����У�����i��j��λ��,ֻ��������
 *           ֮����Ҫ����������Ϊ0��
 *           
 *           4.���ȱ���һ���������飬�ҳ����ֵmax������MAX=max+1;
 *             �ٴα��������ҵ�Ԫ��Ϊ0�Ľ������ڵ��к����еķ�0Ԫ������ΪMAX
 *             ����ٱ���һ�Σ�������MAXȫ����Ϊ0 
 *           5.���Ծ����㷨��˼·�͵�������ֻ࣬������γ䵱����M��N�ı����matrix�ĵ�0�к͵�0��
 */
public class ������� {
	
	public void setZeroes(int[][] matrix) {
        // write your code here
		int M = matrix.length;
		if(M == 0) {
			return;
		}
		int N = matrix[0].length;
		if(N == 0){
		    return;
		}
		
		int[] m = new int[M];
		int[] n = new int[N];
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(matrix[i][j] == 0) {
					m[i] = 1;
					n[j] = 1;
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(m[i] == 1 && n[j] == 1) {
					setZero(matrix, i, j);
				}
			}
		}
    }
	
	private void setZero(int[][] matrix, int m, int n) {
		int M = matrix.length;
		int N = matrix[0].length;
		for(int i=0; i<M; i++) {
			matrix[i][n] = 0;
		}
		for(int i=0; i<N; i++) {
			matrix[m][i] = 0;
		}
	}
	
	//todo �����ַ�ʽʵ�֣�̫�鷳����д
	public void setZeroes3(int[][] matrix) {
        // write your code here
		int M = matrix.length;
		if(M == 0) {
			return;
		}
		int N = matrix[0].length;
		if(N == 0){
		    return;
		}
		int m = -1, n = -1;
label:	for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(matrix[i][j] == 0) {
					if(m == -1 && n == -1)
					
					matrix[i][j] = 0;
					break label;
				}
			}
		}
    }
	
	public void setZeroes4(int[][] matrix) {
		int M = matrix.length;
		if(M == 0) {
			return;
		}
		int N = matrix[0].length;
		if(N == 0){
		    return;
		}
		//��һ����ȡ���ֵ�Ĳ���������ʹ�õ���Integer.MAX_VALUE�����
		//����Ӧ���Ǿ�������ֵ+1
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(matrix[i][j] == 0) {
					setMAX(matrix, i, j);
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(matrix[i][j] == Integer.MAX_VALUE) {
					matrix[i][j] = 0;
				}
			}
		}
	}
	
	private void setMAX(int[][] matrix, int m, int n) {
		int M = matrix.length;
		int N = matrix[0].length;
		for(int i=0; i<M; i++) {
			if(matrix[i][n] != 0) {
			matrix[i][n] = Integer.MAX_VALUE;
			}
		}
		for(int i=0; i<N; i++) {
			if(matrix[m][i] != 0) {
				matrix[m][i] = Integer.MAX_VALUE;
			}
		}
	}
	
	public void setZeroes5(int[][] matrix) {
		int M = matrix.length;
		if(M == 0) {
			return;
		}
		int N = matrix[0].length;
		if(N == 0){
		    return;
		}
		boolean empty_row0 = false;
        boolean empty_col0 = false;
		for(int i=0; i<M; i++) {
			if(matrix[i][0] == 0) {
				empty_col0 = true;
				break;
			}
		}
		
		for(int i=0; i<N; i++) {
			if(matrix[0][i] == 0) {
				empty_row0 = true;
				break;
			}
		}
		
		for(int i=1; i<M; i++) {
			for(int j=1; j<N; j++) {
				if(matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		
		if(empty_row0){
            for(int i = 0; i < M; i++){
                matrix[0][i] = 0;
            }           
        }
        
        if(empty_col0){
            for(int i = 0; i < N; i++){
                matrix[i][0] = 0;
            }           
        }
	}

}
