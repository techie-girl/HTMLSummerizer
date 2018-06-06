import java.util.ArrayList;
class Node {
	//creating the node. 
	public Node (String s){
		tagName = s; 
		children = new ArrayList<Node>();
		isVoid=true;
	}
	public ArrayList<Node> children;
	public String tagName; 
	public boolean isVoid;
	public String getSize() {
		if(isVoid) {
			return "";
		}
		return " "+children.size();
	}
}

