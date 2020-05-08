package fr.gameplayStudio;

import java.util.Scanner;

import static fr.gameplayStudio.Mode.*;

public class App {
    public static void main(String[] args) {

        ModeDeJeu challenger = new Challenger(CHALLENGER);
        ModeDeJeu defenseur = new Defenseur(DEFENSEUR);
        ModeDeJeu duel = new Duel(DUEL);

        // initialisation du scanner pour le  jeu en console
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez selectionner un mode de jeu : (" + CHALLENGER.ordre + ") " + CHALLENGER + " | (" + DEFENSEUR.ordre + ") " + DEFENSEUR + " | (" + DUEL.ordre + ") " + DUEL + "");
        int choixModeDeJeu = sc.nextInt();
        sc.nextLine();
        // choix du mode de jeu
        ModeDeJeu modeSellectionne = null;
        if (choixModeDeJeu == CHALLENGER.ordre) {
            System.out.println("Vous avez selectionner le mode : " + CHALLENGER + "");
            modeSellectionne = challenger;
        } else if (choixModeDeJeu == DEFENSEUR.ordre) {
            System.out.println("Vous avez selectionner le mode : " + DEFENSEUR + "");
            modeSellectionne = defenseur;
        } else if (choixModeDeJeu == DUEL.ordre) {
            System.out.println("Vous avez selectionner le mode : " + DUEL + "");
            modeSellectionne = duel;
        } else {
            System.out.println("Le mode de jeu selectionné n'existe pas");
        }
        modeSellectionne.generate();// genere un nombre secret
        modeSellectionne.storeCombinaisonSecrete();
        System.out.println("Vous devez devinez la combinaison secrete, celle qui comporte " + modeSellectionne.tailleCombinaisonSecrete + " chiffres. c'est à vous de jouer.");
        // DEV MODE LIGNE SUIVANTE A SUPPRIMER //
        System.out.println(modeSellectionne.combinaisonSecrete);
        boolean invalide = true;
        while (invalide) {
            String proposition = sc.nextLine(); // proposition du joueur
            modeSellectionne.storeProposition(proposition); //stock la proposition dans un tableau
            System.out.println("Proposition : " + proposition + "  -> Réponse : " + modeSellectionne.compare() + "");
            String combinaisonSecrete=String.valueOf(modeSellectionne.combinaisonSecrete);
            if (proposition.equals(combinaisonSecrete)) {
                invalide = false;
            }
        }
    }
}