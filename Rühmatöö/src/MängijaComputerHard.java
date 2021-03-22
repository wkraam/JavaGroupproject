public class MängijaComputerHard extends MängijaComputer{
    @Override
    public int käik(int tikkudeArv) {
        //võidab kui
        int b = (tikkudeArv-1)%4;
        if (b==0){
            b=1;
        }
        return b;
    }
}
