public class MängijaComputerHard extends MängijaComputer {
    @Override
    public int käik(int tikkudeArv) {
        int b = (tikkudeArv - 1) % 4;
        if (b == 0) {
            b = 1;
        }
        return b;
    }//tikkude arvust jäägi neljaga jagamisel, kuna siis jääb vastasele võtta alati viimane tikk
}
