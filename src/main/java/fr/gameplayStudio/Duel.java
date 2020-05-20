package fr.gameplayStudio;


import java.util.Scanner;

import static fr.gameplayStudio.Mode.DUEL;

public class Duel extends ModeDeJeu {

    public Duel(Mode mode) {
        super(mode);
    }

    public void play(){
        boolean devMode = Boolean.valueOf(xmlManager.getSettingsValue(0));
        Scanner  sc= new Scanner(System.in);
        System.out.println("Vous avez selectionner le mode : " + DUEL + "");
            int secretIA = generate();
            String sSecretIA = String.valueOf(secretIA);
            if (sSecretIA.length() != tailleCombinaison) {
                do {
                    secretIA = generate();
                } while (sSecretIA.length() != tailleCombinaison);
            }

        storeSecretIA(secretIA); // stock dans le tableau IA la combinaison secrete
            String combiSecreteJoueur;
            do {
                System.out.println("Vous devez definir une combinaison secrete comportant (" + tailleCombinaison + ") chiffres, l'ordinateur devra retrouver celle ci.");
                combiSecreteJoueur = sc.nextLine();
                if (combiSecreteJoueur.length() != tailleCombinaison) {
                    System.out.println("Votre proposition de combinaison secrete ne comporte pas le nombre de chiffres attendu (" + tailleCombinaison + ")");
                } else {
                    storeSecretJoueur(combiSecreteJoueur);
                    break;
                }
            } while (combiSecreteJoueur.length() != tailleCombinaison);
            int tour = tour();

            boolean firstime = true; // verifie si l'on a dejà executé une fois le script
            boolean valide = false;
            do {
                if (tour == 0) { // joueur commence
                    /**
                     * Tour du joueur
                     **/
                    if (firstime) {
                        System.out.println("L'ordinateur a creer une combinaison secrete de (" + tailleCombinaison + ") chiffres.");
                        if (devMode) {
                            System.out.println(" combinaison de l'IA : " + combinaisonSecreteIA);
                        }
                    }
                    System.out.println("Veuillez proposer une combinaison de (" + tailleCombinaison + ") chiffres. correspondant au secret de l'ordinateur");
                    // proposition du joueur/
                    String propositionJoueur = sc.nextLine();
                    // si la proposition comporte +/- de digits demander, redemmander une nouvelle proposition
                    if (propositionJoueur.length() != tailleCombinaison) {
                        System.out.println("votre proposition ne comporte pas le nombre de chiffre attendu (" + tailleCombinaison + ")");
                    } else {
                        storePropositionJoueur(propositionJoueur);
                        String reponseJoueur = compareJoueurXIA();
                        System.out.println();
                        System.out.println("(JOUEUR) Proposition : " + propositionJoueur + "  -> Réponse : " + reponseJoueur + "");
                        String combinaisonSecrete = String.valueOf(combinaisonSecreteIA);
                        if (propositionJoueur.equals(combinaisonSecrete)) {
                            System.out.println("Bravo! vous avez trouver la combinaison secrete.");
                            valide = true;
                        }
                    }
                    tour = 1; // prochain tour sera pour l'ordinateur
                } else if (tour == 1) { // ordi commence
                    /*
                     * tour de l'ia
                     */
                    int propositionIA = 0;
                    String sPropositionIA = null;
                    if (firstime) {
                        // genere un nombre aleatoire de xxxx digit en fonction de la taille de la combinaison
                        propositionIA = this.generate();
                        firstime = false;
                    } else {
                        sPropositionIA = this.newPropositionIA();
                        propositionIA = Integer.parseInt(sPropositionIA);
                    }
                    //Stock la proposition initiale de l'IA dans son tableau & cast en String
                    storePropositionIA(String.valueOf(propositionIA));
                    // retourne la comparaison des deux tableaux ( joueur (secret) / IA(Proposition) )
                    String reponse = compareIAXJoueur();
                    System.out.println();
                    System.out.println("(ORDINATEUR) Proposition IA : " + propositionIA + "  -> Réponse : " + reponse + " votre combinaison secrete: " + combinaisonSecretejoueur);
                    if (String.valueOf(propositionIA).equals(combiSecreteJoueur)) {
                        System.out.println("Dommage! vous avez perdu. l'ordinateur a trouver votre combinaison secrete avant vous.");
                        valide = true;
                    }
                    tour = 0;// prochain tour sera pour le joueur
                }
            } while (!valide);
        }
}







