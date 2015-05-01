package com.benoitkienan.jeu;

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
	ArrayList<Point> shortestPath;
	Node node;

	public Mob(Niveau niveau) {
		super(niveau);
		speed=2;
	}

	public void refreshPath(int bx, int by, int[][] array){
		map = new AreaMap(array.length, array[0].length, array);
		aStar = new AStar(map, heuristic);
		shortestPath = aStar.calcShortestPath((int)(posX/panGame.cellSizeX), (int)(posY/panGame.cellSizeY), bx, by);

	}

	public void followPath(){
		if(shortestPath!=null){
			System.out.println("pathX:"+shortestPath.get(0).getX()+" X:"+(int)(posX/panGame.cellSizeX));
			System.out.println("pathY:"+shortestPath.get(0).getY()+" Y:"+(int)(posY/panGame.cellSizeY));			

		if(shortestPath.get(0).getX() > (int)(posX/panGame.cellSizeX)){

			addForceX(speed);
		}
		else if(shortestPath.get(0).getX() < (int)(posX/panGame.cellSizeX)){
			addForceX(-speed);
		}
		
		if(shortestPath.get(0).getY() < (int)(posY/panGame.cellSizeY)){
			addForceY(-speed);
		}
		else if(shortestPath.get(0).getY() > (int)(posY/panGame.cellSizeY)){
			addForceY(speed);
		}
		}


	}

	public ArrayList<Point> getPath(){
		return shortestPath;
	}

	public void setPath(ArrayList<Point> path){
		shortestPath=path;
	}

}
