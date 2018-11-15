package edu.austral.prog2_2018c2;


import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
// pq serializable??

public class Player extends Sprite implements Commons, Serializable {
    private  int points;
    private final int START_Y = 380;
    private final int START_X = 270;
    public int lives=3;
    private final String playerImg = "src/images/player.png";
    private int width;
    int damage = 1;
    int energy; //Acumula hasta 3 y activa poder

    Timer timer;
    TimerTask task0;

    boolean hasSkill= false;
    boolean doubleDamage =false;
    boolean inmune= false;
    boolean freeze= false;
    protected boolean gameWon;
    Date date;

    public Player() {

        initPlayer();
    }


    private void initPlayer() {
        gameWon = false;

        ImageIcon ii = new ImageIcon(playerImg);
        width = ii.getImage().getWidth(null);
        points = 0;
        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }

    public int getLives() {
        return lives;
    }


    public void activatePower(){
        //Agregrar condicional, solo UNO a la vez
        Random a= new Random();
        int b= a.nextInt(100);
        if(b>70 && b<=90){
            inmune=true;
            hasSkill= true;
            energy=0;
        }
        else if(b<=70){
            doubleDamage =true;
            hasSkill= true;
            energy=0;
        }
        task0= new TimerTask() {
            @Override
            public void run() {
                doubleDamage =false;
                inmune=false;
                hasSkill= false;
                cancel();
            }
        };
        timer= new Timer();
        timer.schedule(task0,5000);
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

    public boolean isGameWon(){
        return gameWon;
    }

    public void isGameWon(boolean estado){
        gameWon = estado;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}