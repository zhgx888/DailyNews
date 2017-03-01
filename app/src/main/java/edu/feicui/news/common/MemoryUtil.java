package edu.feicui.news.common;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class MemoryUtil {
    public HashMap<String, SoftReference<Bitmap>> imgs = new HashMap<String, SoftReference<Bitmap>>();

    /**
     * 获取内存图片
     */
    public Bitmap getMemoryImg(String url) {

        Bitmap bitmap = null;
        SoftReference<Bitmap> softReference = imgs.get(url);//软引用
        if(softReference!=null){
            bitmap = softReference.get();
        }
        return bitmap;
    }

    /**
     * 往内存存图片
     */
    public void setMemoryImg(String url, Bitmap bitmap) {
        imgs.put(url, new SoftReference<Bitmap>(bitmap));
    }
}