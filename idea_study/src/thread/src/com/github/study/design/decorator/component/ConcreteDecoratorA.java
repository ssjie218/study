package thread.src.com.github.study.design.decorator.component;

/**
 * Created by pc on 2018/8/31.
 */
public class ConcreteDecoratorA extends Decorator{

    @Override
    void operation() {
        super.operation();
        System.out.println("2穿A");
    }
}
