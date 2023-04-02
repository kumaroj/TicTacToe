package com.scaler.tictactoe.stratergies.gamewinningstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.Player;

public interface GameWinningStrategy {

    public boolean checkWinner(Board board , Player lastmoveplayer, Cell movecell);

    public void removeUndoMove(Cell Undocell , Board board);
}
