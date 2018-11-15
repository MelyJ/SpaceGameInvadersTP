package edu.austral.prog2_2018c2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener, Commons {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

/**
 *     public Rectangle playButton = new Rectangle(Game.BOARD_WIDTH / 2 - 59  , 200, 120, 50 );
 *     public Rectangle rankingButton = new Rectangle(Game.BOARD_WIDTH / 2 - 59, 300, 120, 50 );
 *     public Rectangle quitButton = new Rectangle(Game.BOARD_WIDTH / 2 - 59, 400, 120, 50 );
 */

        if (Board.State == Board.STATE.MENU || Board.State == Board.STATE.GAME_MENU) {

            // Play Button
            if (mx >= Board.BOARD_WIDTH / 2 - 59 && mx <= Board.BOARD_WIDTH / 2 + 61) {

                if (my >= 200 && my <= 250) {
                    // Press Play Button
                    Board.State = Board.STATE.GAME;
                }
            }
            // Quit Button
            if (mx >= Board.BOARD_WIDTH / 2 - 59 && mx <= Board.BOARD_WIDTH / 2 + 61) {
                if (my >= 300 && my <= 350) {
                    // Press Quit Button
                    System.exit(1);
                }
            }

            if (Board.State == Board.STATE.MENU) {
                // Ranking Button
                if (mx >= Game.BOARD_WIDTH / 2 - 39 && mx <= Board.BOARD_WIDTH / 2 + 61) {
                    if (my >= 400 && my <= 450) {
                        // Press Ranking Button
                        Board.State = Board.STATE.RANKING;
                    }
                }
            }


        } else if (Board.State == Board.STATE.GAME) {
            //     public Rectangle menuButton = new Rectangle(3 , 3, 85, 10);
            if (mx >= 3 && mx <= 88) {
                if (my >= 3 && my <= 13) {
                    Board.State = Board.STATE.GAME_MENU;
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
