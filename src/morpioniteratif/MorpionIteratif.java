/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpioniteratif;

import java.util.Scanner;

/**
 *
 * @author clementserrano
 */
public class MorpionIteratif {

    private static String[][] grille;
    private static Scanner reader;
    private static String input;

    private static int x;
    private static int y;

    private static int x2;
    private static int y2;
    private static int n;
    private static int x3;
    private static int y3;

    private static String player;
    private static String ia;
    private static String empty;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialisation des variables
        grille = new String[3][3];
        reader = new Scanner(System.in);
        input = "";

        x = 0;
        y = 0;

        x2 = 0;
        y2 = 0;
        n = 0;
        x3 = 0;
        y3 = 0;

        player = "o";
        ia = "x";
        empty = "-";

        initGrille();

        // Boucle de la partie
        while (!ligneRemplie()) {
            x = -1;
            y = -1;

            // Utilisateur
            while (x < 0 || x >= grille.length || y < 0 || y >= grille.length
                    || (x >= 0 & x < grille.length & y >= 0 && y < grille.length
                    && !grille[x][y].equals(empty))) {
                System.out.print("Entrez votre coup : ");
                input = reader.nextLine();
                try {
                    x = Integer.parseInt("" + input.charAt(0));
                    y = Integer.parseInt("" + input.charAt(2));
                } catch (Exception e) {
                    x = -1;
                    y = -1;
                }
            }

            // Coup joué
            grille[x][y] = player;

            // Affiche la grille
            printGrille();

            // IA
            x2 = -1;
            y2 = -1;

            // l'IA vérifie si coup gagnant ou contre (x2 et y2 changés)
            verifCoup();

            // Coup random (x2 et y2 non changés)
            if (x2 == -1 && y2 == -1) {

                do {
                    x2 = (int) (Math.random() * (3 - 0)) + 0;
                    y2 = (int) (Math.random() * (3 - 0)) + 0;
                } while (!grille[x2][y2].equals(empty));
            }

            // Coup joué
            grille[x2][y2] = ia;

            // Affiche la grille
            printGrille();

            if (n == 2) { // L'IA a joué un coup gagnant
                System.out.println("IA gagne");
                System.exit(0); // On arrête le jeu
            }
        }
    }

    private static void verifCoup() {
        // Si n == 2, coup gagnant, on sort de la fonction et on joue
        // Si n == -2, coup contre, on continue de vérifier si il y a un coup gagnant
        // A la fin de la vérification, si x2 et y2 sont égaux à -1 cela veut dire qu'il
        // n'y a pas de coup contre donc coup random

        x2 = -1;
        y2 = -1;
        x3 = -1;
        y3 = -1;

        // Colonnes
        for (int j = 0; j < grille[0].length; j++) {
            n = 0;
            for (int i = 0; i < grille.length; i++) {
                if (grille[i][j].equals(ia)) {
                    n++;
                } else if (grille[i][j].equals(player)) {
                    n--;
                } else if (grille[i][j].equals(empty)) {
                    x2 = i;
                    y2 = j;
                }
            }

            switch (n) {
                case 2: // Coup gagnant -> on arrête la vérification
                    return;
                case -2: // Coup contre -> on enregistre le coup
                    x3 = x2;
                    y3 = y2;
                    break;
                default: // Pas de coup contre -> on réinitialise le coup
                    x2 = -1;
                    y2 = -1;
                    break;
            }
        }

        // Lignes
        for (int i = 0; i < grille.length; i++) {
            n = 0;
            for (int j = 0; j < grille[0].length; j++) {
                if (grille[i][j].equals(ia)) {
                    n++;
                } else if (grille[i][j].equals(player)) {
                    n--;
                } else if (grille[i][j].equals(empty)) {
                    x2 = i;
                    y2 = j;
                }
            }

            switch (n) {
                case 2: // Coup gagnant -> on arrête la vérification
                    return;
                case -2: // Coup contre -> on enregistre le coup
                    x3 = x2;
                    y3 = y2;
                    break;
                default: // Pas de coup contre -> on réinitialise le coup
                    x2 = -1;
                    y2 = -1;
                    break;
            }

        }

        // Diagonales
        int i = 0;
        int j = 0;

        // 1ère diagonale
        n = 0;
        while (i < grille.length && j < grille[0].length) {
            if (grille[i][j].equals(ia)) {
                n++;
            } else if (grille[i][j].equals(player)) {
                n--;
            } else if (grille[i][j].equals(empty)) {
                x2 = i;
                y2 = j;
            }
            i++;
            j++;
        }

        switch (n) {
            case 2: // Coup gagnant -> on arrête la vérification
                return;
            case -2: // Coup contre -> on enregistre le coup
                x3 = x2;
                y3 = y2;
                break;
            default: // Pas de coup contre -> on réinitialise le coup
                x2 = -1;
                y2 = -1;
                break;
        }

        // 2ème diagonale
        n = 0;
        while (i < grille.length && j < grille[0].length) {
            if (grille[i][grille[0].length - j].equals(ia)) {
                n++;
            } else if (grille[i][grille[0].length - j].equals(player)) {
                n--;
            } else if (grille[i][grille[0].length - j].equals(empty)) {
                x2 = i;
                y2 = j;
            }
            i++;
            j++;
        }

        switch (n) {
            case 2: // Coup gagnant -> on arrête la vérification
                return;
            case -2: // Coup contre -> on enregistre le coup
                x3 = x2;
                y3 = y2;
                break;
            default: // Pas de coup contre -> on réinitialise le coup
                x2 = -1;
                y2 = -1;
                break;
        }

        // Fin de la vérification
        // Pas de coup gagnant
        // Si x3 et y3 sont différents de -1 -> coup contre
        if (x3 != -1 && y3 != -1) {
            x2 = x3;
            y2 = y3;
        }
    }

    private static void initGrille() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                grille[i][j] = empty;
            }
        }
    }

    private static boolean ligneRemplie() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                if (grille[i][j].equals(empty)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printGrille() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                System.out.print(grille[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
