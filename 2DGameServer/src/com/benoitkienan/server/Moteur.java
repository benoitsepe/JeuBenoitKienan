package com.benoitkienan.server;

import java.net.InetAddress;
import java.net.ServerSocket;
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

            ServerSocket ss = new ServerSocket( port ); // ouverture
                                                        // d'un
                                                        // socket
                                                        // serveur
                                                        // sur port

            Fenetre fen = new Fenetre( ip, port );

            while ( true ) // attente en boucle de connexion (bloquant sur
                           // ss.accept)
            {
                new BlablaThread( ss.accept(), blablaServ ); // un client se
                                                             // connecte, un
                                                             // nouveau
                                                             // thread client
                                                             // est
                                                             // lancé
            }
        } catch ( UnknownHostException e ) {

            ip = "ERREUR DE CONNEXION";
        }
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
