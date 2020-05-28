package fr.gameplayStudio.gameMode;

public enum ModeEnum {
    CHALLENGER(1),DEFENSEUR(2),DUEL(3);
    public int ordre;

    ModeEnum(int ordre) {
        this.ordre = ordre;
    }
}
