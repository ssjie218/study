package com.github.study.baidu;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度文字识别技术
 * Created by pc on 2018/10/9.
 */
class OrcSample {
    //设置APPID/AK/SK
    public static final String APP_ID = "14378602";
    public static final String API_KEY = "pjGczctn3WqppRmdZoyszI1o";
    public static final String SECRET_KEY = "PLAieO1NSPR8W7GDs7WALbOcRSSxvfgw";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:\\Users\\Administrator\\Desktop\\555.jpg";
//        String path="http://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/e61d7b2f393841a7b9249bca767235d1.jpg";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        net.sf.json.JSONObject reso=net.sf.json.JSONObject.fromObject(res.toString(2));
        Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
        classMap.put("words_result", Word.class);

        Result result= (Result) net.sf.json.JSONObject.toBean(reso,Result.class,classMap);
        System.out.println(res.toString(2));
        for (Word word : result.getWords_result()) {
            System.out.print(word.getWords());
        }
        System.out.println(res.get("word_result"));
    }
}
