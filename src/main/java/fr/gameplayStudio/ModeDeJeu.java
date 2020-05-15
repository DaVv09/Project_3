package fr.gameplayStudio;

import java.util.Random;

public abstract class ModeDeJeu {

    //declaration des variables
    public Mode mode;
    public boolean modeDevellopeur = true; // devra pointer sur un XML de config
    public int tailleCombinaison = 4; // default 4  & devra pointer sur un XML de config

    //----------------------------------------------------------
    //------------------- variable pour l'IA -------------------
    //----------------------------------------------------------


    public int combinaisonSecreteIA;
    final public int tentativeIA = 8; // default 8 & devra pointer sur un XML de config
    public int compteurTentativeIA = 0; // initialise a 0
    // [0] combi secrete IA [1] proposition IA [2] difference joueur.secret & IA.proposition ( aStringDonneeJoueur[i][2] = aStringDonneeJoueur[i][1] compare aStringDonneeIA[i][0] )
    public String[][] aStringDonneeIA = new String[tailleCombinaison][3];


    //----------------------------------------------------------
    //----------------- variable pour le joueur ----------------
    //----------------------------------------------------------
    public int combinaisonSecretejoueur;
    final public int tentativeJoueur = 8; // default 8 & devra pointer sur un XML de config
    public int compteurTentativeIJoueur = 0; // initialise a 0
    public String[][] aStringDonneeJoueur = new String[tailleCombinaison][3];

    //constructeur
    public ModeDeJeu(Mode mode) {
        this.mode = mode;
    }

    //methodes
    public int generate() {
        Random randomNb = new Random();
        int random = randomNb.nextInt();
        if (String.valueOf(random).length() > tailleCombinaison) {
            String randomTemp = String.valueOf(random).substring(String.valueOf(random).length() - tailleCombinaison);
            random = Integer.parseInt(randomTemp);
            return random;
        }
        return random;
    }

    public void storePropositionJoueur(String proposition) {
        //creer un tableau temp pour stocker la combinaison afin de la mettre dans le tableau joueur
        String[] aStringPropositionTemp = new String[tailleCombinaison];
        aStringPropositionTemp = String.valueOf(proposition).split("");
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringDonneeJoueur[i][1] = aStringPropositionTemp[i];
        }
    }

    public void storeSecretJoueur(String secret) {
        combinaisonSecretejoueur = Integer.parseInt(secret);
        //creer un tableau temp pour stocker le secret afin de le mettre dans le tableau joueur
        String[] aStringSecretTemp = new String[tailleCombinaison];
        aStringSecretTemp = String.valueOf(secret).split("");
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringDonneeJoueur[i][0] = aStringSecretTemp[i];
        }
    }

    public String compareJoueurXIA() {
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            // cast String en Int pour effectuer la comparaison
            if (Integer.parseInt(aStringDonneeJoueur[i][1]) < Integer.parseInt(aStringDonneeIA[i][0])) {
                aStringDonneeJoueur[i][2] = "+";
            } else if (Integer.parseInt(aStringDonneeJoueur[i][1]) > Integer.parseInt(aStringDonneeIA[i][0])) {
                aStringDonneeJoueur[i][2] = "-";
            } else {
                aStringDonneeJoueur[i][2] = "=";
            }
        }
        // Creer un tableau temporaire pour stocker la reponse uniquement afin de la retourner
        String[] aStringReponseTemp = new String[tailleCombinaison];
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringReponseTemp[i] = aStringDonneeJoueur[i][2];
        }
        // prend chaque valeurs du tableau et les retranscript  en un string
        String reponse = String.join("", (aStringReponseTemp));
        return reponse;
    }


    public void storeSecretIA(int secret) {
        combinaisonSecreteIA = secret;
        //creer un tableau temp pour stocker le secret afin de le mettre dans le tableau IA
        String[] aStringSecretTemp = new String[tailleCombinaison];
        aStringSecretTemp = String.valueOf(secret).split("");

        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringDonneeIA[i][0] = aStringSecretTemp[i];
        }
    }

    public void storePropositionIA(String proposition) {
        //creer un tableau temp pour stocker la combinaison afin de la mettre dans le tableau IA
        String[] aStringPropositionTemp = new String[tailleCombinaison];
        aStringPropositionTemp = String.valueOf(proposition).split("");
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringDonneeIA[i][1] = aStringPropositionTemp[i];
        }
    }

    public String compareIAXJoueur() {
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            // cast String en Int pour effectuer la comparaison
            if (Integer.parseInt(aStringDonneeIA[i][1]) < Integer.parseInt(aStringDonneeJoueur[i][0])) {
                aStringDonneeIA[i][2] = "+";
            } else if (Integer.parseInt(aStringDonneeIA[i][1]) > Integer.parseInt(aStringDonneeJoueur[i][0])) {
                aStringDonneeIA[i][2] = "-";
            } else {
                aStringDonneeIA[i][2] = "=";
            }
        }
        // Creer un tableau temporaire pour stocker la reponse uniquement afin de la retourner
        String[] aStringReponseTemp = new String[tailleCombinaison];
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringReponseTemp[i] = aStringDonneeIA[i][2];
        }
        // prend chaque valeurs du tableau et les retranscript  en un string
        String reponse = String.join("", (aStringReponseTemp));
        return reponse;
    }

    public String newPropositionIA() {
        String[] aStringNewPropositionIA = new String[tailleCombinaison];
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            int newPropositionDigit;
            int oldProposition = Integer.parseInt(aStringDonneeIA[i][1]);

            switch (aStringDonneeIA[i][2]) {
                case "-":
                    // enleve 1 de la precedente proposition
                    oldProposition--;
                    newPropositionDigit = oldProposition;
                    aStringNewPropositionIA[i] = String.valueOf(newPropositionDigit);
                    break;
                case "+":
                    // ajoute 1 de la precedente proposition
                    oldProposition++;
                    newPropositionDigit = oldProposition;
                    aStringNewPropositionIA[i] = String.valueOf(newPropositionDigit);
                    break;
                case "=":
                    //proposition reste inchangée
                    newPropositionDigit = oldProposition;
                    aStringNewPropositionIA[i] = String.valueOf(newPropositionDigit);
                    break;
            }
        }
        String newPurpose = String.join("", aStringNewPropositionIA);
        return newPurpose;
    }
}





