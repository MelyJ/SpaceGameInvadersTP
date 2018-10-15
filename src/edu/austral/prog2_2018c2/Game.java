package edu.austral.prog2_2018c2;

import javax.swing.ImageIcon;

public class Game implements Commons {

    protected int level;
    private final int  maxLevel = 5;

    public Game() {
        level = 1;
    }

    public void nextLevel(Player player){
        level++;
        player.nextLevel();
        player.ganoElNivel(false);
    }

    public int getLevel() {
        return level;
    }
    public boolean isGameWon(){
        return level > maxLevel;
    }
    public int getshotFrecuency()
    {
        return 15-level;
    }
    public long getInvadersVelocity(Long timeDiff){
       return DELAY - timeDiff-level*2;

    }
}
