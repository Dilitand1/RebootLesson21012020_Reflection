import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Handler implements InvocationHandler {
    private final Ipow realObject;
    private Map<Integer, Integer> map = new HashMap<>();

    Handler(Ipow realObject) {
        map.put(1, 1);
        map.put(2, 4);
        map.put(3, 9);
        map.put(4, 16);
        map.put(5, 25);
        map.put(6, 36);
        map.put(7, 49);
        map.put(8, 64);
        map.put(9, 81);

        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //Почему то когда используешь method, то не видит аннотацию, так что юзаем костыль...:
        Method method1 = MyClass.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
        System.out.print("имя метода " + method1.getName());
        if (method1.isAnnotationPresent(Cache.class)) {
            System.out.println(", в методе есть аннотация @Cache, пытаемся обработать...");
            if (map.containsKey(args[0])) {
                System.out.println("Результат найден в кэше: " + map.get(args[0]));
            } else {
                System.out.println("Результат не найден в кэше, обрабатываем как обычно, результат: " + method.invoke(realObject, args));
            }
        } else {
            System.out.println(", в методе нет аннотации @Cashe, обрабатываем метод без прокси");
            method1.invoke(realObject, args);
        }
        return null;

    }
}
