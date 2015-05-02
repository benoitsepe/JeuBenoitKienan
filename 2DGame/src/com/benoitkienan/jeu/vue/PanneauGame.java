package com.benoitkienan.jeu.vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import CDIO.pathFinder.Node;

import com.benoitkienan.jeu.moteur.Mob;
import com.benoitkienan.jeu.moteur.Niveau;
import com.benoitkienan.jeu.moteur.Player;

public class PanneauGame extends JPanel implements MouseListener {
    Color             color      = Color.white;
    Niveau            lvl        = new Niveau();
    int               pointeurX, pointeurY;
    double            realPointeurX, realPointeurY;
    private double    cellSizeX;
    private double    cellSizeY;
    boolean           clicGauche, clicDroit;
    private boolean   clicMiddle;
    BufferedImage     blueBrick, redBrick, blackBrick, goldBrick;
    Dimension         dim        = new Dimension( 1280, 720 );

    ArrayList<Mob>    MobList    = new ArrayList<>();
    ArrayList<Player> PlayerList = new ArrayList<>();

    double            rotation   = 0;
    AffineTransform   rot        = new AffineTransform();
    AffineTransform   tx;
    AffineTransformOp op;
    Image             img;
    Node              node;
    Graphics2D        g2;
    int               zoom       = 1;
    int               xMin, xMax, yMin, yMax;

    public PanneauGame( Color couleur ) {

        try {
            blueBrick = ImageIO.read( new File( "Pictures/blueBrick.png" ) );
            redBrick = ImageIO.read( new File( "Pictures/redBrick.png" ) );
            blackBrick = ImageIO.read( new File( "Pictures/blackBrick.png" ) );
            goldBrick = ImageIO.read( new File( "Pictures/goldBrick.png" ) );

        } catch ( IOException e ) {
            e.printStackTrace();
        }

        color = couleur;

        this.addMouseListener( this );
        this.addMouseMotionListener( new MouseMotionListener() {
            public void mouseDragged( MouseEvent e ) {
                realPointeurX = ( e.getX() );
                realPointeurY = ( e.getY() );
                pointeurX = ( e.getX() / (int) getCellSizeX() );
                pointeurY = ( e.getY() / (int) getCellSizeY() );
            }

            public void mouseMoved( MouseEvent e ) {
                realPointeurX = ( e.getX() );
                realPointeurY = ( e.getY() );
                pointeurX = ( e.getX() / (int) getCellSizeX() );
                pointeurY = ( e.getY() / (int) getCellSizeY() );
            }

        } );

        setCellSizeX( ( 1280 / lvl.getArraySizeX() ) * zoom );
        setCellSizeY( ( 720 / lvl.getArraySizeY() ) * zoom );
    }

    public void paintComponent( Graphics g ) {

        setCellSizeX( ( 1280 / lvl.getArraySizeX() ) * zoom );
        setCellSizeY( ( 720 / lvl.getArraySizeY() ) * zoom );
        System.out.println( getCellSizeX() );
        System.out.println( PlayerList.get( 0 ).getPosX() );

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor( color );
        g2.fillRect( 0, 0, this.getWidth(), this.getHeight() );

        // g2.translate((-PlayerList.get(0).getPosX()+this.getWidth()/2),
        // (-PlayerList.get(0).getPosY()+this.getHeight()/2));

        // Création quadrillage

        /*
         * g.setColor(Color.black); for(int y=0; y<this.getHeight();
         * y=y+(this.getHeight()/lvl.getArraySizeY())){ g.drawLine(0, y,
         * this.getWidth(), y); }
         * 
         * for(int x=0;
         * x<this.getWidth();x=x+(this.getWidth()/lvl.getArraySizeX())){
         * g.drawLine(x,0,x,this.getHeight()); }
         */
        // Fin création quadrillage

        int truc = 50;
        if ( ( ( PlayerList.get( 0 ).getPosX() - getCellSizeX() * truc ) / getCellSizeX() ) < 0 ) {
            xMin = 0;
        } else {
            xMin = (int) ( ( PlayerList.get( 0 ).getPosX() - getCellSizeX() * truc ) / getCellSizeX() );
        }

        if ( ( ( PlayerList.get( 0 ).getPosX() + getCellSizeX() * truc ) / getCellSizeX() ) > lvl.getArraySizeX() ) {
            xMax = lvl.getArraySizeX();
        } else {
            xMax = (int) ( ( PlayerList.get( 0 ).getPosX() + getCellSizeX() * truc ) / getCellSizeX() );
        }

        if ( ( ( PlayerList.get( 0 ).getPosY() - getCellSizeY() * truc ) / getCellSizeY() ) < 0 ) {
            yMin = 0;
        } else {
            yMin = (int) ( ( PlayerList.get( 0 ).getPosY() - getCellSizeY() * truc ) / getCellSizeY() );
        }

        if ( ( ( PlayerList.get( 0 ).getPosY() + getCellSizeY() * truc ) / getCellSizeY() ) > lvl.getArraySizeY() ) {
            yMax = lvl.getArraySizeY();
        } else {
            yMax = (int) ( ( PlayerList.get( 0 ).getPosY() + getCellSizeY() * truc ) / getCellSizeY() );
        }

        for ( int x = xMin; x < xMax; x++ ) {
            for ( int y = yMin; y < yMax; y++ ) {
                if ( lvl.getArray()[x][y] == 0 ) {
                    g2.setColor( Color.gray );
                    g2.fillRect( x * (int) getCellSizeX(), y * (int) getCellSizeY(), (int) getCellSizeX(),
                            (int) getCellSizeY() );
                }
                if ( lvl.getArray()[x][y] == 1 ) {
                    g2.drawImage( blueBrick, x * (int) getCellSizeX(), y * (int) getCellSizeY(), (int) getCellSizeX(),
                            (int) getCellSizeY(), this );
                }

                if ( lvl.getArray()[x][y] == 2 ) {
                    g2.drawImage( redBrick, x * (int) getCellSizeX(), y * (int) getCellSizeY(), (int) getCellSizeX(),
                            (int) getCellSizeY(), this );

                }

                if ( lvl.getArray()[x][y] == 3 ) {
                    g2.drawImage( goldBrick, x * (int) getCellSizeX(), y * (int) getCellSizeY(), (int) getCellSizeX(),
                            (int) getCellSizeY(), this );
                }

                if ( lvl.getArray()[x][y] == 4 ) {
                    g2.drawImage( blackBrick, x * (int) getCellSizeX(), y * (int) getCellSizeY(), (int) getCellSizeX(),
                            (int) getCellSizeY(), this );
                }

            }
        }

        g2.setColor( Color.green );

        for ( Mob mob : MobList ) {
            if ( mob.getPath() != null ) {
                for ( int i = 0; i < mob.getShortestPath().size(); i++ ) {
                    g2.fillRect( (int) ( mob.getPath().get( i ).getX() * getCellSizeX() ), (int) ( mob.getPath()
                            .get( i ).getY() * getCellSizeY() ), (int) getCellSizeX(), (int) getCellSizeY() );
                }
            }
            g2.drawImage( mob.getImage(), (int) mob.getPosX(), (int) mob.getPosY(), (int) getCellSizeX(),
                    (int) getCellSizeY(), this );
        }

        for ( Player player : PlayerList ) {
            g2.drawImage(
                    rotate( player.getImage(), (int) getCellSizeX(), (int) getCellSizeY(),
                            player.getRotationWithMouse( realPointeurX, realPointeurY ) ), (int) player.getPosX(),
                    (int) player.getPosY(), (int) getCellSizeX(), (int) getCellSizeY(), this );

        }

        // Dessin de vecteurs

        // if((int)ent.vectorX!=0 || (int)ent.vectorY!=0){
        // ((Graphics2D) g2).setStroke(new BasicStroke(5));
        // g2.setColor(Color.BLUE);
        // g2.drawLine((int)(ent.getPosX()+cellSizeX/2),
        // (int)(ent.getPosY()+cellSizeY/2),
        // (int)((ent.getPosX()+cellSizeX/2)+ent.vectorX*10),
        // (int)((ent.getPosY()+cellSizeY/2)+ent.vectorY*10));
        // }

        g2.setColor( Color.RED );
        g2.setStroke( new BasicStroke( 2 ) );
        g2.drawRect( pointeurX * (int) getCellSizeX(), pointeurY * (int) getCellSizeY(), (int) getCellSizeX(),
                (int) getCellSizeY() );

    }

