package edu.austral.prog2_2018c2;


import java.util.ArrayList;

public class RankingFileTest{
    public static void main(String[] args) {
        // estructura Array List para guardar los objetos estudiantes
        ArrayList ScoreList = new ArrayList<>();

        // score 1
        Score score = new Score();
        score.setPoint(234);
        score.setName("Augusto");
        score.setDate("11/8/10");

        // añade un score a la lista
        ScoreList.add(score);

        // score 2
        Score score2 = new Score();
        score.setPoint(300);
        score.setName("Augustopepe");
        score.setDate("11/8/1203");

        // añade un score a la lista
        ScoreList.add(score2);


        //nueva lista para añadir al archivo
        ArrayList newScore= new ArrayList<>();
        newScore.add(score);

        //añade un estudiante más al archivo
        RankingFile.addFile(newScore);

        // lista para recibir los objetos estudiantes desde el archivo

        ArrayList listaLeida = new ArrayList<Score> ();

        // asignar a la lista los objetos
        listaLeida = RankingFile.leerArchivo();
        for (Object score1: listaLeida) {
            System.out.println(score.getName() + " " + score.getDate() + " " + score.getPoint());

        }
    }




}



