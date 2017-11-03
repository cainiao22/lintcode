package website.lintcode;

import java.util.Stack;

/**
 * 
 * @author yanpf
 * @date 2017��10��31�� ����11:30:42
 * @description
 * 		����һ���ĵ�(Unix-style)����ȫ·���������·���򻯡�
 * 
 * 		
		    ���Ƿ����� ·�� = "/../" �������
		
		    ����������£����践��"/"��
		
		    ���⣬·����Ҳ���ܰ���˫б��'/'���� "/home//foo/"��
		
		    ����������£��ɺ��Զ����б�ܣ����� "/home/foo"��

 * @example
 *		"/home/", => "/home"
		"/a/./b/../../c/", => "/c"
 * @Solution
 */
public class ��·�� extends HH {
	
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
		print(new ��·��().simplifyPath("/../"));
	}
}
