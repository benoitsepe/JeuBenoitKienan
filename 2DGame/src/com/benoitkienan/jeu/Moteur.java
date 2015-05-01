package com.benoitkienan.jeu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Moteur {

    Niveau lvl = new Niveau();
    Player player = new Player(lvl);
    Mob mob1 = new Mob(lvl);
    Mob mob2 = new Mob(lvl);
    BufferedImage blueBrick, redBrick, blackBrick, goldBrick, sorcier, cage, poulpe, nyan;	


    PanneauGame panGame;
    int x,y,d; //Pour cercle d'Andres
    int toolSelected=2;
    int sliderValue=5;
    ArrayList<Mob> MobList = new ArrayList<>();
    ArrayList<Player> PlayerList = new ArrayList<>();

    public Moteur(PanneauGame pan){

	try {

	    sorcier = ImageIO.read(new File("Pictures/sorcier.png"));
	    cage = ImageIO.read(new File("Pictures/CAGE.png"));
	    poulpe = ImageIO.read(new File("Pictures/poulpe.png"));
	    nyan = ImageIO.read(new File("Pictures/nyan.png"));


	} catch (IOException e) {
	    e.printStackTrace();
	}

	player.setImage(nyan);
	//player2.setImage(poulpe);
	mob1.setImage(sorcier);
	mob2.setImage(cage);

	lvl.createRandomLvl();
	panGame=pan;
	PlayerList.add(player);
	//PlayerList.add(player2);
	MobList.add(mob1);
	MobList.add(mob2);



	for(Player pl : PlayerList){
	    pl.setNiveau(lvl);
	    pl.setPanneauGame(panGame);
	}

	for(Mob mob : MobList){
	    mob.setNiveau(lvl);
	    mob.setPanneauGame(panGame);
	}




    }


    public void runIA(){
	for(Mob mob : MobList){
	    mob.spawnRandom();
	    mob.goToNearestPlayer(PlayerList, lvl.getArray());
	}



	while(true){
		for(Mob mob : MobList){
		    mob.setNiveau(lvl);
		    mob.applyPhysics();
		    

		    mob.getNearestPlayer(PlayerList, lvl.getArray());
		    if(mob.shortestPath!=null){
			mob.goToNearestPlayer(PlayerList, lvl.getArray());
			mob.followPath();
		    }

		}
		panGame.setMobList(MobList);

	    try {
		Thread.sleep(20);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}
    } 


    public void runGame(){

	while(true){

	    if(panGame.getClicMiddle()==true){
		System.out.println("["+panGame.getPointeurX()+"]["+panGame.getPointeurY()+"]:"+lvl.getArray()[panGame.getPointeurX()][panGame.getPointeurY()]);
		panGame.clicMiddle=false;
	    }

	    if(panGame.getClicGauche()==true){
		try{
		    for(int r=0;r<sliderValue;r++){
			//Algorithme de tracé de cercle d'Andres
			x=0;
			y=r;
			d=r-1;
			while(y>=x){
			    lvl.getArray()[panGame.getPointeurX() + x][panGame.getPointeurY() + y]=toolSelected;
			    lvl.getArray()[panGame.getPointeurX() + y][panGame.getPointeurY() + x]=toolSelected;
			    lvl.getArray()[panGame.getPointeurX() - x][panGame.getPointeurY() + y]=toolSelected;
			    lvl.getArray()[panGame.getPointeurX() - y][panGame.getPointeurY() + x]=toolSelected;
			    lvl.getArray()[panGame.getPointeurX() + x][panGame.getPointeurY() - y]=toolSelected;
			    lvl.getArray()[panGame.getPointeurX() + y][panGame.getPointeurY() - x]=toolSelected;
			    lvl.getArray()[panGame.getPointeurX() - x][panGame.getPointeurY() - y]=toolSelected;
			    lvl.getArray()[panGame.getPointeurX() - y][panGame.getPointeurY() - x]=toolSelected;

			    if(d>=2*x){
				d=d-2*x-1;
				x=x+1;
			    }
			    else if(d<2*(r-y)){
				d=d+2*y-1;
				y=y-1;
			    }
			    else{
				d=d+2*(y-x-1);
				y=y-1;
				x=x+1;
			    }
			}
		    }
		}catch(ArrayIndexOutOfBoundsException e){
		    e.printStackTrace();
		}
		lvl.getArray()[panGame.getPointeurX()][panGame.getPointeurY()]=toolSelected;
		panGame.setNiveau(lvl);
	    }

	    if(panGame.getClicDroit()==true){
		try{
		    lvl.getArray()[panGame.getPointeurX()][panGame.getPointeurY()]=0;

		}catch(ArrayIndexOutOfBoundsException e){
		    e.printStackTrace();
		}
	    }


	    panGame.setPlayerList(PlayerList);
	    panGame.setNiveau(lvl);




	    try {
		Thread.sleep(5);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}
    }

    public void motApplyPhysics(){
	panGame.setNiveau(lvl);
	panGame.setPlayerList(PlayerList);
	panGame.setMobList(MobList);

	for(Player player : PlayerList){
	    player.setPanneauGame(panGame);
	    player.setNiveau(lvl);
	    player.spawnRandom();
	    player.nuke(lvl.getArray(),10);

	}


	while(true){

	    try{
		for(Player player : PlayerList){

		    player.runPlayer();
		    player.applyPhysics();
		    player.setNiveau(lvl);
		}

	    }catch(ArrayIndexOutOfBoundsException e){
		e.printStackTrace();
	    }

	    try {
		Thread.sleep(5);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}


    }

    public void setNiveau(Niveau niv){
	lvl=niv;
    }

    public void setPanneau(PanneauGame pan){
	panGame=pan;
    }

    public Niveau getNiveau(){
	return lvl;
    }
    public void setToolSelected(int tool){
	toolSelected=tool;
    }

    public void setSliderValue(int value){
	sliderValue=value;
    }

}
