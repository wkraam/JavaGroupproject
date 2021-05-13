package oop;

import javax.swing.*;

public class Mängija extends MainMenu{
    private String nimi; // nime hoidmise väli

    public Mängija(String nimi) {
        this.nimi = nimi;
    } // konstruktor

    public Mängija() {
    }

    public String getNimi() {
        return nimi;
    } // nime tagastamise get meetod

    public int käik(int tikkudeArv) {
        return 0;
    }
}
