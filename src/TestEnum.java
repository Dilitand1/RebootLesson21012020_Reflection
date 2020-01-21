import java.util.Arrays;

public class TestEnum {
    public static void main(String[] args) {
        Seasons seasons = Seasons.WINTER;
        System.out.println(Seasons.WINTER == seasons);

        Seasons seasons1 = Seasons.valueOf("AUTUMN");
        System.out.println(Arrays.toString(Seasons.values()));

        System.out.println(seasons1.nextSeasons());
        System.out.println(seasons.nextSeasons());
    }
}
enum Seasons{
    WINTER, SPRING, SUMMER, AUTUMN;
    public Seasons nextSeasons(){
        if (this == Seasons.AUTUMN){
            return Seasons.WINTER;
        }
        else {
            return Seasons.values()[this.ordinal()+1];
        }
    }
}
