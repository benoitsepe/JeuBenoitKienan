package com.benoitkienan.jeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity implements KeyListener {
    KeyListener clavier;
    boolean keyUpPressed, keyRightPressed, keyLeftPressed, keyDownPressed, keyNukePressed, keyMinusPressed, keyPlusPressed;;
    char keyUp = 'z';
    char keyRight = 'd';
    char keyLeft = 'q';
    char keyDown = 's';
    char keyNuke = ' ';
    char keyMinus = '-';
    char keyPlus = '+';
    int speed = 10;
    double zoom = 0.4;

    public Player(String name) {
	super(name);
    }

//    public Player(Niveau niveau, char Up, char Down, char Left, char Right, char Nuke) {
//	keyUp = Up;
//	keyDown = Down;
//	keyLeft = Left;
//	keyRight = Right;
//	keyNuke = Nuke;
//    }

    public void runPlayer() {
	if (keyUpPressed == true) {
	    this.addForceY(-speed);
	}
	if (keyDownPressed) {
	    this.addForceY(speed);
	}
	if (keyLeftPressed) {
	    this.addForceX(-speed);
	}
	if (keyRightPressed) {
	    this.addForceX(speed);
	}
	if (keyNukePressed) {
	    this.nuke(niveau.getArray(), 5);
	}
	if (keyPlusPressed && zoom < 0.95) {
	    zoom = zoom + 0.01;
	}
	if (keyMinusPressed && zoom > 0.05) {
	    zoom = zoom - 0.01;
	}

	try {
	    Thread.sleep(5);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public double getRotationWithMouse(double mouseX, double mouseY) {
	double x = mouseX - posX;
	double y = mouseY - posY;
	double theta = Math.atan(y / x);
	if (x > 0) {
	    rotation = theta;
	} else {
	    rotation = theta + Math.PI;
	}
	return rotation;
    }

    public double getZoom() {
	return zoom;
    }

    public void keyPressed(KeyEvent e) {
	if (e.getKeyChar() == keyUp) {
	    keyUpPressed = true;
	}
	if (e.getKeyChar() == keyDown) {
	    keyDownPressed = true;
	}
	if (e.getKeyChar() == keyLeft) {
	    keyLeftPressed = true;
	}
	if (e.getKeyChar() == keyRight) {
	    keyRightPressed = true;
	}
	if (e.getKeyChar() == keyNuke) {
	    keyNukePressed = true;
	}
	if (e.getKeyChar() == keyPlus) {
	    keyPlusPressed = true;
	}
	if (e.getKeyChar() == keyMinus) {
	    keyMinusPressed = true;
	}

    }

    public void keyReleased(KeyEvent e) {
	if (e.getKeyChar() == keyUp) {
	    keyUpPressed = false;
	}
	if (e.getKeyChar() == keyDown) {
	    keyDownPressed = false;
	}
	if (e.getKeyChar() == keyLeft) {
	    keyLeftPressed = false;
	}
	if (e.getKeyChar() == keyRight) {
	    keyRightPressed = false;
	}
	if (e.getKeyChar() == keyNuke) {
	    keyNukePressed = false;
	}
	if (e.getKeyChar() == keyPlus) {
	    keyPlusPressed = false;
	}
	if (e.getKeyChar() == keyMinus) {
	    keyMinusPressed = false;
	}
    }

    public void keyTyped(KeyEvent e) {

    }

}
