package edu.austral.prog2_2018c2;

public class MediumAlien extends Alien {

    //establece la imagen y los puntos del alienMediano

    public MediumAlien(int x, int y) {
        super(x, y);
        points = 20;
        alienImg = "src/images/alienMediano.png";
        setImageAlien();
        this.life = 1;


    }



}
