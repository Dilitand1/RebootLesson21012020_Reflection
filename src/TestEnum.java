// Не ДЗ
// Класс с хламом для обучения

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

        //Доп задание - создать объект класса через конструктор. получить значение приватной переменной не через геттер. Запустить метод
        testReflection();
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

        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
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
