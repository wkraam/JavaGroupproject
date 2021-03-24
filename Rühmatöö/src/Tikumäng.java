import javax.swing.JOptionPane;
import java.util.Scanner;

public class Tikumäng {
    //Küsima mitu mängijat (1 vs comp) vs (1 vs 1)
    //kui arvutiga, siis kas kerge(genereerib arvu suvaliselt) või raske
    //Mängijale/mängijatele nimed
    //Random tikkude arv
    //Random kes alustab
    //hakkab küsima mitu tikku võtab/kui arvutiga, siis valemi järgi
    public static int käigud(int tikkude_arv, Mängija m1, Mängija m2) {
        tikkude_arv -= m1.käik(tikkude_arv);
        System.out.println("Tikkude arv - " + tikkude_arv);
        JOptionPane.showMessageDialog(null, "Tikkude arv on " + tikkude_arv + ".");
        if (tikkude_arv == 0) {
            System.out.println("Võitis: " + m2.getNimi());
            JOptionPane.showMessageDialog(null, "Võitis: " + m2.getNimi(), "VÕITJA!!!", JOptionPane.INFORMATION_MESSAGE);
        }
        return tikkude_arv;
    }

    public static void main(String[] args) {
        System.out.println("Tikkude võtmise mäng, kus laual on tikud ja te peate valima 1-3 tikku.");
        JOptionPane.showMessageDialog(null, "Tikkude mäng \nKaotaja on see, kes valib viimasena tikud. \nValikus on võtta kas 1/2/3 tikku.");
        System.out.println("Kaotaja on see, kes valib viimasena tikud.");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        Mängija mangija1;
        Mängija mangija2;
        while (true) {
            System.out.println("1 - mängite arvuti vastu, 2 - mängite teise mängija vastu");
            System.out.print("Mitu mängijat(1/2): ");

            Object[] optionsPlayer = {"Arvuti", "Inimene"};
            int mangijaid = JOptionPane.showOptionDialog(null, "Vali kelle vastu tahad mängida:", "Inimene või arvuti", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsPlayer, 0);

            //System.out.println(mangijaid); //for debug
            //int mangijaid = sc.nextInt();
            if (mangijaid == 0) { //0 kui kasutada JOptionPane, 1 kui kasutada käsurida
                System.out.println("Mängija nimi: ");
                //String mangija = sc.next();
                String mangija = JOptionPane.showInputDialog(null, "Sisesta mängija nimi", "Nime sisestus", JOptionPane.QUESTION_MESSAGE);
                mangija1 = new Mängija(mangija);
                while (true) {
                    System.out.println("Vali vastane(kerge/raske): ");
                    Object[] optionsVastane = {"Kerge", "Raske"};
                    int vastane = JOptionPane.showOptionDialog(null, "Vali arvuti raskusaste", "Inimene või arvuti", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsVastane, 0);
                    //String vastane = sc.next();
                    if (vastane == 0) {
                        mangija2 = new MängijaComputer();
                        break;
                    } else if (vastane == 1) {
                        mangija2 = new MängijaComputerHard();
                        break;
                    }//pmst on mingi skeem kuidas seda mängu alati võita, u et võtad alguses kui paaritu,
                    // siis paaritu ja ss vastavalt va käigule, et oleks alati paaris vms
                    else {
                        System.out.println("Sisend väär");
                    }
                }//loop, mis küsib sisendit kuni saab kas kerge või raske
                break;
            } //1 mängija vs arvuti
            else if (mangijaid == 1) { //1 kui kasutada JOptionPane, 2 kui kasutada käsurida
                System.out.print("1. mängija nimi: ");
                //String mangija = sc.next();
                String mangijaEsimene = JOptionPane.showInputDialog(null, "Sisesta esimese mängija nimi", "Nime sisestus", JOptionPane.QUESTION_MESSAGE);
                mangija1 = new Mängija(mangijaEsimene);
                System.out.print("2. mängija nimi: ");
                //mangija = sc.next();
                String mangijaTeine = JOptionPane.showInputDialog(null, "Sisesta teise mängija nimi", "Nime sisestus", JOptionPane.QUESTION_MESSAGE);
                mangija2 = new Mängija(mangijaTeine);
                break;
            } //2 mängijat omavahel
            else {
                System.out.println("Sisend väär");
            }
        }//loop, mis küsib nii kaua kas 1 või 2 mängijat kuni normi sisendi saab

        int tikkude_arv = 5 + (int) (Math.random() * 20);
        int a = (int) (Math.random() * 1000);//genereerib suvalise arvu, mille põhjal vaadatakse, kumb alustab
        System.out.println("Tikkude arv on " + tikkude_arv + ".");
        JOptionPane.showMessageDialog(null, "Tikkude arv on " + tikkude_arv + ".");
        if (a % 2 == 0) {
            System.out.println("Alustab " + mangija1.getNimi());
            JOptionPane.showMessageDialog(null, "Alustab "+mangija1.getNimi());
        } else {
            System.out.println("Alustab " + mangija2.getNimi());
            JOptionPane.showMessageDialog(null, "Alustab "+mangija2.getNimi());
            tikkude_arv = käigud(tikkude_arv, mangija2, mangija1);
        }
        while (tikkude_arv > 0) {
            tikkude_arv = käigud(tikkude_arv, mangija1, mangija2);
            if (tikkude_arv > 0) tikkude_arv = käigud(tikkude_arv, mangija2, mangija1);
        }
    }
}
