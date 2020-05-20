package fr.gameplayStudio;

import fr.gameplayStudio.config.XmlManager;

import java.util.Random;

public abstract class ModeDeJeu {


    XmlManager xmlManager=new XmlManager();

    //declaration des variables
    public Mode mode;
    public boolean devMode = Boolean.valueOf(xmlManager.getSettingsValue(0));
    public int tailleCombinaison = Integer.valueOf(xmlManager.getSettingsValue(1));

    //----------------------------------------------------------
    //------------------- variable pour l'IA -------------------
    //----------------------------------------------------------


    public int combinaisonSecreteIA;
    final public int tentativeIA=Integer.valueOf(xmlManager.getSettingsValue(2));
    public int compteurTentativeIA = 0; // initialise a 0
    // [0] combi secrete IA [1] proposition IA [2] difference joueur.secret & IA.proposition ( aStringDonneeJoueur[i][2] = aStringDonneeJoueur[i][1] compare aStringDonneeIA[i][0] )
    public String[][] aStringDonneeIA = new String[tailleCombinaison][3];


    //----------------------------------------------------------
    //----------------- variable pour le joueur ----------------
    //----------------------------------------------------------
    public int combinaisonSecretejoueur;
    final public int tentativeJoueur = Integer.valueOf(xmlManager.getSettingsValue(2));
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

    public int tour(){
        int tour=(int)Math.round(Math.random());
        return tour;
    }

    public void storePropositionJoueur(String proposition) {
        //creer un tableau temp pour stocker la combinaison afin de la mettre dans le tableau joueur
        String[] aStringPropositionTemp = new String[tailleCombinaison];
        aStringPropositionTemp = String.valueOf(proposition).split("");
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringDonneeJoueur[i][1] = aStringPropositionTemp[i];
        }
    }


    /**
     * creer un tableau temp pour stocker le secret afin de le mettre dans le tableau joueur
     * @param secret
     */
    public void storeSecretJoueur(String secret) {
        combinaisonSecretejoueur = Integer.parseInt(secret);
        String[] aStringSecretTemp = new String[tailleCombinaison];
        aStringSecretTemp = secret.split("");
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringDonneeJoueur[i][0] = aStringSecretTemp[i];
        }
    }

    /**
     *
     * @return  +/- en fonction de la difference entre la proposition du joueur et le secret de l IA
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
     * @param secret
     */
    public void storeSecretIA(int secret) {
        combinaisonSecreteIA = secret;
        //creer un tableau temp pour stocker le secret afin de le mettre dans le tableau IA
        String[] aStringSecretTemp = new String[tailleCombinaison];
        aStringSecretTemp = String.valueOf(secret).split("");

        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringDonneeIA[i][0] = aStringSecretTemp[i];
        }
    }

    /**
     * Stock la proposition de l'IA dans sont tableau afin de manipuler les données lors de la comparaison
     * @param proposition
     */
    public void storePropositionIA(String proposition) {
        //creer un tableau temp pour stocker la combinaison afin de la mettre dans le tableau IA
        //String[] aStringPropositionTemp = new String[tailleCombinaison];
        String[] aStringPropositionTemp = String.valueOf(proposition).split("");
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
        String[] aStringresponseTemp = new String[tailleCombinaison];
        for (int i = 0; i <= tailleCombinaison - 1; i++) {
            aStringresponseTemp[i] = aStringDonneeIA[i][2];
        }
        // prend chaque valeurs du tableau et les retranscript  en un string
        String response = String.join("", (aStringresponseTemp));
        return response;
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





