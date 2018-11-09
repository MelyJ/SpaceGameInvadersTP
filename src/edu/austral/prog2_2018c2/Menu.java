package edu.austral.prog2_2018c2;

import java.awt.*;

public class Menu{

    public Rectangle playButton = new Rectangle(Game.BOARD_WIDTH / 2 - 39  , 200, 100, 50 );
    public Rectangle rankingButton = new Rectangle(Game.BOARD_WIDTH / 2 - 39, 300, 100, 50 );
    public Rectangle quitButton = new Rectangle(Game.BOARD_WIDTH / 2 - 39, 400, 100, 50 );


    public void render (Graphics g){

        Graphics2D g2D = (Graphics2D) g;

        Font fnt0= new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.green);
        g.drawString("SPACE INVADERS", 120 , 130);

        Font fnt1= new Font("arial", Font.BOLD, 20);
        g.setFont(fnt1);
        g.setColor(Color.white);


        ((Graphics2D) g).drawString("Play", playButton.x + 29 , playButton.y + 33);
        g2D.draw(playButton);
        ((Graphics2D) g).drawString("Ranking", rankingButton.x + 12 , rankingButton.y + 33);
        g2D.draw(rankingButton);
        ((Graphics2D) g).drawString("Quit", quitButton.x + 29 , quitButton.y + 33);
        g2D.draw(quitButton);
    }

}