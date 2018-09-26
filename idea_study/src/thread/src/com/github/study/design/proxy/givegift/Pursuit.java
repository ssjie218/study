package thread.src.com.github.study.design.proxy.givegift;

/**
 *  追求者被代理人
 * Created by pc on 2018/9/6.
 */
public class Pursuit implements GiveGift{
    private SchoolGirl schoolGirl;
    public Pursuit(SchoolGirl schoolGirl){
        this.schoolGirl=schoolGirl;
    }
    @Override
    public void give1() {
        System.out.println(schoolGirl.getName()+" 送你1");
    }

    @Override
    public void give2() {
        System.out.println(schoolGirl.getName()+" 送你2");
    }

    @Override
    public void give3() {
        System.out.println(schoolGirl.getName()+" 送你3");
    }
}
