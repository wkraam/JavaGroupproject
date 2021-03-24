import java.util.Scanner;

public class Mängija{
    private String nimi;

    public Mängija(String nimi) {
        this.nimi = nimi;
    }

    public Mängija() {
    }

    public String getNimi() {
        return nimi;
    }

    public int käik(int tikkudeArv){
        Scanner sc = new Scanner(System.in);
        int tikud;
        while(true) {
            System.out.print(nimi + " - Mitu tikku võtad(1/2/3): ");
            tikud = sc.nextInt();
            if (tikud > tikkudeArv) {
                System.out.println("Alles pole piisavalt tikke. Vali uuesti.");
            }else if(tikud > 3 || tikud < 1) {
                System.out.println("Vali tikkude arv vahemikust 1-3.");
            }else break;
        }
        return tikud;
    }//küsib mängijalt mitu tikku võtab
}
