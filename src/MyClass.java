//Создаем свой интерфейс
interface Ipow {
    @Cache(maxCashed = 9)
    Integer square(Integer a);

    public void setI(int i);

    void notCashe(Integer a);
}

//Классы которые будем мучать
class MyClass extends MySuperClass implements Ipow {
    private int i = inttt + 1;
    private String s = "sadads";

    public MyClass() {
    }

    public MyClass(int i) {
        this.i = i;
    }

    private MyClass(int i, String s) {
        this.i = i;
        this.s = s;
    }

    private int geti() {
        return i;
    }

    public String getS() {
        return s;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void notCashe(Integer a) {
        System.out.println("результат работы метода notCashe " + a);
    }

    @Cache(maxCashed = 9)
    public Integer square(Integer a) {
        return a * a;
    }

}

class MySuperClass {
    public static final String MONDAY = "MONDAY";
    public static final String SUNDAY = "SUNDAY";
    public static final String TUESDAY = "MONDAY";
    public static final Integer TUESDAYINTEGER = 2;
    public final String FINAL_ = "FINAL_";
    public static String STATIC_ = "STATIC_";
    public final static String PUBLICFINALSTATIC_ = "PUBLICFINALSTATIC_";
    private final static String PRIVATEFINALSTATIC_ = "FINALSTATIC_";
    final static String DEFAULTFINALSTATIC_ = "FINALSTATIC_";
    int inttt = 0;

    public void publicPrint() {
        System.out.println("public");
    }

    private void privatePrint() {
        System.out.println("public");
    }

    void defaultPrint(String... shoto) {
        System.out.println("public");
    }
}

