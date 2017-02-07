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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] grille = new int[3][3];
        
        Scanner reader = new Scanner(System.in);
        System.out.print("Entrez votre coup : ");
        String input = reader.nextLine();
        int x = Integer.parseInt(""+input.charAt(0));
        int y = Integer.parseInt(""+input.charAt(2));
        
        
        
    }
    
}
