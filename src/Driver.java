
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Driver me = new Driver();
		me.doIt();
	}
	
	private void doIt() {
		TextParser parser = new TextParser();
		parser.openFile("./pg345.txt");
		LinkedTree tree = new LinkedTree();
		String word;
		while ((word  = parser.getNextWord()) != null) {
			tree.add(word);
		}
		
		//How many times do the following words appear in the text?
		System.out.println("The transylvania appears "+tree.getWordOccurNum("transylvania")+" times");
		System.out.println("The vampire appears "+tree.getWordOccurNum("vampire")+" times");
		System.out.println("The harker appears "+tree.getWordOccurNum("harker")+" times");
		System.out.println("The expostulate appears "+tree.getWordOccurNum("expostulate")+" times");
		System.out.println("The renfield appears "+tree.getWordOccurNum("renfield")+" times");
		System.out.println("The fang appears "+tree.getWordOccurNum("fang")+" times");

		//What is the depth of the tree?
		System.out.println("Depth of tree is " + tree.getDepth());

		//How many different words are there in the book?
		System.out.println("There are "+tree.getCount()+" different words");
		
		//What word is at the root of the tree?
		System.out.println("The word in root is "+tree.root.getData());

		//Which word(s) are at the deepest leaves in the tree
		System.out.println("The word in deepest leaves is "+tree.getDeepestWord());

		//How many total words are in the book?
		System.out.println("Total word number is " + tree.getTotalWordNum(tree.root));

		//Which word occurs most frequently?
		System.out.println("The most frequently word is "+tree.getMostFreqWord(tree.root).getData());

		//Display the first 20 words in a Pre-Order Traversal of the tree.
		System.out.println("preorder first 20 words");
		tree.preOrder(20);

		//Display the first 20 words in a Post-Order Traversal of the tree.
		System.out.println("postorder first 20 words");
		tree.postOrder(20);

		//Display the first 20 words in an In-Order Traversal of the tree.
		System.out.println("inorder first 20 words");
		tree.inOrder(20);
	}

}
