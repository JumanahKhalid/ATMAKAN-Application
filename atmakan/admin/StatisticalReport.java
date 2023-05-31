package com.example.atmakan.admin;

public class StatisticalReport {
    private int emotions;
    private int interactive;
    private int entertainment;

    public StatisticalReport(int emotions, int interactive, int entertainment) {
        this.emotions = emotions;
        this.interactive = interactive;
        this.entertainment = entertainment;
    }

    public StatisticalReport() {
    }

    public int getEmotions() {
        return emotions;
    }

    public void setEmotions(int emotions) {
        this.emotions = emotions;
    }

    public int getInteractive() {
        return interactive;
    }

    public void setInteractive(int interactive) {
        this.interactive = interactive;
    }

    public int getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(int entertainment) {
        this.entertainment = entertainment;
    }
}

