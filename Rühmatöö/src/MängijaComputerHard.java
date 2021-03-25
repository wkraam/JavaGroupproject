public class MängijaComputerHard extends MängijaComputer {
    @Override
    public int käik(int tikkudeArv) { // samuti siin overriditakse MängijaCompuuter klassi käigu meetodit
        int b = (tikkudeArv - 1) % 4; // tikkude arvust jäägi neljaga jagamisel, kuna siis jääb vastasele võtta alati viimane tikk
        if (b == 0) {
            b = 1;
        }
        return b; // tagastatakse peameetodile võetud tikkude arv
    }
}
