package edu.austral.prog2_2018c2;

import javax.swing.ImageIcon;
import java.util.Random;

public class Alien extends Sprite {
    protected boolean ufo;
    protected int points;
    private Bomb bomb;
    protected String alienImg = "src/images/alien.png";
    protected String ufoImg = "src/images/UfoAlien.png";
    public int vida;
    public Alien(int x, int y) {

        initAlien(x, y);

    }

    private void initAlien(int x, int y) {
        ufo = false;
        this.x = x;
        this.y = y;
        bomb = new Bomb(x, y);
        this.points = 10;
        setImageAlien();
    }

    protected void setImageAlien(){
        String imagenAlien = alienImg;
        if(ufo){
            imagenAlien = ufoImg;
        }
        ImageIcon ii = new ImageIcon(imagenAlien);
        setImage(ii.getImage());
    }

    public boolean isUfo() {
        return ufo;
    }

    public void setUfo(boolean state){
        ufo = state;
        setImageAlien();

    }

    public void act(int direction) {

        this.x += direction;
    }

    public Bomb getBomb() {

        return bomb;
    }
    public int getPoints(){
        if(ufo){
            Random rand = new Random();
            return rand.nextInt((300-50)+1)+50;

        }else{
            return points;
        }

    }

    public class Bomb extends Sprite {

        private final String bombImg = "src/images/bomb.png";
        private boolean destroyed;

        public Bomb(int x, int y) {

            initBomb(x, y);
        }

        private void initBomb(int x, int y) {

            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(bombImg);
            setImage(ii.getImage());

        }

        public void setDestroyed(boolean destroyed) {

            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {

            return destroyed;
        }

    }

}
