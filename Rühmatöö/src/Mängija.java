import javax.swing.*;

public class Mängija {
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
            Object[] optionsVastane = {"1", "2", "3"}; // nuppude väljad
            tikud = (JOptionPane.showOptionDialog(null, nimi + ": vali, mitu tikku võtad:",
                    "tikkude valimine", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsVastane, 0))+1;
            if (tikud > tikkudeArv) { // kui tikkude valik oli rohkem kui alles on siis GUI näitab seda kasutajale
                JOptionPane.showMessageDialog(null, "Alles pole piisavalt tikke. Vali uuesti!",
                        "Väär valik", JOptionPane.ERROR_MESSAGE);
            } else if (tikud > 3 || tikud < 1) { // kui kuidagi valitakse rohkem kui 3 või vähem kui 1 tikku, siis GUI näitab vastavat kirjet kasutajale
                JOptionPane.showMessageDialog(null, "Vali tikkude arv vahemikust 1-3!",
                        "Väär valik", JOptionPane.ERROR_MESSAGE);
            } else break;
        }
        return tikud;
    }
}
