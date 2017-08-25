import javax.swing.JOptionPane;


public class Palindrome {
	
	public static void main(String[] args){
		String testing=JOptionPane.showInputDialog(null,"Type the word you want to test:");
		if(isPalindrome(testing))
			JOptionPane.showMessageDialog(null,"Good news, "+testing+" is a palindrome!");
		else
			JOptionPane.showMessageDialog(null,"Bad news, "+testing+" isn't a palindrome.");
	}
	public static boolean isPalindrome(String blah){
		MyStack stacky=new MyStack();
		String possibleP="";
		for(int i=0;i<blah.length();i++)
			stacky.push(blah.charAt(i));
		for(int i=stacky.size()-1;i>=0;i--)
			possibleP+=stacky.pop();
		return possibleP.equals(blah);
	}
	
	
}
