package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017��12��5�� ����4:35:17
 * @description
 * 		����һ����n�����󣨰���k�ֲ�ͬ����ɫ��������1��k���б�ţ������飬��������з���ʹ��ͬ��ɫ�Ķ������ڣ�������1,2��...k��˳���������
 * 		һ���൱ֱ�ӵĽ��������ʹ�ü�������ɨ��2����㷨��������Ứ��O(k)�Ķ���ռ䡣������ڲ�ʹ�ö���ռ���������ɣ�
 * @example
 * 		����colors=[3, 2, 2, 1, 4]��k=4, ��Ĵ���Ӧ����ԭ�ز���ʹ��������[1, 2, 2, 3, 4]
 *
 * @Solution
 */
public class ����ɫII extends HH {
	
	/**
	 * ��������
	 * @param colors
	 * @param k
	 */
	public void sortColors2(int[] colors, int k) {
        // write your code here
		int[] count = new int[k+1];
		for(int i=0; i<colors.length; i++) {
			count[colors[i]] ++;
		}
		
		int m = 0;
		for(int i=1; i<=k; i++) {
			for(int j=count[i]; j>0; j--) {
				colors[m ++] = i;
			}
		}
    }

	/**
	 * ��ʹ�ö���ռ�
	 * @param colors
	 * @param k
	 */
	public static void sortColors2X(int[] colors, int k) {
		int i = 0;
		for(int cur=1;cur<=k; cur++) {
			while(i < colors.length && colors[i] == cur) {
				i ++;
			}
			int j = i+1;
			while(j < colors.length) {
				if(colors[j] == cur) {
					colors[j] = colors[i];
					colors[i] = cur;
					i++;
				}
				j++;
			}
		}
	}
	
	/**
	 * ����˼��
	 * @param colors
	 * @param k
	 */
	 public void sortColors2QuickSort(int[] colors, int k) {
	        if (colors == null || colors.length == 0) {
	            return;
	        }
	        rainbowSort(colors, 0, colors.length - 1, 1, k);
	    }
	    
	    public void rainbowSort(int[] colors,
	                            int left,
	                            int right,
	                            int colorFrom,
	                            int colorTo) {
	        if (colorFrom == colorTo) {
	            return;
	        }
	        
	        if (left >= right) {
	            return;
	        }
	        
	        int colorMid = (colorFrom + colorTo) / 2;
	        int l = left, r = right;
	        while (l <= r) {
	            while (l <= r && colors[l] <= colorMid) {
	                l++;
	            }
	            while (l <= r && colors[r] > colorMid) {
	                r--;
	            }
	            if (l <= r) {
	                int temp = colors[l];
	                colors[l] = colors[r];
	                colors[r] = temp;
	                
	                l++;
	                r--;
	            }
	        }
	        
	        rainbowSort(colors, left, r, colorFrom, colorMid);
	        rainbowSort(colors, l, right, colorMid + 1, colorTo);
	    }


	    /**
	     * �������������ĵڶ����������ƣ�ֻ������һ�δ���������������С
	     * @param colors: A list of integer
	     * @param k: An integer
	     * @return: nothing
	     */
	    public void sortColors2FromJiuZhang(int[] colors, int k) {
	        int count = 0;
	        int left = 0;
	        int right = colors.length - 1;
	        while (count < k) {
	            int min = Integer.MAX_VALUE;
	            int max = Integer.MIN_VALUE;
	            
	            for (int i = left; i <= right; i++) {
	                min = Math.min(min, colors[i]);
	                max = Math.max(max, colors[i]);
	            }
	            int cur = left;
	            while(cur <= right) {
	                if (colors[cur] == min) {
	                    swap(left, cur, colors);
	                    cur++;
	                    left++;
	                } else if (colors[cur] > min && colors[cur] < max) {
	                    cur++;
	                } else {
	                    swap(cur, right, colors);
	                    right--;
	                }
	            }
	            count += 2;

	        }
	    }
	    
	    void swap(int left, int right, int[] colors) {
	        int tmp = colors[left];
	        colors[left] = colors[right];
	        colors[right] = tmp;
	    }
	
	public static void main(String[] args) {
		int[] colors = new int[] {2,1,1,2,2};
		sortColors2X(colors, 2);
		print(colors);
	}
}
