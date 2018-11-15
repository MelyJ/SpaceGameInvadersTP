package edu.austral.prog2_2018c2;

public class SmallAlien extends Alien {

    //establece la imagen y los puntos del alienChico

    public SmallAlien(int x, int y) {
        super(x, y);
        points = 30;
        this.life = 1;
    }
}
