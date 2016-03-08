package en.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Case extends JPanel{
	char content; //type of image
	JLabel label; //contains the image
	String theme;
	public Case(char content){
		URL url; //url of the image
		Image img; //image without a resize
		Image newimg; //image resized
		theme="classic";
		switch(content){
		
			case ' ': 
				this.label = new JLabel(" ");
				add(label);
				break;
			case 'X': 
				setBackground(Color.BLACK);
				setLayout(new FlowLayout(FlowLayout.CENTER));
				label = new JLabel();
				img = new ImageIcon("sprites/"+theme+"/wall.gif").getImage();
				newimg = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
				label.setIcon(new ImageIcon(newimg));
				add(label);
				break;
			case 'g':
				setBackground(Color.BLACK);
				setLayout(new FlowLayout(FlowLayout.CENTER));
				label = new JLabel();
				img = new ImageIcon("sprites/"+theme+"/PacGum.gif").getImage();
				newimg = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
				label.setIcon(new ImageIcon(newimg));
				add(label);
				break;
			case 'S':
				setBackground(Color.BLACK);
				setLayout(new FlowLayout(FlowLayout.CENTER));
				label = new JLabel();
				img = new ImageIcon("sprites/"+theme+"/Super pacgum.gif").getImage();
				newimg = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
				label = new JLabel(new ImageIcon(newimg));
				add(label);
				break;
			case 'D':
				this.label = new JLabel("D");
				break;
			case 'P':
				setBackground(Color.BLACK);
				setLayout(new FlowLayout(FlowLayout.CENTER));
				label = new JLabel();
				img = new ImageIcon("sprites/"+theme+"/PacMan_right.gif").getImage();
				newimg = img.getScaledInstance(18, 18, Image.SCALE_DEFAULT);
//				label.setIcon(new ImageIcon(newimg));
				label = new JLabel(new ImageIcon(newimg));
				add(label);
				break;
			case 'G':
				setBackground(Color.BLACK);
				setLayout(new FlowLayout(FlowLayout.CENTER));
				label = new JLabel();
				img = new ImageIcon("sprites/"+theme+"/Blinky_down.gif").getImage();
				newimg = img.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
				label.setIcon(new ImageIcon(newimg));
				add(label);
				break;
//			case '1': 
//				setBackground(Color.BLACK);
//				setLayout(new FlowLayout(FlowLayout.CENTER));
//				label = new JLabel();
//				url = getClass().getResource("Super pacgum.gif");
//				img = new ImageIcon(url).getImage();
//				newimg = img.getScaledInstance(18, 18, Image.SCALE_DEFAULT);
////				label.setIcon(new ImageIcon(newimg));
//				label = new JLabel(new ImageIcon(newimg));
//				add(label);
//				System.out.println("lol");
//				break;
//			case '2': 
//				setBackground(Color.BLACK);
//				setLayout(new FlowLayout(FlowLayout.CENTER));
//				label = new JLabel();
//				url = getClass().getResource("Clyde_down.gif");
//				img = new ImageIcon(url).getImage();
//				newimg = img.getScaledInstance(30, 20, Image.SCALE_DEFAULT);
//				label.setIcon(new ImageIcon(newimg));
//				add(label);
//				break;
//			case '3':
//				setLayout(new FlowLayout(FlowLayout.CENTER));
//				label = new JLabel();
//				url = getClass().getResource("Inky_down.gif");
//				img = new ImageIcon(url).getImage();
//				newimg = img.getScaledInstance(30, 20, Image.SCALE_DEFAULT);
//				label.setIcon(new ImageIcon(newimg));
//				add(label);
//				break;
//			case '4':
//				setBackground(Color.BLACK);
//				setLayout(new FlowLayout(FlowLayout.CENTER));
//				label = new JLabel();
//				url = getClass().getResource("Pinky_down.gif");
//				img = new ImageIcon(url).getImage();
//				newimg = img.getScaledInstance(30, 20, Image.SCALE_DEFAULT);
//				label.setIcon(new ImageIcon(newimg));
//				add(label);
//				break;
			default:
				break;
	
			}
		
		this.setBackground(Color.BLACK);
	}
	
}
