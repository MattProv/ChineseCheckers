package org.example.client;

import org.example.message.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Client class used to connect to the server
 * and send/receive messages
 */
public class Client implements Runnable{

    //the socket used to connect to the server
    private Socket socket;

    //the output and input streams used to send and receive messages
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    //the thread used to listen for messages from the server
    private Thread listenerThread;

    //handlers for the different types of messages
    private Queue<MessageSenderPair> messageQueue = new LinkedList<>();
    private List<MessageHandler> messageHandlers =  new ArrayList<>();

    public Client()
    {

    }

    /**
     * Connects to the server
     * and starts the listener thread
     * @param host the host to connect to
     * @param port the port to connect to
     * @return true if the connection was successful, false otherwise
     */
    public boolean Connect(String host, int port)
    {
        try
        {
            socket = new Socket(host, port);

            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            listenerThread = new Thread(this);
            listenerThread.start();

            return true;
        }
        catch (UnknownHostException ex)
        {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * The listener thread that listens for messages from the server
     */
    @Override
    public void run() {
        try {
            Message msg;
            do
            {
                msg = read();
                messageQueue.add(new MessageSenderPair(msg, null));

            } while (!msg.getType().equals(MessageType.DISCONNECT));

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
    }

    /**
     * Disconnects from the server
     */
    public void Disconnect()
    {
        if(socket == null)
            return;
        if(socket.isConnected() == false)
            return;
        try
        {
            send(new DisconnectMessage());

            socket.close();
            socket = null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Sends a message to the server
     * @param message the message to send
     * @return true if the message was sent successfully, false otherwise
     *
     * @see Message
     */
    public <T extends Message> boolean send(T message)
    {
        try
        {
            oos.writeObject(message);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public Message read()
    {
        try
        {
            return (Message) ois.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void AddHandler(MessageHandler handler)
    {
        messageHandlers.add(handler);
    }

    public List<MessageHandler> getMessageHandlersOfType(MessageType type) {
        List<MessageHandler> handlers = new ArrayList<>();
        for (MessageHandler messageHandler : messageHandlers) {
            if(messageHandler.getMessageType() == type) {
                handlers.add(messageHandler);
            }
        }

        return handlers;
    }

    public void HandleMessages()
    {
        while(!messageQueue.isEmpty())
        {
            MessageSenderPair messageSenderPair = messageQueue.poll();

            for(MessageHandler messageHandler : getMessageHandlersOfType(messageSenderPair.getMessageType()))
            {
                messageHandler.handle(messageSenderPair);
            }
        }
    }
}