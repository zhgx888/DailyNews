package edu.feicui.news.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class FileDBUtil {
    MemoryUtil memoryUtil = new MemoryUtil();

    /**
     * 读取数据
     */
    public Bitmap ReadImg(String url) {
        String path = "/data/user/0/edu.feicui.news/cache/";
        Bitmap bitmap = null;
        url=url.substring(url.lastIndexOf("/")+1,url.length());
        File file = new File(path + url);
        if (file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            memoryUtil.setMemoryImg(url, bitmap);
        }
        return bitmap;
    }

    /**
     * 写入数据
     */
    public void wirteIcon(Bitmap bitmap, String url) {
        String path = "/data/user/0/edu.feicui.news/cache/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        url=url.substring(url.lastIndexOf("/")+1,url.length());
        File imgFile = new File(path + url);
        try {
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(imgFile));//压缩图片
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
