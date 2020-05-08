package fr.gameplayStudio;

import java.util.Random;

public class ModeDeJeu {
    public Mode mode;
    // limite de 10digit (int)
    public int combinaisonSecrete;
    //taille par default
    public int tailleCombinaisonSecrete = 4;
    // créer un tableau de la taille choisi comportant X cells.
    public int[] tableauIntStockageCombinaisonSecrete = new int[tailleCombinaisonSecrete];
    public String[] tableauStringStockageCombinaisonSecrete = new String[tailleCombinaisonSecrete];
    // pour la proposition
    public int[] tableauIntStockageProposition = new int[tailleCombinaisonSecrete];
    public String[] tableauStringStockageProposition = new String[tailleCombinaisonSecrete];
    //constructeur
    public ModeDeJeu(Mode mode) {
        this.mode = mode;
    }

    public int generate() {
        Random randomNb = new Random();
        int combinaisonSecreteTemp = randomNb.nextInt();
        // si la taille de la combinaison secrete générée est plus grande que la tailleCombinaisonSecrete (taille max pour jouer)
        // alors on utilise substring pour ne prend que les X dernier chiffre pour faire notre combinaison secrete
        if (String.valueOf(combinaisonSecreteTemp).length() > tailleCombinaisonSecrete) {
            String stringCombinaisonSecreteTemp = String.valueOf(combinaisonSecreteTemp).substring(String.valueOf(combinaisonSecreteTemp).length() - tailleCombinaisonSecrete);
            combinaisonSecrete = Integer.parseInt(stringCombinaisonSecreteTemp);
        }
        return combinaisonSecrete;
    }
    public void storeCombinaisonSecrete() {
        tableauStringStockageCombinaisonSecrete = String.valueOf(combinaisonSecrete).split("");
        for (int i = 0; i < tailleCombinaisonSecrete - 1; i++) {
            tableauIntStockageCombinaisonSecrete[i] = Integer.parseInt(tableauStringStockageCombinaisonSecrete[i]);
        }
    }
    public void storeProposition(String proposition) {
        if (proposition.length() != tailleCombinaisonSecrete) {
            System.out.println("Vous avez fait une erreur dans votre proposition celle-ci ne comprend pas autant de chiffres (" + tailleCombinaisonSecrete + ") à découvrir");
        } else {
            tableauStringStockageProposition = String.valueOf(proposition).split("");
            for (int i = 0; i < tailleCombinaisonSecrete - 1; i++) {
                tableauIntStockageProposition[i] = Integer.parseInt(tableauStringStockageProposition[i]);
            }
        }
    }
    public String compare() {
        String[] tableauReponse = new String[tailleCombinaisonSecrete];

        for (int i = 0; i <= tailleCombinaisonSecrete - 1; i++) {
            if (tableauIntStockageProposition[i] > tableauIntStockageCombinaisonSecrete[i]) {
                //System.out.print("-");
                tableauReponse[i] = "-";
            } else if (tableauIntStockageProposition[i] < tableauIntStockageCombinaisonSecrete[i]) {
                //System.out.print("+");
                tableauReponse[i] = "+";
            } else {
                //System.out.print("=");
                tableauReponse[i] = "=";
            }
        }
        String reponse = String.join("", tableauReponse);
        return reponse;
    }
}




