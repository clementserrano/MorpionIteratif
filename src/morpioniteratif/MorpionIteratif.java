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
        String[][] grille = new String[3][3];
        Scanner reader = new Scanner(System.in);
        String input = "";
        int x = 0;
        int y = 0;
        
        initGrille(grille);
        
        while(!isFilled(grille)){
            x=-1;
            y=-1;
            while(x<0 || x>=grille.length || y<0 || y>=grille.length){
                System.out.print("Entrez votre coup : ");
                input = reader.nextLine();
                x = Integer.parseInt(""+input.charAt(0));
                y = Integer.parseInt(""+input.charAt(2));
            }
            
            grille[x][y]="o";
            
            printGrille(grille);
            
            // IA
            int[] res;
            // l'IA vérifie si coup gagnant
            res = verifCoup(grille, "x","o");
            if(res[0]!=-1 || res[1] != -1){
                grille[res[0]][res[1]] = "x";
            }else{ // Sinon IA vérifie si contre
                res = verifCoup(grille, "o","x");
                if(res[0]!=-1 || res[1] != -1){
                    grille[res[0]][res[1]] = "x";
                }else{ // Sinon IA joue random
                    int x2 = 0;
                    int y2 = 0;
                    do{
                        x2 = (int)(Math.random() * (2-0)) + 0;
                        y2 = (int)(Math.random() * (2-0)) + 0;
                    }while(grille[x2][y2].equals("-"));
                    grille[x2][y2]="x";
                }
            }
        }
    }
    
    private static boolean isFilled(String[][] grille){
        for(int i = 0; i< grille.length;i++){
            for(int j = 0; j<grille[0].length;j++){
                if(grille[i][j].equals("-")){
                    return false;
                }
            }
        }
        return true;
    }
    
    private static void printGrille(String[][] grille){
        for(int i = 0; i< grille.length;i++){
            for(int j = 0; j<grille[0].length;j++){
                System.out.print(grille[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    private static void initGrille(String[][] grille){
        for(int i = 0; i< grille.length;i++){
            for(int j = 0; j<grille[0].length;j++){
                grille[i][j]="-";
            }
        }
    }
    
    private static int[] verifCoup(String[][] grille, String motif, String motifEnnemi){
        int x = -1;
        int y = -1;
        
        int[] res = verifColonnes(grille,motif,motifEnnemi);
        if(res[0]!=-1 || res[1] != -1){
            return new int[]{x,y};
        }
        
        res = verifLignes(grille,motif,motifEnnemi);
        if(res[0]!=-1 || res[1] != -1){
            return new int[]{x,y};
        }
        
        res = verifDiagonales(grille,motif,motifEnnemi);
        if(res[0]!=-1 || res[1] != -1){
            return new int[]{x,y};
        }
        
        return new int[]{x,y};
    }
    
    private static int[] verifColonnes(String[][] grille, String motif, String motifEnnemi){
        int x = -1;
        int y = -1;
        int n = 0;
        
        for(int j = 0; j< grille[0].length;j++){
            n = 0;
            for(int i = 0; i<grille.length;i++){
                if(grille[i][j].equals(motif)){
                    n++;
                }else if(grille[i][j].equals(motifEnnemi)){
                    n--;
                }
            }
            
        }
        
        return new int[]{x,y,n};
    }
    
    private static int[] verifLignes(String[][] grille, String motif, String motifEnnemi){
        int x = -1;
        int y = -1;
        
        return new int[]{x,y};
    }
    
    private static int[] verifDiagonales(String[][] grille, String motif, String motifEnnemi){
        int x = -1;
        int y = -1;
        
        return new int[]{x,y};
    }
}