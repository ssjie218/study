package thread.src.com.github.study.design.decorator;

/**
 * Created by pc on 2018/8/30.
 */
public class Tshirt extends  Finery {

    public Tshirt(Person person) {
        super(person);
    }

    @Override
    public void show() {
        System.out.println("Tshirt");
        super.show();
    }
}
