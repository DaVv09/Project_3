package fr.gameplayStudio;

import java.util.Scanner;

import static fr.gameplayStudio.Mode.DEFENSEUR;

public class Defenseur extends ModeDeJeu {
    public Defenseur(Mode mode) {
        super(mode);
    }

    public void play() {
        boolean devMode = Boolean.valueOf(xmlManager.getSettingsValue(0));
        Scanner sc = new Scanner(System.in);
        System.out.println("Vous avez selectionner le mode : " + DEFENSEUR + "");
        //re-init nombre d'essai
        compteurTentativeIA = 0;
        String combiSecreteJoueur;
        do {
            System.out.println("Vous devez definir une combinaison secrete comportant (" + tailleCombinaison + ") chiffres, l'ordinateur devra retrouver celle ci.");
            combiSecreteJoueur = sc.nextLine();
            if (combiSecreteJoueur.length() != tailleCombinaison) {
                System.out.println("Votre proposition de combinaison secrete ne comporte pas le nombre de chiffres attendu (" + tailleCombinaison + ")");
            } else {
                break;
            }
        } while (combiSecreteJoueur.length() != tailleCombinaison);
        storeSecretJoueur(combiSecreteJoueur);
        boolean valide = false;

        /* genere un nombre aleatoire et ne garde que X digit en fonction de la taille combinaison
         * et s'assure que la combinaison généré comporte bien la bonne taille
         */
        int initialPurposeIA = generate();
        String sInitialPurposeIA = String.valueOf(initialPurposeIA);
        if (sInitialPurposeIA.length() != tailleCombinaison) {
            do {
                initialPurposeIA = generate();
            } while (sInitialPurposeIA.length() != tailleCombinaison);
        }

        //Stock la proposition initiale de l'IA dans son tableau & cast en String
        storePropositionIA(String.valueOf(initialPurposeIA));
        // retourne la comparaison des deux tableaux ( joueur (secret) / IA(Proposition) )
        String reponse = compareIAXJoueur();
        //compteur d'essai incrémenter une fois la 1er proposition generer
        compteurTentativeIA++;
        System.out.println("votre combinaision secrete :" + combiSecreteJoueur + " la suggestion de l'ordi : " + initialPurposeIA + " (" + reponse + ")");
        while (!valide) {
            String newPropositionIA;
            //genere une nouvelle proposition en fonction de la reponse +/-
            newPropositionIA = newPropositionIA();
            // stock la proposition dans le tableau de données IA
            storePropositionIA(newPropositionIA);
            // compare combinaison secrete du joueur et la nouvelle proposition de l'IA
            String newReponse = compareIAXJoueur();
            System.out.println("votre combinaision secrete :" + combiSecreteJoueur + " la suggestion de l'ordi : " + newPropositionIA + " (" + newReponse + ")");
            compteurTentativeIA++;
            if (combiSecreteJoueur.equals(newPropositionIA)) {
                System.out.println("Dommage! vous avez perdu : l'IA a été capable de trouver votre combinaison secrete en " + compteurTentativeIA + " tentative(s)");
                valide = true;
            } else if (compteurTentativeIA >= tentativeIA) {
                System.out.println("Bravo! vous avez gagné : l'IA n'a pas été capable de trouver votre combinaison en moins de " + tentativeIA + " tentatives");
                break;
            }
        }
    }
}
