package com.github.study.httpclient;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *  学信网爬虫
 * Created by pc on 2018/9/7.
 */
public class HCTest {

    static CookieStore cookieStore = null;
    static HttpClientContext context = null;

    private static void login() throws Exception {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        //phone: "13600535837", code: "1111", is_bind_login: false
        formparams.add(new BasicNameValuePair("phone", "13600535837"));
        formparams.add(new BasicNameValuePair("code", "1111"));
        HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setConnectionRequestTimeout(5000)
                .build();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://hhd-api.zhiyun88u.com/tmind/v1/login/phone");
        post.setEntity(reqEntity);
        post.setConfig(requestConfig);
        CloseableHttpResponse response = client.execute(post);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity resEntity = response.getEntity();
            String message = EntityUtils.toString(resEntity, "utf-8");
            System.out.println("111" + message);
            HttpGet httpGet = new HttpGet("http://hhd-api.zhiyun88u.com/tmind/v1/ious?limit=20&offset=0&is_borrow=");
            response = client.execute(httpGet);
            resEntity = response.getEntity();
            message = EntityUtils.toString(resEntity, "utf-8");
            System.out.println("222" + message);
        } else {
            System.out.println("请求失败");
        }
    }

    private static void loginXxw() throws Exception {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();

//        username: 383984883@qq.com
//        password: shen86312971
//        lt: LT-1083756-vjdQiFBTWJG29UceUDRdWgvr1pfl1p
//        execution: e2s1
//        _eventId: submit
//        submit: 登  录
        formparams.add(new BasicNameValuePair("username", "383984883@qq.com"));
        formparams.add(new BasicNameValuePair("password", "shen86312971"));
        formparams.add(new BasicNameValuePair("lt", "LT-1083756-vjdQiFBTWJG29UceUDRdWgvr1pfl1p"));
        formparams.add(new BasicNameValuePair("execution", "e2s1"));
        formparams.add(new BasicNameValuePair("_eventId", "submit"));
        formparams.add(new BasicNameValuePair("submit", "登  录"));
        HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setConnectionRequestTimeout(5000)
                .build();

        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://account.chsi.com.cn/passport/login");
        post.setEntity(reqEntity);
        post.setConfig(requestConfig);
        HttpResponse response = client.execute(post);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity resEntity = response.getEntity();
            String message = EntityUtils.toString(resEntity, "utf-8");
            System.out.println("111" + message);
            HttpGet httpGet = new HttpGet("http://hzq-api.zhiyun88u.com/tmind/v1/ious?limit=20&offset=0&is_borrow=");
            response = client.execute(httpGet);
            resEntity = response.getEntity();
            message = EntityUtils.toString(resEntity, "utf-8");
            System.out.println("222" + message);
        } else {
            System.out.println("请求失败");
        }
    }

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    private static void login2() {
        try {

            System.out.println("444");
            HttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://hzq-api.zhiyun88u.com/tmind/v1/ious?limit=20&offset=0&is_borrow=");
            HttpResponse response = client.execute(httpGet);
            System.out.println("3333" + response.getStatusLine());
            HttpEntity resEntity = response.getEntity();
            String message = EntityUtils.toString(resEntity, "utf-8");
            System.out.println("222" + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getPage() throws IOException {
        // 创建默认的httpClient实例.
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setCircularRedirectsAllowed(true)
//                .build();
//        CloseableHttpClient httpclient = HttpClients.custom()
//                .setDefaultRequestConfig(requestConfig)
//                .setRedirectStrategy(new LaxRedirectStrategy())
//                .build();
        // 创建httppost
        CloseableHttpClient httpclient =HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://account.chsi.com.cn/passport/login");
        httpGet.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        //cookie
        String setCookie = response.getHeaders("Set-Cookie")[0].getValue();

        System.out.println("JSESSIONID:" + setCookie);

        String page=EntityUtils.toString(entity, "UTF-8");
        System.out.println("--------------------------------------");
        System.out.println("Response content execution: " + getStr( "name=\"execution\" value=(.*?)/>",page));
        System.out.println("Response content lt: " + getStr("name=\"lt\" value=(.*?)/>",page));
        System.out.println("--------------------");

        String postUrl="https://account.chsi.com.cn/passport/login";
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();

        formparams.add(new BasicNameValuePair("username", "383984883@qq.com"));
        formparams.add(new BasicNameValuePair("password", "shen86312971"));
        formparams.add(new BasicNameValuePair("lt", getStr("name=\"lt\" value=(.*?)/>", page)));
        formparams.add(new BasicNameValuePair("execution", getStr( "name=\"execution\" value=(.*?)/>",page)));
        formparams.add(new BasicNameValuePair("_eventId", "submit"));
        formparams.add(new BasicNameValuePair("submit", "登  录"));
        postForm(postUrl,httpclient,formparams);
    }


    /**
     * post方式提交表单（模拟用户登录请求）
     */
    public static void postForm(String url, CloseableHttpClient httpclient, List<NameValuePair> formparams  ) {
        // 创建httppost
        HttpPost httppost = new HttpPost(url);

        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            httppost.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
            httppost.setHeader("Content-Type","application/x-www-form-urlencoded");
            CloseableHttpResponse response = httpclient.execute(httppost);


            try {
                HttpEntity entity = response.getEntity();
                String resultPage=EntityUtils.toString(entity, "UTF-8");
                if (entity != null) {
                    System.out.println("--------------------------------------");
                    System.out.println("###LOGIN Response content execution: " + resultPage);
                    System.out.println("--------------------------------------");
                    if(resultPage.trim().isEmpty()) {
                    //if (response.getStatusLine().getStatusCode() == 200) {
                        getPage("https://my.chsi.com.cn/archive/gdjy/xj/show.action", httpclient);
                    }else{
                        String resultMsg=getStr( "<div id=\"status\" class=\"errors\">(.*?)</div>",resultPage);
                        System.out.println("rrrrrrrrrrrrr="+resultMsg);
                    }

                    //-------------
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getPage(String url, CloseableHttpClient httpclient) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)   //设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .setRedirectsEnabled(false)
                .build();
        // 创建httppost
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

        CloseableHttpResponse response = httpclient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        String page=EntityUtils.toString(entity, "UTF-8");

        int code = response.getStatusLine().getStatusCode();
        String newuri="";
        if (code == 302) {
            Header header = response.getFirstHeader("location"); // 跳转的目标地址是在 HTTP-HEAD 中的
            newuri = header.getValue(); // 这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息是啥。
            getPage2(newuri,httpclient);
            return;
        }
        System.out.println("--------------------------------------"+page);
        String imgUrl=getStr( "/gdjy/photo/show.action(.*?)\"",page,2);
        System.out.println("--------------------------------------");
        System.out.println("END############getPage Response content execution:https://my.chsi.com.cn/archive/gdjy/photo/show.action" + imgUrl);
        downloadImg("https://my.chsi.com.cn/archive/gdjy/photo/show.action" + imgUrl,httpclient);
//        System.out.println("--------------------"+page);
        //https://my.chsi.com.cn/archive/gdjy/photo/show.action?pid=4d2ab66f312b85f6a17b56d85a309d27";
    }
    public static void getPage2(String url, CloseableHttpClient httpclient) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

        CloseableHttpResponse response = httpclient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        String page=EntityUtils.toString(entity, "UTF-8");

        String imgUrl=getStr( "/gdjy/photo/show.action(.*?)\"",page,2);
        System.out.println("--------------------------------------");
        System.out.println("END############getPage Response content execution:https://my.chsi.com.cn/archive/gdjy/photo/show.action" + imgUrl);
        downloadImg("https://my.chsi.com.cn/archive/gdjy/photo/show.action" + imgUrl,httpclient);
//        System.out.println("--------------------"+page);
        //https://my.chsi.com.cn/archive/gdjy/photo/show.action?pid=4d2ab66f312b85f6a17b56d85a309d27";
    }
    private static void downloadImg(String url,CloseableHttpClient httpclient) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

        CloseableHttpResponse response = httpclient.execute(httpGet);
        //得到实体
        HttpEntity entity = response.getEntity();

        byte[] data = EntityUtils.toByteArray(entity);

        //图片存入磁盘
        FileOutputStream fos = new FileOutputStream("d:/mpl.jpg");
        fos.write(data);
        fos.close();

        System.out.println("图片下载成功!!!!");
    }

    public static void setContext() {
        System.out.println("----setContext");
        context = HttpClientContext.create();
        Registry<CookieSpecProvider> registry = RegistryBuilder
                .<CookieSpecProvider> create()
                .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
                .register(CookieSpecs.BROWSER_COMPATIBILITY,
                        new BrowserCompatSpecFactory()).build();
        context.setCookieSpecRegistry(registry);
        context.setCookieStore(cookieStore);
    }
    public static void setCookieStore(HttpResponse httpResponse) {
        System.out.println("----setCookieStore");
        cookieStore = new BasicCookieStore();
        // JSESSIONID
        String setCookie = httpResponse.getFirstHeader("Set-Cookie")
                .getValue();
        String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
                setCookie.indexOf(";"));
        System.out.println("JSESSIONID:" + JSESSIONID);
        // 新建一个Cookie
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
                JSESSIONID);
        cookie.setVersion(0);
        cookie.setDomain("127.0.0.1");
        cookie.setPath("/CwlProClient");
        // cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
        // cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
        // cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
        // cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
        cookieStore.addCookie(cookie);
    }
    private static  String getStr(String rgex, String str){

        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(str);
        m.find();
       return m.group(1).replaceAll("\"","").trim();
    }
    private static  String getStr(String rgex, String str,int num){

        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(str);
        for (int i=0;i<num;i++){
            m.find();
        }
        return m.group(1).replaceAll("\"","").trim();

    }
    public static void main(String[] args) throws Exception {
       getPage();
    }
}

