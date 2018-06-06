import java.util.*;
import java.util.Scanner;

public class HTMLSummarizer implements Tester{
	//reading the html input into an arraylist
	public ArrayList<String> readList(Scanner s){
		HTMLScanner sc = new HTMLScanner(s);
		ArrayList<String> list = new ArrayList<String>();
		while (sc.hasNextTag()) {
			String temps = sc.nextTag();
			if(temps.charAt(0)!='!')
				list.add ( temps); 
		}
		return list;
	}
	/*creating a tree. Taking the arraylist and creating a tree. 
	so in this part of the code, I am pushing the values into a stack and popping as it goes
	and printing. 
	*/
	public Node createTree(ArrayList<String> list) {
		Node root = null;
		ArrayList<Node> tempList = new ArrayList<Node>();
		Stack<Node> st = new Stack<Node>();
		for(String tag:list) {
			if(tag.charAt(0)=='/') {
				while(!tag.substring(1,tag.length()).equals(st.peek().tagName)) {
					tempList.add(0,st.pop());
				}
				root = st.peek();
				root.isVoid=false;
				for(Node n:tempList) {
					root.children.add(n);
				}
				tempList.clear();
			}
			else {
				st.push(new Node(tag));
			}
		}
		if(st.size()>1) {
			if(root == null) {
				root = new Node(null);
				root.isVoid=true;
			}
			while(!st.empty()) {
				root.children.add(st.pop());
			}
		}
		else {
			root = st.pop();
		}
		return root;
	}
	public void dfs(Node t, int depth, ArrayList<String> s) {
		if(t==null) {
			return;
		}
		String tempS="";
		for (int i=0; i<depth; ++ i) {
			tempS+=(" ");
		}
		s.add( tempS+(t.tagName+ t.getSize())); 
		for (Node child : t.children) {
			dfs(child, depth +1,s);
		}
	}
	public ArrayList<String> treeToList(Node tree){
		ArrayList<String> s = new ArrayList<String>();
		if(tree ==null) return s;
		if(tree.tagName == null) {
			dfs(tree, -1, s);
			s.remove(0);
			return s;
		}
		else {
			dfs(tree, 0, s);
			return s;
		}
	}
	public ArrayList<String> compute(Scanner s) {
		ArrayList<String> list = readList(s);
		if(list.isEmpty()) {
			return new ArrayList<String>();
		}
		Node tree = createTree(list);
		ArrayList<String> result = treeToList(tree);
		
		return result;
	}
}