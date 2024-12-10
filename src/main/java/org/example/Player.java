package org.example;

import org.example.server.ServerConnection;

public class Player {
    ServerConnection owner;

    public Player(ServerConnection owner) {
        this.owner = owner;
    }

    public ServerConnection getOwner() {
        return owner;
    }
}
