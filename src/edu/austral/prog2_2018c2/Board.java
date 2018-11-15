package edu.austral.prog2_2018c2;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.*;

public class Board extends JPanel implements Runnable, Commons {

    private Dimension d;
    private ArrayList<Alien> aliens;
    private ArrayList<Shield>shields;
    private Player player;
    private Shot shot;
    private Game game;
    private final int SHIELD_INIT_X = 50;
    private final int SHIELD_INIT_Y = 30;
    private final int ALIEN_INIT_X = 150;
    private final int ALIEN_INIT_Y = 32;
    private int direction = -1;
    private int deaths = 0;
    private Menu menu;

    private boolean ingame = true;
    private final String explImg = "src/images/explosion.png";
    private String message;
    private  Alien alienAux;

    private Thread animator;
    public Rectangle menuButton = new Rectangle(3 , 3, 85, 10);


    //inicializamos el board
    public Board() {
        initBoard();
    }

    //usamos enum para crear las opciones del juego(4 diferentes estados que se comportan diferente)
    public static enum STATE {
        MENU,
        GAME_MENU,
        GAME,
        RANKING
    };

    //empieza en modo menu, por lo tanto, va a comenzar mostrando el menu
    public static STATE State = STATE.MENU;


    protected void initBoard() {
        addKeyListener(new TAdapter());
        addMouseListener(new MouseInput());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.red);
        //Crea una instancia del menu
        menu = new Menu();

        //Diferencia con el if, entre in game o no. Si esta en modo Game ejecuta el juego, en cambio si esta en menu establece el color del fondo
        if (State == STATE.GAME) {

            gameInit();
            setDoubleBuffered(true);
        }

        else if (State == STATE.MENU){
            setBackground(Color.black);
        }

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
                int typeOfAlien=  rand.nextInt(3)+1;
                Alien alien;
                // como hay 3 tipos diferentes de aliens, creamos un random para ubicarlos aleatoriamente y usamos un switch para cada caso.
                switch (typeOfAlien){
                    case 1 :   alien = new SmallAlien(ALIEN_INIT_X + 50 * j, ALIEN_INIT_Y + 30 * i);
                        break;
                    case 2 :   alien = new MediumAlien(ALIEN_INIT_X + 50 * j, ALIEN_INIT_Y + 30 * i);
                        break;
                    case 3 :  alien = new BigAlien(ALIEN_INIT_X + 50 * j, ALIEN_INIT_Y + 30 * i);
                        break;
                    default: alien = new SmallAlien(ALIEN_INIT_X + 50 * j, ALIEN_INIT_Y + 30 * i);
                }

