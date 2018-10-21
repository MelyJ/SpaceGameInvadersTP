package edu.austral.prog2_2018c2;

import javax.swing.ImageIcon;

public class Game implements Commons {

    protected int level;
    private final int  maxLevel = 5;
    protected  boolean invasion ;

    public Game() {
        level = 1;
        invasion = false;
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
        return level >maxLevel;
    }
    public int getshotFrecuency()
    {
        return 15-level;
    }
    public long getInvadersVelocity(Long timeDiff){
       return DELAY - timeDiff-level*2;

    }

    public void esInvasion(Sprite player)
    {
        invasion = true;
        player.setDying(true);
    }

    public boolean isInvasion(){
        return  invasion;
    }

}
// problemas con la imagen ufo,haecr tablero y el escudo y reiniciar el juego