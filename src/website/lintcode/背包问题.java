package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��10��13�� ����10:38:26
 * @description
 * 		��n����Ʒ����ѡ������Ʒװ�뱳���������װ���������豳���Ĵ�СΪm��ÿ����Ʒ�Ĵ�СΪA[i]
 * @example
 * 		�����4����Ʒ[2, 3, 5, 7]

		��������Ĵ�СΪ11������ѡ��[2, 3, 5]װ�뱳����������װ��10�Ŀռ䡣
		
		��������Ĵ�СΪ12������ѡ��[2, 3, 7]װ�뱳����������װ��12�Ŀռ䡣
		
		������Ҫ���������װ���Ŀռ��С��
 *
 * @Solution
 */
public class �������� extends HH{
	
	public int backPack(int m, int[] A) {
		//ǰi����Ʒ��������Ϊj�ı����е�����ֵ
       int[][] value = new int[A.length + 1][m + 1];
       //��������Ϊ0
       for(int j=0;j<m+1; j++) {
    	   value[0][j] = 0;
       }
       //һ����Ʒ��û��
       for(int i=0; i<A.length+1; i++) {
    	   value[i][0] = 0;
       }
       
       for(int i=1; i<A.length + 1; i++) {
    	   for(int j=1; j<m+1; j++) {
    		   if(j < A[i-1]) {
    			   value[i][j] = value[i-1][j];
    		   }else {
    			   //�õ�i����Ʒ���ܼ۾���value[i-1][j-A[i-1]] + A[i-1]�� ���þ���value[i-1][j]
    			   value[i][j] = Math.max(value[i-1][j], value[i-1][j-A[i-1]] + A[i-1]);
    		   }
    	   }
       }
       
       return value[A.length][m];
    }
	
	public static void main(String[] args) {
		int m = 12;
		int[] A = new int[] {2, 3, 5, 7};
		print(new ��������().backPack(m, A));
	}

}
