import java.beans.ExceptionListener;
import java.util.Arrays;

public class TestEnum {
    public static void main(String[] args) {
        Seasons seasons = Seasons.WINTER;
        System.out.println(Seasons.WINTER == seasons);

        Seasons seasons1 = Seasons.valueOf("AUTUMN");
        System.out.println(Arrays.toString(Seasons.values()));

        System.out.println(seasons1.nextSeasons());
        System.out.println(seasons.nextSeasons());

        System.out.println(Seasons.NEWSEASON.getClass().getName());
        System.out.println(Seasons.NEWSEASON.getClass().getSuperclass());
        Seasons.NEWSEASON.tst();
    }
}
enum Seasons {

    WINTER, SPRING, SUMMER, AUTUMN
//Так работает полиморфизм, если бы енум имплементил что-то, то нужно было в теле реализовать методы интерфейса
    ,NEWSEASON{
        public void tst(){
            System.out.println("asdsdasad");
        }
    };
    public Seasons nextSeasons(){
        if (this == Seasons.AUTUMN){
            return Seasons.WINTER;
        }
        else {
            return Seasons.values()[this.ordinal()+1];
        }
    }
    public void tst() {
    }
}
