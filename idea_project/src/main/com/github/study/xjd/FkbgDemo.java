package com.github.study.xjd;

import com.github.study.util.GzipUtil;
import com.github.study.xjd.model.*;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 运营商报告生成
 */
public class FkbgDemo {

    private final static String TOKEN_URL = "https://api.dsdatas.com/credit/api/token";
    private final static String A_KEY = "150330eb0bb8a865eb46eb914bbcd94a";
    private final static String S_KEY = "97260eba35590961255430035b2bcea0054fc95c";
    private final static  String REPORT_URL="https://api.dsdatas.com/c/risk/check/v2";
    private static String data;

    public static void main(String[] args) throws Exception {
        getToken();
        //getData();
       //getInfo();
        //getReport();

        String str="res=%7B%22msg%22%3A%22%E5%BB%BA%E8%AE%AE%E9%80%9A%E8%BF%87%22%2C%22state%22%3A0%7D&code=200&orderNo=6476681927747588096&compressStatus=-1&sign=a4ceef51dd99d461b96ec0a9f64dd94a8fe352b1469c56e17fa3c0d30a409d6d&buesType=riskCheckV2&message=%E9%87%87%E9%9B%86%E6%88%90%E5%8A%9F%EF%BC%81&timestamp=1544161304510\n8";
        System.out.println(URLDecoder.decode(str));
    }
    public static String decode(String s) {
        String base64 =null;
        try {
             base64 = new String(Base64.getDecoder().decode(s), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64;
    }

    /**
     * 获取token
     *
     **/
    public static void getToken() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(FkbgDemo.TOKEN_URL);

        httpPost.setHeader("Content-Type",
                "application/json;charset=UTF-8");
        JSONObject jsonParam=new JSONObject();
        jsonParam.put("apiKey", FkbgDemo.A_KEY);
        jsonParam.put("securityKey", FkbgDemo.S_KEY);
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpclient.execute(httpPost);
        String result= EntityUtils.toString(response.getEntity());
        System.out.println(result);
        String token=null;

        JSONObject jsonResult=JSONObject.fromObject(result);
        String data=jsonResult.getString("data");
        JSONObject dataResult=JSONObject.fromObject(data);
        token=dataResult.getString("token");
        System.out.println(token);
        getFkbg(token);
    }


    /**
     * 获取运营商报告
     *
     * @throws Exception
     */
    private static void getFkbg(String token) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(FkbgDemo.REPORT_URL);

        httpPost.setHeader("Content-Type",
                "application/json;charset=UTF-8");
        httpPost.setHeader("auth_token", token);
        StringEntity entity = new StringEntity( getData() , "utf-8");
        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpclient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        JSONObject jsonResult = JSONObject.fromObject(result);
        String res = jsonResult.getString("res");
        System.out.println(GzipUtil.decompressWithBase64(res));
    }

    public static String getData() {
        String result="";
        try {
            FileReader fileReader=new FileReader("C:\\Users\\pc\\Desktop\\fk.json");
            BufferedReader br = new BufferedReader(fileReader);
            String line;

            while ((line = br.readLine()) != null) {
                result=result+line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result=result.replaceAll(" ","");
        System.out.println(result);
        return result;
    }
   public static void getDD(){

   }
}