package com.benoitkienan.jeu.moteur;

import java.io.Serializable;
import java.util.Random;

public class Niveau implements Serializable{
	
	public int arraySizeX=64*1;
	public int arraySizeY=36*1;
	Random rand= new Random();
	int arrayLvl[][] = new int[arraySizeX][arraySizeY];
	
	public Niveau(){
		
	}
	
	
	public void createRandomLvl(){
		for(int x=0;x<arraySizeX;x++){
			for(int y=0;y<arraySizeY;y++){
					arrayLvl[x][y]=rand.nextInt(5);
			}
		}
	}
	
	   public void createEmptyLvl() {

	        for ( int x = 0; x < arraySizeX; x++ ) {
	            for ( int y = 0; y < arraySizeY; y++ ) {
	                arrayLvl[x][y] = 0;
	            }
	        }

	    }

	public void destroyTile(int x, int y){
		arrayLvl[x][y]=0;
	}
	
	public int[][] getArray(){
		return arrayLvl;
	}
	
	public void setArray(int[][] array){
		arrayLvl=array;
	}
	
	public int getArraySizeX(){
		return arraySizeX;
	}
	
	public int getArraySizeY(){
		return arraySizeY;
	}
}
