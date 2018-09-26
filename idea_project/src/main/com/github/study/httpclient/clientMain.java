package com.github.study.httpclient;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class clientMain {

    public static void main(String[] args){
        try {
            //创建client实例
            HttpClient client = HttpClients.createDefault();
            //创建httpget实例
            HttpGet httpGet = new HttpGet("http://www.btba.com.cn");
            //执行 get请求
            HttpResponse response = client.execute(httpGet);
            System.out.println(response);
            String resultString="";
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            //返回获取实体

            //输出网页
            System.out.println(resultString);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}