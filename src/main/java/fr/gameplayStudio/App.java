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
                sc.nextLine();// consomme le saut de ligne après un nextint.
            } catch (InputMismatchException ime) {
                System.out.println("veuillez n'utiliser que des chiffres pour le choix des modes.");
            }


            if (choixModeDeJeu == CHALLENGER.ordre) {
                boolean rejouer=false;
                do {
                    Challenger challenger = new Challenger(CHALLENGER);
                    challenger.play();
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
                            System.exit(-1);
                            ok = true;
                        } else {
                            System.out.println("Désolé, je n'ai pas compris votre choix.");
                            ok = false;
                        }
                    } while (!ok);
                }while(rejouer);
            } else if (choixModeDeJeu == DEFENSEUR.ordre) {

                boolean rejouer=false;
                do{
                Defenseur defenseur = new Defenseur(DEFENSEUR);
                defenseur.play();
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
                        System.exit(-1);
                        ok = true;
                    } else {
                        System.out.println("Désolé, je n'ai pas compris votre choix.");
                        ok = false;
                    }
                } while (!ok);
            }while(rejouer);
            } else if (choixModeDeJeu == DUEL.ordre) {
                boolean rejouer=false;
                do{
                Duel duel = new Duel(DUEL);
                duel.play();
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
                        System.exit(-1);
                        ok = true;
                    } else {
                        System.out.println("Désolé, je n'ai pas compris votre choix.");
                        ok = false;
                    }
                } while (!ok);
                }while(rejouer);
            }else {
                System.out.println("Le mode de jeu selectionné n'existe pas. Veuillez réessayer !");
            }
        }
    }
}

