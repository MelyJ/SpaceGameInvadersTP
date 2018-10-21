package edu.austral.prog2_2018c2;


import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons {
    private  int puntos;
    private final int START_Y = 280;
    private final int START_X = 270;
    public int vidas=3;
    private final String playerImg = "src/images/player.png";
    private int width;
    private int escudos;
    private int porcentajeEscudo;
    private int disparosRecibidos;
    protected boolean ganoElNivel;
    public Player() {

        initPlayer();
    }

    private void initPlayer() {
        ganoElNivel = false;

        escudos = 4;
        initPorcentajeEscudo();
        ImageIcon ii = new ImageIcon(playerImg);
        width = ii.getImage().getWidth(null);
        puntos = 0;
        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }
    public void initPorcentajeEscudo(){
        porcentajeEscudo = 100;
    }
    public void setEscudos(int escudo){
        escudos = escudo;
    }
    public int getEscudo(){
        return escudos;

    }
    public int getPorcentajeEscudo(){
        return porcentajeEscudo;
    }
    public void setPorcentajeEscudo(int porcentajeEscudos){
        porcentajeEscudo = porcentajeEscudos;

    }
    public void setVidas(int vida){
        vidas= vida;

    }

    public int getVidas() {
        return vidas;
    }
    public void restarVidas(){
        vidas = vidas-1;
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
    public void setPuntos(int punto){
        puntos = punto;

    }
    public int getPuntos(){
        return puntos;
    }
    public void agregarPuntos(int punto){
        puntos = puntos+punto;

    }

    public int getDisparosRecibidos()
    {
        return disparosRecibidos;
    }

    @Override
    public void setDying(boolean dying) {
        disparosRecibidos += 1;

        if (getEscudo() <= 0) {
            restarVidas();
        } else {

        }

        if (disparosRecibidos >= 5) {
            disparosRecibidos = 0;
            porcentajeEscudo = porcentajeEscudo - 10;
            if (getPorcentajeEscudo() <= 0) {

                initPorcentajeEscudo();
                restarEscudo();

            }
        }


        if (getVidas() <= 0) {
            super.setDying(true);
        }

    }

    public void restarEscudo(){
        escudos=escudos-1;


    }
    public boolean ganoElNivel(){
        return ganoElNivel;
    }

    public void ganoElNivel(boolean estado){
        ganoElNivel = estado;

    }

    public void nextLevel(){
        initPorcentajeEscudo();
        restarEscudo();


    }

}