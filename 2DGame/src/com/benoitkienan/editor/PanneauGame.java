package com.benoitkienan.editor;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import CDIO.pathFinder.Node;

public class PanneauGame extends JPanel implements MouseListener {

	Color color=Color.white;
	Niveau lvl = new Niveau();
	int pointeurX, pointeurY;
	double realPointeurX, realPointeurY;
	double cellSizeX, cellSizeY;
	boolean clicGauche,clicDroit,clicMiddle;
	BufferedImage blueBrick, redBrick, blackBrick, goldBrick;
	Dimension dim = new Dimension(1280,720);
	
	double rotation =0;
	AffineTransform rot = new AffineTransform();
	AffineTransform tx;
	AffineTransformOp op;
	Image img;
	Node node;
	Graphics2D g2;
	double zoom=0.5;
	int width, height;
	int xMin, xMax, yMin, yMax;
	
	Color exterior = Color.gray.darker();
	
	Camera camera;

	public PanneauGame(){
	    

		try {
			blueBrick = ImageIO.read(new File("Pictures/blueBrick.png"));
			redBrick = ImageIO.read(new File("Pictures/redBrick.png"));
			blackBrick = ImageIO.read(new File("Pictures/blackBrick.png"));
			goldBrick = ImageIO.read(new File("Pictures/goldBrick.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}



		this.addMouseListener(this);
		this.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e) {
				realPointeurX=(((camera.getPosX()-width)+((e.getX())*2)));
				realPointeurY=(((camera.getPosY()-height)+((e.getY())*2)));
				pointeurX=(int)((((camera.getPosX()-width)+((e.getX())*2)))/cellSizeX);
				pointeurY=(int)((((camera.getPosY()-height)+((e.getY())*2)))/cellSizeY);
			}

			public void mouseMoved(MouseEvent e) {
				realPointeurX=(((camera.getPosX()-width)+((e.getX())*2)));
				realPointeurY=(((camera.getPosY()-height)+((e.getY())*2)));
				pointeurX=(int)((((camera.getPosX()-width)+((e.getX())*2)))/cellSizeX);
				pointeurY=(int)((((camera.getPosY()-height)+((e.getY())*2)))/cellSizeY);
			}

		});
		
		cellSizeX=(1280/lvl.getArraySizeX())*20;
		cellSizeY=(720/lvl.getArraySizeY())*20;
	}

	public void paintComponent(Graphics g){
	    width=this.getWidth();
	    height=this.getHeight();

		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(exterior);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2.translate((-camera.getPosX()*zoom)+this.getWidth()/2, (-camera.getPosY()*zoom)+this.getHeight()/2);
		g2.scale(zoom, zoom);


		//Création quadrillage 


		g.setColor(Color.black);
		for(int y=0; y<this.getHeight(); y=y+(this.getHeight()/lvl.getArraySizeY())){
			g.drawLine(0, y, this.getWidth(), y);
		}

		for(int x=0; x<this.getWidth();x=x+(this.getWidth()/lvl.getArraySizeX())){
			g.drawLine(x,0,x,this.getHeight());
		}

		//Fin création quadrillage

		
	        if ( ((camera.getPosX()-this.getWidth())/cellSizeX) < 0 ) {
	            xMin = 0;
	        } else {
	            xMin = (int)((camera.getPosX()-this.getWidth())/cellSizeX);
	        }

	        if (  ((camera.getPosX()+this.getWidth())/cellSizeX) > lvl.getArraySizeX() ) {
	            xMax = lvl.getArraySizeX();
	        } else {
	            xMax = (int)((camera.getPosX()+this.getWidth())/cellSizeX)+1;
	        }

	        if ( ((camera.getPosY()-this.getHeight())/cellSizeY) < 0 ) {
	            yMin = 0;
	        } else {
	            yMin =(int)((camera.getPosY()-this.getHeight())/cellSizeY);
	        }

	        if ( ((camera.getPosY()+this.getHeight())/cellSizeY) > lvl.getArraySizeY() ) {
	            yMax = (lvl.getArraySizeY());
	        } else {
	            yMax =(int)((camera.getPosY()+this.getHeight())/cellSizeY)+1;
	        }
		
		for(int x=xMin;x<xMax;x++){
			for(int y=yMin;y<yMax;y++){
				if(lvl.getArray()[x][y]==0){
					g2.setColor(Color.gray);
					g2.fillRect(x*(int)cellSizeX, y*(int)cellSizeY,(int)cellSizeX,(int)cellSizeY);
				}
				if(lvl.getArray()[x][y]==1){
					g2.drawImage(blueBrick, x*(int)cellSizeX, y*(int)cellSizeY,(int)cellSizeX,(int)cellSizeY, this);
				}

				if(lvl.getArray()[x][y]==2){
					g2.drawImage(redBrick, x*(int)cellSizeX, y*(int)cellSizeY,(int)cellSizeX,(int)cellSizeY, this);

				}

				if(lvl.getArray()[x][y]==3){
					g2.drawImage(goldBrick, x*(int)cellSizeX, y*(int)cellSizeY,(int)cellSizeX,(int)cellSizeY, this);
				}

				if(lvl.getArray()[x][y]==4){
					g2.drawImage(blackBrick, x*(int)cellSizeX, y*(int)cellSizeY,(int)cellSizeX,(int)cellSizeY, this);
				}

			}}



		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect((int)(pointeurX*cellSizeX), (int)(pointeurY*cellSizeY), (int)cellSizeX, (int)cellSizeY);
		
	}
	
	public static BufferedImage rotate(BufferedImage img, int cellSizeX, int cellSizeY, double rotation) {  
		int w = cellSizeX;  
		int h = cellSizeY;  
		BufferedImage newImage = new BufferedImage(cellSizeX, cellSizeY, img.getType());
		Graphics2D g2 = newImage.createGraphics();
		g2.rotate(rotation, h/2, w/2); 
		g2.drawImage(img, 0, 0,cellSizeX,cellSizeY, null);
		return newImage;  
	}

	public void setCamera(Camera cam){
	    camera=cam;
	}
	
	public Dimension getDim(){
		return dim;
	}

	public void setNiveau(Niveau niv){
		lvl=niv;
	}

	public double getRealPointeurX(){
		return realPointeurX;
	}

	public double getRealPointeurY(){
		return realPointeurY;
	}

	public int getPointeurX(){
		return pointeurX;
	}

	public int getPointeurY(){
		return pointeurY;
	}

	public boolean getClicGauche(){
		return clicGauche;
	}

	public boolean getClicDroit(){
		return clicDroit;
	}

	public boolean getClicMiddle(){
		return clicMiddle;
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {
		clicGauche=false;
		clicDroit=false;
		clicMiddle=false;
	}

	public void mousePressed(MouseEvent e) {

		if(e.getButton()==1)
			clicGauche=true;

		if(e.getButton()==3)
			clicDroit=true;

		if(e.getButton()==2)
			clicMiddle=true;

	}

	public void mouseReleased(MouseEvent arg0) {
		clicGauche = false;
		clicDroit=false;
		clicMiddle=false;
	}



}
