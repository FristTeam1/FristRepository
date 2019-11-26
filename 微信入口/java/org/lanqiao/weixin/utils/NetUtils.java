package org.lanqiao.weixin.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.lanqiao.weixin.bean.AccessToken;
import org.lanqiao.weixin.menu.Button;
import org.lanqiao.weixin.menu.Menu;
import org.lanqiao.weixin.menu.ViewButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class NetUtils {

    public static final String APPID = "wxf60df894e94ed183";
    public static final String APPSECRET = "c142817eae0bace7475f660e8bbb5f53";

    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    //创建菜单
    public static final String CREATE_MENU_URL = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //删除菜单
    public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    public static String doGetStr(String urlPath) throws IOException {
        //创建URL对象，建立远程连接
        URL url = new URL(urlPath);
        //返回一个连接，连接后台和微信服务器
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //请求方式
        urlConnection.setRequestMethod("GET");
        //打开输入流，读取微信服务器返回的数据
        urlConnection.setDoInput(true);
        //因为是get请求，所有关闭输出流，不需要向微信输出
        urlConnection.setDoOutput(false);
        //打开连接
        urlConnection.connect();

        //字节流包装成高效流
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

        //读取返回的数据
        String len;
        StringBuilder sb = new StringBuilder();
        while((len=br.readLine())!=null){
            sb.append(len);
        }

        return sb.toString();
    }

    public static String doPostStr(String urlPath , String params) throws IOException {
        URL url = new URL(urlPath);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);
        //打开输出流，因为要向微信服务器传递参数
        urlConnection.setDoOutput(true);
        //关闭缓存
        urlConnection.setUseCaches(false);

        //向微信服务器传递参数
        PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream());
        printWriter.write(params);

        printWriter.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

        String len;
        StringBuilder sb = new StringBuilder();
        while((len=br.readLine())!=null){
            sb.append(len);
        }

        return sb.toString();
    }

    public static AccessToken getAccessToken(){
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);

        AccessToken token = new AccessToken();
        try {
            String json = doGetStr(url);

            ObjectMapper mapper = new ObjectMapper();
            token = mapper.readValue(json,AccessToken.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return token;
    }

    /**
     *创建菜单
     */
    public static void createMenu(AccessToken accessToken) throws IOException {
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token());

        ViewButton button1 = new ViewButton();
        button1.setType(MessageUtils.MESSAGE_EVENT_VIEW);
        button1.setName("我要看房");
        button1.setUrl("http://www.baidu.com/");

        ViewButton button2 = new ViewButton();
        button2.setType(MessageUtils.MESSAGE_EVENT_VIEW);
        button2.setName("预约看房");
        button2.setUrl("http://www.google.cn/");

        Menu menu = new Menu();
        menu.setButton(new Button[]{button1,button2});

        ObjectMapper mapper = new ObjectMapper();
        String params = mapper.writeValueAsString(menu);
        System.out.println(params);

        //{"errcode":0,"errmsg":"ok"}
        String response = doPostStr(url, params);
        System.out.println(response);
        Map<String, Object> map = (Map<String, Object>)mapper.readValue(response, new TypeReference<Map<String, Object>>() {});

        int errcode = -1;
        errcode = (int) map.get("errcode");

        if(errcode == 0){
            System.out.println("菜单创建成功");
        }else {
            System.out.println("菜单创建失败");
        }
    }

    /**
     *删除菜单
     */
    public static void deleteMenu(AccessToken accessToken) throws IOException {
        String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token());

        String result = doGetStr(url);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = (Map<String, Object>)mapper.readValue(result, new TypeReference<Map<String, Object>>() {});

        int errcode = -1;

        errcode  = (int) map.get("errcode");

        if(errcode == 0){
            System.out.println("删除菜单成功");
        }else{
            System.out.println("删除菜单失败");
        }
    }


}
