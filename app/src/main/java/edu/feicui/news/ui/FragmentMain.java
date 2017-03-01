package edu.feicui.news.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.feicui.news.R;
import edu.feicui.news.common.HttpURLConnectionUti;
import edu.feicui.news.model.biz.NewsParser;
import edu.feicui.news.model.entity.News;
import edu.feicui.news.ui.adapter.NewsAdapter;
import edu.feicui.news.view.XListView.XListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment implements View.OnClickListener, XListView.IXListViewListener {
    private Handler handler;
    private WebView mWbNews;
    private ArrayList<News> data = null;
    private HttpURLConnectionUti httpURL;
    private NewsParser newsParser;
    private TextView newsTxtMilitary;
    private TextView newsTxtSociety;
    //    private ListView mLvNews;
    private NewsAdapter newsAdapter;
    private XListView mXListView;
    private int startIndex;
    private int endIndex;

    public FragmentMain() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mian, container, false);
//        mLvNews = (ListView) view.findViewById(R.id.news_list);
        mXListView = (XListView) view.findViewById(R.id.xLv_header);
        mXListView.setPullLoadEnable(true);
        httpURL = new HttpURLConnectionUti();
        newsParser = new NewsParser(getContext());
        newsAdapter = new NewsAdapter(getContext(), mXListView);
        mXListView.setAdapter(newsAdapter);
        mXListView.setXListViewListener(this);
        newsTxtMilitary = (TextView) view.findViewById(R.id.news_txt_military);
        newsTxtSociety = (TextView) view.findViewById(R.id.news_txt_society);
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpURL.getDataHttpURLConnectionGET();
                if (httpURL.result == null) {
                    readDataFormDb();
                } else {
                    newsParser.gsonParser(httpURL.result);//Gson解析
//                    newsParser.jsonParser(httpURL.result);//Json解析
                    handler.sendEmptyMessage(100);
                }
            }
        }).start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 100) {
                    readDataFormDb();//从数据库读取数据
                }
            }
        };
        newsTxtMilitary.setOnClickListener(this);
        newsTxtSociety.setOnClickListener(this);
        mXListView.setAdapter(newsAdapter);
        mXListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                newsTitle.setTextColor(getResources().getColor(R.color.colorGray));
//                newsSummary.setTextColor(getResources().getColor(R.color.colorGray));
                Intent intent = new Intent();
//                Bundle bundle = new Bundle();
                intent.putExtra("link", newsAdapter.newses.get(position).link);
                intent.setClass(getActivity(), NewsShowActivity.class);
                startActivity(intent);
            }
        });
        mXListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://正在滚动中
                        newsAdapter.isScrolling = true;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://停止滚动
                        newsAdapter.isScrolling = false;
                        newsAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int
                    totalItemCount) {
                //设置当前屏幕显示的起始index和结束index   为一会停止滚动时，知道从什么位置开始加载图片
                startIndex = firstVisibleItem;
                endIndex = startIndex + visibleItemCount;
                if (endIndex >= totalItemCount) {
                    endIndex = totalItemCount - 1;
                }
            }
        });
        return view;
    }

    /**
     * 从数据库读取数据
     */
    private void readDataFormDb() {
        data = newsParser.dbManager.queryAllNews();
        if (data.size() == 0) {
        } else {
            newsAdapter.addendData(data);//追加数据，是否清空adapter原有数据
            newsAdapter.notifyDataSetChanged();//刷新UI
        }
    }

    /**
     * 分类控件点击事件
     */
    boolean btn_isMilitary = true;
    boolean btn_isSociety = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_txt_military://军事图标点击事件
                btn_isSociety = true;
                newsTxtSociety.setTextColor(getResources().getColor(R.color.colorBlack));
                if (btn_isMilitary) {
                    newsTxtMilitary.setTextColor(getResources().getColor(R.color.colorRed));
                    data = newsParser.dbManager.queryMilitaryNews();
                    btn_isMilitary = false;
                } else {
                    newsTxtMilitary.setTextColor(getResources().getColor(R.color.colorBlack));
                    data = newsParser.dbManager.queryAllNews();
                    btn_isMilitary = true;
                }
                if (data.size() == 0) {
                    return;
                } else {
                    newsAdapter.addendData(data);//追加数据，是否清空adapter原有数据
                    newsAdapter.notifyDataSetChanged();//刷新UI
                }
                break;
            case R.id.news_txt_society://社会图标点击事件
                btn_isMilitary = true;
                newsTxtMilitary.setTextColor(getResources().getColor(R.color.colorBlack));
                if (btn_isSociety) {
                    newsTxtSociety.setTextColor(getResources().getColor(R.color.colorRed));
                    data = newsParser.dbManager.querySocietyNews();
                    btn_isSociety = false;
                } else {
                    newsTxtSociety.setTextColor(getResources().getColor(R.color.colorBlack));
                    data = newsParser.dbManager.queryAllNews();
                    btn_isSociety = true;
                }
                if (data.size() == 0) {
                    return;
                } else {
                    newsAdapter.addendData(data);//追加数据，是否清空adapter原有数据
                    newsAdapter.notifyDataSetChanged();//刷新UI
                }
                break;
        }
    }

    private void onLoad() {
        mXListView.stopRefresh();
        mXListView.stopLoadMore();
        mXListView.setRefreshTime("刚刚");
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (data.size() == 0) {
                    return;
                } else {
                    newsAdapter.addendData(data);//追加数据，是否清空adapter原有数据
                    onLoad();
                }
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (data.size() == 0) {
                    return;
                } else {
                    newsAdapter.notifyDataSetChanged();//刷新UI
                    onLoad();
                }
            }
        }, 2000);
    }
}
