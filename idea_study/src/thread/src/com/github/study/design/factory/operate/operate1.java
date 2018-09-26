package thread.src.com.github.study.design.factory.operate;

/**
 * 计算器初级
 * Created by shen on 2018/7/19.
 */
public class operate1 {

    public static void main(String[] args) {
        Integer a=33;
        Integer b=44;
        String opt="*";
        Integer r=0;
        switch (opt){
            case "+" :
                r=a+b;
                break;
            case "-":
                r=a-b;
                break;
            case "*":
                r=a*b;
                break;
            case "/":
                if(b==0){
                    System.out.println("除数不能为0");
                }
                r=a/b;
                break;
        }
        System.out.println(r);
    }
}
