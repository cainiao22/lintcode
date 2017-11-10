package website.lintcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author yanpf
 * @date 2017��11��3�� ����5:06:58
 * @description
 * 		һ���������� n յ������ǿ��ŵģ�����ǽ���� 4 �����ء��ڶԿ��ؽ��� m ��δ֪�Ĳ���������Ҫ������ n յ���ж����ֲ�ͬ��״̬��
		���� n յ�Ƶı��Ϊ [1, 2, 3 ..., n]�� 4 �����صĹ�������:
		1.�����еƴӿ���ɹأ��ӹر�ɿ���
		2.�����Ϊż���ĵƴӿ���ɹأ��ӹر�ɿ���
		3.�����Ϊ�����ĵƴӿ���ɹأ��ӹر�ɿ���
		4.�����Ϊ (3k + 1) �ĵƴӿ���ɹأ��ӹر�ɿ���k = 0, 1, 2...
		
 * @example
 * 		���� n = 1�� m = 1��
		���� 2 // ״̬������: [on], [off]
		
		���� n = 2�� m = 1��
		����3 // ״̬������: [on, off], [off, on], [off, off]
 *
 * @Solution
 */
public class ����л� extends HH {
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
		boolean[][] lights = new boolean[height][width];//Ĭ��Ϊfalse��Ҳ���ǿ���
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
		print(new ����л�().flipLights(2147483647, 30));
	}

}
