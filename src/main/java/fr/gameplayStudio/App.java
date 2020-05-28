package fr.gameplayStudio;

import fr.gameplayStudio.config.XmlConstants;
import fr.gameplayStudio.config.XmlManager;
import fr.gameplayStudio.gameMode.Challenger;
import fr.gameplayStudio.gameMode.Defenseur;
import fr.gameplayStudio.gameMode.Duel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

import static fr.gameplayStudio.gameMode.ModeEnum.*;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String devResponse = null;
        XmlManager xmlManager = new XmlManager();
        XmlConstants xmlConstants = new XmlConstants();

        boolean devAnswered = false;

        do {
            System.out.println("Activer le mode devellopeur ? (O)ui ou (N)on");
            devResponse = sc.nextLine();
            if (devResponse.equalsIgnoreCase("O") | devResponse.equalsIgnoreCase("oui")) {
                xmlManager.setDevValue("true");
                xmlConstants.setDevMode(true);
                devAnswered = true;
            } else if (devResponse.equalsIgnoreCase("N") | devResponse.equalsIgnoreCase("non")) {
                xmlManager.setDevValue("false");
                xmlConstants.setDevMode(false);
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
                sc.nextLine();// consomme le saut de ligne après un nextint.
            } catch (InputMismatchException ime) {
                System.out.println("veuillez n'utiliser que des chiffres pour le choix des modes.");
            }


            if (choixModeDeJeu == CHALLENGER.ordre) {
                    Challenger challenger = new Challenger(CHALLENGER);
                    challenger.play(xmlConstants.isDevMode());

            } else if (choixModeDeJeu == DEFENSEUR.ordre) {

                    Defenseur defenseur = new Defenseur(DEFENSEUR);
                    defenseur.play(xmlConstants.isDevMode());

            } else if (choixModeDeJeu == DUEL.ordre) {
                    Duel duel = new Duel(DUEL);
                    duel.play(xmlConstants.isDevMode());
            } else {
                System.out.println("Le mode de jeu selectionné n'existe pas. Veuillez réessayer !");
            }
        }
    }
}

