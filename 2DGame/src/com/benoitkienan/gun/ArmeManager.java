package com.benoitkienan.gun;

import java.util.ArrayList;

public class ArmeManager {
    ArrayList<Arme> ArmeList;
    public Arme pistolet, fusil, mitraillette;

    public ArmeManager() {

	pistolet = new Arme("pistolet.png", 1.0, "pistolet");
	fusil = new Arme("fusil.png", 10.0, "fusil");
	mitraillette = new Arme("mitraillette.png", 2.0, "mitraillette");

	ArmeList = new ArrayList<Arme>();

	ArmeList.add(pistolet);
	ArmeList.add(fusil);
	ArmeList.add(mitraillette);

    }

    public ArrayList<Arme> getArmeList() {
	return ArmeList;
    }

}
