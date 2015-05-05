package com.benoitkienan.affichage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.benoitkienan.entities.Player;
import com.benoitkienan.jeu.IOLevel;
import com.benoitkienan.jeu.Moteur;
import com.benoitkienan.jeu.Niveau;

public class Fenetre extends JFrame {
    MenusBar menuBar = new MenusBar();
    ToolBar toolBar = new ToolBar();
    BorderLayout bl = new BorderLayout();
    Thread tPanel, tFen, tGame, tPhysics, tIA, tHud;
    IOLevel iolvl = new IOLevel();
    Math math;
    Niveau lvl;
    PanneauGame panGame = new PanneauGame();
    Hud hud = new Hud();
    Moteur mot = new Moteur(panGame);
    PanelThread panThread = new PanelThread(this, panGame, hud);
    char touche;
    JLayeredPane mainPan = new JLayeredPane();

    public Fenetre(String title) {

	this.setTitle(title);
	this.setSize(1280, 720);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	// mainPan.setLayout(bl);
	mainPan.add(hud, 101, 0);
	mainPan.add(panGame, 100, 1);
	// mainPan.add(toolBar, BorderLayout.NORTH);
	this.setContentPane(mainPan);
	this.setResizable(true);
	// this.setJMenuBar(menuBar);

	for (Player player : mot.PlayerList) {
	    this.addKeyListener(player);
	}

	this.setVisible(true);
	panGame.setBounds(0, 0, this.getWidth(), this.getHeight());

	this.requestFocus();

	tPanel = new Thread(new refreshPanel());
	tFen = new Thread(new fenThread());
	tGame = new Thread(new gameThread());
	tPhysics = new Thread(new applyPhysicsThread());
	tIA = new Thread(new IAThread());

	tFen.start();
	tGame.start();
	tPanel.start();
	tPhysics.start();
	tIA.start();

    }

    private void runFen() {
	while (true) {
	    panGame.setBounds(0, 0, this.getWidth(), this.getHeight());
	    hud.setBounds(400, panGame.getHeight() - 95, panGame.getWidth() - 800, 50);

	    this.requestFocus();
	    mot.setToolSelected(panGame.getToolSelected());
	    mot.setHudItems(hud.getHudItems());
	    hud.setToolSelected(panGame.getToolSelected());
	    mot.setPanneau(panGame);
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

    class IAThread implements Runnable {
	public void run() {
	    mot.runIA();
	}
    }

    class refreshPanel implements Runnable {
	public void run() {
	    panThread.goPanel();
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