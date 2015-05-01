package com.benoitkienan.jeu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ToolBar extends JToolBar {
	
	JButton eraserButton = new JButton(new ImageIcon("eraser.png"));
	JButton blueBrickButton = new JButton(new ImageIcon("blueBrick.png"));
	JButton redBrickButton = new JButton(new ImageIcon("redBrick.png"));
	JButton blackBrickButton = new JButton(new ImageIcon("blackBrick.png"));
	JButton goldBrickButton = new JButton(new ImageIcon("goldBrick.png"));
	JButton rainbow = new JButton(new ImageIcon("rainbow.png"));
	JSlider slider = new JSlider();

	int toolSelected;
	int sliderValue=5;
	
	
	public ToolBar(){
		
		slider.setMinimum(1);
		slider.setMaximum(10);
		slider.setValue(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		slider.setMaximumSize(new Dimension(250,50));
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent event){
				sliderValue=((JSlider) event.getSource()).getValue();
			}
		});
		
		
		eraserButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a) {
				toolSelected=0;
			}
		});
		
		blueBrickButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a) {
				toolSelected=1;
			}
		});
		
		redBrickButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a) {
				toolSelected=2;
			}
		});
		
		blackBrickButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a) {
				toolSelected=3;
			}
		});
		
		goldBrickButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a) {
				toolSelected=4;
			}
		});
		
		this.add(eraserButton);
		this.add(blueBrickButton);
		this.add(redBrickButton);
		this.add(blackBrickButton);
		this.add(goldBrickButton);
		this.add(slider);
		this.setFloatable(false);
		
		
	}
	
	public int getToolSelected(){
		return toolSelected;
	}
	
	public int getSliderValue(){
		return sliderValue;
	}
	
	
}
