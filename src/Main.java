import java.lang.reflect.*;
import java.util.Arrays;
import java.lang.annotation.*;

public class Main {
    public int x = 0;
    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println("Задание №1 напечатать все методы класса и суперкласса:");
        printAllMethods(MyClass.class);
        System.out.println();

        System.out.println("Задание №2 напечатать все геттеры");
        printAllGetters(MyClass.class);
        System.out.println();

        System.out.println("Задание №3 напечатать все String константы (public static final) если значения и названия равны");
        printAllConstantsEqualsValue(MySuperClass.class);
        System.out.println();

        //Доп задание - создать объект класса через конструктор. получить значение приватной переменной не через геттер. Запустить метод
        testReflection();

        System.out.println("Задание №4 Реализовать кэширующий прокси перехватывающий вызов интерфейса и подменяет работу метода помеченного аннотацией @Cache");

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
    static void printAllConstantsEqualsValue(Class cl) {
        if (cl != Object.class && cl != null) {
            for (Field field : cl.getDeclaredFields()) {
                //Методом тыка определил что модификатор public final static - это 25, можно написать еще вот так field.getModifiers() == (Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL)
                if (field.getType() == String.class && field.getModifiers() == 25) {
                    try {
                        String tmpFieldValue = (String) field.get(cl);
                        String tmpFieldName = field.getName();
                        //Проверяем соответствие
                        if(tmpFieldName.equals(tmpFieldValue)) System.out.println(field.getName());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    static void testReflection(){
        try {
            MyClass mc = MyClass.class.newInstance();
            Field field = mc.getClass().getDeclaredField("s");
            field.setAccessible(true);
            field.set(mc,"new Value");
            System.out.println(field.get(mc));
            System.out.println(mc.getS());

            Field field2 = MyClass.class.getDeclaredField("i");
            MyClass mc2 = MyClass.class.getConstructor(int.class).newInstance(10);
            //System.out.println(field2.get(mc2)); - если вызвать до setAcceible будет иллегалАксессЭксепшн
            field2.setAccessible(true);
            System.out.println(field2.get(mc2));
            field2.set(mc2,111111);


            System.out.println(field2.get(mc2));

            Method method = MyClass.class.getDeclaredMethod("geti");
            method.setAccessible(true);
            Integer s = (Integer) method.invoke(mc);
            System.out.println(s);

            Constructor[] constructors = MyClass.class.getDeclaredConstructors();
            Constructor constructor = MyClass.class.getDeclaredConstructor(int.class,String.class);
            constructor.setAccessible(true);
            MyClass myClass3 = MyClass.class.getConstructor(int.class,String.class).newInstance();


        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
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
    private String s = "sadads";

    public MyClass(){
    }
    public MyClass(int i){
        this.i = i;
    }
    private MyClass(int i,String s){
        this.i = i;
        this.s = s;
    }
    private int geti(){
        return i;
    }

    public String getS(){
        return s;
    }

    public void setI(int i){
        this.i = i;
    }
    @Cache
    public int sum(Integer a,Integer b){
        return a + b;
    }
}

//Создаем свою аннотацию
@Target(value=ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Cache{

}