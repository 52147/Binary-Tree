import java.util.ArrayList;
import java.util.List;

public class LinkedTree {
	
	Node root;
	private int  count;
	
	public void add(String data) {
		if (root == null) {
			// First Node in the tree
			Node nn = new Node();
			nn.setData(data);
			count++;
			root = nn;
			return;
		}
		
		// Search for duplicates
		if (search(data)) {
			return;
		}
		
		// Insert this value into the tree;
		internalAdd(data, root);
	}
	
	public int size() {
		return count;
	}
	
	public void internalAdd(String data, Node root) {
		// WARNING:  This method assumes that the value is not
		//           already in the tree.
		if (root == null) {
			return;
		}
		
		if (data.compareTo(root.getData()) < 0) {
			// go left
			if (root.getlChild() != null) {
				internalAdd(data, root.getlChild());
			} else {
				// The left child is null, add this node there.
				Node nn = new Node();
				nn.setData(data);
				root.setlChild(nn);
				count++;
			}
		} else {
			// go right.
			if (root.getrChild() != null) {
				internalAdd(data, root.getrChild());
			} else {
				// The left child is null, add this node there.
				Node nn = new Node();
				nn.setData(data);
				root.setrChild(nn);
				count++;
			}
		}
	}

	public int getCount() {
		return count;
	}
	
	public void printTree() {
		printTree(root);
	}
	
	private void printTree(Node root) {
		if (root == null) {
			return;
		}
		
		printTree(root.getlChild());
		System.out.println(root.getData());
		printTree(root.getrChild());
	}
	
	private String getMinValue(Node root) {
		String minv = root.getData();
		
		while(root.getlChild() != null) {
			minv = root.getlChild().getData();
			root = root.getlChild();
		}
		
		return minv;
	}

	
	public void delete(String data) {
		int size = size();
		root = recDelete(root, data);
		
		// If the size of the tree changed, we deleted something
		if (size != size()) {
			count = size-1;
		}
	}
	
	private Node recDelete(Node root, String data) {
		
		// Base Case
		if (root == null) {
			return root;
		}
		
		// Recurse down the tree
		int cmp = data.compareTo(root.getData());
		if (cmp < 0) {
			root.setlChild(recDelete(root.getlChild(), data));
		} else if (cmp > 0) {
			root.setrChild(recDelete(root.getrChild(), data));
		} else {
			// If the data is the same, delete this node
			count--;
			// see if node has one child or no children
			if (root.getlChild() == null) {
				return root.getrChild();
			} else if (root.getrChild() == null) {
				return root.getlChild();
			}
			
			// The Node has two children, get the smallest in the right subtree.
			root.setData(getMinValue(root.getrChild()));
			
			// Delete the in-Order successor
			root.setrChild(recDelete(root.getrChild(), root.getData()));
			
		}
		
		return root;
	}

	
	public boolean search(String data) {
		return (internalSearch(data, root) != null);
	}

	public int getWordOccurNum(String data) {
		Node node = internalSearch(data, root);
		if (node == null)
			return 0;
		else
			return node.getCount();
	}
	
	public Node internalSearch(String data, Node root) {
		if (root == null) {
			return null;
		}
		
		if (root.getData().equals(data)) {
			root.incrementCount();
			return root;
		}
		
		if (data.compareTo(root.getData()) < 0) {
			return internalSearch(data, root.getlChild());
		} else {
			return internalSearch(data, root.getrChild());
		}
	}

	public int getTotalWordNum(Node root) {
		// BASE CASE
		if (root == null) {
			return 0;
		}
		return getTotalWordNum(root.getlChild()) + root.getCount() + getTotalWordNum(root.getrChild());
	}

	public Node getMostFreqWord(Node root) {
		// BASE CASE
		if (root == null) {
			Node node = new Node();
			node.setCount(0);
			return node;
		}

		Node l = getMostFreqWord(root.getlChild());
		Node r = getMostFreqWord(root.getrChild());
		if (l.getCount() > r.getCount()) {
			if (l.getCount() > root.getCount())
				return l;
			else
				return root;
		} else {
			if (r.getCount() > root.getCount())
				return r;
			else
				return root;
		}
	}
	
	public void preOrder(int num) {
		List<String> ls = new ArrayList<>();
		internalPreOrder(root, ls);
		for (int i=0; i<num; i++)
			System.out.println(ls.get(i));
	}
	
	private void internalPreOrder(Node root, List<String> ls) {
		// BASE CASE
		if (root == null) {
			return;
		}
		ls.add(root.getData());
		internalPreOrder(root.getlChild(), ls);
		internalPreOrder(root.getrChild(), ls);
	}

	private void getLeaves(Node root, List<Node> ls) {
		// BASE CASE
		if (root == null) {
			return;
		}
		getLeaves(root.getlChild(), ls);
		if (root.getlChild()==null && root.getrChild()==null)
			ls.add(root);
		getLeaves(root.getrChild(), ls);
	}
	
	public void inOrder(int num) {
		List<String> ls = new ArrayList<>();
		internalInOrder(root, ls);
		for (int i=0; i<num; i++)
			System.out.println(ls.get(i));
	}
	
	private void internalInOrder(Node root, List<String> ls) {
		// BASE CASE
		if (root == null) {
			return;
		}
		internalInOrder(root.getlChild(), ls);
		ls.add(root.getData());
		internalInOrder(root.getrChild(), ls);
	}

	public void postOrder(int num) {
		List<String> ls = new ArrayList<>();
		internalPostOrder(root, ls);
		for (int i=0; i<num; i++)
			System.out.println(ls.get(i));
	}
	
	private void internalPostOrder(Node root, List<String> ls) {
		// BASE CASE
		if (root == null) {
			return;
		}
		internalPostOrder(root.getlChild(), ls);
		internalPostOrder(root.getrChild(), ls);
		ls.add(root.getData());
	}
	
	public int getDepth() {
		return getDepth(root, -1);
	}
	
	private int getDepth(Node root, int depth) {
		if (root == null) {
			return depth;
		}
		
		int dp = getDepth(root.getlChild(), depth+1);
		int dp2 = getDepth(root.getrChild(), depth+1);
		
		return dp > dp2 ? dp : dp2;
	}

	public String getDeepestWord() {
		List<Node> ls = new ArrayList<>();
		getLeaves(root, ls);
		Node deepest = root;
		for (Node node: ls) {
			if (getDepth(node, -1) > getDepth(deepest, -1))
				deepest = node;
		}
		return deepest.getData();
	}

}
