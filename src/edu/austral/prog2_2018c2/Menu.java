package edu.austral.prog2_2018c2;

import java.awt.*;

public class Menu{
    // creamos los botones del menu
    public Rectangle playButton = new Rectangle(Game.BOARD_WIDTH / 2 - 59  , 200, 120, 50 );
    public Rectangle quitButton = new Rectangle(Game.BOARD_WIDTH / 2 - 59, 300, 120, 50 );
    public Rectangle rankingButton;


    public void render (Graphics g){
        //QUE ES GRAPHICS2D?
        Graphics2D g2D = (Graphics2D) g;
        //tipo de letra y tamaño
        Font fnt0= new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.green);
        g.drawString("SPACE INVADERS", 120 , 130);

        Font fnt1= new Font("arial", Font.BOLD, 20);
        g.setFont(fnt1);
        g.setColor(Color.white);


        if (Board.State == Board.STATE.MENU) {
            ((Graphics2D) g).drawString("Play", playButton.x + 39 , playButton.y + 33);
            g2D.draw(playButton);
            rankingButton = new Rectangle(Game.BOARD_WIDTH / 2 - 59, 400, 120, 50 );
            ((Graphics2D) g).drawString("Ranking", rankingButton.x + 22, rankingButton.y + 33);
            g2D.draw(rankingButton);
        }
        if (Board.State == Board.STATE.GAME_MENU ){
            ((Graphics2D) g).drawString("Resume", playButton.x + 21 , playButton.y + 33);
            g2D.draw(playButton);

        }
        ((Graphics2D) g).drawString("Quit", quitButton.x + 39 , quitButton.y + 33);
        g2D.draw(quitButton);
    }

}