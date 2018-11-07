package edu.austral.prog2_2018c2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.SplittableRandom;

public class Score implements Serializable {


    ArrayList<Player> players;


    public Score() {
players = new ArrayList<Player>();
    }
    public void setPlayer(Player player){
       if(  players.contains(player)){
           players.get(players.indexOf(player)).setPoints(player.getPoints());

       }else{
           players.add(player);
       }
       RankingFile.escribir(this);
    }


}
//falta ranking 10 jugadores