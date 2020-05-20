package fr.gameplayStudio;

import java.util.Scanner;

import static fr.gameplayStudio.Mode.CHALLENGER;

public class Challenger extends ModeDeJeu {

    public Challenger(Mode mode) {
        super(mode);
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Vous avez selectionner le mode : " + CHALLENGER + "");
        //genere un nombre aleatoire et ne garde que X digit en fonction de la taille combinaison
        //et s 'assure que la combinaison généré comporte bien la bonne taille
        int secretIA = generate();
        String sSecretIA = String.valueOf(secretIA);
        if (sSecretIA.length() != tailleCombinaison) {
            do {
                secretIA = generate();
            } while (sSecretIA.length() != tailleCombinaison);
        }
        storeSecretIA(secretIA); // stock dans le tableau IA la combinaison secrete
        System.out.println("Vous devez devinez la combinaison secrete, celle qui comporte " + tailleCombinaison + " chiffres. c'est à vous de jouer.");
        // si le mode devellopeur est activé, ecrire dans la console la combinaison secrete generée
        if (devMode) {
            System.out.println("Devellopeur mode Activé :  combi secrete = " + combinaisonSecreteIA);
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
            if (proposition.length() != tailleCombinaison) {
                System.out.println("votre proposition ne comporte pas le nombre de chiffre attendu (" + tailleCombinaison + ")");
            } else {
                storePropositionJoueur(proposition);
                String reponseJoueur = compareJoueurXIA();
                System.out.println("Proposition : " + proposition + "  -> Réponse : " + reponseJoueur + "");
                String combinaisonSecrete = String.valueOf(combinaisonSecreteIA);
                if (proposition.equals(combinaisonSecrete)) {
                    System.out.println("Bravo! vous avez trouver la combinaison secrete.");
                    valide = true;
                }
            }
        }
    }
}

