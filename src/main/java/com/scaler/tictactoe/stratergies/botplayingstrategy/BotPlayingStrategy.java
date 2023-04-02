package com.scaler.tictactoe.stratergies.botplayingstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Move;
import com.scaler.tictactoe.models.Player;

public interface BotPlayingStrategy {

    public Move decideMove(Player player, Board board);
}
