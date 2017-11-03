package website.lintcode;

import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017年10月31日 上午11:30:42
 * @description
 * 		给定一个文档(Unix-style)的完全路径，请进行路径简化。
 * 
 * 		
		    你是否考虑了 路径 = "/../" 的情况？
		
		    在这种情况下，你需返回"/"。
		
		    此外，路径中也可能包含双斜杠'/'，如 "/home//foo/"。
		
		    在这种情况下，可忽略多余的斜杠，返回 "/home/foo"。

 * @example
 *		"/home/", => "/home"
		"/a/./b/../../c/", => "/c"
 * @Solution
 */
public class 简化路径 extends HH {
	
	public String simplifyPath(String path) {
        // write your code here
		if(path == null) {
			return null;
		}
		Stack<String> stack = new Stack<>();
		String[] paths = path.split("/");
		for(int i=0; i<paths.length; i++) {
			if(!paths[i].equals("") && !paths[i].equals(".") && !paths[i].equals("..")) {
				stack.push(paths[i]);
			}else if(paths[i].equals("..") && !stack.isEmpty()) {
				stack.pop();
			}
		}
		int i = paths.length - 1;
		while(!stack.isEmpty()) {
			paths[i] = "/" + stack.pop();
			i --;
		}
		StringBuffer sb = new StringBuffer();
		for(int j=i+1;j<paths.length; j++) {
			sb.append(paths[j]);
		}
		if(sb.length() == 0) {
			return "/";
		}
		return sb.toString();
    }

	public static void main(String[] args) {
		print(new 简化路径().simplifyPath("/../"));
	}
}
