package thread.src.com.github.study.design.strategy.shopA;

import java.lang.reflect.Method;

/**
 * 策略模式加反射
 * Created by pc on 2018/8/29.
 */
public class ContextReflect {
    private String strategtName;

    public ContextReflect(String strategtName) {
        this.strategtName = strategtName;
    }

    public void contextInterface() {
        try {
            StragegyA a = (StragegyA) Class.forName(strategtName).newInstance();
            a.algorithmInterface();

            Method method = Class.forName(strategtName).getMethod("algorithmInterface");
            method.invoke(Class.forName(strategtName).newInstance(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ContextReflect c = new ContextReflect("com.github.study.design.strategy.shopA.ConcreteStrategyB");
        c.contextInterface();
    }
}
