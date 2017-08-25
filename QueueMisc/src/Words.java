import java.util.*;
import javax.swing.*;

public class Words {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue queue=new PriorityQueue();
		for(int i=0;i<10;i++){	
			String wordy= JOptionPane.showInputDialog(null,"Gimme a word!");
			queue.add(wordy);
		}
		for(int i=0;i<10;i++){
			System.out.println(queue.remove());
		}
	}

}
