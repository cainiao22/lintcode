package website.lintcode;

import java.util.List;

/**
 * �������� ���������������
 * @author yanpf
 *
 */
public class HH {
	
	public static void print(int[] a) {
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static void print(List a) {
		for(int i=0; i<a.size(); i++) {
			System.out.print(a.get(i) + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println("����ÿ�������");
	}
	
	public static void print(int[][] nums) {
		for(int i=0; i<nums.length; i++) {
			for(int j=0; j<nums[i].length; j++) {
				System.out.print(nums[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void swap(int[] a, int i, int j) {
		if(a[i] != a[j]) {
			a[i] = a[i]^a[j];
			a[j] = a[i]^a[j];
			a[i] = a[i]^a[j];
		}
	}
	
}
