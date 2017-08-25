

public class TreeNode {
	private Comparable value;
	private TreeNode left, right;
	
	public TreeNode(Comparable v, TreeNode l, TreeNode r){
		value = v;
		left = l;
		right = r;
	}
	public TreeNode(Comparable v){this(v, null, null);}
	public TreeNode(){this(null, null, null);}
	
	//accessors
	public Comparable getValue(){return value;}
	public TreeNode getLeft(){return left;}
	public TreeNode getRight(){return right;}
	
	//mutators
	public void setValue(Comparable v){value = v;}
	public void setLeft(TreeNode l){left = l;}
	public void setRight(TreeNode r){right = r;}
	
	public String toString(){return ""+value;}
}
