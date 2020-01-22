import java.lang.reflect.*;
import java.util.Arrays;
import java.lang.annotation.*;
import java.util.Scanner;

public class Main {
    public int x = 0;

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        System.out.println("Задание №1 напечатать все методы класса и суперкласса:");
        printAllMethods(MyClass.class);
        System.out.println();

        System.out.println("Задание №2 напечатать все геттеры");
        printAllGetters(MyClass.class);
        System.out.println();

        System.out.println("Задание №3 напечатать все String константы (public static final) если значения и названия равны");
        printAllConstantsEqualsValue(MySuperClass.class);
        System.out.println();

        System.out.println("Задание №4 Реализовать кэширующий прокси, перехватывающий вызов интерфейса и подменяющий работу метода помеченного аннотацией @Cache. (В кэше от 1 до 9)");

        MyClass myClass = MyClass.class.newInstance();
        Handler handler = new Handler(myClass);
        Ipow proxy = (Ipow) Proxy.newProxyInstance(Ipow.class.getClassLoader(), new Class[]{Ipow.class}, handler);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число:");
        if (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            proxy.square(i);
            proxy.setI(i);
            proxy.notCashe(i);
        } else {
            System.out.println("Введен не Integer");
        }
    }

    static void printAllMethods(Class cl) {
        if (cl != Object.class && cl != null) {
            System.out.println("Класс: " + cl.getName() + ", Методы класса:");
            for (Method method : cl.getDeclaredMethods()) {
                System.out.println("Имя метода: " + method.getName() + ", Модификатор доступа: " + method.getModifiers() +
                        ", Количество параметров:" + method.getParameterCount() + ", параметры метода: " + Arrays.toString(method.getParameterTypes()));
            }
            printAllMethods(cl.getSuperclass());
        }
    }

    static void printAllGetters(Class cl) {
        if (cl != Object.class && cl != null) {
            System.out.println("Класс: " + cl.getName() + ", Методы класса:");
            for (Method method : cl.getDeclaredMethods()) {
                if (method.getName().substring(0, 3).equals("get")) {
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
                        if (tmpFieldName.equals(tmpFieldValue)) System.out.println(field.getName());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
