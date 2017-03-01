package edu.feicui.news.ui;

import android.os.Bundle;

import edu.feicui.news.R;
import edu.feicui.news.ui.base.MyBaseActivity;

public class LogoActivity extends MyBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        hideActionBar();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    startActivity(NewsActivity.class);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onClickActionBar(int type) {

    }
}
