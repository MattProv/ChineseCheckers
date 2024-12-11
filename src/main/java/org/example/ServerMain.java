package org.example;

import org.example.message.LogMessageHandler;
import org.example.message.MessageType;
import org.example.message.serverHandlers.CommandMessageHandler;
import org.example.message.serverHandlers.MoveMessageHandler;
import org.example.server.GameManager;
import org.example.server.Server;

public class ServerMain {
    public static void main(String[] args) {

        System.out.println("Hello World from Server!");

        GameManager gameManager = new GameManager();
        gameManager.setBoard(new TestBoard());

        Server server = Server.create();

        for(MessageType type : MessageType.values()) {
            server.AddHandler(new LogMessageHandler(type));
        }
        server.AddHandler(new MoveMessageHandler(gameManager));
        server.AddHandler(new CommandMessageHandler(gameManager));
        server.Bind(Config.PORT);
        server.Listen();

        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
