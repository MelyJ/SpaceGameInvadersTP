package edu.austral.prog2_2018c2;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

    public class RankingFile {


        public static void escribir(Score score) {
            try {

                ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Propietario\\IdeaProjects\\SpaceGameInvaders\\src\\edu\\austral\\prog2_2018c2\\score.txt",true));
                file.writeObject(score);
                file.close();
            } catch (IOException ex) {
ex.printStackTrace();
            }


        }

        public static Score leer () {
            Score score = new Score();
            try {


                ObjectInputStream file = new ObjectInputStream(new FileInputStream("C:\\Users\\Propietario\\IdeaProjects\\SpaceGameInvaders\\src\\edu\\austral\\prog2_2018c2\\score.txt"));
            score = (Score)file.readObject();
                file.close();
            } catch (IOException ex) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return  score;
        }

    }
