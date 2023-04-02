package org.example;

import com.scaler.tictactoe.controllers.GameController;
import com.scaler.tictactoe.models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeGame {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        //it will take input dimensons, players
        GameController gamecontroller = new GameController();
        Game game = null;

        System.out.println("Please enter the dimension of the game");
        int dimension = scanner.nextInt();


        int toiterate = 0;
        System.out.println("Please enter if there is any BOT? y/n");
        String isBotString = scanner.next();
        if (isBotString.equals("y")){
            toiterate = dimension - 2;
        }
        else
            toiterate = dimension - 1;

        ArrayList<Player> listofPlayers = new ArrayList<Player>();

        for (int i = 0; i<toiterate; i++){
            System.out.println("Enter the name of the player");
            String playerName = scanner.next();
            System.out.println("Enter the playersymbol");
            String playerSymbol = scanner.next();

          Player player = new Player(playerName , playerSymbol.charAt(0), PlayerType.HUMAN);
            listofPlayers.add(player);
        }

        if (isBotString.equals("y")){
            System.out.println("Enter the name of the BOT");
            String playerName = scanner.next();
            System.out.println("Enter the BOTsymbol");
            String playerSymbol = scanner.nextLine();
            Player player = new Bot(playerName , playerSymbol.charAt(0), BotDiffcultyLevel.EASY);
            listofPlayers.add(player);
        }

       game = gamecontroller.createGame(dimension ,listofPlayers);

        while(gamecontroller.getgameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("This is the current board");
            gamecontroller.displayBoard(game);

            System.out.println("Does anyone want to undo?y/n");
             String ip = scanner.next();

            if (ip.equals("y")) {
                gamecontroller.undoGame(game);
            } else {
                gamecontroller.executeNextMove(game);
            }
        }

            if (!game.getGameStatus().equals(GameStatus.DRAW)){
                System.out.println("The Winner is "+gamecontroller.getWinner(game).getName());
            }
            else
                System.out.println("The Game Ended with a draw");

        }

}