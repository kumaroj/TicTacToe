package com.scaler.tictactoe.models;

import com.scaler.tictactoe.factories.BotPlayingStartergyFactory;
import com.scaler.tictactoe.stratergies.botplayingstrategy.BotPlayingStrategy;

import java.util.Scanner;

public class Bot extends Player{

    private BotDiffcultyLevel botDiffcultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String botname , char botsymbol, BotDiffcultyLevel botDiffcultyLevel){
        super(botname , botsymbol , PlayerType.BOT);
        this.botDiffcultyLevel = botDiffcultyLevel;
        this.botPlayingStrategy = BotPlayingStartergyFactory.getStrategyForDifficultyLevel(botDiffcultyLevel);
    }

    public BotDiffcultyLevel getBotDiffcultyLevel() {
        return botDiffcultyLevel;
    }

    public void setBotDiffcultyLevel(BotDiffcultyLevel botDiffcultyLevel) {
        this.botDiffcultyLevel = botDiffcultyLevel;
    }

    @Override
    public Move decideMove(Board board){
       return botPlayingStrategy.decideMove(this,board);
    }
}
