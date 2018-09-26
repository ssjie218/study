package thread.src.com.github.study.design.factory.operate;

import com.github.study.design.Operate;

/**
 * 计算器
 * Created by shen on 2018/7/19.
 */
public class operate2 {

    public static void main(String[] args) {
        Integer  a = 33;
        Integer b = 44;
        String opt = "*";
        Integer r = OperateUtil.getOptResult(a, b, opt);
        System.out.println(r);
    }

}
