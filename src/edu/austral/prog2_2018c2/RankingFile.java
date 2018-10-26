package edu.austral.prog2_2018c2;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

    public class RankingFile  {

        //crea el archivo en disco, recibe como parámetro la lista de score
        public static void crearArchivo(ArrayList<Score> score) throws IOException {
            FileWriter flwriter = null;
            try {
                //crea el flujo para escribir en el archivo
                flwriter = new FileWriter("src/edu/austral/prog2_2018c2/score.txt");
                //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                for (Score score1 : score) {
                    //escribe los datos en el archivo
                    bfwriter.write(  "\n" +score1.getName() + "," + score1.getPoint() + "," + score1.getDate());

                }

                //cierra el buffer intermedio
                bfwriter.close();
                System.out.println("Archivo creado satisfactoriamente..");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (flwriter != null) {
                    try {//cierra el flujo principal
                        flwriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //crea el archivo en disco, retorna la lista de estudiantes
        public static ArrayList leerArchivo() {
            // crea el flujo para leer desde el archivo
            File file = new File("src/edu/austral/prog2_2018c2/score.txt");
            ArrayList ScoreList= new ArrayList<>();
            Scanner scanner;
            try {
                //se pasa el flujo al objeto scanner
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    // el objeto scanner lee linea a linea desde el archivo
                    String linea = scanner.nextLine();
                    Scanner delimitar = new Scanner(linea);
                    //se usa una expresión regular
                    //que valida que antes o despues de una coma (,) exista cualquier cosa
                    //parte la cadena recibida cada vez que encuentre una coma
                    delimitar.useDelimiter("\\s*,\\s*");
                    Score e= new Score();
                    e.setDate(delimitar.next());
                    e.setName(delimitar.next());
                    e.setPoint((delimitar.next()));
                    ScoreList.add(e);
                }
                //se cierra el ojeto scanner
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return ScoreList;
        }

        //añadir más estudiantes al archivo
        public static void addFile(ArrayList<Score> score) {
            FileWriter flwriter = null;
            try {//además de la ruta del archivo recibe un parámetro de tipo boolean, que le indican que se va añadir más registros
                flwriter = new FileWriter("src/edu/austral/prog2_2018c2/score.txt", true);
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                for (Score score1: score) {
                    //escribe los datos en el archivo
                    bfwriter.write(score1.getName() + "," + score1.getPoint() + "," + score1.getDate());

                }
                bfwriter.close();
                System.out.println("Archivo modificado satisfactoriamente..");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (flwriter != null) {
                    try {
                        flwriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
