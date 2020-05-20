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
        int secretIA = this.generate();
        String sSecretIA = String.valueOf(secretIA);
        if (sSecretIA.length() != this.tailleCombinaison) {
            do {
                secretIA = this.generate();
            } while (sSecretIA.length() != this.tailleCombinaison);
        }
        this.storeSecretIA(secretIA); // stock dans le tableau IA la combinaison secrete
        System.out.println("Vous devez devinez la combinaison secrete, celle qui comporte " + this.tailleCombinaison + " chiffres. c'est à vous de jouer.");
        // si le mode devellopeur est activé, ecrire dans la console la combinaison secrete generée
        if (this.devMode) {
            System.out.println("Devellopeur mode Activé :  combi secrete = " + this.combinaisonSecreteIA);
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
            if (proposition.length() != this.tailleCombinaison) {
                System.out.println("votre proposition ne comporte pas le nombre de chiffre attendu (" + this.tailleCombinaison + ")");
            } else {
                this.storePropositionJoueur(proposition);
                String reponseJoueur = this.compareJoueurXIA();
                System.out.println("Proposition : " + proposition + "  -> Réponse : " + reponseJoueur + "");
                String combinaisonSecrete = String.valueOf(this.combinaisonSecreteIA);
                if (proposition.equals(combinaisonSecrete)) {
                    System.out.println("Bravo! vous avez trouver la combinaison secrete.");
                    valide = true;
                }
            }
        }
    }


    @Override
    public int generate() {
        return super.generate();
    }

    @Override
    public void storePropositionJoueur(String proposition) {
        super.storePropositionJoueur(proposition);
    }

    @Override
    public void storeSecretJoueur(String secret) {
        super.storeSecretJoueur(secret);
    }

    @Override
    public String compareJoueurXIA() {
        return super.compareJoueurXIA();
    }

    @Override
    public void storeSecretIA(int secret) {
        super.storeSecretIA(secret);
    }

    @Override
    public void storePropositionIA(String proposition) {
        super.storePropositionIA(proposition);
    }

    @Override
    public String compareIAXJoueur() {
        return super.compareIAXJoueur();
    }
}
