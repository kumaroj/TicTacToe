package com.scaler.tictactoe.models;

import java.util.Scanner;

public class Player {

    private char symbol;
    private String name;
    private PlayerType type;

    public Player(String name , char symbol , PlayerType type){
        this.name = name;
        this.symbol = symbol;
        this.type = type;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public Move decideMove(Board board){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the row number , starting from 0");
        int row  = scanner.nextInt();
        System.out.println("Please enter the col number, starting from 0");
        int col = scanner.nextInt();

        Move move = new Move(this , new Cell(row,col));
        return  move;
    }
}
