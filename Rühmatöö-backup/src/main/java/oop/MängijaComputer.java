package oop;
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
        } //genereerib arvu 1 ja 3 vahel
        System.out.println("Arvuti võttis " + tikud + " tikku");
        return tikud;
    }
}
