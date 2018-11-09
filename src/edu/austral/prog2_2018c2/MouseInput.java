package edu.austral.prog2_2018c2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

/**
 *     public Rectangle playButton = new Rectangle(Game.BOARD_WIDTH / 2 - 39  , 200, 100, 50 );
 *     public Rectangle rankingButton = new Rectangle(Game.BOARD_WIDTH / 2 - 39, 300, 100, 50 );
 *     public Rectangle quitButton = new Rectangle(Game.BOARD_WIDTH / 2 - 39, 400, 100, 50 );
 */
        if (Board.State == Board.STATE.MENU){
            // Play Button
            if (mx >= Board.BOARD_WIDTH / 2 - 39  && mx <= Board.BOARD_WIDTH / 2 + 61 ) {

                if (my >= 200 && my <= 250) {
                    // Press Play Button
                    Board.State = Board.STATE.GAME;
                }
            }

            // Ranking Button
            if (mx >= Board.BOARD_WIDTH / 2 - 39  && mx <= Board.BOARD_WIDTH / 2 + 61  ){
                if (my >= 300 &&  my <= 350){
                    // Press Ranking Button
                    Board.State = Board.STATE.RANKING;
                }
            }
            // Quit Button
            if (mx >= Game.BOARD_WIDTH / 2 - 39 && mx <= Board.BOARD_WIDTH / 2 + 61  ){
                if (my >= 400 &&  my <= 450){
                    // Press Quit Button
                    System.exit(1);
                }
            }
        }
        else if (Board.State == Board.STATE.GAME){
            //     public Rectangle menuButton = new Rectangle(3 , 3, 85, 10);
            if (mx >= 3 && mx <= 88 ){
                if (my >= 3 &&  my <= 13){
                    Board.State = Board.STATE.MENU;
                }
            }

        }


        }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
