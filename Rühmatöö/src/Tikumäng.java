import javax.swing.JOptionPane;

public class Tikumäng {

    public static int käigud(int tikkude_arv, Mängija m1, Mängija m2) {
        tikkude_arv -= m1.käik(tikkude_arv);//kutsub välja Mängija klassis oleva funktsiooni käik ning lahutab vastava tikkude arvu kogu tikkude hulgast.
        JOptionPane.showMessageDialog(null, "Tikkude arv on " + tikkude_arv + ".");
        if (tikkude_arv == 0) {
            JOptionPane.showMessageDialog(null, "Võitis: " + m2.getNimi(), "VÕITJA!!!", JOptionPane.INFORMATION_MESSAGE);
        }//väljastab võitja
        return tikkude_arv;
    }

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Tikkude mäng \nKaotaja on see, kes valib viimasena tikud. \nValikus on võtta 1, 2 või 3 tikku.");

        Mängija mangija1;
        Mängija mangija2;

        while (true) {
            Object[] optionsPlayer = {"Arvuti", "Inimene"};
            int mangijaid = JOptionPane.showOptionDialog(null, "Vali, kelle vastu tahad mängida:", "Inimene või arvuti", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsPlayer, 0);

            if (mangijaid == 0) { //0 kui kasutada JOptionPane, 1 kui kasutada käsurida
                String mangija = JOptionPane.showInputDialog(null, "Sisesta mängija nimi", "Nime sisestus", JOptionPane.QUESTION_MESSAGE);
                mangija1 = new Mängija(mangija);
                Object[] optionsVastane = {"Kerge", "Raske"};
                int vastane = JOptionPane.showOptionDialog(null, "Vali arvuti raskusaste", "Inimene või arvuti", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsVastane, 0);
                if (vastane == 0) {
                    mangija2 = new MängijaComputer();
                } else {
                    mangija2 = new MängijaComputerHard();
                }
            } //1 mängija vs arvuti, loob mängijate isendid

            else { //1 kui kasutada JOptionPane, 2 kui kasutada käsurida
                String mangijaEsimene = JOptionPane.showInputDialog(null, "Sisesta esimese mängija nimi", "Nime sisestus", JOptionPane.QUESTION_MESSAGE);
                mangija1 = new Mängija(mangijaEsimene);
                String mangijaTeine = JOptionPane.showInputDialog(null, "Sisesta teise mängija nimi", "Nime sisestus", JOptionPane.QUESTION_MESSAGE);
                mangija2 = new Mängija(mangijaTeine);
            } //2 mängijat omavahel, loob mängijate isendid

            int tikkude_arv = 5 + (int) (Math.random() * 20);//suvaline tikkude arv
            JOptionPane.showMessageDialog(null, "Tikkude arv on " + tikkude_arv + ".");

            int a = (int) (Math.random() * 1000);//suvaline arv, et vaadata kumb alustab
            if (a % 2 == 0) {
                JOptionPane.showMessageDialog(null, "Alustab " + mangija1.getNimi());
            } else {
                JOptionPane.showMessageDialog(null, "Alustab " + mangija2.getNimi());
                tikkude_arv = käigud(tikkude_arv, mangija2, mangija1);
            }

            while (tikkude_arv > 0) {
                tikkude_arv = käigud(tikkude_arv, mangija1, mangija2);
                if (tikkude_arv > 0) tikkude_arv = käigud(tikkude_arv, mangija2, mangija1);
            }//käigud, kuni kumbki võidab

            Object[] valikud = {"Jah", "Ei"};
            int uus = JOptionPane.showOptionDialog(null, "Soovid uut mängu?", "Inimene või arvuti", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, valikud, 0);
            if (uus == 1) break;//kui ei soovita uut mängu, siis lõpetab programm töö
        }
    }
}
