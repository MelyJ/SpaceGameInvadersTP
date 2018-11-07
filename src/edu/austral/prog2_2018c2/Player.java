package edu.austral.prog2_2018c2;


import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons {
    private  int points;
    private final int START_Y = 280;
    private final int START_X = 270;
    public int lives=3;
    private final String playerImg = "src/images/player.png";
    private int width;
    private int shield;
    private int shieldPercentage;
    private int shotsReceived;
    protected boolean gameWon;
    Date date;

    public Player() {

        initPlayer();
    }

    private void initPlayer() {
        gameWon = false;

        shield = 4;
        initShieldPercentage();
        ImageIcon ii = new ImageIcon(playerImg);
        width = ii.getImage().getWidth(null);
        points = 0;
        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }

    public void initShieldPercentage(){
        shieldPercentage = 100;
    }

    public void setShield(int shield){

        this.shield = shield;
    }

    public int getShield(){
        return shield;

    }

    public int getShieldPercentage(){
        return shieldPercentage;
    }

    public void setShieldPercentage(int shieldPercentage){
        this.shieldPercentage = shieldPercentage;
    }

    public void setLives(int lives){
        this.lives= lives;
    }

    public int getLives() {
        return lives;
    }

    public void substractLives(){
        lives = lives-1;
    }

    public void act() {

        x += dx;

        if (x <= 2) {
            x = 2;
        }

        if (x >= BOARD_WIDTH - 2 * width) {
            x = BOARD_WIDTH - 2 * width;
        }

    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }

    }
    public void setPoints(int points){
        this.points = points;

    }
    public int getPoints(){
        return points;
    }

    public void addPoints(int points){
        this.points += points;

    }

    public int getShotsReceived() {
        return shotsReceived;
    }

    @Override
    public void setDying(boolean dying) {
        shotsReceived += 1;

        if (getShield() <= 0) {
            substractLives();
        }

        if (shotsReceived >= 5) {
            shotsReceived = 0;
            shieldPercentage = shieldPercentage - 10;
            if (getShieldPercentage() <= 0) {

                initShieldPercentage();
                substractShield();

            }
        }


        if (getLives() <= 0) {
            super.setDying(true);
        }

    }

    public void substractShield(){
        shield = shield -1;


    }
    public boolean isGameWon(){
        return gameWon;
    }

    public void isGameWon(boolean estado){
        gameWon = estado;

    }

    public void nextLevel(){
        initShieldPercentage();
        substractShield();
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
