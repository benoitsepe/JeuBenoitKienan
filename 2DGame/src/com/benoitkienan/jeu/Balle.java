package com.benoitkienan.jeu;

public class Balle {

    double posX;
    double posY;

    int type; // Type de balle (pistolet, mitraillette, lance-roquete)
    double degats;

    public Balle(double posX, double posY, int type, double degats) {

	this.posX = posX;
	this.posY = posY;
	this.type = type;
	this.degats = degats;
    }

    /**
     * @return the posX
     */
    public double getPosX() {
	return posX;
    }

    /**
     * @param posX
     *            the posX to set
     */
    public void setPosX(double posX) {
	this.posX = posX;
    }

    /**
     * @return the posY
     */
    public double getPosY() {
	return posY;
    }

    /**
     * @param posY
     *            the posY to set
     */
    public void setPosY(double posY) {
	this.posY = posY;
    }

    /**
     * @return the type
     */
    public int getType() {
	return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(int type) {
	this.type = type;
    }

    /**
     * @return the degats
     */
    public double getDegats() {
	return degats;
    }

    /**
     * @param degats
     *            the degats to set
     */
    public void setDegats(double degats) {
	this.degats = degats;
    }

}
