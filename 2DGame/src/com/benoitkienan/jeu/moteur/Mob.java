package com.benoitkienan.jeu.moteur;

import java.awt.Point;
import java.util.ArrayList;

import CDIO.pathFinder.AStar;
import CDIO.pathFinder.AreaMap;
import CDIO.pathFinder.Node;
import CDIO.pathFinder.heuristics.AStarHeuristic;
import CDIO.pathFinder.heuristics.DiagonalHeuristic;


public class Mob extends Entity{
    AreaMap map;
    AStarHeuristic heuristic = new DiagonalHeuristic();
    AStar aStar;
    private ArrayList<Point> shortestPath;
    ArrayList<Point> path;
    Node node;
    Player nearestPlayer;

    public Mob(Niveau niveau) {
	super(niveau);
	speed=2;
    }

    public void refreshPath(int bx, int by, int[][] array){
	map = new AreaMap(array.length, array[0].length, array);
	aStar = new AStar(map, heuristic);
	setShortestPath( aStar.calcShortestPath((int)(posX/panGame.getCellSizeX()), (int)(posY/panGame.getCellSizeY()), bx, by) );

    }

    public void followPath(){
	if(getShortestPath()!=null){	
	    if(getShortestPath().size()!=0){
		if(getShortestPath().get(0).getX() > (int)(posX/panGame.getCellSizeX())){
		    addForceX(speed);
		}
		else if(getShortestPath().get(0).getX() < (int)(posX/panGame.getCellSizeX())){
		    addForceX(-speed);
		}

		if(getShortestPath().get(0).getY() < (int)(posY/panGame.getCellSizeY())){
		    addForceY(-speed);
		}
		else if(getShortestPath().get(0).getY() > (int)(posY/panGame.getCellSizeY())){
		    addForceY(speed);
		}
	    }
	}

    }

    public Player getNearestPlayer(ArrayList<Player> plist, int[][] array){
	for(Player pl : plist){
	    map = new AreaMap(array.length, array[0].length, array);
	    aStar = new AStar(map, heuristic);
	    path = aStar.calcShortestPath((int)(posX/panGame.getCellSizeX()), (int)(posY/panGame.getCellSizeY()), (int)(pl.getPosX()/panGame.getCellSizeX()), (int)(pl.getPosY()/panGame.getCellSizeY()));

	    if(path!=null){
		if(getShortestPath()==null){
		    setShortestPath( path );
		}
		else if(getShortestPath().size() > path.size()){
		    nearestPlayer= pl;
		}


	    }
	}
	return nearestPlayer;
    }

    public void goToNearestPlayer(ArrayList<Player> plist, int[][] array){
	this.getNearestPlayer(plist, array);
	if(nearestPlayer!=null)
	    this.refreshPath((int)(this.nearestPlayer.getPosX()/panGame.getCellSizeX()), (int)(this.nearestPlayer.getPosY()/panGame.getCellSizeY()), array);
    }

    public ArrayList<Point> getPath(){
	return getShortestPath();
    }

    public void setPath(ArrayList<Point> path){
	setShortestPath( path );
    }

    /**
     * @return the shortestPath
     */
    public ArrayList<Point> getShortestPath() {
        return shortestPath;
    }

    /**
     * @param shortestPath the shortestPath to set
     */
    public void setShortestPath( ArrayList<Point> shortestPath ) {
        this.shortestPath = shortestPath;
    }

}
