package thread.src.com.github.study.design.factory.operate;

/**
 * Created by pc on 2018/7/19.
 */
public class OperateSub extends OperateModel{
    @Override
    public double getResult(){
        return getNumberA()-getNumberB();
    }
}