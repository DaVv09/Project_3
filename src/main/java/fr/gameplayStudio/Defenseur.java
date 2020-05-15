package fr.gameplayStudio;

public class Defenseur extends ModeDeJeu {
    public Defenseur(Mode mode) {
        super(mode);
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
