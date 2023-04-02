package com.scaler.tictactoe.models;

import com.scaler.tictactoe.exceptions.InvalidGameConstructorParameterException;
import com.scaler.tictactoe.stratergies.gamedrawStrategy.CheckGameDraw;
import com.scaler.tictactoe.stratergies.gamedrawStrategy.GameDrawStrategy;
import com.scaler.tictactoe.stratergies.gamewinningstrategy.GameWinningStrategy;
import com.scaler.tictactoe.stratergies.gamewinningstrategy.OrderOneGameWinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {

    private Board board;
    private List<Move> moves;
    private List<Player> players;
    private int nextplayeridx;
    private  GameStatus gameStatus;
    private Player Winner;

    private GameWinningStrategy gameWinningStrategy;
    private GameDrawStrategy gameDrawStrategy;

    private int noOfTurns;

    public int getNoOfTurns() {
        return noOfTurns;
    }

    public void setNoOfTurns(int noOfTurns) {
        this.noOfTurns = noOfTurns;
    }

    public GameDrawStrategy getGameDrawStrategy() {
        return gameDrawStrategy;
    }

    public void setGameDrawStrategy(GameDrawStrategy gameDrawStrategy) {
        this.gameDrawStrategy = gameDrawStrategy;
    }

    public Player getWinner() {
        return Winner;
    }

    public void setWinner(Player winner) {
        Winner = winner;
    }

    private Game(){

    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNextplayeridx() {
        return nextplayeridx;
    }

    public void setNextplayeridx(int nextplayeridx) {
        this.nextplayeridx = nextplayeridx;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }


    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public void undo(){


        if (moves.size()>0){

            for(int i = 0; i< board.getDimension(); i++){
                for(int j = 0;j< board.getDimension(); j++){
                    this.board.getBoard().get(i).get(j).setCellstate(CellState.EMPTY);
                }
            }
            System.out.println("Game is in the Undo status");
            getBoard().display();

            //keep the list<Moves>
            //delete the last move

            if (moves.size()==1){
                moves.get(0).getCell().setCellstate(CellState.EMPTY);
                getGameWinningStrategy().removeUndoMove(moves.get(0).getCell() , board);
            }
            else{
                this.moves.remove(moves.size()-1);

                // redo all the moves in the game;
                for(Move undomove:moves){
                    undomove.getCell().setCellstate(CellState.FILLED);
                    getGameWinningStrategy().removeUndoMove(undomove.getCell() , board);
                }
            }

            noOfTurns--;
            nextplayeridx = nextplayeridx/players.size();
        }
    }

    public void makenextmove(){
         Player playetomakemove = players.get(nextplayeridx);

        System.out.println("Now it is "+players.get(nextplayeridx).getName()+"'s turn");
         Move   move = playetomakemove.decideMove(this.board);

        //validate Move

         int row  = move.getCell().getRow();
         int col  = move.getCell().getCol();

         System.out.println("Move Happened at "+ row +
                ","+col);

        this.board.getBoard().get(row).get(col).setCellstate(CellState.FILLED);
        this.board.getBoard().get(row).get(col).setPlayer(players.get(nextplayeridx));
        Move finalmove =  new Move(players.get(nextplayeridx) , this.board.getBoard().get(row).get(col));
        this.moves.add(finalmove);

        noOfTurns++;
        boolean isSomeoneWinner =  gameWinningStrategy.checkWinner(board,players.get(nextplayeridx),
              this.board.getBoard().get(row).get(col));

        if (isSomeoneWinner){
            setGameStatus(GameStatus.ENDED);
            setWinner(players.get(nextplayeridx));
        }
        if (!getGameStatus().equals(GameStatus.ENDED)){
            boolean isGameDraw =  gameDrawStrategy.checkDraw(noOfTurns , board.getDimension());
            if (isGameDraw)
                gameStatus =GameStatus.DRAW;

        }
        nextplayeridx++;
        nextplayeridx%= players.size();
    }

    public void displayBoard(){
       this.board.display();

    }
    public static Builder getBuilder(){
        Builder builder= new Builder();
        return builder;
    }

    public static  class Builder{

        private int dimensions;
        private List<Player> players;

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean valid() throws InvalidGameConstructorParameterException {
            if (this.dimensions<3)
                throw new InvalidGameConstructorParameterException("Dimension cannot be less than 3");
            if(this.players.size() != dimensions-1 )
                throw new InvalidGameConstructorParameterException("Players size must be equal to dimension size -1");

            // validate no two players have same symbol.

            // validate there is only one bot in the game;
            return true;
        }

        public Game build()  {

                try{
                    valid();
                }catch (InvalidGameConstructorParameterException e){
                    e.getMessage();
                }

                Game game = new Game();
                game.setGameStatus(GameStatus.IN_PROGRESS);
                game.setBoard(new Board(dimensions));
                game.setPlayers(players);
                game.setMoves(new ArrayList<>());
                game.setNextplayeridx(0);
                game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimensions));
                game.setGameDrawStrategy(new CheckGameDraw(0 , dimensions));


            return game;
        }
    }
}
