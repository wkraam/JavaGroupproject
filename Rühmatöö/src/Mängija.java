import javax.swing.*;
import java.util.Scanner;

public class Mängija {
    private String nimi;

    public Mängija(String nimi) {
        this.nimi = nimi;
    }

    public Mängija() {
    }

    public String getNimi() {
        return nimi;
    }

    public int käik(int tikkudeArv) {
        //Scanner sc = new Scanner(System.in);
        int tikud;
        while (true) {
            System.out.print(nimi + " - Mitu tikku võtad(1/2/3): ");
            //tikud = sc.nextInt();
            Object[] optionsVastane = {"1", "2", "3"};
            tikud = (JOptionPane.showOptionDialog(null, nimi + ": vali, mitu tikku võtad:", "tikkude valimine", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsVastane, 0))+1;
            if (tikud > tikkudeArv) {
                System.out.println("Alles pole piisavalt tikke. Vali uuesti.");
                JOptionPane.showMessageDialog(null, "Alles pole piisavalt tikke. Vali uuesti!", "Väär valik", JOptionPane.ERROR_MESSAGE);
            } else if (tikud > 3 || tikud < 1) {
                System.out.println("Vali tikkude arv vahemikust 1-3.");
                JOptionPane.showMessageDialog(null, "Vali tikkude arv vahemikust 1-3!", "Väär valik", JOptionPane.ERROR_MESSAGE);
            } else break;
        }
        return tikud;
    }//küsib mängijalt mitu tikku võtab
}
