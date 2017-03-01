package edu.feicui.news.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.feicui.news.model.biz.NewsDBManager;

/**
 * Created by Administrator on 2017/2/16 0016.
 */

public class DBHelper extends SQLiteOpenHelper {//数据库辅助类

    public DBHelper(Context ctx) {//创建管理新闻数据库
        super(ctx, NewsDBManager.DATA_BASE_NAME, null, NewsDBManager.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//创建新闻表
        db.execSQL("create table if not exists news(summary text,icon varchar,stamp varchar,title varchar," +
                "nid integer primary key,link varchar,type integer)");
        db.execSQL("create table if not exists favornews(summary text,icon varchar,stamp varchar,title varchar," +
                "nid integer primary key,link varchar,type integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//升级新闻数据库

    }
}
