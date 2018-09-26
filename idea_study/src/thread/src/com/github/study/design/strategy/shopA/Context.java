package thread.src.com.github.study.design.strategy.shopA;

/**
 * Created by pc on 2018/8/29.
 */
public class Context {
    private StragegyA stragegyA;

    public Context(StragegyA stragegyA) {
        this.stragegyA = stragegyA;
    }
    public void contextInterface(){
        stragegyA.algorithmInterface();
    }

    public static void main(String[] args) {
        Context c=new Context(new ConcreteStrategyA());
        c.contextInterface();
    }
}
