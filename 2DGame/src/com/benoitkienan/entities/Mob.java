package com.benoitkienan.entities;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import CDIO.pathFinder.AStar;
import CDIO.pathFinder.AreaMap;
import CDIO.pathFinder.Node;
import CDIO.pathFinder.heuristics.AStarHeuristic;
import CDIO.pathFinder.heuristics.DiagonalHeuristic;

import com.benoitkienan.tiles.Tile;

public class Mob extends Entity {
    AreaMap map;
    AStarHeuristic heuristic = new DiagonalHeuristic();
    AStar aStar;
    public ArrayList<Point> shortestPath;
    ArrayList<Point> path;
    Node node;
    Player nearestPlayer;
    Entity nearestEntity;
    int distanceX, distanceY;
    int searchZone = 20;

    public Mob(String name) {
	super(name);
	speed = 10;
    }

    public Mob(String name, String imgName) {
	super(name);
	speed = 10;
	try {
	    image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/" + imgName));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public boolean isInSearchZone() {
	if (getManhattanDistance(nearestPlayer.getPosX(), nearestPlayer.getPosY()) < searchZone) {
	    return true;
	} else {
	    return false;
	}
    }

    public void refreshPath(int bx, int by, Tile[][] array) {
	map = new AreaMap(array.length, array[0].length, array);
	aStar = new AStar(map, heuristic);
	shortestPath = aStar.calcShortestPath((int) (posX / panGame.cellSizeX), (int) (posY / panGame.cellSizeY), bx, by);

    }

    public void followPath() {
	if (shortestPath != null) {
	    if (shortestPath.size() != 0) {
		if (shortestPath.get(0).getX() > (int) (posX / panGame.cellSizeX)) {
		    addForceX(speed);
		} else if (shortestPath.get(0).getX() < (int) (posX / panGame.cellSizeX)) {
		    addForceX(-speed);
		}

		if (shortestPath.get(0).getY() < (int) (posY / panGame.cellSizeY)) {
		    addForceY(-speed);
		} else if (shortestPath.get(0).getY() > (int) (posY / panGame.cellSizeY)) {
		    addForceY(speed);
		}
	    }
	}

    }

    public Entity getNearestEntity(ArrayList<Entity> eList) {
	nearestEntity = eList.get(0);
	for (Entity entity : eList) {
	    if ((Math.abs(entity.getPosX() - posX) + Math.abs(entity.getPosY() - posY)) < (Math.abs(nearestEntity.getPosX() - posX) + Math.abs(nearestEntity.getPosY() - posY))) {
		nearestEntity = entity;
	    }
	}
	return nearestEntity;
    }

    public Player getNearestPlayer(ArrayList<Player> pList) {
	nearestPlayer = pList.get(0);
	for (Player player : pList) {
	    if ((Math.abs(player.getPosX() - posX) + Math.abs(player.getPosY() - posY)) < (Math.abs(nearestPlayer.getPosX() - posX) + Math.abs(nearestPlayer.getPosY() - posY))) {
		nearestPlayer = player;
	    }
	}
	return nearestPlayer;

    }

    public double getManhattanDistance(double posX2, double posY2) {
	double ManhattanDistance = Math.abs((posX2 - posX) / panGame.cellSizeX) + Math.abs((posY2 - posY) / panGame.cellSizeY);
	return ManhattanDistance;
    }

    public void goToNearestPlayer(ArrayList<Player> plist, Tile[][] array) {
	if (nearestPlayer != null) {
	    this.refreshPath((int) (this.nearestPlayer.getPosX() / panGame.cellSizeX), (int) (this.nearestPlayer.getPosY() / panGame.cellSizeY), array);
	}
    }

    public void goToNearestEntity(ArrayList<Entity> plist, Tile[][] array) {

	this.getNearestEntity(plist);
	if (distanceX < searchZone && distanceY < searchZone) {
	    if (nearestEntity != null)
		this.refreshPath((int) (this.nearestEntity.getPosX() / panGame.cellSizeX), (int) (this.nearestEntity.getPosY() / panGame.cellSizeY), array);
	}
    }

    public ArrayList<Point> getPath() {
	return shortestPath;
    }

    public void setPath(ArrayList<Point> path) {
	shortestPath = path;
    }

}
