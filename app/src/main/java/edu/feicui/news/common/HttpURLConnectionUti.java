package edu.feicui.news.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/2/16 0016.
 */

public class HttpURLConnectionUti {//数据交互类
    final String HTTP = "http://118.244.212.82:9092/newsClient/news_list" +
            "?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    public String result;


    public  void getDataHttpURLConnectionGET() {
        StringBuilder resultData = new StringBuilder("");
        try {
            //新建HttpURLConnection对象
            HttpURLConnection httpUrl = (HttpURLConnection) new URL(HTTP).openConnection();
            //设置超时时间
            httpUrl.setConnectTimeout(5000);
            //设置请求方式
            httpUrl.setRequestMethod("GET");
            //设置使用缓存
            httpUrl.setUseCaches(false);
//            //判断是否连接成功
//            httpUrl.connect();
            if (httpUrl.getResponseCode() == 200) {
                // 获取到读取流
                InputStream in = httpUrl.getInputStream();
                //字节转换字符
                InputStreamReader inReader = new InputStreamReader(in);
                //
                BufferedReader bufReader = new BufferedReader(inReader);
                String inputLine = null;
                while ((inputLine = bufReader.readLine()) != null) {
                    resultData.append(inputLine);
                    resultData.append("\n");
                    result = String.valueOf(resultData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}