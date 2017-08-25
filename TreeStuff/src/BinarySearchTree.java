
public class BinarySearchTree extends BinaryTree{
	
	//constructor
	public BinarySearchTree(){super();}
	
	@Override
	public void insert(Comparable toAdd) {
		// TODO Auto-generated method stub
		if(isEmpty()){
			root=new TreeNode(toAdd);
			return;
		}
		boolean movedRight=false;
		TreeNode prev=root,curr=root;
		while(curr!=null){
			if(toAdd.compareTo(curr.getValue())<0){
				prev=curr;
				curr=curr.getLeft();
				movedRight=false;
			}else{
				prev=curr;
				curr=curr.getRight();
				movedRight=true;
			}
		}//end while
		if(movedRight){
			prev.setRight(new TreeNode(toAdd));
		}else{
			prev.setLeft(new TreeNode(toAdd));
		}
	}

	//FIND FUNCTIONS
	public TreeNode find(Comparable key) {
		if(isEmpty())
			return null;
		TreeNode curr=root;
		while(curr!=null && !curr.getValue().equals(key)){
			if(key.compareTo(curr.getValue())>0)
				curr=curr.getRight();
			else if(key.compareTo(curr.getValue())<0)//key<curr's val
				curr=curr.getLeft();
		}
		return curr;
	}
	
	public TreeNode recFindFunction(TreeNode r, Comparable toFind){
		if(r==null)//if its empty or u hav reached the end
			return null;
		if(r.getValue().equals(toFind))//if u found it, return it
			return r;
		if(toFind.compareTo(r.getValue())<0)
			return recFindFunction(r.getLeft(),toFind);//if there are more leaves and the current one doesnt match, recur by going to the appropriate leaf
		else 
			return recFindFunction(r.getRight(),toFind);
	}
	
	public TreeNode recFind(Comparable finding){//thing just to make it a bit more friendly to me
		return recFindFunction(root,finding);
	}
	
	//PRINT FUNCTIONS
	public void inOrderPrint(TreeNode r){
		if(r==null)
			return;
		inOrderPrint(r.getLeft());
		System.out.print(r.getValue()+" ");
		inOrderPrint(r.getRight());
	}
	
	public void preOrderPrint(TreeNode r){
		if(r==null)
			return;
		System.out.print(r.getValue()+" ");
		preOrderPrint(r.getLeft());
		preOrderPrint(r.getRight());
	}
	
	public void postOrderPrint(TreeNode r){
		if(r==null)
			return;
		postOrderPrint(r.getLeft());
		postOrderPrint(r.getRight());
		System.out.print(r.getValue()+" ");
	}
	
	public void print(){inOrderPrint(root);	}
	
	public String toString(){
		return helper(root);
	}
	
	private String helper(TreeNode r){
		if(r==null)
			return "";
		String strL=helper(r.getLeft());
		String strR=helper(r.getRight());
		return strL+" "+r.getValue()+" "+strR;
	}
	
	
	//SIZE-RELATED FUNCTIONS
	public int numElements(TreeNode r){
		if(r==null)
			return 0;
		return 1+numElements(r.getLeft())+numElements( r.getRight());
	}
	
	public int height(TreeNode r){
		if(r==null)
			return -1;
		int rite=numElements(r.getRight());
		int lerft=numElements(r.getLeft());
		if(rite>=lerft)
			return 1+height(r.getRight());
		else
			return 1+height(r.getLeft());
	}
	
	public Comparable minElement(TreeNode r){
		if(r.getLeft()==null)
			return r.getValue();
		return minElement(r.getLeft());
	}
}
