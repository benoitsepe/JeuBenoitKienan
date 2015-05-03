package com.benoitkienan.jeu;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
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
	
	ArrayList<Mob> MobList = new ArrayList<>();
	ArrayList<Player> PlayerList = new ArrayList<>();
	
	double rotation =0;
	AffineTransform rot = new AffineTransform();
	AffineTransform tx;
	AffineTransformOp op;
	Image img;
	Node node;
	Graphics2D g2;
	double zoom=1;
	int width, height;
	int xMin, xMax, yMin, yMax;
	
	Color exterior = Color.gray.darker();
	

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
		this.addMouseWheelListener(new MouseWheelListener(){

		    public void mouseWheelMoved(MouseWheelEvent e) {
			if(e.getWheelRotation()<0 && zoom<0.95){
			    zoom=zoom+0.01;
			}
			
			if(e.getWheelRotation()>0 && zoom>0.05){
			    zoom=zoom-0.01;
			}
		    }
		    
		    
		});
		this.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e) {
				realPointeurX=(((PlayerList.get(0).getPosX()-width)+((e.getX())*2)));
				realPointeurY=(((PlayerList.get(0).getPosY()-height)+((e.getY())*2)));
				pointeurX= (int)((PlayerList.get(0).getPosX()-width/2/zoom)/cellSizeX)+(int)(((e.getX())/zoom)/cellSizeX);
				pointeurY= (int)((PlayerList.get(0).getPosY()-height/2/zoom)/cellSizeY)+(int)(((e.getY())/zoom)/cellSizeY);
			}

			public void mouseMoved(MouseEvent e) {
				realPointeurX=(((PlayerList.get(0).getPosX()-width)+((e.getX())*2)));
				realPointeurY=(((PlayerList.get(0).getPosY()-height)+((e.getY())*2)));
				pointeurX= (int)((PlayerList.get(0).getPosX()-width/2/zoom)/cellSizeX)+(int)(((e.getX())/zoom)/cellSizeX);
				pointeurY= (int)((PlayerList.get(0).getPosY()-height/2/zoom)/cellSizeY)+(int)(((e.getY())/zoom)/cellSizeY);
			}

		});
		
		cellSizeX=(1280/lvl.getArraySizeX())*50;
		cellSizeY=(720/lvl.getArraySizeY())*50;
	}

	public void paintComponent(Graphics g){
	    width=this.getWidth();
	    height=this.getHeight();

		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(exterior);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2.translate((-PlayerList.get(0).getPosX()*zoom)+this.getWidth()/2, (-PlayerList.get(0).getPosY()*zoom)+this.getHeight()/2);
		g2.scale(zoom, zoom);


		//Création quadrillage 
/*

		g.setColor(Color.black);
		for(int y=0; y<this.getHeight(); y=y+(this.getHeight()/lvl.getArraySizeY())){
			g.drawLine(0, y, this.getWidth(), y);
		}

		for(int x=0; x<this.getWidth();x=x+(this.getWidth()/lvl.getArraySizeX())){
			g.drawLine(x,0,x,this.getHeight());
		}
*/
		//Fin création quadrillage

		
	        if ( (PlayerList.get(0).getPosX()-this.getWidth()/2/zoom)/cellSizeX < 0 ) {
	            xMin = 0;
	        } else {
	            xMin = (int)((PlayerList.get(0).getPosX()-this.getWidth()/2/zoom)/cellSizeX);
	        }

	        if (  ((PlayerList.get(0).getPosX()+this.getWidth()/2/zoom)/cellSizeX)+5 > lvl.getArraySizeX() ) {
	            xMax = lvl.getArraySizeX();
	        } else {
	            xMax = (int)((PlayerList.get(0).getPosX()+this.getWidth()/2/zoom)/cellSizeX)+5;
	        }

	        if ( (PlayerList.get(0).getPosY()-this.getHeight()/2/zoom)/cellSizeY < 0 ) {
	            yMin = 0;
	        } else {
	            yMin = (int)((PlayerList.get(0).getPosY()-this.getHeight()/2/zoom)/cellSizeY);
	        }

	        if ( ((PlayerList.get(0).getPosY()+this.getHeight()/2/zoom)/cellSizeY)+5 > lvl.getArraySizeY() ) {
	            yMax = (lvl.getArraySizeY());
	        } else {
	            yMax =(int)((PlayerList.get(0).getPosY()+this.getHeight()/2/zoom)/cellSizeY)+5;
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


		g2.setColor(Color.green);
		for(Mob mob : MobList){
			g2.draw(mob.getHitbox());

		    //Dessin du path
			if(mob.getPath()!=null){
				for(int i=0; i<mob.shortestPath.size();i++){
					g2.fillRect((int)(mob.getPath().get(i).getX()*cellSizeX), (int)(mob.getPath().get(i).getY()*cellSizeY), (int)cellSizeX, (int)cellSizeY);
				}
			}
		    //Fin dessin path
			g2.drawImage(rotate(mob.getImage(),(int)cellSizeX,(int)cellSizeY, mob.getRotationWithVectors()), (int)(mob.getPosX()-cellSizeX/2), (int)(mob.getPosY()-cellSizeY/2),(int)cellSizeX,(int)cellSizeY, this);

		}


		for(Player player : PlayerList){
			g2.drawImage(rotate(player.getImage(),(int)cellSizeX,(int)cellSizeY, player.getRotationWithMouse(realPointeurX, realPointeurY)), (int)(player.getPosX()-cellSizeX/2), (int)(player.getPosY()-cellSizeY/2),(int)cellSizeX,(int)cellSizeY, this);

		}
		


//		Dessin de vecteurs
		
//		if((int)ent.vectorX!=0 || (int)ent.vectorY!=0){
//			((Graphics2D) g2).setStroke(new BasicStroke(5));
//			g2.setColor(Color.BLUE);
//			g2.drawLine((int)(ent.getPosX()+cellSizeX/2), (int)(ent.getPosY()+cellSizeY/2), (int)((ent.getPosX()+cellSizeX/2)+ent.vectorX*10), (int)((ent.getPosY()+cellSizeY/2)+ent.vectorY*10));
//		}


		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(10));
		g2.drawRect((int)(pointeurX*cellSizeX), (int)(pointeurY*cellSizeY), (int)cellSizeX, (int)cellSizeY);
		
		g2.setColor(Color.BLUE);
		g2.draw(PlayerList.get(0).getHitbox());
		

		
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


	
	
	public Dimension getDim(){
		return dim;
	}

	public void setNiveau(Niveau niv){
		lvl=niv;
	}

	public void setMobList(ArrayList<Mob> mo){
		MobList=mo;
	}
	
	public void setPlayerList(ArrayList<Player> play){
		PlayerList = play;
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
