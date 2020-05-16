package fr.gameplayStudio;

public enum Mode {
    CHALLENGER(1),DEFENSEUR(2),DUEL(3);
    public int ordre;

    Mode(int ordre) {
        this.ordre = ordre;
    }
}
