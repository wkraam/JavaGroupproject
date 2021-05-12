package oop;
import java.util.Scanner;

public class Tikumäng{
    //Küsima mitu mängijat (1 vs comp) vs (1 vs 1)
    //kui arvutiga, siis kas kerge(genereerib arvu suvaliselt) või raske
    //Mängijale/mängijatele nimed
    //Random tikkude arv
    //Random kes alustab
    //hakkab küsima mitu tikku võtab/kui arvutiga, siis valemi järgi
    public static int käigud(int tikkude_arv, Mängija m1, Mängija m2) {
        tikkude_arv -= m1.käik(tikkude_arv);
        System.out.println("Tikkude arv - " + tikkude_arv);
        if (tikkude_arv == 0) {
            System.out.println("Võitis: " + m2.getNimi());
        }
        return tikkude_arv;
    }

    public static void main(String[] args) {

        System.out.println("Tikkude võtmise mäng, kus laual on tikud ja te peate valima 1-3 tikku.");
        System.out.println("Kaotaja on see, kes valib viimasena tikud.");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        Mängija mangija1;
        Mängija mangija2;
        while (true) {
            System.out.println("1 - mängite arvuti vastu, 2 - mängite teise mängija vastu");
            System.out.print("Mitu mängijat(1/2): ");
            int mangijaid = sc.nextInt();
            if (mangijaid == 1) {
                System.out.print("Mängija nimi: ");
                String mangija = sc.next();
                mangija1 = new Mängija(mangija);
                while (true) {
                    System.out.print("Vali vastane(kerge/raske): ");
                    String vastane = sc.next();
                    if (vastane.equals("kerge")) {
                        mangija2 = new MängijaComputer();
                        break;
                    } else if (vastane.equals("raske")) {
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
            else if (mangijaid == 2) {
                System.out.print("1. mängija nimi: ");
                String mangija = sc.next();
                mangija1 = new Mängija(mangija);
                System.out.print("2. mängija nimi: ");
                mangija = sc.next();
                mangija2 = new Mängija(mangija);
                break;
            } //2 mängijat omavahel
            else {
                System.out.println("Sisend väär");
            }
        }//loop, mis küsib nii kaua kas 1 või 2 mängijat kuni normi sisendi saab

        int tikkude_arv = 5 + (int) (Math.random() * 20);
        int a = (int) (Math.random() * 1000);//genereerib suvalise arvu, mille põhjal vaadatakse, kumb alustab
        System.out.println("Tikkude arv on " + tikkude_arv + ".");
        if (a % 2 == 0) {
            System.out.println("Alustab " + mangija1.getNimi());
        } else {
            System.out.println("Alustab " + mangija2.getNimi());
            tikkude_arv = käigud(tikkude_arv, mangija2, mangija1);
        }
        while (tikkude_arv > 0) {
            tikkude_arv = käigud(tikkude_arv, mangija1, mangija2);
            if (tikkude_arv > 0) tikkude_arv = käigud(tikkude_arv, mangija2, mangija1);
        }
    }
}
