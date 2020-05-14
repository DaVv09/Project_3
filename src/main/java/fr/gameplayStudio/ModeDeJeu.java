package fr.gameplayStudio;

import java.util.Random;
import java.util.Scanner;

public abstract class ModeDeJeu {
    public Mode mode;

    public boolean modeDevellopeur=true;
    public int combinaisonSecrete;
    public int random;

    public int tailleCombinaisonSecrete = 4; // default 4
    public int nbEssai = 8;
    public int compteurEssai =0 ;
    public int[] tableauIntStockageProposition = new int[tailleCombinaisonSecrete];

    public String[] tableauStringStockageCombinaisonSecrete = new String[tailleCombinaisonSecrete];
    public String[] tableauStringStockageProposition = new String[tailleCombinaisonSecrete];
    public String[] tableauReponse = new String[tailleCombinaisonSecrete];
    public String[][] tableauStringStockageSecretPropositionReponse = new String[tailleCombinaisonSecrete][3];


    //constructeur
    public ModeDeJeu(Mode mode) {
        this.mode = mode;
    }

    public void generate() {
        Random randomNb = new Random();
        random = randomNb.nextInt();
        if (String.valueOf(random).length() > tailleCombinaisonSecrete) {
            String randomTemp = String.valueOf(random).substring(String.valueOf(random).length() - tailleCombinaisonSecrete);
            random = Integer.parseInt(randomTemp);
        }
    }

    public String storeData(String proposition) {
        if (proposition.length() != tailleCombinaisonSecrete) {
            System.out.println("votre proposition ne comporte pas le nombre de chiffre attendu (" + tailleCombinaisonSecrete + ")");
        } else {
            tableauStringStockageCombinaisonSecrete = String.valueOf(combinaisonSecrete).split(""); // stock le split de la combi secrete dans un tableau temporaire
            tableauStringStockageProposition = String.valueOf(proposition).split(""); // stock le split de la proposition dans un tableau temporaire
            for (int i = 0; i < tailleCombinaisonSecrete; i++) {
                tableauStringStockageSecretPropositionReponse[i][0] = tableauStringStockageCombinaisonSecrete[i]; // secret
                tableauStringStockageSecretPropositionReponse[i][1] = tableauStringStockageProposition[i]; // proposition
               /* if(modeDevellopeur) {
                    System.out.println("secret= " + tableauStringStockageSecretPropositionReponse[i][0] + " Proposition = " + tableauStringStockageSecretPropositionReponse[i][1]);
                }*/
                if (Integer.valueOf(tableauStringStockageSecretPropositionReponse[i][1]) > Integer.valueOf(tableauStringStockageSecretPropositionReponse[i][0])) { // proposition > secret
                    tableauStringStockageSecretPropositionReponse[i][2] = "-";
                } else if (Integer.valueOf(tableauStringStockageSecretPropositionReponse[i][1]) < Integer.valueOf(tableauStringStockageSecretPropositionReponse[i][0])) {
                    tableauStringStockageSecretPropositionReponse[i][2] = "+";
                } else if (Integer.valueOf(tableauStringStockageSecretPropositionReponse[i][1]) == Integer.valueOf(tableauStringStockageSecretPropositionReponse[i][0])) {
                    tableauStringStockageSecretPropositionReponse[i][2] = "=";
                }
            }
            for (int i = 0; i < tailleCombinaisonSecrete; i++) {
                tableauReponse[i] = tableauStringStockageSecretPropositionReponse[i][2];
            }
            String reponse = String.join("", (tableauReponse));
           /* if(modeDevellopeur) {
                System.out.println(reponse);
            }*/
            return reponse;
        }
        return "XXXX";
    }



    public String newPurpose() {
        for (int i = 0; i < tailleCombinaisonSecrete; i++) {
            int oldPurposal = Integer.parseInt(tableauStringStockageSecretPropositionReponse[i][1]); // interroge colonne "proposition"
            switch (tableauStringStockageSecretPropositionReponse[i][2]) {
                case "-":
                    oldPurposal--;
                    int newPurposal=oldPurposal;
                    tableauIntStockageProposition[i] = (newPurposal);
                    tableauStringStockageSecretPropositionReponse[i][1]=String.valueOf(tableauIntStockageProposition[i]);
                    break;
                case "+":
                    oldPurposal++;
                    newPurposal=oldPurposal;
                    tableauIntStockageProposition[i] = (newPurposal);
                    tableauStringStockageSecretPropositionReponse[i][1]=String.valueOf(tableauIntStockageProposition[i]);
                    break;
                case "=":
                    tableauIntStockageProposition[i] = oldPurposal;
                    tableauStringStockageSecretPropositionReponse[i][1]=String.valueOf(tableauIntStockageProposition[i]);
                    break;
            }
        }
        for (int i = 0; i < tailleCombinaisonSecrete; i++) {
            tableauStringStockageProposition[i] = tableauStringStockageSecretPropositionReponse[i][1];
        }
        String newPurpose = String.join("", tableauStringStockageProposition);
        return newPurpose;
    }
}




