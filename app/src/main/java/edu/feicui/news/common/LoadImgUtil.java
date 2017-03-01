package edu.feicui.news.common;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class LoadImgUtil {
    MemoryUtil memoryUtil;
    FileDBUtil fileDBUtil;
    NetUtil netUtil;
    boolean isLock;

    public LoadImgUtil() {
        memoryUtil = new MemoryUtil();
        fileDBUtil = new FileDBUtil();
        netUtil = new NetUtil();
    }

    public void display(ImageView view, String url) {//显示图片
        Bitmap bitmap = null;
        String urlsub = url.substring(url.lastIndexOf("/") + 1, url.length());
        bitmap = memoryUtil.getMemoryImg(urlsub);
        if (bitmap != null) {
            view.setImageBitmap(bitmap);
            return;
        }
        bitmap = fileDBUtil.ReadImg(urlsub);
        if (bitmap != null) {
            view.setImageBitmap(bitmap);
            return;
        }
        view.setTag(url);
        netUtil.downImg(view, url);
    }
}
