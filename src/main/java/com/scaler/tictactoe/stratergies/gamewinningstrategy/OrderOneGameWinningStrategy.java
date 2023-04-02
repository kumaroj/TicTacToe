package com.scaler.tictactoe.stratergies.gamewinningstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy {

    private List<HashMap<Character , Integer>>rowsymbolcount  = new ArrayList<>();
    private List<HashMap<Character, Integer>>colsymbolcount = new ArrayList<>();

    private HashMap<Character, Integer> topleftdaigsymbolcnt = new HashMap<Character, Integer>();
    private HashMap<Character, Integer> toprightdaigsymbolcnt = new HashMap<Character, Integer>();

    public OrderOneGameWinningStrategy(int dimension){

        for (int i = 0; i<dimension; i++){
            rowsymbolcount.add(new HashMap<>());
            colsymbolcount.add(new HashMap<>());
        }
    }

    private boolean isCellTopLeftDaigonal(int row , int col){
        if (row == col)
            return true;

        return false;
    }

    private boolean isCellTopRightDaigonal(int row , int col ,int dimension){
      if (row + col == dimension-1) {
          return true;
      }
          return false;
    }
  @Override
    public boolean checkWinner(Board board, Player lastmoveplayer, Cell movecell) {

        int row = movecell.getRow();
        int col = movecell.getCol();
        char symbol = movecell.getPlayer().getSymbol();


        if (rowsymbolcount.get(row).containsKey(symbol)){
            rowsymbolcount.get(row).put(symbol , rowsymbolcount.get(row).get(symbol)+1);
        }
        else
            rowsymbolcount.get(row).put(symbol , 1);

        if (colsymbolcount.get(col).containsKey(symbol)){
            colsymbolcount.get(col).put(symbol , colsymbolcount.get(col).get(symbol)+1);
        }
        else
           colsymbolcount.get(col).put(symbol , 1);

        if (isCellTopLeftDaigonal(row , col)){
            if (topleftdaigsymbolcnt.containsKey(symbol))
                topleftdaigsymbolcnt.put(symbol , topleftdaigsymbolcnt.get(symbol)+1);
            else
                topleftdaigsymbolcnt.put(symbol , 1);
        }

        if (isCellTopRightDaigonal(row , col, board.getDimension())){
            if (toprightdaigsymbolcnt.containsKey(symbol))
                toprightdaigsymbolcnt.put(symbol , toprightdaigsymbolcnt.get(symbol)+1);
            else
                toprightdaigsymbolcnt.put(symbol , 1);
        }

     if (rowsymbolcount.get(row).get(symbol) == board.getDimension()
        ||colsymbolcount.get(col).get(symbol) == board.getDimension()){
         return true;
     }
     else if (isCellTopLeftDaigonal(row , col)){
         if (topleftdaigsymbolcnt.get(symbol) == board.getDimension())
             return true;
     }
     else if (isCellTopRightDaigonal(row, col , board.getDimension())) {
         if (toprightdaigsymbolcnt.get(symbol) == board.getDimension())
            return true;
      }
       return  false;
    }

    @Override
    public void removeUndoMove(Cell undocell , Board board) {

        int row = undocell.getRow();
        int col = undocell.getCol();
        char symbol = undocell.getPlayer().getSymbol();

       if(isCellTopRightDaigonal(row, col ,board.getDimension()))
          toprightdaigsymbolcnt.put(symbol ,toprightdaigsymbolcnt.get(symbol)-1);

       if(isCellTopLeftDaigonal(row, col))
           topleftdaigsymbolcnt.put(symbol , topleftdaigsymbolcnt.get(symbol)-1);

       rowsymbolcount.get(row).put(symbol , rowsymbolcount.get(row).get(symbol)-1);
       colsymbolcount.get(col).put(symbol , colsymbolcount.get(col).get(symbol)-1);

    }


}
