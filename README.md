
# Chinese Checkers

This project combines server- and client- side of Chinese Checkers game. It is being developed for Computer Science course and will be progressively updated with new requirements.



## Deployment
In Project directory, to execute client run:

```bash
java -cp target/classes org.example.ClientMain
```
To execute server run:

```bash
java -cp target/classes org.example.ServerMain
```

## Usage/Examples
If the **server** starts correctly, you will see:
```bash
> java -cp target/classes org.example.ServerMain

Hello World from Server!
Binding at port 8045
Server bound
```

Similarly, in the **client** application you will see this popup:
```bash
> java -cp target/classes org.example.ClientMain

Welcome to the Client Application!

Options:
1. Connect to server
2. Exit
Enter your choice:
```
you can then connect to the server:
```bash
Enter your choice: 1
Enter server host: localhost
Enter server port: 8045
Connected to the server successfully.
```
Once another client is connected (there are two players connected to the server), you can start the game:
```bash
Enter your choice: 1
Enter your message: /start_game
Message sent: COMMAND [start_game]
Command sent successfully.
Menu:
1. Send Message
2. Disconnect from Server
3. Exit

Message received: STRING Game started!

```
Once the game is started, your next message will be the move you want to perform, which will be synchronized with all clients:
```bash
Enter your choice: 1
Enter your message: a1 b2
Message sent: MOVE [a1, b2]
Move sent successfully.
Message received: GAMESTATE Game is running.
Last move: a1 -> b2
MOVES:
a1 -> b2
```

You can change the amount of players by using `/set_player_count ` command followed by the amount of players (default board allows 2, 3, 4 or 6 players).
``` bash
Enter your choice: 1
Enter your message: /set_player_count 2
```
## Features

- Connecting to the server
- Setting desired player count
- Starting the game
- Synchronizing mock up moves across the server to other clients


## Authors

- Mateusz Prowans   [@osicat10](https://github.com/osicat10) [@MattProv](https://github.com/MattProv)
- Wiktor Koczkodaj  [@WuKaKa95](https://github.com/WuKaKa95)

