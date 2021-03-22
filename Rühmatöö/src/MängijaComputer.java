public class MängijaComputer extends Mängija{
    public MängijaComputer() {
    }

    @Override
    public String getNimi() {
        return "Computer";
    }

    @Override
    public int käik(int tikkudeArv) {
        int tikud;
        while(true){
            tikud = 1+(int)(Math.random()*3);//seda võiks vaadata veits
            if(tikud<=tikkudeArv && tikud <= 3) break;
        } //genereerib arvu 1 ja 3 vahel
        System.out.println("-"+tikud + " tikku");
        return tikud;
    }
}
