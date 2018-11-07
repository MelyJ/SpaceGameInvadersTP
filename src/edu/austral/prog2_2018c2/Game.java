package edu.austral.prog2_2018c2;

public class Game implements Commons {

    protected int level;
    private final int  maxLevel = 5;
    protected  boolean invasion ;
    protected Score score;

    public Game() {
        level = 1;
        invasion = false;
        score = RankingFile.leer();
    }

    public void nextLevel(Player player){
        level++;
        player.nextLevel();
        player.isGameWon(false);
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

    public void isInvasion(Sprite player)
    {
        invasion = true;
        player.setDying(true);
    }

    public boolean isInvasion(){
        return  invasion;
    }
    public  void setScore(Player player){
        score.setPlayer(player);
    }

}
// hacer tablero y el escudo y reiniciar el juego
