package com.benoitkienan.editor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity implements KeyListener{
	KeyListener clavier;
	boolean keyUpPressed,keyRightPressed,keyLeftPressed,keyDownPressed, keyNukePressed;
	char keyUp='z';
	char keyRight='d';
	char keyLeft='q';
	char keyDown='s';
	char keyNuke = ' ';
	
	public Player(Niveau niveau) {
		super(niveau);
		speed=10;

	}
	
	public void runPlayer(){
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
			if(keyNukePressed){
			    this.nuke(niveau.getArray(), 2);
			}
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public double getRotationWithMouse(double mouseX, double mouseY){
		double x = mouseX-posX;
		double y = mouseY-posY;
		double theta = Math.atan(y/x);
		if(x>0){
			rotation=theta;
		}
		else{
			rotation=theta+Math.PI;
		}
		return rotation;
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
		if(e.getKeyChar()==keyNuke){
			keyNukePressed=true;
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
		if(e.getKeyChar()==keyNuke){
			keyNukePressed=false;
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}



}
