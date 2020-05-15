package fr.gameplayStudio;

import javax.swing.*;
import java.util.Scanner;

import static fr.gameplayStudio.Mode.*;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean changer = true;
        while (changer) {
            //selection du mode de jeu
            System.out.println("Veuillez selectionner un mode de jeu : (" + CHALLENGER.ordre + ") " + CHALLENGER + " | (" + DEFENSEUR.ordre + ") " + DEFENSEUR + " | (" + DUEL.ordre + ") " + DUEL + "");
            int choixModeDeJeu = sc.nextInt();
            sc.nextLine();// consomme le saut de ligne après un nextint.
            // en fonction de la reponse fourni, execute le process pour le mode choisi.
            if (choixModeDeJeu == CHALLENGER.ordre) {
                Challenger challenger = new Challenger(CHALLENGER);
                System.out.println("Vous avez selectionner le mode : " + CHALLENGER + "");
                //rejouer tant que  rejouer = true
                // on reste dans la boucle pour execute de nouveau le process du jeu
                boolean rejouer = true;
                while (rejouer) {
                    // genere un nombre aleatoire et ne garde que X digit en fonction de la taille
                    int secretIA = challenger.generate();
                    challenger.storeSecretIA(secretIA); // stock dans le tableau IA la combinaison secrete
                    // joueur joue
                    System.out.println("Vous devez devinez la combinaison secrete, celle qui comporte " + challenger.tailleCombinaison + " chiffres. c'est à vous de jouer.");
                    // si le mode devellopeur est activé, ecrire dans la console la combinaison secrete generée
                    if (challenger.modeDevellopeur) {
                        System.out.println("Devellopeur mode :  combi secrete = " + challenger.combinaisonSecreteIA);
                    }
                    //creer une variable boolean afin de creer une boucle et de re demander de tester une nouvelle propositions jusqu'a ce qu'elle soit valide.
                    String proposition;
                    boolean valide = false;
                    while (!valide) {
                        // proposition du joueur
                        proposition = sc.nextLine();
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
                    }
                    while (!ok);

                }

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            } else if (choixModeDeJeu == DEFENSEUR.ordre) {
                Defenseur defenseur = new Defenseur(DEFENSEUR);
                System.out.println("Vous avez selectionner le mode : " + DEFENSEUR + "");
                //rejouer
                boolean rejouer = true;
                while (rejouer) {
                    //re-init nombre d'essai
                    defenseur.compteurTentativeIA = 0;
                    System.out.println("Vous devez definir la combinaison secrete (seulement des chiffres), l'ordinateur devra retrouver celle ci.");
                    String combiSecreteJoueur = sc.nextLine();
                    defenseur.storeSecretJoueur(combiSecreteJoueur);
                    boolean valide = false;
                    // genere un nombre aleatoire de xxxx digit en fonction de la taille de la combinaison
                    int initialPurposeIA = defenseur.generate();
                    //Stock la proposition initiale de l'IA dans son tableau & cast en String
                    defenseur.storePropositionIA(String.valueOf(initialPurposeIA));
                    // retourne la comparaison des deux tableaux ( joueur (secret) / IA(Proposition) )
                    String reponse = defenseur.compareIAXJoueur();
                    //compteur d'essai incrémenter une fois la 1er proposition generer
                    defenseur.compteurTentativeIA++;
                    System.out.println("votre combinaision secrete :" + combiSecreteJoueur + " la suggestion de l'ordi : " + initialPurposeIA + " (" + reponse + ")");
                    while (!valide) {
                        String newPropositionIA = null;
                        //genere une nouvelle proposition en fonction de la reponse +/-
                        newPropositionIA = defenseur.newPropositionIA();
                        // stock la proposition dans le tableau de données IA
                        defenseur.storePropositionIA(newPropositionIA);
                        // compare combinaison secrete du joueur et la nouvelle proposition de l'IA
                        String newReponse = defenseur.compareIAXJoueur();
                        System.out.println("votre combinaision secrete :" + combiSecreteJoueur + " la suggestion de l'ordi : " + newPropositionIA + " (" + newReponse + ")");
                        if (combiSecreteJoueur.equals(newPropositionIA)) {
                            // TODO : phrase bravo IA // IA pas capable + incrémentation du nombre de tentative
                            // TODO : check proposition/secret.lenght() dans chaque mode
                            valide = true;
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
                Duel duel = new Duel(DUEL);
                System.out.println("Vous avez selectionner le mode : " + DUEL + "");
                boolean rejouer = true;
                while (rejouer) {
                    // TODO : mode duel complet
                    //generation des combis secretes respective
                    duel.generate();// genere un nombre secret  pour l IA
                    // duel.combinaisonSecreteIA = duel.random; // stock le nombre generer dans la variable
                    System.out.println("Vous devez definir la combinaison secrete (seulement des chiffres), l'ordinateur devra retrouver celle ci.");
                    String combiSecreteJoueur = sc.nextLine();


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
                //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            } else {
                System.out.println("Le mode de jeu selectionné n'existe pas");
            }


        }
    }
}
