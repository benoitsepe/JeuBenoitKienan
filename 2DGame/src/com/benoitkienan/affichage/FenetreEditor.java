package com.benoitkienan.affichage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.benoitkienan.editor.Moteur;
import com.benoitkienan.main.IOLevel;
import com.benoitkienan.niveau.Niveau;

public class FenetreEditor extends JFrame {
    MenusBar menuBar = new MenusBar();
    ToolBar toolBar = new ToolBar();
    BorderLayout bl = new BorderLayout();
    Thread tPanel, tFen, tGame, tPhysics, tHud;
    IOLevel iolvl = new IOLevel();
    Math math;
    Niveau lvl;
    PanneauGame panGame = new PanneauGame();
    OptionsMenu options = new OptionsMenu();
    Moteur mot = new Moteur(panGame);
    char touche;
    JLayeredPane mainPan = new JLayeredPane();
    int guiSize = 1;

    public FenetreEditor(String title) {

	this.setTitle(title);
	this.setSize(1280, 720);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	mainPan.add(options, 105, 0);
	mainPan.add(toolBar, 105, 0);
	mainPan.add(panGame, 100, 2);
	this.setContentPane(mainPan);
	this.setResizable(true);
	this.setJMenuBar(menuBar);

	this.addKeyListener(mot.camera);
	

	this.setVisible(true);
	panGame.setBounds(0, 0, this.getWidth(), this.getHeight());
	toolBar.setBounds(0, 0, this.getWidth(), 50);

	this.requestFocus();

	this.addKeyListener(new KeyListener() {

	    public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyChar() == KeyEvent.VK_ESCAPE && !options.isVisible()) {
		    options.openMenu();
		}
	    }

	    public void keyReleased(KeyEvent arg0) {

	    }

	    public void keyTyped(KeyEvent arg0) {

	    }

	});

	tPanel = new Thread(new refreshPanel());
	tFen = new Thread(new fenThread());
	tGame = new Thread(new gameThread());
	tPhysics = new Thread(new applyPhysicsThread());

	tFen.start();
	tGame.start();
	tPanel.start();
	tPhysics.start();

    }

    private void runFen() {
	while (true) {
	    panGame.setBounds(0, 0, this.getWidth(), this.getHeight());
	    options.setBounds((this.getWidth() / 2) - guiSize * (100), (this.getHeight() / 2) - guiSize * (100), guiSize * (200), guiSize * (200));

	    this.requestFocus();
	    mot.setPanneau(panGame);
	    mot.setToolSelected(toolBar.getToolSelected());

	    if (options.isNeedToQuit()) {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	    }

	    try {
		Thread.sleep(10);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}
    }
    
    public void goPanel() {

	while (true) {

	    panGame.repaint();

	    try {
		Thread.sleep(5);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}
    }
    

    class fenThread implements Runnable {
	public void run() {
	    runFen();
	}
    }

    class gameThread implements Runnable {
	public void run() {
	    mot.runGame();
	}
    }


    class refreshPanel implements Runnable {
	public void run() {
	    goPanel();
	}
    }

    class applyPhysicsThread implements Runnable {
	public void run() {
	    mot.motApplyPhysics();
	}
    }

    class MenusBar extends JMenuBar {
	IOLevel iolvl = new IOLevel();

	JMenu fichier = new JMenu("Fichier");
	JMenuItem save = new JMenuItem("Enregistrer");
	JMenuItem saveUnder = new JMenuItem("Enregistrer sous");
	JMenuItem ouvrir = new JMenuItem("Ouvrir");

	JMenu options = new JMenu("Options");
	JMenuItem surprise = new JMenuItem("surprise");

	public MenusBar() {
	    surprise.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent a) {
		    System.out.println("SURPRISE");
		}
	    });

	    ouvrir.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent a) {
		    try {
			mot.setNiveau(iolvl.openLvl("Pouet"));
		    } catch (NullPointerException e) {
			System.out.println("Ouverture annulée");
		    }
		}
	    });

	    save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent a) {
		    try {
			if (iolvl.doPathExist())
			    iolvl.saveLvl(mot.getNiveau());
			else {
			    iolvl.saveLvlUnder(mot.getNiveau());
			}
		    } catch (NullPointerException e) {
			System.out.println("Enregistrement annulé");
		    }

		}
	    });

	    saveUnder.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent a) {
		    try {
			iolvl.saveLvlUnder(mot.getNiveau());
		    } catch (NullPointerException e) {
			System.out.println("Enregistrement annulé");
		    }
		}
	    });

	    fichier.add(save);
	    fichier.add(saveUnder);
	    fichier.add(ouvrir);
	    options.add(surprise);
	    this.add(fichier);
	    this.add(options);

	}

    }

}