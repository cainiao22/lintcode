package website.lintcode;

/**
 * 
 * @author yanpf
 * @description 

	在数据结构中，哈希函数是用来将一个字符串（或任何其他类型）转化为小于哈希表大小且大于等于零的整数。一个好的哈希函数可以尽可能少地产生冲突。一种广泛使用的哈希函数算法是使用数值33，假设任何字符串都是基于33的一个大整数，比如：
	
	hashcode("abcd") = (ascii(a) * 333 + ascii(b) * 332 + ascii(c) *33 + ascii(d)) % HASH_SIZE 
	
	                              = (97* 333 + 98 * 332 + 99 * 33 +100) % HASH_SIZE
	
	                              = 3595978 % HASH_SIZE
	
	其中HASH_SIZE表示哈希表的大小(可以假设一个哈希表就是一个索引0 ~ HASH_SIZE-1的数组)。
	
	给出一个字符串作为key和一个哈希表的大小，返回这个字符串的哈希值。

 * @example 对于key="abcd" 并且 size=100， 返回 78
 * 
 * @Solution 水题 注意防止越界
 */
public class 哈希函数 {
	
	public static void main(String[] args) {
		int result = new 哈希函数().hashCode("abcd".toCharArray(), 100);
		System.err.println(result);
	}
	
	public int hashCode(char[] key,int HASH_SIZE) {
        // write your code here
		//这里需要用long,防止越界
		long sum = 0;
		for(int i=0; i<key.length; i++) {
			sum *= 33;
			sum += key[i];
			sum %= HASH_SIZE;
		}
		
		return (int)sum;
    }

}
