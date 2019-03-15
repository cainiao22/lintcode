package website.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanpf
 * @date 2019��1��11�� ����11:21:30
 * @description �������� ϡ����� A �� B������AB�Ľ����
				�����Լ���A����������B��������
 * @example
 * 				A = [
				   [ 1, 0, 0],
				   [-1, 0, 3]
				]
				
				B = [
				   [ 7, 0, 0 ],
				   [ 0, 0, 0 ],
				   [ 0, 0, 1 ]
				]
				
				
				     |  1 0 0 |   | 7 0 0 0 |   |  7 0 0 0 |
				AB = | -1 0 3 | x | 0 0 0 0 | = | -7 0 3 0 |
				                  | 0 0 1 0 |
 *
 * @Solution
 */
public class ϡ�����˷� extends HH {
	
    public int[][] multiply(int[][] A, int[][] B) {
        // write your code here
    	int[][] result = new int[A.length][B[0].length];
    	for(int i=0; i<A.length; i++) {
    		for(int j=0; j<A[i].length; j++) {
    			//�Ż���
    			if(A[i][j] == 0) {
    				continue;
    			}
    			for(int k=0; k<B.length; k++) {
    				result[i][k] += A[i][j] * B[j][k];
    			}
    		}
    	}
    	
    	return result;
    }
    
    public int[][] multiplyBest(int[][] A, int[][] B) {
    	List<List<Integer>> aList = new ArrayList<>();
    	List<List<Integer>> bList = new ArrayList<>();
    	
    	int[][] result = new int[A.length][B[0].length];
    	
    	for(int i=0; i<A.length; i++) {
    		aList.add(new ArrayList<>());
    		for(int j=0; j<A[0].length; j++) {
    			if(A[i][j] != 0) {
    				aList.get(i).add(j);
    			}
    		}
    	}
    	
    	for(int j=0; j<B[0].length; j++) {
    		bList.add(new ArrayList<>());
    		for(int i=0; i<B.length; i++) {
    			if(B[i][j] != 0) {
    				bList.get(j).add(i);
    			}
    		}
    	}
    	
    	for(int i=0; i<aList.size(); i++) {
    		for(int j : aList.get(i)) {
    			for(int j1=0; j1<bList.size(); j1++) {
    				for(int k : bList.get(j1)) {
    					if(j == k) {
    						result[i][j1] += A[i][j] * B[k][j1];
    					}
    				}
    			}
    		}
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
    	int[][] A = new int[][] {{1, 0, 0}, {-1, 0, 3}};
    	int[][] B = new int[][] {{7, 0, 0}, {0, 20, 0}, {11, 0, 1}};
		new ϡ�����˷�().multiplyBest(A, B);
	}

}
