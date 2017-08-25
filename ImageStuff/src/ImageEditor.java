import java.awt.*;

import javax.swing.*;

import java.io.*;
import java.awt.event.*;

public class ImageEditor extends JFrame implements ActionListener{
	private ImageCanvas orig, alter;
	String file = "./src/flower.jpg";
	
	private JMenuItem open, testIt, stretchX, stretchY, rotate;
	
	private JButton buttonVert;
	private JButton buttonHoriz;
	private JButton buttonRed;
	private JButton buttonBlu;
	private JButton buttonGre;
	private JButton buttonNeg;
	private JButton buttonGray;
	private JButton buttonSep;
	private JButton buttonNorm;
	private JButton buttonBlur;
	private JButton buttonSharp;
	private JPanel thingHolder;
	
	
	public ImageEditor(){
		super("Image Editor");
		makeMenu();
		
		orig = new ImageCanvas();
		orig.setImage(new File(file));
		
		alter = new ImageCanvas();
		alter.setImage(new File(file));
		
		//alter.tester();
		
		buttonVert=new JButton("Mirror Vertically");
		buttonVert.addActionListener(this);
		
		buttonHoriz=new JButton("Mirror Horizontally");
		buttonHoriz.addActionListener(this);
		
		buttonRed=new JButton("Redify");
		buttonRed.addActionListener(this);
		
		buttonBlu=new JButton("Blueify");
		buttonBlu.addActionListener(this);
		
		buttonGre=new JButton("Greenify");
		buttonGre.addActionListener(this);
		
		buttonNeg=new JButton("Negative");
		buttonNeg.addActionListener(this);
		
		buttonGray=new JButton("Greyscale");
		buttonGray.addActionListener(this);
	
		buttonSep=new JButton("Sepia");
		buttonSep.addActionListener(this);
		
		buttonNorm=new JButton("Back to Original");
		buttonNorm.addActionListener(this);

		buttonBlur=new JButton("Blur");
		buttonBlur.addActionListener(this);
		
		buttonSharp=new JButton("Sharpen");
		buttonSharp.addActionListener(this);
		
		thingHolder=new JPanel();
		thingHolder.setLayout(new GridLayout(2,7));
		
		thingHolder.add(buttonVert);
		thingHolder.add(buttonHoriz);
		thingHolder.add(buttonRed);
		thingHolder.add(buttonBlu);
		thingHolder.add(buttonGre);
		thingHolder.add(buttonNeg);
		thingHolder.add(buttonGray);
		thingHolder.add(buttonSep);
		thingHolder.add(buttonNorm);
		thingHolder.add(buttonBlur);
		thingHolder.add(buttonSharp);
		this.add(thingHolder, BorderLayout.NORTH);
		
		
		JPanel stuff = new JPanel();
		stuff.setLayout(new GridLayout(1,2));
		stuff.add(new JScrollPane(orig));
		stuff.add(new JScrollPane(alter));
		this.add(stuff, BorderLayout.CENTER);
		//finishing up
		this.setSize(900,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	private void makeMenu(){
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		open = new JMenuItem("Open");
		open.addActionListener(this);
		file.add(open);

		testIt = new JMenuItem("Test");
		testIt.addActionListener(this);
		file.add(testIt);
		
		
		
		JMenu transform=new JMenu("Transformations");
		
		stretchX=new JMenuItem("Horizontal Stretch");
		stretchX.addActionListener(this);
		
		stretchY=new JMenuItem("Vertical Stretch");
		stretchY.addActionListener(this);
		
		rotate=new JMenuItem("Rotate");
		rotate.addActionListener(this);
		
		bar.add(file);
		bar.add(transform);
		
		transform.add(stretchX);
		transform.add(stretchY);
		transform.add(rotate);
		
		this.setJMenuBar(bar);
	}
	
	public static void main(String[] args) {new ImageEditor();}

	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==open){
			JFileChooser jfc = new JFileChooser();
			int result = jfc.showOpenDialog(this);
			if(result == JFileChooser.CANCEL_OPTION)
				return;
			File f = jfc.getSelectedFile();
			orig.setImage(f);
			alter.setImage(f);
			this.repaint();
		}
		if(e.getSource()==testIt){
			alter.tester();
		}
		if(e.getSource()==stretchX){
			double factor=Double.parseDouble(JOptionPane.showInputDialog("Stretch by what factor?"));
			alter.stretchHoriz(factor);
		}
		if(e.getSource()==stretchY){
			double factor=Double.parseDouble(JOptionPane.showInputDialog("Stretch by what factor?"));
			alter.stretchVert(factor);
		}
		if(e.getSource()==rotate){
			double degrees=Double.parseDouble(JOptionPane.showInputDialog("Rotate by how many degrees?"));
			alter.rotate(degrees);
		}
			
		if(e.getSource()==buttonVert)
			alter.mirror(true);
		if(e.getSource()==buttonHoriz)
			alter.mirror(false);
		if(e.getSource()==buttonRed)
			alter.red();
		if(e.getSource()==buttonBlu)
			alter.blue();
		if(e.getSource()==buttonGre)
			alter.green();
		if(e.getSource()==buttonNeg)
			alter.negative();
		if(e.getSource()==buttonGray)
			alter.greyscale();
		if(e.getSource()==buttonSep)
			alter.sepia();
		if(e.getSource()==buttonBlur)
			alter.blur();
		if(e.getSource()==buttonNorm)
			alter.setImage(new File(file));
		if(e.getSource()==buttonSharp)
			alter.sharpen();
		repaint();
	}
	
}
