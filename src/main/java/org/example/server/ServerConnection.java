package org.example.server;

import org.example.message.Message;
import org.example.message.MessageSenderPair;
import org.example.message.MessageType;

import java.io.*;
import java.net.Socket;

/**
 * ConnectionThread class used to handle the connection with client on the server
 * and send/receive messages
 */
public class ServerConnection implements Runnable
{
    final Socket socket; // the socket used to connect to the client
    final Server server; // the server that created this connection

    // the output and input streams used to send and receive messages
    ObjectOutputStream oos;
    ObjectInputStream ois;

    // the thread used to listen for messages from the client
    Thread listenerThread;

    /**
     * Constructor for the ConnectionThread class
     * Sets the values, creates the output and input streams and starts the listener thread
     * @param socket the socket used to connect to the client
     */
    ServerConnection(Socket socket)
    {
        this.socket = socket;
        this.server = Server.getServer();

        try
        {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            listenerThread = new Thread(this);
            listenerThread.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Sends a message to the client
     * @param message the message to send
     * @see Message
     */
    public <T extends Message> void send(T message) throws IOException
    {
        oos.writeObject(message);
    }

    /**
     * Reads a message from the client
     * @return the message read
     * @see Message
     */
    @SuppressWarnings("unchecked")
    public <T extends Message> T read() throws ClassNotFoundException, IOException
    {
        return (T) ois.readObject();
    }

    /**
     * The listener thread that listens for messages from the client
     */
    @Override
    public void run() {
        try {
            Message msg;
            do {
                msg = read();

                synchronized(server)
                {
                    server.messageQueue.add(new MessageSenderPair(msg, this));
                }
            } while (server.running && !msg.getType().equals(MessageType.DISCONNECT));

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        server.Disconnect(this);
    }
}
