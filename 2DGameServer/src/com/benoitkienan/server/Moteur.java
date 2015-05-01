package com.benoitkienan.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class Moteur {

    public final int   port    = 16540;

    Thread             tConnexions;

    LinkedList<Client> clients = new LinkedList<>();

    public Moteur() {

        InetAddress LocaleAdresse;
        String ip;

        try {

            LocaleAdresse = InetAddress.getLocalHost();
            ip = LocaleAdresse.getHostAddress();

        } catch ( UnknownHostException e ) {

            ip = "ERREUR DE CONNEXION";
        }

        Fenetre fen = new Fenetre( ip, port );

        tConnexions = new Thread( new Clients() );
        tConnexions.start();

    }

    class Clients implements Runnable {
        public void run() {

            try {
                AttenteClient attClients = new AttenteClient( port, clients );
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

}
