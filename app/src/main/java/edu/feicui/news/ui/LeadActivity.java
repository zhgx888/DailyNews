package edu.feicui.news.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import edu.feicui.news.R;
import edu.feicui.news.ui.base.MyBaseActivity;

public class LeadActivity extends MyBaseActivity {

    ViewPager mVpg;
    ImageView mImg1, mImg2, mImg3, mImg4;
    ViewPagerAdapter viewPagerAdapter;
    int positions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        hideActionBar();
        mVpg = (ViewPager) findViewById(R.id.lead_viewpager);
        mVpg.setVisibility(ViewPager.VISIBLE);
        mImg1 = (ImageView) findViewById(R.id.lead_img1);
        mImg2 = (ImageView) findViewById(R.id.lead_img2);
        mImg3 = (ImageView) findViewById(R.id.lead_img3);
        mImg4 = (ImageView) findViewById(R.id.lead_img4);
        ArrayList<View> views = initViews();
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.setViews(views);
        mVpg.setAdapter(viewPagerAdapter);
        mVpg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                positions = position;
                switch (positions) {
                    case 0:
                        mImg1.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_selected));
                        mImg2.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                        mImg3.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                        mImg4.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                        break;
                    case 1:
                        mImg1.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                        mImg2.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_selected));
                        mImg3.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                        mImg4.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                        break;
                    case 2:
                        mImg1.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                        mImg2.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                        mImg3.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_selected));
                        mImg4.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                        break;
                    case 3:
                        mImg1.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                        mImg2.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                        mImg3.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                        mImg4.setImageDrawable(getResources().getDrawable(R.mipmap.adware_style_selected));
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                    startActivity(LogoActivity.class);
                                    finish();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        break;
                    default:
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        SharedPreferences setting = getSharedPreferences("ho", 0);
        Boolean user_first = setting.getBoolean("FIRST", true);
        if (user_first) {//第一次
            setting.edit().putBoolean("FIRST", false).commit();
        } else {
            super.startActivity(NewsActivity.class);
            finish();
        }
    }

    private ArrayList<View> initViews() {//界面图片
        ArrayList<View> views = new ArrayList<>();
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT));
//        imageView.setScaleType(ImageView.ScaleType.CENTER);//按图片的原来size居中显示，当图片长/宽超过View的长/宽，则截取图片的居中部分显示
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//按比例扩大图片的size居中显示，使得图片长(宽)等于或大于View的长(宽)
//        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
// 将图片的内容完整居中显示，通过按比例缩小或原来的size使得图片长/宽等于或小于View的长/宽
//        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
// 把图片按比例扩大/缩小到View的宽度，居中显示，其中还包括了FIT_START, FIT_END，这两个效果与之差不多，只是一个是显示于顶部，一个显示于底部；
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);//不按比例缩放图片，目标是把图片塞满整个View
//        imageView.setScaleType(ImageView.ScaleType.MATRIX);//用矩阵来绘制，这个没有详细了解过，是用矩阵计算达到缩放
        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.welcome_orange));
        views.add(imageView);
        imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup
                .LayoutParams.FILL_PARENT));
        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.welcome_rose_red));
        views.add(imageView);
        imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT));
        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.welcome_blue));
        views.add(imageView);
        imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT));
        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.welcome_yellow));
        views.add(imageView);
        return views;
    }

    @Override
    protected void onClickActionBar(int type) {

    }

    private class ViewPagerAdapter extends PagerAdapter {
        private Context mCtx;
        private ArrayList<View> views = new ArrayList<>();

        public void setViews(ArrayList<View> views) {
            this.views = views;
        }

        public ViewPagerAdapter(Context context) {
            mCtx = context;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }
}
