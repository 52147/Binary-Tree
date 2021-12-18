
public class Node {
	private String data;
	private int count = 1;
	private Node lChild;
	private Node rChild;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Node getlChild() {
		return lChild;
	}
	public void setlChild(Node lChild) {
		this.lChild = lChild;
	}
	public Node getrChild() {
		return rChild;
	}
	public void setrChild(Node rChild) {
		this.rChild = rChild;
	}

	public void incrementCount() {
		count++;
	}

	public int getCount() {
		return count;
	}
}
