package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��8��29�� ����12:06:28
 * @description 
			����һ�����������Σ��ҵ��Ӷ������ײ�����С·���͡�ÿһ�������ƶ�������һ�е����������ϡ�
			ע������
			
			�����ֻ�ö���ռ临�Ӷ�O(n)����������ɿ��Ի�üӷ֣�����n�����������ε���������

 * @example 
 * 			���磬�����������������Σ�
			[
			     [2],
			    [3,4],
			   [6,5,7],
			  [4,1,8,3]
			]
			
			�Ӷ����ײ�����С·����Ϊ11 ( 2 + 3 + 5 + 1 = 11)��
 *
 * @Solution 1���Զ����£�triangle[i][j] = Math.min(triangle[i-1][j-j], triangle[i-1][j]) + triangle[i][j];
 * 			 	��Ϊÿ��ֻ����������鳤�ȱ���������鳤�ȼ���1�����Բ����Գ���2�������������
 * 				(��������Զ���������ס�ˣ���һ��Ӧ��ѡ�����������Ե����ϵķ�ʽҪ��������
 * 
 * 			 2���Ե����ϣ����ַ�ʽ����ȵ�һ��Ҫ���Ķ࣬����û��j=0��j=length-1��������������
 * 				����û��Ҫ�������һ�αȽϣ�ֱ����a[0][0]�Ϳ��ԡ�(�е�����˼ά�ĸо�)
 * 			 
 * 			 3���ݹ飬˼��ͷ���2��ͬ
 * 			
 */
public class ���������� extends HH {

	public int minimumTotal(int[][] triangle) {
        // write your code here
		for(int i=1; i<triangle.length; i++) {
			for(int j=0; j<triangle[i].length; j ++) {
				if(j == 0) {
					triangle[i][j] = triangle[i-1][0] + triangle[i][j];
				}else if (j == triangle[i].length - 1) {
					triangle[i][j] = triangle[i-1][triangle[i-1].length - 1] + triangle[i][j];
				} else {
					triangle[i][j] = Math.min(triangle[i-1][j-1], triangle[i-1][j]) + triangle[i][j];
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i=0; i<triangle[triangle.length - 1].length; i++) {
			min = Math.min(min, triangle[triangle.length - 1][i]);
		}
		
		return min;
    }
	
	public int minimumTotalbottomToUp(int[][] triangle) {
		if(triangle.length == 1) {
			return triangle[0][0];
		}
		int length = triangle.length;
		for(int i=length - 2; i>= 0; i -- ) {
			for(int j=0; j<triangle[i].length; j++) {
				triangle[i][j] = Math.min(triangle[i+1][j], triangle[i+1][j+1]) + triangle[i][j];
			}
		}
		
		return triangle[0][0];
	}
	
	public int minimumTotalbottomToUpDFS(int[][] triangle) {
		if(triangle.length == 0) {
			return -1;
		}
		this.triangle = triangle;
		this.length = triangle.length;
		f = new int[length][length];
		for(int i=0; i<length; i++) {
			for(int j=0; j<length; j++) {
				f[i][j] = Integer.MAX_VALUE;
			}
		}
		
		return search(0, 0);
	}
	int[][] f, triangle;
	int length;
	public int search(int x, int y) {
		if(x >= length) return 0;
		if(f[x][y] != Integer.MAX_VALUE) {
			return f[x][y];
		}
		f[x][y] = Math.min(search(x + 1, y), search(x + 1, y + 1)) + triangle[x][y];
		return f[x][y];
	}
	
	public static void main(String[] args) {
		int[][] triangle = new int[][] {
					{-7},
				   {-2,1},
			      {-5,-5,9},
			     {-4,-5,4,4},
			   {-6,-6,2,-1,-5},
			{3,7,8,-3,7,-9},
			{-9,-1,-9,6,9,0,7},
			{-7,0,-6,-8,7,1,-4,9},
			{-3,2,-6,-9,-7,-6,-9,4,0},
			{-8,-6,-3,-9,-2,-6,7,-5,0,7},
			{-9,-1,-2,4,-2,4,4,-1,2,-5,5},
			{1,1,-6,1,-2,-4,4,-2,6,-6,0,6},
			{-3,-3,-6,-2,-6,-2,7,-9,-5,-7,-5,5,1}};
		int num = new ����������().minimumTotalbottomToUpDFS(triangle);
		System.out.println(num);
		print(triangle);
	}
}
