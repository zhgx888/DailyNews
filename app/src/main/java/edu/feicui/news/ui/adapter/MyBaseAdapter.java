package edu.feicui.news.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.news.model.entity.News;

/**
 * Created by Administrator on 2017/2/16 0016.
 */

public abstract class MyBaseAdapter extends BaseAdapter {//适配器基类
    public List<News> newses = new ArrayList<News>();
    Context mCtx;
    public MyBaseAdapter(Context context) {
        mCtx=context;
    }
    @Override
    public int getCount() {
        return newses.size();
    }

    @Override
    public Object getItem(int position) {
        return newses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, convertView, parent);
    }

    public abstract View getItemView(int position, View convertView, ViewGroup parent);
}
