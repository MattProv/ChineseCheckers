package org.example;

import org.example.message.EchoMessageHandler;
import org.example.server.Server;

public class ServerMain {
    public static void main(String[] args) {

        System.out.println("Hello World from Server!");

        Server server = Server.create();

        server.AddHandler(new EchoMessageHandler());

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
