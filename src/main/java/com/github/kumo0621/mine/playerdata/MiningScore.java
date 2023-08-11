package com.github.kumo0621.mine.playerdata;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class MiningScore {


    public static void increaseBreakScore(Player player, int amount) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();

        Objective objective = scoreboard.getObjective("break");

        Score score = objective.getScore(player.getName());
        score.setScore(score.getScore() + amount);
    }

    public static int getBreakScore(Player player) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();

        Objective objective = scoreboard.getObjective("break");

        Score score = objective.getScore(player.getName());
        return score.getScore();
    }

}
