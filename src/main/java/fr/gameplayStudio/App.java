package fr.gameplayStudio;

import fr.gameplayStudio.config.XmlManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

import static fr.gameplayStudio.Mode.*;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Logger logger = LogManager.getLogger(App.class);
        String devResponse = null;
        XmlManager xmlManager = new XmlManager();
        boolean devAnswered = false;
        do {
            System.out.println("Activer le mode devellopeur ? (O)ui ou (N)on");
            devResponse = sc.nextLine();
            if (devResponse.equalsIgnoreCase("O") | devResponse.equalsIgnoreCase("oui")) {
                xmlManager.setDevValue("true");
                devAnswered = true;
            } else if (devResponse.equalsIgnoreCase("N") | devResponse.equalsIgnoreCase("non")) {
                xmlManager.setDevValue("false");
                devAnswered = true;
            }
        } while (devAnswered = false);

        boolean changer = true;
        while (changer) {
            //selection du mode de jeu
            System.out.println("Veuillez selectionner un mode de jeu : (" + CHALLENGER.ordre + ") " + CHALLENGER + " | (" + DEFENSEUR.ordre + ") " + DEFENSEUR + " | (" + DUEL.ordre + ") " + DUEL + "");
            int choixModeDeJeu = 0;
            try {
                choixModeDeJeu = sc.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("veuillez n'utiliser que des chiffres pour le choix des modes.");
            }
            sc.nextLine();// consomme le saut de ligne après un nextint.
            // en fonction de la reponse fourni, execute le process pour le mode choisi.
            if (choixModeDeJeu == CHALLENGER.ordre) {
                Challenger challenger = new Challenger(CHALLENGER);
                challenger.play();
                /*System.out.println("Vous avez selectionner le mode : " + CHALLENGER + "");
                //rejouer tant que  rejouer = true
                // on reste dans la boucle pour execute de nouveau le process du jeu
                boolean rejouer = true;
                while (rejouer) {
                    *//* genere un nombre aleatoire et ne garde que X digit en fonction de la taille combinaison
                     * et s'assure que la combinaison généré comporte bien la bonne taille
                     *//*

                    int secretIA = challenger.generate();
                    String sSecretIA = String.valueOf(secretIA);
                    if (sSecretIA.length() != challenger.tailleCombinaison) {
                        do {
                            secretIA = challenger.generate();
                        } while (sSecretIA.length() != challenger.tailleCombinaison);
                    }
                    challenger.storeSecretIA(secretIA); // stock dans le tableau IA la combinaison secrete
                    // joueur joue
                    System.out.println("Vous devez devinez la combinaison secrete, celle qui comporte " + challenger.tailleCombinaison + " chiffres. c'est à vous de jouer.");
                    // si le mode devellopeur est activé, ecrire dans la console la combinaison secrete generée
                    if (challenger.devMode) {
                        System.out.println("Devellopeur mode Activé :  combi secrete = " + challenger.combinaisonSecreteIA);
                    }
                    //creer une variable boolean afin de creer une boucle et de re demander de tester une nouvelle propositions jusqu'a ce qu'elle soit valide.
                    String proposition = null;
                    boolean valide = false;
                    while (!valide) {
                        // proposition du joueur
                        try {
                            proposition = sc.nextLine();
                        } catch (NumberFormatException nfe) {
                            System.out.println("vous ne pouvez n'utiliser que des chiffres (0-9)");
                        }
                        // si la proposition comporte +/- de digits demander, redemmander une nouvelle proposition
                        if (proposition.length() != challenger.tailleCombinaison) {
                            System.out.println("votre proposition ne comporte pas le nombre de chiffre attendu (" + challenger.tailleCombinaison + ")");
                        } else {
                            challenger.storePropositionJoueur(proposition);
                            String reponseJoueur = challenger.compareJoueurXIA();
                            System.out.println("Proposition : " + proposition + "  -> Réponse : " + reponseJoueur + "");
                            String combinaisonSecrete = String.valueOf(challenger.combinaisonSecreteIA);
                            if (proposition.equals(combinaisonSecrete)) {
                                System.out.println("Bravo! vous avez trouver la combinaison secrete.");
                                valide = true;
                            }
                        }
                    }
                    boolean ok;
                    do {
                        System.out.println("voulez-vous (R)ejouer ? (C)hanger de mode ? (Q)uitter ?");
                        String recommencer = null;

                        try {
                            recommencer = sc.nextLine();
                        } catch (NumberFormatException nfe) {
                            System.out.println("les chiffres ne sont pas autorisés dans cette reponse");
                        }

                        if (recommencer.equalsIgnoreCase("R") | recommencer.equalsIgnoreCase("Rejouer")) {
                            rejouer = true;
                            changer = false;
                            ok = true;
                        } else if (recommencer.equalsIgnoreCase("C") | recommencer.equalsIgnoreCase("Changer")) {
                            rejouer = false;
                            changer = true;
                            ok = true;
                        } else if (recommencer.equalsIgnoreCase("Q") | recommencer.equalsIgnoreCase("Quitter")) {
                            rejouer = false;
                            changer = false;
                            ok = true;
                        } else {
                            System.out.println("Désolé, je n'ai pas compris votre choix.");
                            ok = false;
                        }
                    }
                    while (!ok);

                }*/

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            } else if (choixModeDeJeu == DEFENSEUR.ordre) {
                // mise a 0 des données IA et joueur
                Defenseur defenseur = new Defenseur(DEFENSEUR);
                System.out.println("Vous avez selectionner le mode : " + DEFENSEUR + "");
                //rejouer
                boolean rejouer = true;
                while (rejouer) {
                    //re-init nombre d'essai
                    defenseur.compteurTentativeIA = 0;
                    String combiSecreteJoueur;
                    do {
                        System.out.println("Vous devez definir une combinaison secrete comportant (" + defenseur.tailleCombinaison + ") chiffres, l'ordinateur devra retrouver celle ci.");
                        combiSecreteJoueur = sc.nextLine();
                        if (combiSecreteJoueur.length() != defenseur.tailleCombinaison) {
                            System.out.println("Votre proposition de combinaison secrete ne comporte pas le nombre de chiffres attendu (" + defenseur.tailleCombinaison + ")");
                        } else {
                            break;
                        }
                    } while (combiSecreteJoueur.length() != defenseur.tailleCombinaison);
                    defenseur.storeSecretJoueur(combiSecreteJoueur);
                    boolean valide = false;

                    /* genere un nombre aleatoire et ne garde que X digit en fonction de la taille combinaison
                     * et s'assure que la combinaison généré comporte bien la bonne taille
                     */
                    int initialPurposeIA = defenseur.generate();
                    String sInitialPurposeIA = String.valueOf(initialPurposeIA);
                    if (sInitialPurposeIA.length() != defenseur.tailleCombinaison) {
                        do {
                            initialPurposeIA = defenseur.generate();
                        } while (sInitialPurposeIA.length() != defenseur.tailleCombinaison);
                    }

                    //Stock la proposition initiale de l'IA dans son tableau & cast en String
                    defenseur.storePropositionIA(String.valueOf(initialPurposeIA));
                    // retourne la comparaison des deux tableaux ( joueur (secret) / IA(Proposition) )
                    String reponse = defenseur.compareIAXJoueur();
                    //compteur d'essai incrémenter une fois la 1er proposition generer
                    defenseur.compteurTentativeIA++;
                    System.out.println("votre combinaision secrete :" + combiSecreteJoueur + " la suggestion de l'ordi : " + initialPurposeIA + " (" + reponse + ")");
                    while (!valide) {
                        String newPropositionIA;
                        //genere une nouvelle proposition en fonction de la reponse +/-
                        newPropositionIA = defenseur.newPropositionIA();
                        // stock la proposition dans le tableau de données IA
                        defenseur.storePropositionIA(newPropositionIA);
                        // compare combinaison secrete du joueur et la nouvelle proposition de l'IA
                        String newReponse = defenseur.compareIAXJoueur();
                        System.out.println("votre combinaision secrete :" + combiSecreteJoueur + " la suggestion de l'ordi : " + newPropositionIA + " (" + newReponse + ")");
                        defenseur.compteurTentativeIA++;
                        if (combiSecreteJoueur.equals(newPropositionIA)) {
                            System.out.println("Dommage! vous avez perdu : l'IA a été capable de trouver votre combinaison secrete en " + defenseur.compteurTentativeIA + " tentative(s)");
                            valide = true;
                        } else if (defenseur.compteurTentativeIA >= defenseur.tentativeIA) {
                            System.out.println("Bravo! vous avez gagné : l'IA n'a pas été capable de trouver votre combinaison en moins de " + defenseur.tentativeIA + " tentatives");
                            break;
                        }
                    }
                    boolean ok;
                    do {
                        System.out.println("voulez-vous (R)ejouer ? (C)hanger de mode ? (Q)uitter ?");
                        String recommencer = sc.nextLine();
                        if (recommencer.equalsIgnoreCase("R") | recommencer.equalsIgnoreCase("Rejouer")) {
                            rejouer = true;
                            changer = false;
                            ok = true;
                        } else if (recommencer.equalsIgnoreCase("C") | recommencer.equalsIgnoreCase("Changer")) {
                            rejouer = false;
                            changer = true;
                            ok = true;
                        } else if (recommencer.equalsIgnoreCase("Q") | recommencer.equalsIgnoreCase("Quitter")) {
                            rejouer = false;
                            changer = false;
                            ok = true;
                        } else {
                            System.out.println("Désolé, je n'ai pas compris votre choix.");
                            ok = false;
                        }
                    } while (!ok);

                }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            } else if (choixModeDeJeu == DUEL.ordre) {
                // mise a 0 des données IA et joueur
                Duel duel = new Duel(DUEL);
                System.out.println("Vous avez selectionner le mode : " + DUEL + "");
                boolean rejouer = true;
                while (rejouer) {
                    // création des combinaisons secrete respectives

                    /* genere un nombre aleatoire et ne garde que X digit en fonction de la taille combinaison
                     * et s'assure que la combinaison généré comporte bien la bonne taille
                     */
                    int secretIA = duel.generate();
                    String sSecretIA = String.valueOf(secretIA);
                    if (sSecretIA.length() != duel.tailleCombinaison) {
                        do {
                            secretIA = duel.generate();
                        } while (sSecretIA.length() != duel.tailleCombinaison);
                    }

                    duel.storeSecretIA(secretIA); // stock dans le tableau IA la combinaison secrete
                    String combiSecreteJoueur;
                    do {
                        System.out.println("Vous devez definir une combinaison secrete comportant (" + duel.tailleCombinaison + ") chiffres, l'ordinateur devra retrouver celle ci.");
                        combiSecreteJoueur = sc.nextLine();
                        if (combiSecreteJoueur.length() != duel.tailleCombinaison) {
                            System.out.println("Votre proposition de combinaison secrete ne comporte pas le nombre de chiffres attendu (" + duel.tailleCombinaison + ")");
                        } else {
                            duel.storeSecretJoueur(combiSecreteJoueur);
                            break;
                        }
                    } while (combiSecreteJoueur.length() != duel.tailleCombinaison);
                    int tour = duel.tour();

                    boolean firstime = true; // verifie si l'on a dejà executé une fois le script
                    boolean valide = false;
                    do {
                        if (tour == 0) { // joueur commence
                            /**
                             * Tour du joueur
                             **/
                            if (firstime) {
                                System.out.println("L'ordinateur a creer une combinaison secrete de (" + duel.tailleCombinaison + ") chiffres.");
                                if (duel.devMode) {
                                    System.out.println(" combinaison de l'IA : " + duel.combinaisonSecreteIA);
                                }
                            }
                            System.out.println("Veuillez proposer une combinaison de (" + duel.tailleCombinaison + ") chiffres. correspondant au secret de l'ordinateur");
                            // proposition du joueur/
                            String propositionJoueur = sc.nextLine();
                            // si la proposition comporte +/- de digits demander, redemmander une nouvelle proposition
                            if (propositionJoueur.length() != duel.tailleCombinaison) {
                                System.out.println("votre proposition ne comporte pas le nombre de chiffre attendu (" + duel.tailleCombinaison + ")");
                            } else {
                                duel.storePropositionJoueur(propositionJoueur);
                                String reponseJoueur = duel.compareJoueurXIA();
                                System.out.println();
                                System.out.println("(JOUEUR) Proposition : " + propositionJoueur + "  -> Réponse : " + reponseJoueur + "");
                                String combinaisonSecrete = String.valueOf(duel.combinaisonSecreteIA);
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
                                propositionIA = duel.generate();
                                firstime = false;
                            } else {
                                sPropositionIA = duel.newPropositionIA();
                                propositionIA = Integer.parseInt(sPropositionIA);
                            }
                            //Stock la proposition initiale de l'IA dans son tableau & cast en String
                            duel.storePropositionIA(String.valueOf(propositionIA));
                            // retourne la comparaison des deux tableaux ( joueur (secret) / IA(Proposition) )
                            String reponse = duel.compareIAXJoueur();
                            System.out.println();
                            System.out.println("(ORDINATEUR) Proposition IA : " + propositionIA + "  -> Réponse : " + reponse + " votre combinaison secrete: " + duel.combinaisonSecretejoueur);
                            if (String.valueOf(propositionIA).equals(combiSecreteJoueur)) {
                                System.out.println("Dommage! vous avez perdu. l'ordinateur a trouver votre combinaison secrete avant vous.");
                                valide = true;
                            }
                            tour = 0;// prochain tour sera pour le joueur
                        }
                    } while (!valide);
                    boolean ok;
                    do {
                        System.out.println("voulez-vous (R)ejouer ? (C)hanger de mode ? (Q)uitter ?");
                        String recommencer = sc.nextLine();
                        if (recommencer.equalsIgnoreCase("R") | recommencer.equalsIgnoreCase("Rejouer")) {
                            rejouer = true;
                            changer = false;
                            ok = true;
                        } else if (recommencer.equalsIgnoreCase("C") | recommencer.equalsIgnoreCase("Changer")) {
                            rejouer = false;
                            changer = true;
                            ok = true;
                        } else if (recommencer.equalsIgnoreCase("Q") | recommencer.equalsIgnoreCase("Quitter")) {
                            rejouer = false;
                            changer = false;
                            ok = true;
                        } else {
                            System.out.println("Désolé, je n'ai pas compris votre choix.");
                            ok = false;
                        }
                    } while (!ok);
                }
            }
            //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            else {
                System.out.println("Le mode de jeu selectionné n'existe pas. Veuillez réessayer !");
            }
        }
    }
}

