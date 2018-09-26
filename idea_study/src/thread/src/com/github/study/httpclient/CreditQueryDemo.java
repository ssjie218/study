package thread.src.com.github.study.httpclient;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 失信信息查詢
 * Created by pc on 2018/9/12.
 */
public class CreditQueryDemo {
    private static void getBaidu(String name, String cardId) throws Exception {
        String url = "https://www.baidu.com/s?wd=%E5%A4%B1%E4%BF%A1%E8%A2%AB%E6%89%A7%E8%A1%8C%E4%BA%BA%E6%9F%A5%E8%AF%A2&rsv_spt=1&rsv_iqid=0xb68d3ad50001d1da&issp=1&f=3&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=62095104_8_oem_dg&rsv_enter=1&rsv_sug3=2&rsv_sug1=1&rsv_sug7=100&rsv_sug2=1&prefixsug=%25E5%25A4%25B1%25E4%25BF%25A1%25E8%25A2%25AB%25E6%2589%25A7%25E8%25A1%258C%25E4%25BA%25BA&rsp=0&inputT=2303&rsv_sug4=2567";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String page = EntityUtils.toString(entity, "UTF-8");
        //System.out.println(page);
    }

    private static void getInfo(String name, String cardId) throws Exception {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("iname", name));
        params.add(new BasicNameValuePair("cardNum", cardId));
        params.add(new BasicNameValuePair("resource_id", "6899"));
        params.add(new BasicNameValuePair("query", "失信被执行人名单"));
        String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        System.out.println("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?" + paramsStr);
        HttpGet httpGet = new HttpGet("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?" + paramsStr);
        httpGet.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String page = EntityUtils.toString(entity, "UTF-8");
        System.out.println(page);
        if (response.getStatusLine().getStatusCode() == 200) {
            JSONObject json = JSONObject.fromObject(page);
            if (json.has("data")) {
                JSONArray dataList = json.getJSONArray("data");
                if (dataList.isEmpty()) {
                    System.out.println("无信息");
                } else {
                    System.out.println(dataList.getString(0));
                    JSONObject resultJson =  JSONObject.fromObject(dataList.getString(0));
                    JSONArray resultList = resultJson.getJSONArray("result");
                    for (int i = 0; i < resultList.size(); i++) {
                        System.out.println(name+":" + resultList.getString(i) + " ");
                    }
                }

            }
        }

    }

    public static void main(String[] args) throws Exception {
        getInfo("贾跃亭2", "");
        getInfo("贾跃亭", "");


        //1、使用JSONObject
//        JSONObject jsonObject=JSONObject.fromObject(objectStr);
//        if (jsonObject.has("name")) {
//            System.out.println("name:" + jsonObject.getString("UserName"));
//        }
//        if (obj.has("Array")) {
//            JSONArray transitListArray = obj.getJSONArray("Array");
//            for (int i = 0; i < transitListArray.size(); i++) {
//                System.out.println("Array:" + transitListArray.getString(i) + " ");
//
//            }
//        }

    }
}
