import java.lang.reflect.*;
import java.util.Arrays;

public class Main {
    public int x = 0;
    public static void main(String[] args) {

        //System.out.println("Задание №1 напечатать все методы класса и суперкласса:");
        //printAllMethods(MyClass.class);

        //System.out.println("Задание №2 напечатать все геттеры");
        //printAllGetters(MyClass.class);

        System.out.println("Задание №3 напечатать все String константы (public static final) поля если значения и названия равны");
        printAllConstantsEqualsValue(MySuperClass.class);

    }
    static void printAllMethods(Class cl){
        if (cl != Object.class && cl != null) {
            System.out.println("Класс: " + cl.getName() + ", Методы класса:");
            for (Method method : cl.getDeclaredMethods()) {
                System.out.println("Имя метода: " + method.getName() + ", Модификатор доступа: " + method.getModifiers() +
                        ", Количество параметров:" + method.getParameterCount() + ", параметры метода: " + Arrays.toString(method.getParameterTypes()));
            }
            printAllMethods(cl.getSuperclass());
        }
    }
    static void printAllGetters(Class cl){
        if (cl != Object.class && cl != null) {
            System.out.println("Класс: " + cl.getName() + ", Методы класса:");
            for (Method method : cl.getDeclaredMethods()) {
                if (method.getName().substring(0,3).equals("get")) {
                    System.out.println("Имя метода: " + method.getName() + ", Модификатор доступа: " + method.getModifiers() +
                            ", возвращаемое значение: " + method.getReturnType());
                }
            }
        }
    }
    static <T> void printAllConstantsEqualsValue(Class<T> cl) {
        String tmpFieldName = "";
        String tmpFieldValue = "";
        if (cl != Object.class && cl != null) {
            for (Field field : cl.getDeclaredFields()) {
                //Методом тыка определил что модификатор public static - это 25, можно написать еще вот так field.getModifiers() == (Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL)
                if (field.getType() == String.class && field.getModifiers() == 25) {
                    try {
                        tmpFieldValue = (String) field.get(cl);
                        tmpFieldName = field.getName();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    //Проверяем соответствие
                    if(!tmpFieldName.equals("") && tmpFieldName.equals(tmpFieldValue)) System.out.println(field.getName());
                }
            }
        }
    }
}

//Классы которые будем мучать:
class MySuperClass{
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

    public void publicPrint(){
        System.out.println("public");
    }
    private void privatePrint(){
        System.out.println("public");
    }
    void defaultPrint(String... shoto){
        System.out.println("public");
    }
}

class MyClass extends MySuperClass{
    private int i = inttt + 1;
    String s = "sadads";

    public MyClass(){
    }
    private MyClass(int i){
        this.i = i;
    }

    private int geti(){
        return i;
    }

    public String getS(){
        return s;
    }

    public void setI(){
        this.i = i;
    }
    public int sum(Integer a,Integer b){
        return a + b;
    }
}
