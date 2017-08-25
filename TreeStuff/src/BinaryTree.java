

public abstract class BinaryTree{
	protected TreeNode root;
	
	public BinaryTree(){root = null;}
	public BinaryTree(TreeNode r){root = r;}
	
	public boolean isEmpty(){return root==null;}
	
	public TreeNode getRoot(){return root;}
	
	public void setRoot(TreeNode r){root = r;}
	
	//abstract classes
	public abstract void insert(Comparable item);
	public abstract TreeNode find(Comparable key);
}
