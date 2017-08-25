import javax.swing.*;//needed for JOptionPane
public class Pretty {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Variable Declarations
		String name;
		
		//Get user input
		name=JOptionPane.showInputDialog(null, "Why is arno such a derp?");
		
		//show the output
		JOptionPane.showMessageDialog(null, "Wow, i had no idea "+name+"!");
		
	}

}
