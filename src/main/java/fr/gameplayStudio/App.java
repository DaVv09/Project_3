package fr.gameplayStudio;

import java.util.Scanner;

import static fr.gameplayStudio.Mode.*;

public class App {
    public static void main(String[] args) {



        Scanner sc = new Scanner(System.in);
        //changer de mode de jeu
        boolean changer = true;
        while (changer) {
            System.out.println("Veuillez selectionner un mode de jeu : (" + CHALLENGER.ordre + ") " + CHALLENGER + " | (" + DEFENSEUR.ordre + ") " + DEFENSEUR + " | (" + DUEL.ordre + ") " + DUEL + "");
            int choixModeDeJeu = sc.nextInt();
            sc.nextLine();


            if (choixModeDeJeu == CHALLENGER.ordre) {
                ModeDeJeu challenger = new Challenger(CHALLENGER);
                System.out.println("Vous avez selectionner le mode : " + CHALLENGER + "");
                //rejouer
                boolean rejouer = true;
                while (rejouer) {
                    challenger.generate();// genere un nombre secret
                    challenger.combinaisonSecrete = challenger.random;
                    System.out.println("Vous devez devinez la combinaison secrete, celle qui comporte " + challenger.tailleCombinaisonSecrete + " chiffres. c'est à vous de jouer.");

                    if (challenger.modeDevellopeur) {
                        System.out.println("Devellopeur mode :  combi secrete = " + challenger.combinaisonSecrete);
                    }

                    boolean invalide = true;

                    while (invalide) {
                        String proposition = sc.nextLine(); // proposition du joueur
                        String reponse = challenger.storeData(proposition); // stock la proposition & secret  et donne la reponse dans un tableau
                        System.out.println("Proposition : " + proposition + "  -> Réponse : " + reponse + "");
                        String combinaisonSecrete = String.valueOf(challenger.combinaisonSecrete);
                        if (proposition.equals(combinaisonSecrete)) {
                            invalide = false;
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


//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            } else if (choixModeDeJeu == DEFENSEUR.ordre) {
                ModeDeJeu defenseur = new Defenseur(DEFENSEUR);
                System.out.println("Vous avez selectionner le mode : " + DEFENSEUR + "");
                //rejouer
                boolean rejouer = true;
                while (rejouer) {
                    //init nombre d'essai
                    defenseur.compteurEssai = 0;
                    System.out.println("Vous devez definir la combinaison secrete (seulement des chiffres), l'ordinateur devra retrouver celle ci.");
                    String combiSecrete = sc.nextLine();
                    defenseur.combinaisonSecrete = (Integer.parseInt(combiSecrete)); // stock notre combinaison secrete
                    boolean invalide = true;
                    defenseur.generate(); // genere un nombre aleatoire de xxxx digit
                    String iaPurposal = String.valueOf(defenseur.random); // genere un nombre aleatoire de xxxx digit
                    //compteur d'essai incrémenter une fois la 1er proposition generer
                    defenseur.compteurEssai++;
                    while (invalide) {
                        String reponse = defenseur.storeData(iaPurposal); // stock  secret & proposition & compare -> reponse
                        System.out.println("votre combinaision secrete :" + combiSecrete + " la suggestion de l'ordi : " + iaPurposal + " (" + reponse + ")");
                        String combinaisonSecrete = String.valueOf(defenseur.combinaisonSecrete);
                        if (iaPurposal.equals(combinaisonSecrete)) {
                            invalide = false;
                        } else {
                            if (defenseur.compteurEssai >= defenseur.nbEssai) {
                                System.out.println("Bravo, vous avez battu l'ordinateur. il n'a pas réussit a retrouver votre combinaison en moins de " + defenseur.nbEssai + " essais.");
                                break;
                            } else {
                                iaPurposal = defenseur.newPurpose();  // regenere une nouvelle reponse en fonction de la reponse +/-/=
                                defenseur.compteurEssai++;
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
                }


//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            } else if (choixModeDeJeu == DUEL.ordre) {
                ModeDeJeu duel = new Duel(DUEL);
                System.out.println("Vous avez selectionner le mode : " + DUEL + "");
                boolean rejouer = true;
                while (rejouer) {





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
