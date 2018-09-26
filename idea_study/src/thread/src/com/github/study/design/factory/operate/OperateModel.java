package thread.src.com.github.study.design.factory.operate;

/**
 * Created by pc on 2018/7/19.
 */
public class OperateModel {

    private Double numberA;
    private Double numberB;

    public Double getNumberB() {
        return numberB;
    }

    public Double getNumberA() {
        return numberA;
    }

    public void setNumberA(Double numberA) {
        this.numberA = numberA;
    }

    public void setNumberB(Double numberB) {
        this.numberB = numberB;
    }
    public double getResult(){
        return 0;
    }
}