    public static BufferedImage rotate( BufferedImage img, int cellSizeX, int cellSizeY, double rotation ) {
        int w = cellSizeX;
        int h = cellSizeY;
        BufferedImage newImage = new BufferedImage( cellSizeX, cellSizeY, img.getType() );
        Graphics2D g2 = newImage.createGraphics();
        g2.rotate( rotation, h / 2, w / 2 );
        g2.drawImage( img, 0, 0, cellSizeX, cellSizeY, null );
        return newImage;
    }

    public Dimension getDim() {
        return dim;
    }

    public void setNiveau( Niveau niv ) {
        lvl = niv;
    }

    public void setMobList( ArrayList<Mob> mo ) {
        MobList = mo;
    }

    public void setPlayerList( ArrayList<Player> play ) {
        PlayerList = play;
    }

    public double getRealPointeurX() {
        return realPointeurX;
    }

    public double getRealPointeurY() {
        return realPointeurY;
    }

    public int getPointeurX() {
        return pointeurX;
    }

    public int getPointeurY() {
        return pointeurY;
    }

    public boolean getClicGauche() {
        return clicGauche;
    }

    public boolean getClicDroit() {
        return clicDroit;
    }

    public boolean getClicMiddle() {
        return isClicMiddle();
    }

    public void mouseClicked( MouseEvent e ) {

    }

    public void mouseEntered( MouseEvent e ) {

    }

    public void mouseExited( MouseEvent e ) {
        clicGauche = false;
        clicDroit = false;
        setClicMiddle( false );
    }

    public void mousePressed( MouseEvent e ) {

        if ( e.getButton() == 1 )
            clicGauche = true;

        if ( e.getButton() == 3 )
            clicDroit = true;

        if ( e.getButton() == 2 )
            setClicMiddle( true );

    }

    public void mouseReleased( MouseEvent arg0 ) {
        clicGauche = false;
        clicDroit = false;
        setClicMiddle( false );
    }

    /**
     * @return the cellSizeX
     */
    public double getCellSizeX() {
        return cellSizeX;
    }

    /**
     * @param cellSizeX
     *            the cellSizeX to set
     */
    public void setCellSizeX( double cellSizeX ) {
        this.cellSizeX = cellSizeX;
    }

    /**
     * @return the cellSizeY
     */
    public double getCellSizeY() {
        return cellSizeY;
    }

    /**
     * @param cellSizeY
     *            the cellSizeY to set
     */
    public void setCellSizeY( double cellSizeY ) {
        this.cellSizeY = cellSizeY;
    }

    /**
     * @return the clicMiddle
     */
    public boolean isClicMiddle() {
        return clicMiddle;
    }

    /**
     * @param clicMiddle
     *            the clicMiddle to set
     */
    public void setClicMiddle( boolean clicMiddle ) {
        this.clicMiddle = clicMiddle;
    }

}
