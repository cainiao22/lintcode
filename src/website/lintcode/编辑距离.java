package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月7日 下午3:14:33
 * @description 
 * 			给出两个单词word1和word2，计算出将word1 转换为word2的最少操作次数。
			
			你总共三种操作方法：
			
			    插入一个字符
			    删除一个字符
			    替换一个字符
			    
			    
 * @example  给出 work1="mart" 和 work2="karma"  返回 3
 *
 * @Solution  
 * 		1）我们可以在k个操作内将 s[1…i] 转换为 t[1…j-1]

		2）我们可以在k个操作里面将s[1..i-1]转换为t[1..j]
		
		3）我们可以在k个步骤里面将 s[1…i-1] 转换为 t [1…j-1]
		
		针对第1种情况，我们只需要在最后将 t[j] 加上s[1..i]就完成了匹配，这样总共就需要k+1个操作。
		
		针对第2种情况，我们只需要在最后将s[i]移除，然后再做这k个操作，所以总共需要k+1个操作。
		
		针对第3种情况，我们只需要在最后将s[i]替换为 t[j]，使得满足s[1..i] == t[1..j]，这样总共也需要k+1个操作。而如果在第3种情况下，s[i]刚好等于t[j]，那我们就可以仅仅使用k个操作就完成这个过程。
		
		  最后，为了保证得到的操作次数总是最少的，我们可以从上面三种情况中选择消耗最少的一种最为将s[1..i]转换为t[1..j]所需要的最小操作次数。
 */
public class 编辑距离 {
	
	public int minDistance(String word1, String word2) {
        // write your code here
		int M = word1.length();
		int N = word2.length();
		//行代表word1，列代表word2
		int[][] matrix = new int[M+1][N+1];
		//从word1的前i个字母变成word2的前0个字母。只需要删除i次
		for(int i=0; i<=M; i++) {
			matrix[i][0] = i;
		}
		//从word1的前0个字母变成word2的前i个字母，只需要新增i次
		for(int i=0; i<=N; i++) {
			matrix[0][i] = i;
		}
		
		for(int i=1; i<=M; i++) {
			for(int j=1; j<= N; j++) {
				//对于前j-1个变成前i-1个。假如第j个和第i个相等。不用再操作。否则会加一步，将j变成i
				int temp = word1.charAt(i-1) != word2.charAt(j-1) ? 1:0;
				//前j-1个变成前i个。第j个字母要移除，前j个变成前i-1个。那么第i个要加入。所以都会加一步操作
				matrix[i][j] = Math.min(matrix[i-1][j]+1, matrix[i][j-1]+1);
				matrix[i][j] = Math.min(matrix[i][j], matrix[i-1][j-1] + temp);
				
			}
		}
		
		return matrix[M][N];
    }
	
	public static void main(String[] args) {
		int a = new 编辑距离().minDistance("a", "a");
		System.out.println(a);
	}

}
