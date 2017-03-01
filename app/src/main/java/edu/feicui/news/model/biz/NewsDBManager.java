package edu.feicui.news.model.biz;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import edu.feicui.news.model.db.DBHelper;
import edu.feicui.news.model.entity.News;

/**
 * Created by Administrator on 2017/2/16 0016.
 */

public class NewsDBManager {//数据库管理类
    public static final String DATA_BASE_NAME = "dailynews";
    public static final int VERSION = 1;
    SQLiteDatabase db;
    DBHelper dbHelper;

    public NewsDBManager(Context ctx) {
        dbHelper = new DBHelper(ctx);
    }

    public void insertNews(News news) {//插入新闻数据库
        db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("summary", news.summary);
//        cv.put("icon", news.icon);
//        cv.put("stamp", news.stamp);
//        cv.put("title", news.title);
//        cv.put("nid", news.nid);
//        cv.put("link", news.link);
//        cv.put("type", news.type);
//        db.insert("news", null, cv);
        try {
            db.execSQL("insert into news(summary,icon,stamp,title,nid,link,type) values(?,?,?,?,?,?,?)"
                    , new Object[]{news.summary, news.icon, news.stamp, news.title, news.nid, news.link,
                            news.type});
        } catch (SQLException e) {
            Log.e("数据库已有此信息！", "");
        }
        closeDb();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * 查询数据库军事信息
     * 拟定nid除以2余0为军事
     *
     * @return
     */
    public ArrayList<News> queryMilitaryNews() {
        ArrayList<News> newses = new ArrayList<News>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("news", new String[]{"summary", "icon", "stamp", "title", "nid",
                "link", "type"}, null, null, null, null, "nid");
        while (cursor.moveToNext()) {
            if (cursor.getInt(4) % 2 !=  0) {//拟定nid除以2余0为军事
                continue;
            } else {
                News news = new News();
                news.summary = cursor.getString(0);
                news.icon = cursor.getString(1);
                news.stamp = cursor.getString(2);
                news.title = cursor.getString(3);
                news.nid = cursor.getInt(4);
                news.link = cursor.getString(5);
                news.type = cursor.getInt(6);
                newses.add(news);
            }
        }
        closeDb();
        return newses;
    }

    /**
     * 查询数据库社会信息
     * 拟定nid除以2余1为社会
     *
     * @return
     */
    public ArrayList<News> querySocietyNews() {
        ArrayList<News> newses = new ArrayList<News>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("news", new String[]{"summary", "icon", "stamp", "title", "nid",
                "link", "type"}, null, null, null, null, "nid");
        while (cursor.moveToNext()) {
            if (cursor.getInt(4) % 2 != 1) {//拟定nid除以2余1为社会
                continue;
            } else {
                News news = new News();
                news.summary = cursor.getString(0);
                news.icon = cursor.getString(1);
                news.stamp = cursor.getString(2);
                news.title = cursor.getString(3);
                news.nid = cursor.getInt(4);
                news.link = cursor.getString(5);
                news.type = cursor.getInt(6);
                newses.add(news);
            }
        }
        closeDb();
        return newses;
    }

    /**
     * 查询数据库全部信息
     *
     * @return
     */
    public ArrayList<News> queryAllNews() {
        ArrayList<News> newses = new ArrayList<News>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("news", new String[]{"summary", "icon", "stamp", "title", "nid",
                "link", "type"}, null, null, null, null, "nid");
        while (cursor.moveToNext()) {
            News news = new News();
            news.summary = cursor.getString(0);
            news.icon = cursor.getString(1);
            news.stamp = cursor.getString(2);
            news.title = cursor.getString(3);
            news.nid = cursor.getInt(4);
            news.link = cursor.getString(5);
            news.type = cursor.getInt(6);
            newses.add(news);
        }
        closeDb();
        return newses;
    }
    /**
     * 查询数据库全部信息
     *
     * @return
     */
    public ArrayList<News> queryAllFavorNews() {
        ArrayList<News> newses = new ArrayList<News>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("favornews", new String[]{"summary", "icon", "stamp", "title", "nid",
                "link", "type"}, null, null, null, null, "nid");
        while (cursor.moveToNext()) {
            News news = new News();
            news.summary = cursor.getString(0);
            news.icon = cursor.getString(1);
            news.stamp = cursor.getString(2);
            news.title = cursor.getString(3);
            news.nid = cursor.getInt(4);
            news.link = cursor.getString(5);
            news.type = cursor.getInt(6);
            newses.add(news);
        }
        closeDb();
        return newses;
    }

    public void closeDb() {
        db.close();
    }
}
