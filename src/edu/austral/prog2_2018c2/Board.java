package edu.austral.prog2_2018c2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, Commons {

    private Dimension d;
    private ArrayList<Alien> aliens;
    private Player player;
    private Shot shot;
    private Game game;

    private final int ALIEN_INIT_X = 150;
    private final int ALIEN_INIT_Y = 5;
    private int direction = -1;
    private int deaths = 0;

    private boolean ingame = true;
    private final String explImg = "src/images/explosion.png";
    private String message = "Game Over";
    private String message2 = "Game Won!!!!!";
    private  Alien alienAux;

    private Thread animator;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.red);

        gameInit();
        setDoubleBuffered(true);
    }

    @Override
    public void addNotify() {

        super.addNotify();
        gameInit();
    }
    public void initAliens(){

        aliens = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                int tipoDeAlien=  rand.nextInt(3)+1;
                Alien alien = null ;

                switch (tipoDeAlien){
                    case 1 :   alien = new AlienChico(ALIEN_INIT_X + 18 * j, ALIEN_INIT_Y + 18 * i);
                        break;
                    case 2 :   alien = new AlienMediano(ALIEN_INIT_X + 18 * j, ALIEN_INIT_Y + 18 * i);
                        break;
                    case 3 :  alien = new AlienGrande(ALIEN_INIT_X + 18 * j, ALIEN_INIT_Y + 18 * i);
                        break;
                    default: alien = new AlienChico(ALIEN_INIT_X + 18 * j, ALIEN_INIT_Y + 18 * i);
                }

                aliens.add(alien);
            }
        }

    }

    public void gameInit() {

        initAliens();
        player = new Player();
        shot = new Shot();

        if (animator == null || !ingame) {

            animator = new Thread(this);
            animator.start();
        }
        game = new Game();
    }

    public void drawAliens(Graphics g) {

        Iterator it = aliens.iterator();

        for (Alien alien: aliens) {

            if (alien.isVisible()) {

                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {

                alien.die();
            }
        }
    }

    public void drawPlayer(Graphics g) {

        if (player.isVisible()) {

            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {

                player.die();
                ingame = false;

        }
    }

    public void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    public void drawBombing(Graphics g) {

        for (Alien a : aliens) {

            Alien.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (ingame) {

            g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
            g.drawString("Vida:" + player.vidas ,5,18);
            g.drawString("Puntos:" + player.getPuntos(),280,18);
            g.drawString("Escudos:" + player.getEscudo(),580,18);
            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void gameOver() {

        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2,
                BOARD_WIDTH / 2);
    }


    public void animationCycle() {

        if (deaths == NUMBER_OF_ALIENS_TO_DESTROY) {
       //if (deaths == 3){

            player.ganoElNivel(true);
            //ingame = false;
            deaths = 0;

        }

        // player
        player.act();

        // shot
        if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();

            for (Alien alien: aliens) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + ALIEN_HEIGHT)) {
                        ImageIcon ii
                                = new ImageIcon(explImg);
                        alien.setImage(ii.getImage());
                        alien.setDying(true);
                        deaths++;
                        player.agregarPuntos(alien.getPuntos());
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }

        // aliens

        for (Alien alien: aliens) {

            int x = alien.getX();

            if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {

                direction = -1;
                Iterator i1 = aliens.iterator();

                while (i1.hasNext()) {

                    Alien a2 = (Alien) i1.next();
                    a2.setY(a2.getY() + GO_DOWN);
                }
            }

            if (x <= BORDER_LEFT && direction != 1) {

                direction = 1;

                Iterator i2 = aliens.iterator();

                while (i2.hasNext()) {

                    Alien a = (Alien) i2.next();
                    a.setY(a.getY() + GO_DOWN);
                }
            }
        }

        Iterator it = aliens.iterator();

        while (it.hasNext() ) {

            Alien alien = (Alien) it.next();

            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > GROUND - ALIEN_HEIGHT) {
                    //ingame = false;
                    message = "Invasion!";
                    game.esInvasion(player);
                }

                alien.act(direction);
            }
        }

        // bombs
        Random generator = new Random();

        for (Alien alien: aliens) {

            int shot = generator.nextInt(game.getshotFrecuency());
            Alien.Bomb b = alien.getBomb();

            if (shot == CHANCE && alien.isVisible() && b.isDestroyed()) {

                b.setDestroyed(false);
                b.setX(alien.getX());
                b.setY(alien.getY());
            }

            int bombX = b.getX();
            int bombY = b.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !b.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + PLAYER_HEIGHT)) {
                    ImageIcon ii
                            = new ImageIcon(explImg);
                  //  player.setImage(ii.getImage());
                    player.setDying(true);

                    b.setDestroyed(true);
                }
            }

            if (!b.isDestroyed()) {

                b.setY(b.getY() + 1);

                if (b.getY() >= GROUND - BOMB_HEIGHT) {
                    b.setDestroyed(true);
                }
            }
        }
        System.out.println("cant escudos:" + player.getEscudo() + " porcentaje escudo: " + player.getPorcentajeEscudo() +
                " disparos recibidos:" + player.getDisparosRecibidos() + " cant vidas:" +  player.getVidas() + "Nivel" + game.getLevel());
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep,beforeTimeUfo,randomUfo;
        beforeTimeUfo = System.currentTimeMillis();
        beforeTime = System.currentTimeMillis();
        Random rand = new Random();
        randomUfo = rand.nextInt((60-45)+1)+45;
        int cantidadIntentos = 0;
        boolean cambioAUfo = false;
        while (/*ingame &&*/ !player.isDying() /*&& game.*/ && ! game.isGameWon()) {
            if (player.ganoElNivel())
            {
                game.nextLevel(player);
                initAliens();
            }

            repaint();
            animationCycle();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = game.getInvadersVelocity(timeDiff);

            if (sleep < 0) {
                sleep = 2;
            }
            if( System.currentTimeMillis() >= beforeTimeUfo+randomUfo * 1000  ){

                cambioAUfo = false;
                beforeTimeUfo = System.currentTimeMillis();
                randomUfo = rand.nextInt((60-45)+1)+45;
                cantidadIntentos = 0;
                while (!cambioAUfo && cantidadIntentos < 5)
                {
                    alienAux = aliens.get(rand.nextInt((23)+1));

                    if(!alienAux.isUfo()){
                        alienAux.setUfo(true);
                        cambioAUfo = true;
                    }
                    cantidadIntentos++;
                }
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }
        if(game.isGameWon()){
            message = "game won";
        }else {
            if (game.isInvasion())
                message= "Invasion";
            else
                message = "game over";
        }
        gameOver();
        }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (ingame) {
                    if (!shot.isVisible()) {
                        shot = new Shot(x, y);
                    }
                }
            }
        }
    }
        }

