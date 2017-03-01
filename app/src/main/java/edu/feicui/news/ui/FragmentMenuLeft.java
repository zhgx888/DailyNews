package edu.feicui.news.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import edu.feicui.news.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenuLeft extends Fragment implements View.OnClickListener {
    LinearLayout menuLeftLineNews;
    LinearLayout menuLeftLineFavorite;
    LinearLayout menuLeftLineLocal;
    LinearLayout menuLeftLineComment;
    LinearLayout menuLeftLinePhone;

    public FragmentMenuLeft() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_left, container, false);
        menuLeftLineNews = (LinearLayout) view.findViewById(R.id.menu_left_news);
        menuLeftLineFavorite = (LinearLayout) view.findViewById(R.id.menu_left_favorite);
        menuLeftLineLocal = (LinearLayout) view.findViewById(R.id.menu_left_local);
        menuLeftLineComment = (LinearLayout) view.findViewById(R.id.menu_left_comment);
        menuLeftLinePhone = (LinearLayout) view.findViewById(R.id.menu_left_phone);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        menuLeftLineNews.setOnClickListener(this);
        menuLeftLineFavorite.setOnClickListener(this);
        menuLeftLineLocal.setOnClickListener(this);
        menuLeftLineComment.setOnClickListener(this);
        menuLeftLinePhone.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_left_news:

                break;
            case R.id.menu_left_favorite:
                break;
            case R.id.menu_left_local:
                Toast.makeText(getContext(), "LOCAL", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_left_comment:
                Toast.makeText(getContext(), "COMMENT", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_left_phone:
                Toast.makeText(getContext(), "PHONE", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 从数据库读取数据
     */
    private void readDataFormDb() {
//        data = newsParser.dbManager.queryAllFavorNews();
//        if (data.size() == 0) {
//        } else {
//            newsAdapter.addendData(data);//追加数据，是否清空adapter原有数据
//            newsAdapter.notifyDataSetChanged();//刷新UI
//        }
    }
}
