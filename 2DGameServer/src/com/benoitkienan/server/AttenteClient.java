package com.benoitkienan.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class AttenteClient {

    static Socket      soc;
    public int         port;

    LinkedList<Client> clients;

    public AttenteClient( int port, LinkedList<Client> clients )
    {
        this.port = port;
        this.clients = clients;

        ServerSocket s;

        try {
            s = new ServerSocket( port );

            System.out.println( "Socket serveur: " + s );

            soc = s.accept();

            System.out.println( "Serveur a accepte connexion: " + soc );

        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * @return the soc
     */
    public static Socket getSoc() {
        return soc;
    }

    /**
     * @param soc
     *            the soc to set
     */
    public void setSoc( Socket soc ) {
        this.soc = soc;
    }

}
