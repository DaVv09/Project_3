package fr.gameplayStudio.gameMode;

import fr.gameplayStudio.config.XmlConstants;

import java.util.Random;
import java.util.Scanner;

public abstract class ModeDeJeu {


    XmlConstants xmlConstants = new XmlConstants();

    //declaration des variables
    public ModeEnum modeEnum;
    public int tailleCombinaison = xmlConstants.getTailleCombinaison();
    public boolean devMode = xmlConstants.isDevMode();
    public int tentative = xmlConstants.getTentative();

    //----------------------------------------------------------
    //------------------- variable pour l'IA -------------------
    //----------------------------------------------------------


    public int combinaisonSecreteIA;
    public int compteurTentativeIA = 0; // initialise a 0
    // [0] combi secrete IA [1] proposition IA [2] difference joueur.secret & IA.proposition ( aStringDonneeJoueur[i][2] = aStringDonneeJoueur[i][1] compare aStringDonneeIA[i][0] )
    public String[][] aStringDonneeIA = new String[tailleCombinaison][3];


    //----------------------------------------------------------
    //----------------- variable pour le joueur ----------------
    //----------------------------------------------------------
    public int combinaisonSecretejoueur;
    public String[][] aStringDonneeJoueur = new String[tailleCombinaison][3];

    //constructeur
    public ModeDeJeu(ModeEnum modeEnum) {
        this.modeEnum = modeEnum;
    }

    /**
     * @return retourne un int aleatoire ayant pour taille celle definit dans le fichier de config( default 4)
     */
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

    /**
     * utiliser dans le mode duel afin de choisir la personne commencant a jouer.
     *
     * @return tour correspondand soit a 0 ou 1
     */
    public int tour() {
        int tour = (int) Math.round(Math.random());
        return tour;
    }

    /**
     * stock la proposition du joueur issu du scanner dans son tableau pour comparaison future
     *
     * @param proposition
     */
    public void storePropositionJoueur(String proposition) {
        //creer un tableau temp pour stocker la combinaison afin de la mettre dans le tableau joueur
        String[] aStringPropositionTemp = String.valueOf(proposition).split("");
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringDonneeJoueur[i][1] = aStringPropositionTemp[i];
        }
    }


    /**
     * creer un tableau temp pour stocker le secret afin de le mettre dans le tableau joueur
     *
     * @param secret
     */
    public void storeSecretJoueur(String secret) {
        combinaisonSecretejoueur = Integer.parseInt(secret);
        String[] aStringSecretTemp = secret.split("");
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringDonneeJoueur[i][0] = aStringSecretTemp[i];
        }
    }

    /**
     * @return +/- en fonction de la difference entre la proposition du joueur et le secret de l IA
     */
    public String compareJoueurXIA() {
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
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

    /**
     * stock la combinaison secrete de l IA dans un tableau appartenant a l'IA pour manipulation future
     *
     * @param secret
     */
    public void storeSecretIA(int secret) {
        combinaisonSecreteIA = secret;
        //creer un tableau temp pour stocker le secret afin de le mettre dans le tableau IA
        String[] aStringSecretTemp = String.valueOf(secret).split("");
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringDonneeIA[i][0] = aStringSecretTemp[i];
        }
    }

    /**
     * Stock la proposition de l'IA dans sont tableau afin de manipuler les données lors de la comparaison
     *
     * @param proposition
     */
    public void storePropositionIA(String proposition) {
        //creer un tableau temp pour stocker la combinaison afin de la mettre dans le tableau IA
        String[] aStringPropositionTemp = String.valueOf(proposition).split("");
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringDonneeIA[i][1] = aStringPropositionTemp[i];
        }
    }

    /**
     * compare la proposition de l'IA avec la combinaison secrete du joueur
     *
     * @return +/- en fonction des differences etablies
     */
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
        String[] aStringresponseTemp = new String[tailleCombinaison];
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringresponseTemp[i] = aStringDonneeIA[i][2];
        }
        // prend chaque valeurs du tableau et les retranscript  en un string
        String response = String.join("", (aStringresponseTemp));
        return response;
    }

    /**
     * @return une nouvelle proposition en fonction de la comparaison donné precedement
     */
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

    public void menu(int value,boolean devValue) {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        do {
            System.out.println("voulez-vous (R)ejouer ? (C)hanger de mode ? (Q)uitter ?");
            String recommencer = sc.nextLine();
            if (recommencer.equalsIgnoreCase("R") | recommencer.equalsIgnoreCase("Rejouer")) {
                switch (value){
                    case 1:
                        Challenger challenger=new Challenger(ModeEnum.CHALLENGER);
                        challenger.play(devValue);
                        break;
                    case 2:
                        Defenseur defenseur=new Defenseur(ModeEnum.DEFENSEUR);
                        defenseur.play(devValue);
                        break;
                    case 3:
                        Duel duel=new Duel(ModeEnum.DUEL);
                        duel.play(devValue);
                        break;
                }
                ok = true;
            } else if (recommencer.equalsIgnoreCase("C") | recommencer.equalsIgnoreCase("Changer")) {
                ok = true;
                break;
            } else if (recommencer.equalsIgnoreCase("Q") | recommencer.equalsIgnoreCase("Quitter")) {
                System.exit(0);
            } else {
                System.out.println("Désolé, je n'ai pas compris votre choix.");
                ok = false;
            }
        } while (!ok);
    }
}







