package com.scaler.tictactoe.controllers;

import com.scaler.tictactoe.models.Game;
import com.scaler.tictactoe.models.GameStatus;
import com.scaler.tictactoe.models.Player;

import java.util.List;

//It allows multiple actions to be taken on the game;
public class GameController {

    public Game createGame(int dimensions , List<Player>players){
        Game  game = Game.getBuilder().setDimensions(dimensions).setPlayers(players).build();
        return game;
    }

    public void undoGame(Game game){
        game.undo();
    }

    public void displayBoard(Game game){
        game.displayBoard();
    }
    public GameStatus getgameStatus(Game game){
        return game.getGameStatus();
    }

    public void executeNextMove(Game game){
        game.makenextmove();
    }

    public Player getWinner(Game game){

     return game.getWinner();
    }
}
