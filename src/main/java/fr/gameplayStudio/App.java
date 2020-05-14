package fr.gameplayStudio;

import java.util.Scanner;

import static fr.gameplayStudio.Mode.*;

public class App {
    public static void main(String[] args) {

        //rejouer
        boolean rejouer = true;
        //changer de mode de jeu
        boolean changer = true;


        Scanner sc = new Scanner(System.in);

        while (changer) {
            System.out.println("Veuillez selectionner un mode de jeu : (" + CHALLENGER.ordre + ") " + CHALLENGER + " | (" + DEFENSEUR.ordre + ") " + DEFENSEUR + " | (" + DUEL.ordre + ") " + DUEL + "");
            int choixModeDeJeu = sc.nextInt();
            sc.nextLine();


            if (choixModeDeJeu == CHALLENGER.ordre) {
                ModeDeJeu challenger = new Challenger(CHALLENGER);
                System.out.println("Vous avez selectionner le mode : " + CHALLENGER + "");

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
                    String recommencer;
                    do {
                        System.out.println("voulez-vous (R)ejouer ? (C)hanger de mode ? (Q)uitter ?");
                        recommencer = sc.nextLine();
                        if (recommencer.equalsIgnoreCase("R") | recommencer.equalsIgnoreCase("Rejouer")) {
                            rejouer = true;
                            changer = false;
                        } else if (recommencer.equalsIgnoreCase("C") | recommencer.equalsIgnoreCase("Changer")) {
                            rejouer = false;
                            changer = true;
                        } else if (recommencer.equalsIgnoreCase("Q") | recommencer.equalsIgnoreCase("Quitter")) {
                            rejouer = false;
                            changer = false;
                        } else {
                            System.out.println("Désolé, je n'ai pas compris votre choix.");
                        }
                    }
                    while (!recommencer.equalsIgnoreCase("Q") | !recommencer.equalsIgnoreCase("Quitter")|!recommencer.equalsIgnoreCase("R") | !recommencer.equalsIgnoreCase("Rejouer")|!recommencer.equalsIgnoreCase("C") | !recommencer.equalsIgnoreCase("Changer"));
                }

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            } else if (choixModeDeJeu == DEFENSEUR.ordre) {
                ModeDeJeu defenseur = new Defenseur(DEFENSEUR);
                System.out.println("Vous avez selectionner le mode : " + DEFENSEUR + "");
                while (rejouer) {
                    System.out.println("Vous devez definir la combinaison secrete (seulement des chiffres), l'ordinateur devra retrouver celle ci.");
                    String combiSecrete = sc.nextLine();
                    defenseur.combinaisonSecrete = (Integer.parseInt(combiSecrete)); // stock notre combinaison secrete
                    boolean invalide = true;
                    defenseur.generate(); // genere un nombre aleatoire de xxxx digit
                    String iaPurposal = String.valueOf(defenseur.random); // genere un nombre aleatoire de xxxx digit
                    // System.out.println(iaPurposal);
                    while (invalide) {
                        String reponse = defenseur.storeData(iaPurposal); // stock  secret & proposition & compare -> reponse
                        System.out.println("votre combinaision secrete :" + combiSecrete + " la suggestion de l'ordi : " + iaPurposal + " (" + reponse + ")");
                        String combinaisonSecrete = String.valueOf(defenseur.combinaisonSecrete);
                        if (iaPurposal.equals(combinaisonSecrete)) {
                            invalide = false;
                        } else {
                            iaPurposal = defenseur.newPurpose();  // regenere une nouvbelle reponse en fonction de la reponse +/-/=
                        }
                    }
                    System.out.println("voulez-vous (R)ejouer ? (C)hanger de mode ? (Q)uitter ?");
                    String recommencer = sc.nextLine();
                    if (recommencer.equalsIgnoreCase("R") | recommencer.equalsIgnoreCase("Rejouer")) {
                        rejouer = true;
                        changer = false;
                    } else if (recommencer.equalsIgnoreCase("C") | recommencer.equalsIgnoreCase("Changer")) {
                        rejouer = false;
                        changer = true;
                    } else if (recommencer.equalsIgnoreCase("Q") | recommencer.equalsIgnoreCase("Quitter")) {
                        rejouer = false;
                        changer = false;
                    } else {
                        System.out.println("Désolé, je n'ai pas compris votre choix.");
                    }
                }


            } else if (choixModeDeJeu == DUEL.ordre) {
                ModeDeJeu duel = new Duel(DUEL);
                System.out.println("Vous avez selectionner le mode : " + DUEL + "");
            } else {
                System.out.println("Le mode de jeu selectionné n'existe pas");
            }


        }
    }
}
