package website.lintcode;

import java.util.List;

/**
 * 神马玩意 当个基础工具类吧
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
		System.out.println("这个好看哈哈哈");
	}
	
}
