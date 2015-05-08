package com.benoitkienan.entities;

import java.util.ArrayList;

public class EntitiesManager {
    ArrayList<Entity> EntityList;
    ArrayList<Player> PlayerList;
    ArrayList<Mob> MobList;
    Player player;
    Mob mob1,mob2;

    public EntitiesManager() {

	
	player = new Player("Joueur", "personnage.png");
	mob1 = new Mob("Cage", "sorcier.png");
	mob2 = new Mob("CAGE", "CAGE.png");

	EntityList = new ArrayList<Entity>();
	PlayerList = new ArrayList<Player>();
	MobList = new ArrayList<Mob>();

	EntityList.add(player);
	EntityList.add(mob1);
	EntityList.add(mob2);
	
	for(Entity ent : EntityList){
	    if(ent instanceof Mob){
		MobList.add((Mob) ent);
	    }if(ent instanceof Player){
		PlayerList.add((Player) ent);
	    }
	}



    }

    public ArrayList<Entity> getEntityList() {
	return EntityList;
    }
    
    public ArrayList<Mob> getMobList(){
	return MobList;
    }
    
    public ArrayList<Player> getPlayerList(){
	return PlayerList;
    }
    
    
    
    public void addMob(Mob mob){
	EntityList.add(mob);
	MobList.add(mob);
    }
    
    public void addPlayer(Player player){
	EntityList.add(player);
	PlayerList.add(player);
    }


}
