package edu.austral.prog2_2018c2;

public class Game implements Commons {

    protected int level;
    private final int  maxLevel = 5;
    protected  boolean invasion ;
    protected Score score;
    public Shield shield;

    public Game() {
        level = 1;
        invasion = false;
        score = RankingFile.leer();
    }

    public void nextLevel(Player player){
        level++;
        player.isGameWon(false);
        //shield.nextLevel();

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
//        score.setPlayer(player);
    }

    public static class SpecialPower extends Player {
        private Alien.Bomb alienShot;
        private boolean deadSum;
        private int alienDeadCount = 0;
        public int countingDeadAlien(){
            for(;alienShot.isDestroyed();){
                alienDeadCount += 1;
                if(alienDeadCount > 4){
                    alienDeadCount = 0;
                }
            }
            return alienDeadCount;
        }
        public boolean numberOfDeadAliens(){
            if(countingDeadAlien() == 4){
                return deadSum = true;
            }else if(countingDeadAlien() < 4){
                return deadSum = false;
            }
            return deadSum;
        }
        public void immunity(){
            setDying(false);
        }
        public void freeze(){
            Alien alienFreeze = new Alien(0,0);
        }
        public void doubleShot(){
            Shot aTwoShot = new Shot(x + 2, y);
        }
    }
}