                aliens.add(alien);
            }
        }

    }
    //creamos un arrayList de escudos, lo instaciamos y agregamos los distintos 4 escudos a la lista .
    public void initShield(){
        shields = new ArrayList<>();
        for(int i = 1 ; i <= 4 ; i++){
            Shield shield = new Shield(SHIELD_INIT_X+100*i,SHIELD_INIT_Y+290);
            shields.add(shield);
        }
    }

    public void gameInit() {

        initAliens();
        player = new Player();
        shot = new Shot();
        initShield();
        game = new Game();
        if (animator == null || !ingame) {

            animator = new Thread(this);
            animator.start();
        }

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

    //dibuja los escudos
    public void drawShield(Graphics g) {
        Iterator<Shield> it = shields.iterator();
        while (it.hasNext()){
            Shield shield = it.next();
            if(shield.isVisible()){
                g.drawImage(shield.getImage(), shield.getX(), shield.getY(), this);
            }
            if(shield.isDying()){
                shield.die();
                it.remove();
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

        if (State == STATE.GAME) {

            super.paintComponent(g);

            g.setColor(Color.black);
            g.fillRect(0, 0, d.width, d.height);
            g.setColor(Color.green);

            if (ingame) {

                g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
                //aparace en pantalla vidas, ptos, nivel, nivel de energia y si hay un special power enabled.
                g.drawString("Lives: " + player.lives, 5, 30);
                g.drawString("Score: " + player.getPoints(), 280, 30);
                g.drawString("Level: " + game.getLevel(), 580, 30);
                g.drawString("Energy: " + player.energy, 5, 60);
                if (player.hasSkill) g.drawString("Special Power activated", 5, 90);

                drawAliens(g);
                drawPlayer(g);
                drawShot(g);
                drawBombing(g);
                drawShield(g);
                // Crea lo que aparece dentro del menuButton. Al tocarlo pausa el juego y aparace un nuevo menu con distintas opciones.
                Font fnt1= new Font("Helvetica", Font.BOLD, 8);
                g.setFont(fnt1);
                g.setColor(Color.white);
                Graphics2D g2D = (Graphics2D) g;
                ((Graphics2D) g).drawString("BACK TO MENU", menuButton.x + 10 , menuButton.y + 8);
                g2D.draw(menuButton);

            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();

        }
        //si el juego esta en estado menu, aparece el mismo en pantalla.
        if (State == STATE.MENU || State == STATE.GAME_MENU){
            menu.render(g);
        }

    }

    public void gameOver() {


           Graphics g = this.getGraphics();

           g.setColor(Color.black);
           g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

           g.setColor(new Color(0, 32, 48));
           g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 70);
           g.setColor(Color.white);
           g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 70);


           Font small = new Font("Helvetica", Font.BOLD, 14);
           FontMetrics metr = this.getFontMetrics(small);

           g.setColor(Color.white);
           g.setFont(small);
           //Muestra por pantalla alguno de los tres mensajes posibles, de porque acabo el juego: "GAME WON" "GAME OVER" "INVASION". Muestra los puntos alcanzado en la partida.
           g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2, BOARD_WIDTH / 2);
           g.drawString("Score: " + player.getPoints(), (BOARD_WIDTH - metr.stringWidth("Score: " + player.getPoints())) / 2, BOARD_WIDTH / 2 + 20);

           //sleep para que muestre por 3000 milisegundos lo anterior. Al acabar ese tiempo, pide el nombre del usuario y el sistema salga .exit(0) con estado 0
           try {
               Thread.sleep(3000);
           } catch (InterruptedException e) {
               System.out.println("interrupted");
           }

           String userName = JOptionPane.showInputDialog("User name: ");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

           System.exit(0);



    }

    public void animationCycle() {
        if (State == STATE.GAME) {
            if (deaths == NUMBER_OF_ALIENS_TO_DESTROY) {
            //    if (deaths == 3){

                player.isGameWon(true);
                //ingame = false;
                deaths = 0;

            }

            // player
            player.act();

            // shot
            if (shot.isVisible()) {

                int shotX = shot.getX();
                int shotY = shot.getY();

                for (Alien alien : aliens) {

                    int alienX = alien.getX();
                    int alienY = alien.getY();
                    //si el disparo esta en el mismo lugar que el alien, resta vidas
                    if (alien.isVisible() && shot.isVisible()) {
                        if (shotX >= (alienX)
                                && shotX <= (alienX + ALIEN_WIDTH)
                                && shotY >= (alienY)
                                && shotY <= (alienY + ALIEN_HEIGHT)) {


                            alien.life -= 1;
                            if (alien.life == 0) {
                                //si las vidas =  el alien muere y agrega ptos al player.
                                ImageIcon ii = new ImageIcon(explImg);
                                alien.setImage(ii.getImage());
                                alien.setDying(true);
                                deaths++;
                                player.addPoints(alien.getPoints());

                            }

                            shot.die();

                            player.energy++;
                            if (player.energy == 10){
                                player.activatePower();
                            }
                            if (!player.doubleDamage){
                                alien.life -= player.damage;
                            }
                            else{
                                alien.life -= player.damage*2;
                            }
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

            for (Alien alien : aliens) {

                int x = alien.getX();

                if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {
                //Moviento de los aliens. (de que lado esta)

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
            // recorre la lista, si existe y esta en la misma linea del piso "invasion" y fin del juego.
            Iterator it = aliens.iterator();

            while (it.hasNext()) {

                Alien alien = (Alien) it.next();

                if (alien.isVisible()) {

                    int y = alien.getY();

                    if (y > GROUND - ALIEN_HEIGHT) {
                        //ingame = false;
                        message = "Invasion!";
                        game.isInvasion(player);
                    }

                    alien.act(direction);
                }
            }

            // bombs
            Random generator = new Random();

            for (Alien alien : aliens) {
            // tira de forma random las bombas
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

                //Recorre la lista de los shields y si la bomba esta en la misma posicion que el shield, el mismo muere
                for (Shield shield: shields){
                    int shieldX= shield.getX();
                    int shieldY= shield.getY();

                    if(shield.isVisible() && !b.isDestroyed() ){
                        if (bombX >= (shieldX)
                                && bombX <= (shieldX + SHIELD_WIDTH)
                                && bombY >= (shieldY - SHIELD_HEIGHT)
                                && bombY <= (shieldY + SHIELD_HEIGHT)) {
                            shield.substractLives();
                            b.setDestroyed(true);
                            b.setVisible(false);


                        }
                    }


                }

                if (player.isVisible() && !b.isDestroyed()) {

                    if (bombX >= (playerX)
                            && bombX <= (playerX + PLAYER_WIDTH)
                            && bombY >= (playerY)
                            && bombY <= (playerY + PLAYER_HEIGHT)) {

                       if(player.getLives() <= 3){
                           player.lives --;
                           b.setDestroyed(true);
                       }

                       if(player.getLives() == 0) {
                           ImageIcon ii = new ImageIcon(explImg);
                           //  player.setImage(ii.getImage());
                           player.setDying(true);

                           b.setDestroyed(true);
                       }
                    }
                }

                if (!b.isDestroyed()) {

                    b.setY(b.getY() + 1);

                    if (b.getY() >= GROUND - BOMB_HEIGHT) {
                        b.setDestroyed(true);
                    }
                }
            }
            //System.out.println("Cant vidas:" +  player.getLives() + "; Nivel: " + game.getLevel());
        }
    }

    @Override
    public void run() {
        // viene de la implementacion de runnable,
        // QUE ES RUNNABLE??
        long beforeTime, timeDiff, sleep, beforeTimeUfo,randomUfo;
        beforeTimeUfo = System.currentTimeMillis();
        beforeTime = System.currentTimeMillis();
        Random rand = new Random();
        randomUfo = rand.nextInt((60-45)+1)+45;
        int numberOfTries;
        boolean changeToUfo;
        //  while (ingame){
        while (/*ingame &&*/ !player.isDying() /*&& game.*/ && ! game.isGameWon()) {

            if (player.isGameWon())
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
            // si el tiempo de juego es mayor a 45 a 60 seg aparece un ufo, multiplicamos por 1000 pq esta en milisegundos
                changeToUfo = false;
                beforeTimeUfo = System.currentTimeMillis();
                randomUfo = rand.nextInt((60-45)+1)+45;
                numberOfTries = 0;
                while (!changeToUfo && numberOfTries < 5) {
                    //<5 para evitar que entre en un loop
                    alienAux = aliens.get(rand.nextInt((23)+1));

                    if(!alienAux.isUfo()){
                        alienAux.setUfo(true);
                        changeToUfo = true;
                    }
                    // agarra un alien random y si no es ufo, lo convierte.
                    numberOfTries++;
                }
            }
            //PAUSA
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }

//        game.setScore(player);

        if(game.isGameWon()){
            message = "Game Won!!!!";
        }else {
            if (game.isInvasion())
                message= "Invasion :o";
            else
                message = "Game Over :(";
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
