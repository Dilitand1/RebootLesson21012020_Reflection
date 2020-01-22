import java.lang.reflect.*;
import java.util.Arrays;

public class Main {
    public int x = 0;
    public static void main(String[] args) {
        //Задание №1 напечатать все методы класса и суперкласса
        printAllMethods(MyClass.class);
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
                System.out.println("Имя метода: " + method.getName() + ", Модификатор доступа: " + method.getModifiers() +
                        ", Количество параметров:" + method.getParameterCount() + ", параметры метода: " + Arrays.toString(method.getParameterTypes()));
            }
            printAllMethods(cl.getSuperclass());
        }
    }
}

//Классы которые будем мучать:
class MySuperClass{
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
    private int i = 1;
    public  String s = "sadads";

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
