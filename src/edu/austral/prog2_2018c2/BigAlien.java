package edu.austral.prog2_2018c2;

public class BigAlien extends Alien {

    //establece la imagen.

    public BigAlien(int x, int y) {
        super(x, y);
        alienImg = "src/images/alienGrande.png";
       setImageAlien();
        this.life = 1;
    }
}
