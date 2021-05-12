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

    public int käik(int tikkudeArv) { // meetod, kus tehakse tikkude valik ning see tagastatakse
        int tikud; // väli tikkude valiku kohta
        while (true) {
            tikud = tikkudeArvComboBox.getSelectionModel().getSelectedIndex()+1;
            if (tikud > tikkudeArv) { // kui tikkude valik oli rohkem kui alles on siis GUI näitab seda kasutajale
                tikkudeError.setText("Nii palju tikke enam pole! Vali vähem tikke.");

            } else if (tikud > 3 || tikud < 1) { // kui kuidagi valitakse rohkem kui 3 või vähem kui 1 tikku, siis GUI näitab vastavat kirjet kasutajale
                JOptionPane.showMessageDialog(null, "Vali tikkude arv vahemikust 1-3!",
                        "Väär valik", JOptionPane.ERROR_MESSAGE);
            } else break;
        }
        return tikud;
    }
}
