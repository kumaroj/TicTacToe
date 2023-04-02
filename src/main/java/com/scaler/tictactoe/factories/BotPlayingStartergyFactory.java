package com.scaler.tictactoe.factories;

import com.scaler.tictactoe.models.BotDiffcultyLevel;
import com.scaler.tictactoe.stratergies.botplayingstrategy.BotPlayingStrategy;
import com.scaler.tictactoe.stratergies.botplayingstrategy.RandomBotPlayingStrategy;

public class BotPlayingStartergyFactory {

    public static BotPlayingStrategy getStrategyForDifficultyLevel(BotDiffcultyLevel level){
       return new RandomBotPlayingStrategy();
    }
}
