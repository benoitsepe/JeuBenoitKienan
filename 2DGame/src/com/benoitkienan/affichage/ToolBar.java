package com.benoitkienan.affichage;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import com.benoitkienan.items.Item;
import com.benoitkienan.items.ItemManager;
import com.benoitkienan.tiles.Tile;
import com.benoitkienan.tiles.TileManager;

public class ToolBar extends JToolBar {
    
    TileManager tileManager = new TileManager();
    Tile toolSelected = tileManager.grass;
    int sliderValue = 1;
    ArrayList<JButton> buttonList = new ArrayList<JButton>();
    

    public ToolBar() {
	
	for(final Tile tile : tileManager.getTileList()){
	    buttonList.add(new JButton(new ImageIcon(tile.getImg().getScaledInstance(20, 20, Image.SCALE_DEFAULT))));
	    buttonList.get(buttonList.size() - 1).addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent a){
		    toolSelected=tile;
		}
	    });;
	}
	
	for(JButton button : buttonList){
	    this.add(button);
	}
	
	


	
	this.setFloatable(false);
	this.setVisible(true);

    }

    public Tile getToolSelected() {
	return toolSelected;
    }

}
