package website.lintcode;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Segment;

/**
 * 
 * @author yanpf
 * @date 2019年1月8日 下午5:24:14
 * @description 给定一个整数数组 （下标由 0 到 n-1，其中 n 表示数组的规模，数值范围由 0 到 10000），以及一个 查询列表。
 *              对于每一个查询，将会给你一个整数，请你返回该数组中小于给定整数的元素的数量。
 * @example
 * 
 * 			对于数组 [1,2,7,8,5] ，查询 [1,8,5]，返回 [0,4,2]
 * 
 *          挑战 可否用一下三种方法完成以上题目。
 * 
 *          仅用循环方法
 * 
 *          分类搜索 和 二进制搜索
 * 
 *          构建 线段树 和 搜索
 *
 * @Solution
 */
public class 统计比给定整数小的数的个数 extends HH {

	public List<Integer> countOfSmallerNumberIterator(int[] A, int[] queries) {
		int[] result = new int[queries.length];
        for(int i=0; i<A.length; i++) {
        	for(int j=0; j<queries.length; j++) {
        		if(queries[j] > A[i]) {
        			result[j] ++;
        		}
        	}
        }
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < result.length; i++) {
			a.add(result[i]);
		}
        
        return a;
    }
	
	/**
	 *  二进制搜索
	 * @param A
	 * @param queries
	 * @return
	 */
	public static List<Integer> countOfSmallerNumberBit(int[] A, int[] queries) {
		//堆排序
		for(int i = A.length / 2 - 1; i >=0; i--) {
			int index = i;
			while(index * 2 + 1 < A.length) {
				int j = index * 2 + 1;
				if(j + 1 < A.length && A[j] < A[j+1]) {
					j = j + 1;
				}
				if(A[index] < A[j]) {
					swap(A, index, j);
				}else {
					break;
				}
				index = j;
			}
		}
		
		for(int i=A.length - 1; i>=0; i--) {
			swap(A, i, 0);
			int index = 0;
			while(index * 2 + 1 < i) {
				int j = index * 2 + 1;
				if(j + 1 < i && A[j] < A[j+1]) {
					j = j + 1;
				}
				if(A[index] < A[j]) {
					swap(A, index, j);
				}else {
					break;
				}
				index = j;
			}
		}
		
		//print(A);
		List<Integer> result = new ArrayList<>();
		for(int i=0; i<queries.length; i++) {
			int query = queries[i];
			int left = 0, right = A.length - 1;
			while(left < right) {
				int mid = left + right >> 1;
				if(A[mid] < query) {
					left = mid + 1;
				}else {
					right = mid - 1;
				}
			}
			if(left >= A.length) {
				result.add(0);
				continue;
			}
			if(A[left] >= query) {
				result.add(left);
			}else {
				result.add(left + 1);
			}
		}
		
		
		return result;
    }
	
	/**
	 * 另一种查找方式
	 *
	 */
	int find(int[] array, int val) {
        int l = 0;
        int r = array.length - 1;
        int ans = -1;
        while(l <= r) {
            int mid = (l + r) >> 1;
            if(array[mid] < val) {
                l = mid + 1;
                ans = mid;
            }
            else
                r = mid - 1;
        }
        return ans + 1;
    }
	
	/**
	 * 线段树解法
	 * @param A
	 * @param queries
	 * @return
	 */
	public static List<Integer> countOfSmallerNumberSegmentTree(int[] A, int[] queries){
		SegmentTree root = buildSegmentTree(0, 10000);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int res;
        for(int i = 0; i < A.length; i++) {
            modifySegmentTree(root, A[i], 1);
        }
        for(int i = 0; i < queries.length; i++) {
            res = 0;
            if(queries[i] > 0)
                res = querySegmentTree(root, 0, queries[i] - 1);
            ans.add(res);
        }
        return ans;
	}
	
	public static SegmentTree buildSegmentTree(int start, int end) {
		if(start > end) {
			return null;
		}
		SegmentTree root = new SegmentTree(start, end);
		int mid = (start + end) >> 1;
        if(start == end) {
    		return root;	
    	}

        root.left = buildSegmentTree(start, mid);
        root.right = buildSegmentTree(mid + 1, end);
        return root;
	}
	
	public static void modifySegmentTree(SegmentTree tree, int index, int value) {
		if(tree == null) {
			return;
		}
		if(tree.start == index && tree.end == index) {
			tree.count += value;
			return;
		}
		int mid = (tree.start + tree.end) >> 1;
		if(index <=mid && index >= tree.start) {
			modifySegmentTree(tree.left, index, value);
		}else if(index > mid && index <= tree.end) {
			modifySegmentTree(tree.right, index, value);
		}
		
		tree.count = tree.left.count + tree.right.count;
	}
	
	public static int querySegmentTree(SegmentTree root, int start, int end) {
		if(root == null) {
			return 0;
		}
		if(start <= root.start && end >= root.end) {
			return root.count;
		}
		
		int leftCount = 0, rightCount = 0;
		int mid = root.start + root.end >> 1;
		
		if(start <= mid) {
			if(end < mid) {
				leftCount = querySegmentTree(root.left, start, end);
			}else {
				leftCount = querySegmentTree(root.left, start, mid);
			}
		}
		
		if(mid <= end) {
			if(start >= mid) {
				rightCount = querySegmentTree(root.right, start, end);
			}else {
				rightCount = querySegmentTree(root.right, mid, end);
			}
		}
		
		return leftCount + rightCount;
		
	}
	
	public static class SegmentTree {
		public SegmentTree left;
		public SegmentTree right;
		public int start, end;
		public int count;
		
		public SegmentTree(int start, int end) {
			this.start = start;
			this.end = end;
			this.count = 0;
		}
	}
	
	public static void main(String[] args) {
		int[] A = new int[] {1,2,3,4,5,6};
		int[] queries = new int[] {1,2,3,4};
		List<Integer> result = countOfSmallerNumberSegmentTree(A, queries);
		print(result);
	}
}
