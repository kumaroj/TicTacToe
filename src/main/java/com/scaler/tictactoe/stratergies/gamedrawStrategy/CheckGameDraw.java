package com.scaler.tictactoe.stratergies.gamedrawStrategy;

public class CheckGameDraw implements  GameDrawStrategy{


    private  int noofTurns;
    private int dimensions;
    public CheckGameDraw(int noofTurns , int dimensions){
        this.noofTurns = noofTurns;
        this.dimensions = dimensions;
    }
    @Override
    public boolean checkDraw(int noofTurns , int dimensions) {
        return noofTurns == dimensions*dimensions;
    }
}
