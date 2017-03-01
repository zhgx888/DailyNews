package edu.feicui.news.model.biz;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import edu.feicui.news.model.entity.Gsons;
import edu.feicui.news.model.entity.News;

/**
 * Created by Administrator on 2017/2/16 0016.
 */

public class NewsParser {//新闻数据解析类
    public NewsDBManager dbManager;

    public NewsParser(Context ctx) {
        dbManager = new NewsDBManager(ctx);
    }

    public void jsonParser(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                News news = new News();
                news.summary = object.getString("summary");
                news.icon = object.getString("icon");
//                String icon=object.getString("icon");
//                news.icon=icon.substring(icon.lastIndexOf("/")+1,icon.length());
                news.stamp = object.getString("stamp");
                news.title = object.getString("title");
                news.nid = object.getInt("nid");
                news.link = object.getString("link");
                news.type = object.getInt("type");
                dbManager.insertNews(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void gsonParser(String result) {
        Gson gson = new Gson();
        Type type = new TypeToken<Gsons<List<News>>>() {
        }.getType();
        Gsons<List<News>> gsons = gson.fromJson(result, type);
        //获取内部对象数据 Data
        List<News> newses = gsons.data;
        for (News newse : newses) {
            dbManager.insertNews(newse);
        }

    }
}
