import javax.swing.JOptionPane;

public class Tikumäng {

    public static int käigud(int tikkude_arv, Mängija m1, Mängija m2) {  // meetod, kus tehakse mängija käik
        tikkude_arv -= m1.käik(tikkude_arv);//kutsub välja Mängija klassis oleva funktsiooni käik ning lahutab vastava tikkude arvu kogu tikkude hulgast.
        JOptionPane.showMessageDialog(null, "Tikkude arv on " + tikkude_arv + "."); //GUI osa, kus väljastatakse kasutajale, palju on tikke alles
        if (tikkude_arv == 0) {
            JOptionPane.showMessageDialog(null, "Võitis: " + m2.getNimi(), "VÕITJA!!!", JOptionPane.INFORMATION_MESSAGE); //GUI osa, kus näidatakse, kes võitis
        }//väljastab võitja
        return tikkude_arv;
    }

    public static void main(String[] args) {
        //GUI osa, mis selgitab kasutajale, kuidas mäng toimib
        JOptionPane.showMessageDialog(null,
                "Tikkude mäng \nKaotaja on see, kes valib viimasena tikud. \nValikus on võtta 1, 2 või 3 tikku.");

        Mängija mangija1;
        Mängija mangija2;

        while (true) {
            Object[] optionsPlayer = {"Arvuti", "Inimene"};
            // GUI osa, kus küsitakse kasutajalt kelle vastu ta tahab mängida... kas inimene või arvuti
            int mangijaid = JOptionPane.showOptionDialog(null, "Vali, kelle vastu tahad mängida:",
                    "Inimene või arvuti", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsPlayer, 0);

            if (mangijaid == 0) { //1 mängija vs arvuti, loob mängijate isendid
                String mangija = JOptionPane.showInputDialog(null, "Sisesta mängija nimi",
                        "Nime sisestus", JOptionPane.QUESTION_MESSAGE); // GUI osa, kus küsitakse mängija nime
                mangija1 = new Mängija(mangija); // esimene mängija isend

                Object[] optionsVastane = {"Kerge", "Raske"};
                // GUI osa, kus küsitakse, kas raske või kerge arvuti valida endale vastaseks
                int vastane = JOptionPane.showOptionDialog(null, "Vali arvuti raskusaste",
                        "Inimene või arvuti", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsVastane, 0);
                if (vastane == 0) { // esimene valik, mis on kerge arvuti
                    mangija2 = new MängijaComputer();
                } else { // teine valik, mis on raske arvuti
                    mangija2 = new MängijaComputerHard();
                }
            }

            else { //2 mängijat omavahel, loob mängijate isendid
                String mangijaEsimene = JOptionPane.showInputDialog(null,
                        "Sisesta esimese mängija nimi", "Nime sisestus", JOptionPane.QUESTION_MESSAGE); //GUI osa, mis väljastab, et esimene mängija alustab
                mangija1 = new Mängija(mangijaEsimene); // esimene mängija isend

                String mangijaTeine = JOptionPane.showInputDialog(null,
                        "Sisesta teise mängija nimi", "Nime sisestus", JOptionPane.QUESTION_MESSAGE); //GUI osa, mis väljastab, et esimene mängija alustab
                mangija2 = new Mängija(mangijaTeine); // teine mängija isend

            }

            int tikkude_arv = 5 + (int) (Math.random() * 20); // suvaline tikkude arv
            JOptionPane.showMessageDialog(null, "Tikkude arv on " + tikkude_arv + "."); // GUI osa, mis väljastab tikkude arvu

            int a = (int) (Math.random() * 1000);//suvaline arv, et vaadata kumb alustab
            if (a % 2 == 0) {
                JOptionPane.showMessageDialog(null, "Alustab " + mangija1.getNimi()); //GUI osa, mis väljastab, et esimene mängija alustab
            } else {
                JOptionPane.showMessageDialog(null, "Alustab " + mangija2.getNimi()); //GUI osa, mis väljastab, et teine mängija alustab
                tikkude_arv = käigud(tikkude_arv, mangija2, mangija1);
            }

            while (tikkude_arv > 0) { // while loop, mis töötab, kuni tikkude arv on üle nulli
                tikkude_arv = käigud(tikkude_arv, mangija1, mangija2); // esimese mängija käik
                if (tikkude_arv > 0) tikkude_arv = käigud(tikkude_arv, mangija2, mangija1); // teise mängija käik
            }//käigud, kuni kumbki võidab

            //GUI osa, kus küsitakse, kas tahetakse uut mängu alustada
            Object[] valikud = {"Jah", "Ei"};
            int uus = JOptionPane.showOptionDialog(null, "Soovid uut mängu?",
                    "Inimene või arvuti", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, valikud, 0);
            if (uus == 1) break;//kui ei soovita uut mängu, siis lõpetab programm töö
        }
    }
}
