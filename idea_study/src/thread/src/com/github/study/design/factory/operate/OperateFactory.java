package thread.src.com.github.study.design.factory.operate;

/**
 * Created by pc on 2018/7/19.
 */
public class OperateFactory {

    public  static OperateModel createOperate(String opt){
        OperateModel operateModel=null;
        Integer r = 0;
        switch (opt) {
            case "+":
                operateModel=new OperateAdd();
                break;
            case "-":
                operateModel=new OperateSub();
                break;
            case "*":
                operateModel=new OperateAdd();
                break;
            case "/":
                operateModel=new OperateAdd();
                break;
        }
        return operateModel;

    }
}