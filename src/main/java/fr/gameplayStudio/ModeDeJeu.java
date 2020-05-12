package fr.gameplayStudio;

import java.util.Random;

public abstract class ModeDeJeu {
    public Mode mode;
    // limite de 10digit (int)
    public int combinaisonSecrete;
    //taille par default
    public int tailleCombinaisonSecrete = 4;
    public String[] tableauStringStockageCombinaisonSecrete = new String[tailleCombinaisonSecrete];
    public int[] tableauIntStockageProposition = new int[tailleCombinaisonSecrete];
    public String[] tableauStringStockageProposition = new String[tailleCombinaisonSecrete];
    String[] tableauReponse = new String[tailleCombinaisonSecrete];
    // 1ere colonne = combinaison secrete
    // 2eme colonne = proposition
    // 3eme colonne = resultat
    public String[][] tableauStringStockageSecretPropositionReponse = new String[tailleCombinaisonSecrete][3];


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

    public String storeData(String proposition) {
        if (proposition.length() != tailleCombinaisonSecrete) {
            System.out.println("votre proposition ne comporte pas le nombre de chiffre attendu (" + tailleCombinaisonSecrete + ")");
        } else {
            tableauStringStockageCombinaisonSecrete = String.valueOf(combinaisonSecrete).split(""); // stock le split de la combi secrete dans un tableau temporaire
            tableauStringStockageProposition = String.valueOf(proposition).split(""); // stock le split de la proposition dans un tableau temporaire
            for (int i = 0; i < tailleCombinaisonSecrete; i++) {
                tableauStringStockageSecretPropositionReponse[i][0] = tableauStringStockageCombinaisonSecrete[i]; // secret
                tableauStringStockageSecretPropositionReponse[i][1] = tableauStringStockageProposition[i]; // proposition
                //dev debug
                //System.out.println("secret= " + tableauStringStockageSecretPropositionReponse[i][0] + " Proposition = " + tableauStringStockageSecretPropositionReponse[i][1]);
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
            //dev debug
            //System.out.println(reponse);
            return reponse;
        }
        return "XXXX";
    }


    public String newPurpose() {
        for (int i = 0; i < tailleCombinaisonSecrete; i++) {
            int oldPurposal = Integer.parseInt(tableauStringStockageSecretPropositionReponse[i][i]);
            switch (tableauStringStockageSecretPropositionReponse[i][i]) {
                case "-":
                    tableauIntStockageProposition[i] = oldPurposal--;
                    break;
                case "+":
                    tableauIntStockageProposition[i] = oldPurposal++;
                    break;
                case "=":
                    tableauIntStockageProposition[i] = oldPurposal;
                    break;
            }
        }
        String newPurpose = String.join("", String.valueOf(tableauIntStockageProposition));
        return newPurpose;
    }
}




    /*
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
    */
    /*
    public void newPurposalIA() {
        for (int i = 0; i < tailleCombinaisonSecrete; i++) {
            int oldPurposal = Integer.parseInt(tableauStringStockageSecretPropositionReponse[i][i][0]);
            switch (tableauStringStockageIA[i][1]) {
                case "-":
                    tableauIntStockageProposition[i] = oldPurposal--;
                    break;
                case "+":
                    tableauIntStockageProposition[i] = oldPurposal++;
                    break;
                case "=":
                    tableauIntStockageProposition[i] = oldPurposal;
                    break;
            }
        }
    }

     */
    /*
    public String compare() {
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

 */




