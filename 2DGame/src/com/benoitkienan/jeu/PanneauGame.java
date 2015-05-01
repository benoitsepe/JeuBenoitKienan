package com.benoitkienan.jeu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

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
	int zoom = 1;
	int xMin,xMax,yMin,yMax;

	public PanneauGame(Color couleur){
	    

		try {
			blueBrick = ImageIO.read(new File("Pictures/blueBrick.png"));
			redBrick = ImageIO.read(new File("Pictures/redBrick.png"));
			blackBrick = ImageIO.read(new File("Pictures/blackBrick.png"));
			goldBrick = ImageIO.read(new File("Pictures/goldBrick.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		color=couleur;


		this.addMouseListener(this);
		this.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e) {
				realPointeurX=(e.getX());
				realPointeurY=(e.getY());
				pointeurX=(e.getX()/(int)cellSizeX);
				pointeurY=(e.getY()/(int)cellSizeY);
			}

			public void mouseMoved(MouseEvent e) {
				realPointeurX=(e.getX());
				realPointeurY=(e.getY());
				pointeurX=(e.getX()/(int)cellSizeX);
				pointeurY=(e.getY()/(int)cellSizeY);
			}

		});
		
		cellSizeX=(1280/lvl.getArraySizeX())*zoom;
		cellSizeY=(720/lvl.getArraySizeY())*zoom;
	}

	public void paintComponent(Graphics g){
	    
		cellSizeX=(1280/lvl.getArraySizeX())*zoom;
		cellSizeY=(720/lvl.getArraySizeY())*zoom;
		System.out.println(cellSizeX);
		System.out.println(PlayerList.get(0).getPosX());
		
		Graphics2D g2 = (Graphics2D)g;		
		g2.setColor(color);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//g2.translate((-PlayerList.get(0).getPosX()+this.getWidth()/2), (-PlayerList.get(0).getPosY()+this.getHeight()/2));
		

		
		
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

		int truc = 50;
		if(((PlayerList.get(0).getPosX()-cellSizeX*truc)/cellSizeX)<0){
		    xMin=0;
		}else{
		    xMin=(int)((PlayerList.get(0).getPosX()-cellSizeX*truc)/cellSizeX);
		}
		
		if(((PlayerList.get(0).getPosX()+cellSizeX*truc)/cellSizeX)>lvl.getArraySizeX()){
		    xMax=lvl.getArraySizeX();
		}else{
		    xMax=(int)((PlayerList.get(0).getPosX()+cellSizeX*truc)/cellSizeX);
		}
		
		if(((PlayerList.get(0).getPosY()-cellSizeY*truc)/cellSizeY)<0){
		    yMin=0;
		}else{
		    yMin=(int)((PlayerList.get(0).getPosY()-cellSizeY*truc)/cellSizeY);
		}
		
		if(((PlayerList.get(0).getPosY()+cellSizeY*truc)/cellSizeY)>lvl.getArraySizeY()){
		    yMax=lvl.getArraySizeY();
		}else{
		    yMax=(int)((PlayerList.get(0).getPosY()+cellSizeY*truc)/cellSizeY);
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
			if(mob.getPath()!=null){
				for(int i=0; i<mob.shortestPath.size();i++){
					g2.fillRect((int)(mob.getPath().get(i).getX()*cellSizeX), (int)(mob.getPath().get(i).getY()*cellSizeY), (int)cellSizeX, (int)cellSizeY);
				}
			}
			g2.drawImage(mob.getImage(), (int)mob.getPosX(), (int)mob.getPosY(),(int)cellSizeX,(int)cellSizeY, this);
		}


		for(Player player : PlayerList){
			g2.drawImage(rotate(player.getImage(),(int)cellSizeX,(int)cellSizeY, player.getRotationWithMouse(realPointeurX, realPointeurY)), (int)player.getPosX(), (int)player.getPosY(),(int)cellSizeX,(int)cellSizeY, this);

		}
		


//		Dessin de vecteurs
		
//		if((int)ent.vectorX!=0 || (int)ent.vectorY!=0){
//			((Graphics2D) g2).setStroke(new BasicStroke(5));
//			g2.setColor(Color.BLUE);
//			g2.drawLine((int)(ent.getPosX()+cellSizeX/2), (int)(ent.getPosY()+cellSizeY/2), (int)((ent.getPosX()+cellSizeX/2)+ent.vectorX*10), (int)((ent.getPosY()+cellSizeY/2)+ent.vectorY*10));
//		}


		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(pointeurX*(int)cellSizeX, pointeurY*(int)cellSizeY, (int)cellSizeX, (int)cellSizeY);




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

	public void setMobList(ArrayList mo){
		MobList=mo;
	}
	
	public void setPlayerList(ArrayList play){
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
