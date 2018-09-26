package thread.src.com.github.study.httpclient;

import com.github.study.util.RegexSpiderUtil;
import com.github.study.util.ZxingUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/9/11.
 */
public class ZfbDemo {

    private static final String QRCODE_STATUS_WAITING = "waiting";
    //扫码成功
    private static final String QRCODE_STATUS_SCANNED = "scanned";
    //确认登录
    private static final String QRCODE_STATUS_CONFIRMED = "confirmed";

    private static void getZfEwm() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://auth.alipay.com/login/index.htm");
        httpGet.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();

            String page = EntityUtils.toString(entity, "UTF-8");
            getLoginParams(page);
            //System.out.println(getStr("barcode: \"(.*?)\"", page));
            System.out.println(ZxingUtil.createQrCode(RegexSpiderUtil.getStr("barcode: \"(.*?)\"", page)));
            //生成二维码后监听二维码状态
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            int i = 0;
            params.add(new BasicNameValuePair("securityId", RegexSpiderUtil.getStr("securityId=(.*?)\"", page).toString().replaceAll("%257C", "|")));
            while (true) {
                System.out.println(RegexSpiderUtil.getStr("securityId=(.*?)\"", page));
                params.add(new BasicNameValuePair("_callback", "light.request._callbacks.callback" + i));
                i += 1;
                Thread.sleep(3000);
                if (checkStatus(params, httpclient, page)) {
                    break;
                }
            }
        }
    }

    private static boolean checkStatus(List<NameValuePair> params, CloseableHttpClient httpclient, String indexPage) throws Exception {
        String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
        HttpGet httpGet = new HttpGet("https://securitycore.alipay.com/barcode/barcodeProcessStatus.json" + "?" + paramsStr);
        httpGet.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (response.getStatusLine().getStatusCode() == 200) {
            String page = EntityUtils.toString(entity, "UTF-8");
            //System.out.println(getStr("barcode: \"(.*?)\"", page));
            System.out.println(page);
            //如果确认了 手机信息
            if (page.indexOf(ZfbDemo.QRCODE_STATUS_CONFIRMED) != -1) {
                login(httpclient, indexPage);
                return true;
            }
        }
        return false;
    }

    private static void login(CloseableHttpClient httpclient, String indexPage) throws Exception {
        List<NameValuePair> loginparams = getLoginParams(indexPage);
        HttpEntity reqEntity = new UrlEncodedFormEntity(loginparams, "utf-8");
        HttpPost post = new HttpPost("https://authet15.alipay.com/login/index.htm");
        post.setEntity(reqEntity);
        CloseableHttpResponse response = httpclient.execute(post);

        getInformation(httpclient);

    }

    private static List<NameValuePair> getLoginParams(String indexPage) {
        List<NameValuePair> loginparams = new ArrayList<NameValuePair>();
        String support = RegexSpiderUtil.getStr("<input type=\"hidden\" name=\"support\" value = \"(.*?)\"", indexPage);
        String CtrlVersion = RegexSpiderUtil.getStr("<input type=\"hidden\" name=\"CtrlVersion\" value = \"(.*?)\"", indexPage);
        //String loginScene=RegexSpiderUtil.getStr("<input type=\"hidden\" name=\"loginScene\" value = \"(.*?)\"", indexPage);
        String rds_form_token = RegexSpiderUtil.getStr("<input type=\"hidden\" value=\"(.*?)\" name=\"rds_form_token", indexPage);
        String qrCodeSecurityId = RegexSpiderUtil.getStr("securityId=(.*?)\"", indexPage).toString().replaceAll("%257C", "|");
        String J_aliedit_key_hidn = RegexSpiderUtil.getStr("<input type=\"hidden\" name=\"J_aliedit_key_hidn\" value=\"(.*?)\"", indexPage);
        String J_aliedit_uid_hidn = RegexSpiderUtil.getStr("<input type=\"hidden\" name=\"J_aliedit_uid_hidn\" value=\"(.*?)\"", indexPage);
        String alieditUid = RegexSpiderUtil.getStr("<input type=\"hidden\" id=\"alieditUid\" name=\"alieditUid\" value=\"(.*?)\"", indexPage);
        String passwordSecurityId = RegexSpiderUtil.getStr("<input type=\"hidden\" name=\"passwordSecurityId\" value=\"(.*?)\"", indexPage);

        loginparams.add(new BasicNameValuePair("support", support));
        loginparams.add(new BasicNameValuePair("CtrlVersion", CtrlVersion));
        loginparams.add(new BasicNameValuePair("loginScene", "index"));
        loginparams.add(new BasicNameValuePair("rds_form_token", rds_form_token));
        loginparams.add(new BasicNameValuePair("json_tk", "json_tk"));
        loginparams.add(new BasicNameValuePair("method", "qrCodeLogin"));
        loginparams.add(new BasicNameValuePair("qrCodeSecurityId", qrCodeSecurityId));
        loginparams.add(new BasicNameValuePair("qrCodeSecurityId", J_aliedit_key_hidn));
        loginparams.add(new BasicNameValuePair("qrCodeSecurityId", J_aliedit_uid_hidn));
        loginparams.add(new BasicNameValuePair("qrCodeSecurityId", alieditUid));
        loginparams.add(new BasicNameValuePair("qrCodeSecurityId", passwordSecurityId));
        return loginparams;
    }

    private static void getInformation(CloseableHttpClient httpclient) throws IOException {
        // HttpGet httpGet = new HttpGet("https://custweb.alipay.com/account/index.htm");
        HttpGet httpGet = new HttpGet("https://personalweb.alipay.com/account/payAndCredit.htm");

        httpGet.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String page = EntityUtils.toString(entity, "UTF-8");
        System.out.println(page);
        if (response.getStatusLine().getStatusCode() == 200) {

            //String name=RegexSpiderUtil.getStr("<span id=\"username\">(.*?)</span>", page);
            //System.out.println(page);
        }
    }

    public static void main(String[] args) throws Exception {
        getZfEwm();
    }
}
