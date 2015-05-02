package com.benoitkienan.editor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Camera implements KeyListener{
    KeyListener clavier;
    boolean keyUpPressed,keyRightPressed,keyLeftPressed,keyDownPressed, keyNukePressed;
    char keyUp='z';
    char keyRight='d';
    char keyLeft='q';
    char keyDown='s';

    Niveau        niveau;
    double        posX, posY;
    double        vectorX, vectorY;
    int           speed;
    PanneauGame   panGame;
    Graphics2D    g2;

    public Camera( Niveau niveau ) {
	speed=10;
    }


    public void runCamera(){
	if(keyUpPressed==true){
	    this.addForceY(-speed);
	}
	if(keyDownPressed){
	    this.addForceY(speed);
	}
	if(keyLeftPressed){
	    this.addForceX(-speed);
	}
	if(keyRightPressed){
	    this.addForceX(speed);
	}

	try {
	    Thread.sleep(5);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public void setNiveau( Niveau niv ) {
	niveau = niv;
    }

    public void setPanneauGame( PanneauGame pan ) {
	panGame = pan;
    }

    public void applyPhysics() {
	posX = posX + vectorX;
	posY = posY + vectorY;

	vectorX = vectorX / 2; // WHY ?
		vectorY = vectorY / 2;

    }

    public void addForceX( double force ) {
	vectorX = vectorX + force;
    }

    public void addForceY( double force ) {
	vectorY = vectorY + force;
    }

    public void setPosX( double pos ) {
	posX = pos;
    }

    public void setPosY( double pos ) {
	posY = pos;
    }

    public double getPosX() {
	return posX;
    }

    public double getPosY() {
	return posY;
    }


    public void keyPressed(KeyEvent e) {
	if(e.getKeyChar()==keyUp){
	    keyUpPressed=true;
	}
	if(e.getKeyChar()==keyDown){
	    keyDownPressed=true;
	}
	if(e.getKeyChar()==keyLeft){
	    keyLeftPressed=true;
	}
	if(e.getKeyChar()==keyRight){
	    keyRightPressed=true;
	}


    }

    public void keyReleased(KeyEvent e) {
	if(e.getKeyChar()==keyUp){
	    keyUpPressed=false;
	}
	if(e.getKeyChar()==keyDown){
	    keyDownPressed=false;
	}
	if(e.getKeyChar()==keyLeft){
	    keyLeftPressed=false;
	}
	if(e.getKeyChar()==keyRight){
	    keyRightPressed=false;
	}

    }

    public void keyTyped(KeyEvent e) {

    }

}
