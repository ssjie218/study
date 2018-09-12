package com.github.study.httpclient;

import com.github.study.util.RegexSpiderUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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

/**
 * 学信网爬虫
 * Created by pc on 2018/9/7.
 */
public class SchoolNetDemo {

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
        formparams.add(new BasicNameValuePair("username", "13849893919"));
        formparams.add(new BasicNameValuePair("password", "lu123456"));
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
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://account.chsi.com.cn/passport/login");
        httpGet.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();


        String page = EntityUtils.toString(entity, "UTF-8");
        System.out.println("--------------------------------------");
        System.out.println("Response content execution: " + RegexSpiderUtil.getStr("name=\"execution\" value=(.*?)/>", page));
        System.out.println("Response content lt: " + RegexSpiderUtil.getStr("name=\"lt\" value=(.*?)/>", page));
        System.out.println("--------------------");

        String postUrl = "https://account.chsi.com.cn/passport/login";
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();

        formparams.add(new BasicNameValuePair("username", "13849893919"));
        formparams.add(new BasicNameValuePair("password", "lu123456"));
        formparams.add(new BasicNameValuePair("lt", RegexSpiderUtil.getStr("name=\"lt\" value=(.*?)/>", page)));
        formparams.add(new BasicNameValuePair("execution", RegexSpiderUtil.getStr("name=\"execution\" value=(.*?)/>", page)));
        formparams.add(new BasicNameValuePair("_eventId", "submit"));
        formparams.add(new BasicNameValuePair("submit", "登  录"));
        postForm(postUrl, httpclient, formparams);
    }


    /**
     * post方式提交表单（模拟用户登录请求）
     */
    public static void postForm(String url, CloseableHttpClient httpclient, List<NameValuePair> formparams) {
        // 创建httppost
        HttpPost httppost = new HttpPost(url);

        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            httppost.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
            httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            CloseableHttpResponse response = httpclient.execute(httppost);


            try {
                HttpEntity entity = response.getEntity();
                String resultPage = EntityUtils.toString(entity, "UTF-8");
                if (entity != null) {
                    System.out.println("--------------------------------------");
                    System.out.println("###LOGIN Response content execution: " + resultPage);
                    System.out.println("--------------------------------------");
                    if (resultPage.trim().isEmpty()) {
                        //if (response.getStatusLine().getStatusCode() == 200) {
                        getPage("https://my.chsi.com.cn/archive/gdjy/xj/show.action", httpclient);
                    } else {
                        String resultMsg = RegexSpiderUtil.getStr("<div id=\"status\" class=\"errors\">(.*?)</div>", resultPage);
                        System.out.println("rrrrrrrrrrrrr=" + resultMsg);
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
        String page = EntityUtils.toString(entity, "UTF-8");

        int code = response.getStatusLine().getStatusCode();
        String newuri = "";
        if (code == 302) {
            Header header = response.getFirstHeader("location"); // 跳转的目标地址是在 HTTP-HEAD 中的
            newuri = header.getValue(); // 这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息是啥。
            getPage2(newuri, httpclient);
            return;
        }
        System.out.println("--------------------------------------" + page);
        String imgUrl = RegexSpiderUtil.getStr("/gdjy/photo/show.action(.*?)\"", page, 2);
        System.out.println("--------------------------------------");
        System.out.println("END############getPage Response content execution:https://my.chsi.com.cn/archive/gdjy/photo/show.action" + imgUrl);
        downloadImg("https://my.chsi.com.cn/archive/gdjy/photo/show.action" + imgUrl, httpclient);
//        System.out.println("--------------------"+page);
        //https://my.chsi.com.cn/archive/gdjy/photo/show.action?pid=4d2ab66f312b85f6a17b56d85a309d27";
    }

    public static void getPage2(String url, CloseableHttpClient httpclient) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

        CloseableHttpResponse response = httpclient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        String page = EntityUtils.toString(entity, "UTF-8");

        String imgUrl = RegexSpiderUtil.getStr("/gdjy/photo/show.action(.*?)\"", page, 2);
        System.out.println("--------------------------------------");
        System.out.println("END############getPage Response content execution:https://my.chsi.com.cn/archive/gdjy/photo/show.action" + imgUrl);
        downloadImg("https://my.chsi.com.cn/archive/gdjy/photo/show.action" + imgUrl, httpclient);
//        System.out.println("--------------------"+page);
        //https://my.chsi.com.cn/archive/gdjy/photo/show.action?pid=4d2ab66f312b85f6a17b56d85a309d27";
    }

    private static void downloadImg(String url, CloseableHttpClient httpclient) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

        CloseableHttpResponse response = httpclient.execute(httpGet);
        //得到实体
        HttpEntity entity = response.getEntity();

        byte[] data = EntityUtils.toByteArray(entity);
        String imgName = System.currentTimeMillis() + ".jpg";
        String path="d:/download/xxw/"+imgName;
        //图片存入磁盘
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(data);
        fos.close();

        System.out.println("图片下载成功!!!!");
    }

    public static void main(String[] args) throws IOException {
        getPage();
    }



}

