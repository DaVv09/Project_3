package fr.gameplayStudio;

public enum Mode {
    CHALLENGER(1),DEFENSEUR(2),DUEL(3);
    public Integer ordre;

    Mode(Integer ordre) {
        this.ordre = ordre;
    }
}
