package org.example;

import org.example.message.serverHandlers.CommandMessageHandler;
import org.example.message.serverHandlers.EchoMessageHandler;
import org.example.message.serverHandlers.MoveMessageHandler;
import org.example.server.Server;

public class ServerMain {
    public static void main(String[] args) {

        System.out.println("Hello World from Server!");

        Server server = Server.create();

        server.AddHandler(new EchoMessageHandler());
        server.AddHandler(new MoveMessageHandler());
        server.AddHandler(new CommandMessageHandler());
        server.Bind(Config.PORT);
        server.Listen();

        while (true) {
            server.HandleMessages();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
