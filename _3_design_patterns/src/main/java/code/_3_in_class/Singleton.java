package code._3_in_class;

public class Singleton {

    static int nrInstante = 0;
    static Singleton INSTANCE = null;
    private Singleton(){
        nrInstante = this.nrInstante + 1;
        System.out.println("s-a apelat constructorul de " + this.nrInstante+" atatea ori");
    }

    public static Singleton getInstance(){
        if(nrInstante == 0){
            return INSTANCE = new Singleton();
        }
        else{
            return  INSTANCE;
        }
    }
}
