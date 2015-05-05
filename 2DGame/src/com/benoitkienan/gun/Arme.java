package com.benoitkienan.gun;

public class Arme {

    String nom;
    double degat;
    String img;

    public Arme(String img, double degat, String nom) {

	this.img = img;
	this.nom = nom;
	this.degat = degat;
    }

    /**
     * @return the nom
     */
    public String getNom() {
	return nom;
    }

    /**
     * @param nom
     *            the nom to set
     */
    public void setNom(String nom) {
	this.nom = nom;
    }

    /**
     * @return the degat
     */
    public double getDegat() {
	return degat;
    }

    /**
     * @param degat
     *            the degat to set
     */
    public void setDegat(double degat) {
	this.degat = degat;
    }

    /**
     * @return the img
     */
    public String getImg() {
	return img;
    }

    /**
     * @param img
     *            the img to set
     */
    public void setImg(String img) {
	this.img = img;
    }

}
