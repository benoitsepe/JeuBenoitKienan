package com.benoitkienan.entities;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.benoitkienan.affichage.PanneauGame;
import com.benoitkienan.gun.Balle;
import com.benoitkienan.niveau.Niveau;
import com.benoitkienan.tiles.Tile;
import com.benoitkienan.tiles.TileManager;

public class Player extends Entity implements KeyListener {
    KeyListener clavier;
    boolean keyUpPressed, keyRightPressed, keyLeftPressed, keyDownPressed, keyNukePressed, keyMinusPressed, keyPlusPressed;
    boolean callOptions;
    char keyUp = 'z';
    char keyRight = 'd';
    char keyLeft = 'q';
    char keyDown = 's';
    char keyNuke = ' ';
    char keyMinus = '-';
    char keyPlus = '+';

    ArrayList<Thread> listBalle = new ArrayList<Thread>();

    int speed = 10;
    double zoom = 0.4;

    public Player(String name) {
	super(name);
    }

    public Player(String name, String imgName) {
	super(name);
	try {
	    image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/" + imgName));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // public Player(Niveau niveau, char Up, char Down, char Left, char Right,
    // char Nuke) {
    // keyUp = Up;
    // keyDown = Down;
    // keyLeft = Left;
    // keyRight = Right;
    // keyNuke = Nuke;
    // }

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

	// if(Math.toDegrees(rotation)>0 && Math.toDegrees(rotation)<90){
	// try {
	// image =
	// ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/linkRight.png"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }else if(Math.toDegrees(rotation)>90 &&
	// Math.toDegrees(rotation)<180){
	// try {
	// image =
	// ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/linkBack.png"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }else if(Math.toDegrees(rotation)>-180 &&
	// Math.toDegrees(rotation)<-90){
	// try {
	// image =
	// ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/linkLeft.png"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }else if(Math.toDegrees(rotation)>-90 && Math.toDegrees(rotation)<0){
	// try {
	// image =
	// ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/linkFace.png"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	try {
	    Thread.sleep(5);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public void shoot(double posXTir, double posYTir, ArrayList<Entity> EntityList, Niveau lvl, PanneauGame panGame) {

	listBalle.add(new Thread(new Tirer(posXTir, posYTir, EntityList, lvl, panGame, this)));
	int index = listBalle.size() - 1;
	listBalle.get(index).start();

    }

    public double getRotationWithMouse(double mouseX, double mouseY) {
	double x = mouseX - posX;
	double y = mouseY - posY;
	double modZ = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	double cosTheta = x / modZ;
	double sinTheta = y / modZ;
	double theta = Math.acos(cosTheta);

	if (y < 0) {
	    rotation = -theta;
	} else {
	    rotation = theta;
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

    class Tirer implements Runnable {

	double posXTir;
	double posYTir;
	ArrayList<Entity> EntityList;
	Niveau lvl;
	PanneauGame panGame;
	Player player;

	public Tirer(double posXTir, double posYTir, ArrayList<Entity> entityList, Niveau lvl, PanneauGame panGame, Player player) {
	    this.posXTir = posXTir;
	    this.posYTir = posYTir;
	    this.EntityList = entityList;
	    this.lvl = lvl;
	    this.panGame = panGame;
	    this.player = player;
	}

	public void run() {

	    double angle = getRotationWithMouse(posXTir, posYTir);

	    Balle balle = new Balle(posX, posY, 1, 10.0); // Nouvelle
							  // balle

	    boolean touche = false;

	    while (true) {

		Tile[][] arrayLvl = lvl.getArray();

		balle.setPosX(balle.getPosX() + Math.cos(angle));
		balle.setPosY(balle.getPosY() + Math.sin(angle));

		int x = (int) (balle.getPosX() / panGame.cellSizeX);
		int y = (int) (balle.getPosY() / panGame.cellSizeY);

		for (Entity entity : EntityList) {
		    if (entity.getHitbox().contains(new Point((int) balle.getPosX(), (int) balle.getPosY()))) {
			if (entity != player) {
			    System.out.println(entity.getName() + " a ete touche");
			    touche = true;
			    break;
			}
		    }
		}

		if (touche)
		    break;

		if (niveau.getArray()[x][y].isSolid()) {
		    System.out.println("[" + x + "][" + y + "] a recu un tir");
		    niveau.getArray()[x][y] = (new TileManager().grass);
		    break;
		}

	    }

	}

    }

}
