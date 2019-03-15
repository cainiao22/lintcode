package website.lintcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017��12��14�� ����3:01:36
 * @description
 * 		
		һֻ������Ҫ���ӣ������ӷֳ��� x ����λ��ÿ����λ���ܴ���ʯͷ�����ܿ�������ʯͷ�ϣ�������������ˮ�
		����˳�����ʯͷ���ڵ�λ�ã��ж������ܷ񵽴����һ��ʯͷ���ڵ�λ�á��տ�ʼʱ�����ڵ�һ��ʯͷ�ϣ��������ܵ�һ����ֻ����һ����λ�ĳ��ȡ�
		����������һ���� k ����λ����ô����һ��ֻ���� k - 1 ��k ���� k + 1 ����λ��ע������ֻ����ǰ����
		ע������
		
		ʯͷ�ĸ��� >= 2���� <= 1100��
		ÿ��ʯͷ��λ����һ���Ǹ������� < 2^31��
		��һ��ʯͷ��λ������ 0.

 * @example
 * 		����ʯͷ��λ��Ϊ [0,1,3,5,6,8,12,17]

		�ܹ�8��ʯͷ��
		��һ��ʯͷ�� 0 λ�ã��ڶ���ʯͷ�� 1 λ�ã�������ʯͷ�� 3 λ�õȵ�......
		���һ��ʯͷ�� 17 λ�á�
		
		���� true�����ܿ���ͨ���� 1 �񵽵ڶ���ʯͷ���� 2 �񵽵�����ʯͷ���� 2 �񵽵��Ŀ�ʯͷ���� 3 �񵽵�����ʯͷ���� 4 �񵽵��߿�ʯͷ������� 5 �񵽵ڰ˿�ʯͷ��
		
		����ʯͷ��λ��Ϊ `[0,1,2,3,4,8,9,11]`
		���� false������û�а취�������һ��ʯͷ��Ϊ�����ʯͷ��������ʯͷ�ľ���̫���ˡ�
 *
 * @Solution 1��dfs
 */
public class ������ extends HH {
	
	/**
	 * �ݹ鷽ʽ
	 * @param stones
	 * @return
	 */
	public boolean canCross(int[] stones) {
        // write your code here
		if(stones.length == 1) {
			return stones[0] == 0;
		}
		if(stones[1] != 1) {
			return false;
		}
		return cross(stones, 1, 1);
    }
	
	//Ҳ������boolean��������ʾ��memary[stones.length][stones.length] ��������stoneIndex����������step
	private Map<String, Boolean> cache = new HashMap<>();
	
	boolean cross(int[] stones, int stoneIndex, int steps) {
		if(stoneIndex == stones.length - 1) {
			return true;
		}
		int curPos = stones[stoneIndex];
		for(int step = steps + 1; step > 0 && step > steps - 2; step --) {
			if(cache.get(curPos + "_" + step) != null) {
				return false;
			}
			for(int i=stoneIndex + 1; i < stones.length; i++) {
				int nextPos = curPos + step;
				if(nextPos < stones[i]) {
					break;
				}
				if(nextPos == stones[i]) {
					boolean flag = cross(stones, i, step);
					if(flag) {
						return true;
					}
				}
			}
			cache.put(curPos + "_" + step, false);
		}
		return false;
	}
	
	/**
	 * �ö�ջʵ��
	 * @param stones
	 * @return
	 */
	
	public boolean canCross2(int[] stones) {
		Stack<Position> stack = new Stack<>();
		// write your code here
		if(stones.length == 1) {
			return stones[0] == 0;
		}
		if(stones[1] != 1) {
			return false;
		}
		stack.push(new Position(1, 1));
		boolean[][] cache2 = new boolean[stones.length][stones.length];
		while(!stack.isEmpty()) {
			Position pos = stack.pop();
			if(pos.stoneIndex == stones.length - 1) {
				return true;
			}
			if(cache2[pos.stoneIndex][pos.step]) {
				continue;
			}
			int curPos = stones[pos.stoneIndex];
			for(int step = pos.step + 1; step > 0 && step > pos.step - 2; step --) {
				for(int i=pos.stoneIndex + 1; i < stones.length; i++) {
					int nextPos = curPos + step;
					if(nextPos < stones[i]) {
						break;
					}
					if(nextPos == stones[i]) {
						stack.push(new Position(i, step));
					}
				}
			}
			//�ߵ����ﲻ֪���Ƿ��ҵ������ս����ֻ�Ǹ�����������������Ѿ��߹��ˣ����ҷ���stack��ȥ�ˣ�û��Ҫ�ٷ�һ��
			cache2[pos.stoneIndex][pos.step] = true;
			
		}
		return false;
	}
	
	/**
	 * ���Ʒ�ʽ
	 * @param stones
	 * @return
	 */
	public boolean canCross3(int[] stones) {
		Map<Integer, HashSet<Integer>> map = new HashMap<>();
		for(int i=0; i<stones.length; i++) {
			map.put(stones[i], new HashSet<>());
		}
		//��ʼʱ�����0��λ�ã����Ե�0��ʯͷ�Ǵ���0�������ģ���˵���һ��ʯͷ�϶�ֻ����һ������0-1,0,0+1������ȥ��ǰ������Ч��
		map.get(0).add(0);
		for(int i=0; i<stones.length - 1; i++) {
			for(int k : map.get(stones[i])) {
				for(int j=k+1; j > 0 && j>=k-1; j--) {
					if(map.containsKey(stones[i] + j)) {
						map.get(stones[i] + j).add(j);
					}
				}
			}
		}
		return !map.get(stones[stones.length - 1]).isEmpty();
	}

	
	static class Position {
		public int stoneIndex;
		public int step;
		
		public Position(int stoneIndex, int step) {
			super();
			this.stoneIndex = stoneIndex;
			this.step = step;
		}
	}
	
	public static void main(String[] args) {
		int[] stones = new int[] {0,1,3,5,6,8,12,17};
		print(new ������().canCross3(stones));
	}

}
