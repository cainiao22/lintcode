package website.lintcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2017年11月3日 下午5:06:58
 * @description
 * 		一个房间中有 n 盏灯最初是开着的，并且墙上有 4 个开关。在对开关进行 m 次未知的操作后，你需要返回这 n 盏灯有多少种不同的状态。
		假设 n 盏灯的标号为 [1, 2, 3 ..., n]， 4 个开关的功能如下:
		1.将所有灯从开变成关，从关变成开。
		2.将标号为偶数的灯从开变成关，从关变成开。
		3.将标号为奇数的灯从开变成关，从关变成开。
		4.将标号为 (3k + 1) 的灯从开变成关，从关变成开，k = 0, 1, 2...
		
 * @example
 * 		给出 n = 1， m = 1。
		返回 2 // 状态可以是: [on], [off]
		
		给出 n = 2， m = 1。
		返回3 // 状态可以是: [on, off], [off, on], [off, off]
 *
 * @Solution
 */
public class 电灯切换 extends HH {
	private int width;
	private int n;
	public int flipLightsMemError(int n, int m) {
        // write your code here
		this.n = n;
		this.width = 1 << 16;
		int height = 1;
		if(n >= width) {
			height = 1 + n/width;
		}
		boolean[][] lights = new boolean[height][width];//默认为false，也就是开着
		helper(m, lights);
		print(status);
		return status.size();
    }
	
	private Set<String> status =  new HashSet<>();
	private void helper(int m, boolean[][] lights) {
		if(m == 0) {
			StringBuffer sb = new StringBuffer();
			for(int i=1; i<=n; i++) {
				if(lights[i/width][i%width]) {
					sb.append('T');
				}else{
					sb.append('F');
				}
			}
			status.add(sb.toString());
			return;
		}
		for(int i=1; i<=4; i++) {
			turn(i, lights);
			helper(m-1, lights);
			turn(i, lights);
		}
	}
	
	private void turn(int step, boolean[][] lights) {
		switch (step) {
		case 1:
			for(int i=1; i<=n; i++) {
				lights[i/width][i%width] = !lights[i/width][i%width];
			}
			break;
		case 2:
			for(int i=2; i<=n; i+=2) {
				lights[i/width][i%width] = !lights[i/width][i%width];
			}
			break;
		case 3:
			for(int i=1; i<=n; i+=2) {
				lights[i/width][i%width] = !lights[i/width][i%width];
			}
			break;
		case 4:
			for(int i=1; i<=n; i+=3) {
				lights[i/width][i%width] = !lights[i/width][i%width];
			}
		default:
			break;
		}
	}
	
	
	public int flipLights(int n, int m) {
		int length = n/32 + 1;
		this.n = n;
		int[] lights = new int[length];
		helper(m, lights);
		print(status);
		return status.size();
	}
	
	private void helper(int m, int[] lights) {
		if(m == 0) {
			StringBuffer sb = new StringBuffer();
			for(int i=1; i<=n; i++) {
				int num = lights[i/32];
				int index = i % 32;
				if((num & 1 << index) == 0) {
					sb.append('T');
				}else {
					sb.append('F');
				}
			}
			status.add(sb.toString());
			return;
		}
		for(int i=1; i<=4; i++) {
			turn2(i, lights);
			helper(m-1, lights);
			turn2(i, lights);
		}
	}
	
	private void turn2(int step, int[] lights) {
		switch (step) {
		case 1:
			for(int i=1; i<=n; i++) {
				int num = lights[i/32];
				int index = i % 32;
				if((num & 1 << index) == 0) {
					num += 1 << index;
				}else {
					num -= 1 << index;
				}
				lights[i/32] = num;
			}
			break;
		case 2:
			for(int i=2; i<=n; i+=2) {
				int num = lights[i/32];
				int index = i % 32;
				if((num & 1 << index) == 0) {
					num += 1 << index;
				}else {
					num -= 1 << index;
				}
				lights[i/32] = num;
			}
			break;
		case 3:
			for(int i=1; i<=n; i+=2) {
				int num = lights[i/32];
				int index = i % 32;
				if((num & 1 << index) == 0) {
					num += 1 << index;
				}else {
					num -= 1 << index;
				}
				lights[i/32] = num;
			}
			break;
		case 4:
			for(int i=1; i<=n; i+=3) {
				int num = lights[i/32];
				int index = i % 32;
				if((num & 1 << index) == 0) {
					num += 1 << index;
				}else {
					num -= 1 << index;
				}
				lights[i/32] = num;
			}
		default:
			break;
		}
	}
	
	public static void main(String[] args) {
		print(new 电灯切换().flipLights(2147483647, 30));
	}

}
