package edu.feicui.news.ui;

import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import edu.feicui.news.R;
import edu.feicui.news.ui.base.MyBaseActivity;

public class NewsActivity extends MyBaseActivity  {//新闻主界面
    SlidingMenu slidingMenu;
    FragmentMain fragmentMain;
    FragmentMenuLeft fragmentMenuLeft;
    FragmentMenuRight fragmentMenuRight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initActionBar();
        fragmentMain = new FragmentMain();
        fragmentMenuLeft = new FragmentMenuLeft();
        fragmentMenuRight = new FragmentMenuRight();
        getSupportFragmentManager().beginTransaction().replace(R.id.news_fragmentmain, fragmentMain).commit();
        slidingMenu = new SlidingMenu(this);
        slidingMenuSet();//侧滑设置
    }

    /**
     * 初始化ActionBar
     */
    void initActionBar() {
        setLeftIcon(getResources().getDrawable(R.mipmap.ic_title_home_default));
        setMiddleTitle(this.getString(R.string.app_name));
        setRightIcon(getResources().getDrawable(R.mipmap.ic_title_share_default));
    }

    /**
     * 侧滑设置
     */
    private void slidingMenuSet() {
        //设置为左右两边菜单栏
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        //设置全屏范围都可以打开菜单栏
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置菜单栏的宽度
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_horizontal_width);
        //设置动画效果
        slidingMenu.setFadeDegree(0.35f);
        //设置菜单栏与类的关联：当前类显示的为菜单栏的中间界面
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //设置左菜单栏样式
        slidingMenu.setMenu(R.layout.layout_menu_left);
        //设置右菜单栏样式
        slidingMenu.setSecondaryMenu(R.layout.layout_menu_right);
        //在Fragment里写点击事件
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_left_line, fragmentMenuLeft)
                .commit();
        getSupportFragmentManager().beginTransaction().replace(R.id._menu_right_rela, fragmentMenuRight)
                .commit();
    }

    /**
     * ActionBar左右图片点击事件
     */
    @Override
    protected void onClickActionBar(int type) {
        switch (type) {
            case ACTION_LEFT_ICON:
                slidingMenu.toggle();
                break;
            case ACTION_RIGHT_ICON:
                if (slidingMenu.isMenuShowing()) {
                    slidingMenu.showContent();
                } else {
                    slidingMenu.showSecondaryMenu();
                }
                break;
        }
    }
}