import java.lang.annotation.*;

//Создаем свою аннотацию
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@interface Cache {
}