package org.example;

import org.example.client.Client;
import org.example.message.*;
import org.example.message.clientHandlers.MoveMessageHandler;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();

        // Add message handlers
        client.AddHandler(new MoveMessageHandler());

        boolean isConnected = false;

        System.out.println("Welcome to the Client Application!");

        while (true) {
            client.HandleMessages();
            System.out.println("\nMenu:");
            System.out.println("1. Connect to Server");
            System.out.println("2. Send Message");
            System.out.println("3. Disconnect from Server");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    if (isConnected) {
                        System.out.println("Already connected to a server!");
                    } else {
                        System.out.print("Enter server host: ");
                        String host = scanner.nextLine();
                        System.out.print("Enter server port: ");
                        int port;
                        try {
                            port = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid port number. Please try again.");
                            scanner.nextLine();
                            break;
                        }

                        if (client.Connect(host, port)) {
                            isConnected = true;
                            System.out.println("Connected to the server successfully.");
                        } else {
                            System.out.println("Failed to connect to the server.");
                        }
                    }
                    break;

                case 2:
                    if (!isConnected) {
                        System.out.println("You are not connected to a server. Please connect first.");
                    } else {
                        System.out.print("Enter the first part of your message: ");
                        String part1 = scanner.nextLine();
                        System.out.print("Enter the second part of your message: ");
                        String part2 = scanner.nextLine();

                        MoveMessage moveMessage = new MoveMessage(part1, part2);
                        if (client.send(moveMessage)) {
                            System.out.println("Message sent successfully.");
                        } else {
                            System.out.println("Failed to send the message.");
                        }
                    }
                    break;

                case 3:
                    if (isConnected) {
                        client.Disconnect();
                        isConnected = false;
                        System.out.println("Disconnected from the server.");
                    } else {
                        System.out.println("You are not connected to any server.");
                    }
                    break;

                case 4:
                    if (isConnected) {
                        client.Disconnect();
                    }
                    System.out.println("Exiting the application.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}
