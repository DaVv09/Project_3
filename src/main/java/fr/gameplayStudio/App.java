package fr.gameplayStudio;

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
                // mise a 0 des données IA et joueur
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
                    // genere un nombre aleatoire et ne garde que X digit en fonction de la taille
                    int secretIA = duel.generate();
                    duel.storeSecretIA(secretIA); // stock dans le tableau IA la combinaison secrete
                    //
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
                    // ############################################################################################
                    // TODO debut de boucle
                    boolean valide=false;
                    do {
                        if (tour == 0) { // joueur commence
                            System.out.println("c'est au joueur de commencer a jouer");
                            // ############################################################################################
                            // TODO tour du joueur
                            System.out.println("L'ordinateur a creer une combinaison secrete de (" + duel.tailleCombinaison + ") chiffres.");
                            System.out.println("Veuillez proposer une combinaison de (" + duel.tailleCombinaison + ") chiffres.");
                            // proposition du joueur/
                            String propositionJoueur = sc.nextLine();
                            // si la proposition comporte +/- de digits demander, redemmander une nouvelle proposition
                            if (propositionJoueur.length() != duel.tailleCombinaison) {
                                System.out.println("votre proposition ne comporte pas le nombre de chiffre attendu (" + duel.tailleCombinaison + ")");
                            } else {
                                duel.storePropositionJoueur(propositionJoueur);
                                String reponseJoueur = duel.compareJoueurXIA();
                                System.out.println("Proposition : " + propositionJoueur + "  -> Réponse : " + reponseJoueur + "");
                                String combinaisonSecrete = String.valueOf(duel.combinaisonSecreteIA);
                                if (propositionJoueur.equals(combinaisonSecrete)) {
                                    System.out.println("Bravo! vous avez trouver la combinaison secrete.");
                                    valide=true;
                                }
                            }
                            tour = 1; // prochain tour sera pour l'ordinateur
                        } else if (tour == 1) { // ordi commence
                            System.out.println("c'est a l'ordinateur de commencer a jouer");
                            // ############################################################################################
                            // TODO tour DE  L'IA
                            // genere un nombre aleatoire de xxxx digit en fonction de la taille de la combinaison
                            int propositionIA = 0;
                            String sPropositionIA = null;
                            if (firstime) {
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
                            System.out.println("votre combinaision secrete :" + combiSecreteJoueur + " la suggestion de l'ordi : " + propositionIA + " (" + reponse + ")");
                            if (String.valueOf(propositionIA).equals(combiSecreteJoueur)) {
                                System.out.println("Dommage! vous avez perdu. l'ordinateur a trouver votre combinaison secrete avant vous.");
                                valide=true;
                            }
                            tour = 0;// prochain tour sera pour le joueur
                        }
                    }while(!valide);

                        // ############################################################################################
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
