package thread.src.com.github.study.design.proxy.givegift;

/**
 * 代理人帮忙送的
 * Created by pc on 2018/9/6.
 */
public class GiveProxy implements GiveGift {

    private Pursuit pursuit;

    public GiveProxy(SchoolGirl schoolGirl) {
        pursuit = new Pursuit(schoolGirl);
    }

    @Override
    public void give1() {
        pursuit.give1();
    }

    @Override
    public void give2() {
        pursuit.give2();
    }

    @Override
    public void give3() {
        pursuit.give3();
    }

    public static void main(String[] args) {
        SchoolGirl schoolGirl=new SchoolGirl("PP");
        GiveProxy proxy=new GiveProxy(schoolGirl);
        proxy.give1();
        proxy.give2();
        proxy.give3();
    }
}
