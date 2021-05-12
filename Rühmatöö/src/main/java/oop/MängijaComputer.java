package oop;

import javax.swing.*;

public class MängijaComputer extends Mängija { // see klass kasutab Mängija klassi

    public MängijaComputer() {
    }

    @Override
    public String getNimi() {
        return "Computer";
    } // nime tagastamise get meetod

    @Override
    public int käik(int tikkudeArv) { // mängija klassis kasutatav käik overriditakse käigu meetod, seekord arvuti poolel
        int tikud; // väli tikkude valiku kohta
        while (true) {
            //(int) ((Math.random() * (max - min)) + min);
            tikud = (int) ((Math.random() * (4 - 1)) + 1); // tehakse random number 1-3 vahel, mida siis arvuti kasutab tikkude võtmisel
            if (tikud <= tikkudeArv && tikud <= 3) break; // kui random meetod kuidagi väära arvu, või liiga palju, siis korratakse loopi
        }
        return tikud;
    }//genereerib suvaliselt arvu 1-3 vahel
}
