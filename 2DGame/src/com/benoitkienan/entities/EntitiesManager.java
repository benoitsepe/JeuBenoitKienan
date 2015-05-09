package com.benoitkienan.entities;

import java.util.ArrayList;

import com.benoitkienan.affichage.PanneauGame;
import com.benoitkienan.niveau.Niveau;

public class EntitiesManager {
    ArrayList<Entity> EntityList = new ArrayList<Entity>();
    ArrayList<Player> PlayerList = new ArrayList<Player>();
    ArrayList<Mob> MobList = new ArrayList<Mob>();
    Player player;
    Mob mob1, mob2;

    public EntitiesManager() {

	player = new Player("Joueur", "personnage.png");
	mob1 = new Mob("Cage", "sorcier.png");
	mob2 = new Mob("CAGE", "CAGE.png");

	EntityList.add(player);
	// EntityList.add(mob1);
	// EntityList.add(mob2);

	for (Entity ent : EntityList) {
	    if (ent instanceof Mob) {
		MobList.add((Mob) ent);
	    }
	    if (ent instanceof Player) {
		PlayerList.add((Player) ent);
	    }
	}

    }

    public ArrayList<Entity> getEntityList() {
	return EntityList;
    }

    public ArrayList<Mob> getMobList() {
	return MobList;
    }

    public ArrayList<Player> getPlayerList() {
	return PlayerList;
    }

    public void addMob(String name, int posX, int posY, Niveau lvl, PanneauGame panGame) {
	EntityList.add(new Mob(name + (EntityList.size())));
	EntityList.get(EntityList.size() - 1).setImage("nyan.png");
	EntityList.get(EntityList.size() - 1).spawnAt(posX, posY);
	EntityList.get(EntityList.size() - 1).setNiveau(lvl);
	EntityList.get(EntityList.size() - 1).setPanneauGame(panGame);
	MobList.add((Mob) EntityList.get(EntityList.size() - 1));

	// Mob mob5 = new Mob(name);
	// mob5.spawnAt(posX, posY);
	// mob5.setNiveau(lvl);
	// mob5.setPanneauGame(panGame);
	// EntityList.add(mob5);
	// MobList.add(mob5);
    }

    public void addPlayer(Player player) {
	EntityList.add(player);
	PlayerList.add(player);
    }

}
