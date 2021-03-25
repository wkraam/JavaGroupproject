import javax.swing.*;

public class MängijaComputer extends Mängija {
    public MängijaComputer() {
    }

    @Override
    public String getNimi() {
        return "Computer";
    }

    @Override
    public int käik(int tikkudeArv) {
        int tikud;
        while (true) {
            //(int) ((Math.random() * (max - min)) + min);
            tikud = (int) ((Math.random() * (4 - 1)) + 1);
            if (tikud <= tikkudeArv && tikud <= 3) break;
        }
        JOptionPane.showMessageDialog(null, "Arvuti võttis " + tikud + " tikku");
        return tikud;
    }//genereerib suvaliselt arvu 1-3 vahel
}
