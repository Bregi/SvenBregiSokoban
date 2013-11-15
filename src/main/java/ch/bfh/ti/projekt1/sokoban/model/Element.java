package ch.bfh.ti.projekt1.sokoban.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Element extends JComponent {
	private String imageUrl;
	private int x,y;
	
	public void addImage(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public  void setLocation(int x, int y){
		this.x = x; 
		this.y = y;
		this.setSize(40,40);
		repaint();
	}
	
	public void moveElement(int x, int y){
		this.setLocation(x, y);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		String backgroundImage = imageUrl;
		try {
			Image img = ImageIO.read(new File(backgroundImage));
			g2.drawImage(img, x, y, null);
		} catch (IOException ex) {
	        System.out.println (ex.toString());
	        System.out.println("Image file was not found " + backgroundImage);
		}
	}
}
