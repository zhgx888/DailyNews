package edu.feicui.news.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.feicui.news.R;
import edu.feicui.news.common.MBitMapUtil;
import edu.feicui.news.common.NetUtil;
import edu.feicui.news.model.entity.News;

/**
 * Created by Administrator on 2017/2/16 0016.
 */

public class NewsAdapter extends MyBaseAdapter
//        implements AbsListView.OnScrollListener
{//新闻主界面适配器
    private LayoutInflater layoutInflater;
    private MBitMapUtil mBitMapUtil;
    NetUtil netUtil;
    ListView mLvNews;
    public boolean isScrolling = false;
    News news;

    public NewsAdapter(Context context, ListView listView) {
        super(context);
        mLvNews = listView;//优化ListView,已在Avtivity里优化
        netUtil = new NetUtil();
        layoutInflater = (LayoutInflater) mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBitMapUtil = new MBitMapUtil();
//        mLvNews.setOnScrollListener(this);
    }

    public void addendData(ArrayList<News> data) {//追加数据，是否清空adapter原有数据
        super.newses = data;
    }


    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        //1.复用view优化listview,创建一个view作为getview的返回值用来显示一个条目
        View view = convertView;
        ViewHolder viewHolder = new ViewHolder();
        /**
         * 防止运行时会来回滚动一直生成新布局占用内存
         */
        if (view == null) {
            view = layoutInflater.inflate(R.layout.news_list, null);
            viewHolder.mTxtTitle = (TextView) view.findViewById(R.id.news_list_title);
            viewHolder.mTxtSummary = (TextView) view.findViewById(R.id.news_list_summary);
            viewHolder.mTxtStamp = (TextView) view.findViewById(R.id.news_list_stamp);
            viewHolder.mImgIcon = (ImageView) view.findViewById(R.id.news_list_img);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        news = newses.get(position);
        viewHolder.mTxtTitle.setText(news.title);
        viewHolder.mTxtSummary.setText(news.summary);
        viewHolder.mTxtStamp.setText(news.stamp);
        if (isScrolling) {
            viewHolder.mImgIcon.setImageDrawable(mCtx.getResources().getDrawable(R.mipmap.ic_launcher));
        } else {
            mBitMapUtil.display(viewHolder.mImgIcon, news.icon);
        }
        return view;
    }

//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        switch (scrollState) {
//            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://正在滚动中
//                isScrolling=true;
//                break;
//            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://停止滚动
//                isScrolling=false;
//                this.notifyDataSetChanged();
//                break;
//        }
//    }
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int
//            totalItemCount) {
//        //设置当前屏幕显示的起始index和结束index   为一会停止滚动时，知道从什么位置开始加载图片
////        startIndex = firstVisibleItem;
////        endIndex = startIndex + visibleItemCount;
////        if (endIndex >= totalItemCount) {
////            endIndex = totalItemCount - 1;
////        }
//    }

//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        Log.i("~~~~~~~~~","$%$#%$#@");
//        switch (scrollState) {
//            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://正在滚动中
//                isScrolling=true;
//                Log.i("####@@@@@@@isScrolling",isScrolling+"");
//                break;
//            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://停止滚动
//                isScrolling=false;
//                Log.i("####$$$$isScrolling",isScrolling+"");
//                this.notifyDataSetChanged();
//                break;
//        }
//    }
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//    //设置当前屏幕显示的起始index和结束index   为一会停止滚动时，知道从什么位置开始加载图片
//        Log.i("~~~~~~~~~","$%$#%$#@");
//        startIndex = firstVisibleItem;
//        endIndex = firstVisibleItem + visibleItemCount;
//        if (endIndex >= totalItemCount) {
//            endIndex = totalItemCount - 1;
//        }
//    }

    class ViewHolder {
        public ImageView mImgIcon;
        public TextView mTxtTitle;
        public TextView mTxtSummary;
        public TextView mTxtStamp;
    }
}
