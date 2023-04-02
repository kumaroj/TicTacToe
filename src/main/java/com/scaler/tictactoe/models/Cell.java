package com.scaler.tictactoe.models;

public class Cell {

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        this.cellstate = CellState.EMPTY;
    }
    private Player player;
    private int row;
    private int col;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellState getCellstate() {
        return cellstate;
    }

    public void setCellstate(CellState cellstate) {
        this.cellstate = cellstate;
    }

    private CellState cellstate;

}
