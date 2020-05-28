package fr.gameplayStudio.config;

public class XmlConstants {

    private int tailleCombinaison=4; //default
    private boolean devMode=false;
    private int tentative=8;

    public int getTailleCombinaison() {
        return tailleCombinaison;
    }

    public void setTailleCombinaison(int tailleCombinaison) {
        this.tailleCombinaison = tailleCombinaison;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }

    public int getTentative() {
        return tentative;
    }

    public void setTentative(int tentativeIA) {
        this.tentative = tentativeIA;
    }
}

