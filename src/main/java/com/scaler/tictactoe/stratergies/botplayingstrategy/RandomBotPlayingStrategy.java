package com.scaler.tictactoe.stratergies.botplayingstrategy;

import com.scaler.tictactoe.models.*;

import java.util.ArrayList;

public class RandomBotPlayingStrategy implements  BotPlayingStrategy {
    @Override
    public Move decideMove(Player player, Board board) {
        for (int i = 0; i < board.getBoard().size(); i++) {
            for (int j = 0; j < board.getBoard().size(); j++) {
                if (board.getBoard().get(i).get(j).getCellstate().equals(CellState.EMPTY)) {
//                    Cell  cell= new Cell(i , j);
//                    board.getBoard().get(i).get(j).setCellstate(CellState.FILLED);
//                    board.getBoard().add(cell);
                 return new Move(player, new Cell(i, j));
                }
            }
        }
       return null;
    }


}
