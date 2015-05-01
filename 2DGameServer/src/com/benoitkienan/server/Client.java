package com.benoitkienan.server;

import java.net.Socket;

public class Client {

    static int compteur_id = 0;
    int        id;
    Socket     soc;

    public Client( Socket soc ) {
        this.soc = soc;

        id = compteur_id++;
    }

    /**
     * @return the soc
     */
    public Socket getSoc() {
        return soc;
    }

    /**
     * @param soc
     *            the soc to set
     */
    public void setSoc( Socket soc ) {
        this.soc = soc;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

}
