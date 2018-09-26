package thread.src.com.github.study.design.decorator.component;

/**
 * 装饰类，对外扩张对componet类的装饰
 * Created by pc on 2018/8/31.
 */
public class Decorator extends Component {
    private Component component;

    public void setComponent(Component c) {
        this.component = component;
    }
    @Override
    void operation() {
        component.operation();
    }
}
