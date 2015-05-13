package com.benoitkienan.affichage;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.benoitkienan.items.Item;
import com.benoitkienan.items.ItemManager;
import com.benoitkienan.tiles.Tile;
import com.benoitkienan.tiles.TileManager;

public class ToolBar extends JToolBar {

    TileManager tileManager = new TileManager();
    Tile toolSelected = tileManager.grass;
    int sliderValue = 5;
    ArrayList<JButton> buttonList = new ArrayList<JButton>();
    JSlider slider = new JSlider();

    public ToolBar() {

	slider.setMinimum(1);
	slider.setMaximum(10);
	slider.setValue(5);
	slider.setPaintTicks(true);
	slider.setPaintLabels(true);
	slider.setMajorTickSpacing(1);
	slider.setMaximumSize(new Dimension(250, 50));
	slider.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent event) {
		sliderValue = ((JSlider) event.getSource()).getValue();
	    }
	});

	for (final Tile tile : tileManager.getTileList()) {
	    buttonList.add(new JButton(new ImageIcon(tile.getImg().getScaledInstance(20, 20, Image.SCALE_DEFAULT))));
	    buttonList.get(buttonList.size() - 1).addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent a) {
		    toolSelected = tile;
		}
	    });
	    ;
	}

	for (JButton button : buttonList) {
	    this.add(button);
	}

	this.setFloatable(false);
	this.setVisible(true);

    }

    public Tile getToolSelected() {
	return toolSelected;
    }

    public int getSliderValue() {
	return sliderValue;
    }

}
