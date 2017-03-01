package edu.feicui.news.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import edu.feicui.news.R;
import edu.feicui.news.ui.base.MyBaseActivity;

public class NewsShowActivity extends MyBaseActivity {//新闻内容界面
    private WebView mWbNews;
    private View mErrorView;
    private ProgressBar mPbNews, progressBar;
    RelativeLayout loading_over;
    String url;
    WebSettings webSettings;
    PopupWindow popupWindow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_show);
        initActionBar(this.getString(R.string.app_name), getResources().getDrawable(R.mipmap.back),
                getResources().getDrawable(R.mipmap.news_menu), null);
        mWbNews = (WebView) findViewById(R.id.news_show_wb);
        mPbNews = (ProgressBar) findViewById(R.id.news_show_pb);
        mPbNews.setVisibility(View.GONE);
        loading_over = (RelativeLayout) findViewById(R.id.loading_over);
        setView();
    }

    public void setView() {
        //加载需要显示的网页
        url = this.getIntent().getStringExtra("link");
        mWbNews.loadUrl(url);
        //设置WebView属性，能够执行Javascript脚本
        mWbNews.getSettings().setJavaScriptEnabled(true);
        webSettings = mWbNews.getSettings();
        webSettings.setJavaScriptEnabled(true);     //允许加载javascript
        webSettings.setSupportZoom(true);           //允许缩放
        webSettings.setBuiltInZoomControls(true);   //原网页基础上缩放
        webSettings.setUseWideViewPort(true);       //任意比例缩放
        mWbNews.setWebViewClient(webViewClient);   //设置Web视图
    }

    WebViewClient webViewClient = new WebViewClient() {//处理网页加载失败时
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            showErrorPage();//显示错误页面
        }

        ;

        public void onPageFinished(WebView view, String url) {//处理网页加载成功时
            loading_over.setVisibility(View.GONE);
        }
    };
    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */
    boolean mIsErrorPage;

    private void showErrorPage() {
        LinearLayout webParentView = (LinearLayout) mWbNews.getParent();
        initErrorPage();//初始化自定义页面
        while (webParentView.getChildCount() > 1) {
            webParentView.removeViewAt(0);
        }
//        @SuppressWarnings("deprecation")
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        webParentView.addView(mErrorView, 0, lp);
        mIsErrorPage = true;
    }

    /****
     * 把系统自身请求失败时的网页隐藏
     */
    private void hideErrorPage() {
        LinearLayout webParentView = (LinearLayout) mWbNews.getParent();
        mIsErrorPage = false;
        while (webParentView.getChildCount() > 1) {
            webParentView.removeViewAt(0);
        }
    }

    /***
     * 显示加载失败时自定义的网页
     */
    private void initErrorPage() {
        if (mErrorView == null) {
            mErrorView = View.inflate(this, R.layout.activity_url_error, null);
            RelativeLayout button = (RelativeLayout) mErrorView.findViewById(R.id.online_error_btn_retry);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWbNews.reload();
                }
            });
            mErrorView.setOnClickListener(null);
        }
    }
//设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWbNews.canGoBack()) {
            mWbNews.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return false;
    }

//    private WebView mWbNews;
//    private ProgressBar mPbNews, progressBar;
//    String url;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_news_show);
//        initActionBar(this.getString(R.string.app_name),getResources().getDrawable(R.mipmap.back),null,
// null);
//        mWbNews = (WebView) findViewById(R.id.news_show_wb);
//        mPbNews = (ProgressBar) findViewById(R.id.news_show_pb);
//        webViewSet();//设置网页
//    }
//
//    /**
//     *
//     */
//    public void webViewSet(){
//        //设置WebView属性，能够执行Javascript脚本
//        mWbNews.getSettings().setJavaScriptEnabled(true);
//        //加载需要显示的网页
//        url = this.getIntent().getStringExtra("link");
//        mWbNews.loadUrl(url);
//        //设置Web视图;
//        mWbNews.setWebViewClient(new WebViewClient() {
//
//            @Override
//            //设置回退 ，覆盖Activity类的onKeyDown方法  
//            public void onScaleChanged(WebView view, float oldScale, float newScale) {
//                super.onScaleChanged(view, oldScale, newScale);
//                if (oldScale > newScale) {
//                    mWbNews.zoomIn();
//                    return;
//                }
//                if (oldScale < newScale) {
//                    mWbNews.zoomOut();
//                    return;
//                }
//            }
//
//            /**
//             * 在当前界面打开新连接
//             * @param view
//             * @param url
//             * @return
//             */
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        mWbNews.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                if (newProgress == 100) {
//                    mPbNews.setVisibility(View.GONE);
//                    return;
//                }
//                mPbNews.setProgress(newProgress);
//            }
//
//        });
//    }
////    监听手机屏幕上的按键
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mWbNews.canGoBack()) {
//                mWbNews.goBack();
//                return true;
//            }
//            finish();
//        }
//        return false;
//    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK||mWbNews.canGoBack()) {
//            //如果点击的是后退键  首先判断webView是否能够后退
//            //如果点击的是后退键  首先判断webView是否能够后退   返回值是boolean类型的
////            if (mWbNews.canGoBack()) {
//                mWbNews.goBack();
//                return true;
////            }
////            this.finish();
//        }
//        return false;
//    }
//private long clickTime=0;
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (SystemClock.uptimeMillis() - clickTime <= 1500) {
//                //如果两次的时间差＜1s，就不执行操作
//
//            } else {
//                //当前系统时间的毫秒值
//                clickTime = SystemClock.uptimeMillis();
//                Toast.makeText(NewsShowActivity.this, "再次点击退出", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        }
//        return false;
//    }
//private long clickTime=0;
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exit();
//            return true;
//        }
//        return false;
//    }
//
//    private void exit() {
//        if ((System.currentTimeMillis() - clickTime) > 2000) {
//            Toast.makeText(getApplicationContext(), "再次点击退出",  Toast.LENGTH_SHORT).show();
//            clickTime = System.currentTimeMillis();
//        } else {
//            this.finish();
//            System.exit(0);
//        }
//    }

    @Override
    protected void onClickActionBar(int type) {
        switch (type) {
            case ACTION_LEFT_ICON:
                finish();
                break;
            case ACTION_RIGHT_ICON:
                popupWindow();
                break;
        }
    }

    private void popupWindow() {
        // 获取自定义布局文件activity_popupwindow_left.xml的视图
        final View popupWindow_view = getLayoutInflater().inflate(R.layout.activity_popupwindow_left,
                null, false);
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置动画效果
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            popupWindow.showAsDropDown(mIvCommonRightIcon,0,21);
        }
    }
}

