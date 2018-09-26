package com.github.study.httpclient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

public class CookieUtilBak {

    public final static String CONTENT_TYPE = "Content-Type";

    public static void main(String[] args) {

        //String loginURL = "http://www.p2peye.com/member.php?mod=logging&action=login&loginsubmit=yes&loginhash=Lsc66&username=puqiuxiaomao&password=a1234567";
        String logURL = "http://hzq-api.zhiyun88u.com/tmind/v1/login/phone";
        String listURL = "http://www.p2peye.com/member.php";
        //phone: "13600535837", code: "1234", is_bind_login: false

        //********************************需要登录的*************************************************
        try {
            Connection.Response res =
                    Jsoup.connect(logURL)
                            .data("phone","logging"
                                    ,"code","1234"
                                    ,"is_bind_login","false")
                            .method(Method.POST)
                            .execute();


            //这儿的SESSIONID需要根据要登录的目标网站设置的session Cookie名字而定
            Connection con=Jsoup.connect(listURL);
            //设置访问形式（电脑访问，手机访问）：直接百度都参数设置
            con.header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
            //把登录信息的cookies保存如map对象里面
            Map <String,String> map=res.cookies();
            Iterator<Entry<String,String>> it =map.entrySet().iterator();
            while(it.hasNext()){
                Entry<String,String> en= it.next();
                //把登录的信息放入请求里面
                con =con.cookie(en.getKey(), en.getValue());

            }
            //再次获取Document对象。
            Document objectDoc = con.get();

            Elements elements = objectDoc.getAllElements();//获取这个连接返回页面的源码内容（不是源码跟源码差不多）
            for (Element element : elements) {
                //element是迭代出来的标签：如：<div><span></span></div>
                Elements elements2= element.getAllElements();//
                for (Element element2 : elements2) {
                    element2.text();
                    element2.attr("href");//获取标签属性。element2代表a标签：href代表属性
                    element2.text();//获取标签文本
                }
            }

            //********************************不需要登录的*************************************************

            String URL = "http://www.p2peye.com/blacklist.php?p=2";
            Document conTemp = Jsoup.connect(URL).get();
            Elements elementsTemps = conTemp.getAllElements();
            for (Element elementsTemp : elementsTemps) {
                elementsTemp.text();
                elementsTemp.attr("href");//获取标签属性。element2代表a标签：href代表属性
                elementsTemp.text();//获取标签文本
            }


            //********************************ajax方法获取内容。。。*************************************************。
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                StringBuffer sb = new StringBuffer();
                URL getUrl = new URL(URL);
                connection = (HttpURLConnection)getUrl.openConnection();
                reader = new BufferedReader(new InputStreamReader(
                        connection.getInputStream(),"utf-8"));
                String lines;
                while ((lines = reader.readLine()) != null) {
                    sb.append(lines);
                };
                List<Map<String, Object>> list = parseJSON2List(sb.toString());//json转换成list
            } catch (Exception e) {

            } finally{
                if(reader!=null)
                    try {
                        reader.close();
                    } catch (IOException e) {
                    }
                // 断开连接
                connection.disconnect();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static Map<String, Object> parseJSON2Map(String jsonStr){
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    public static List<Map<String, Object>> parseJSON2List(String jsonStr){
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){
            JSONObject json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
    }



}
