package edu.feicui.news.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class NetUtil {
    FileDBUtil fileDBUtil = new FileDBUtil();

    public void downImg(ImageView view, String url) {
        AsyncTask asyncTask = new MAsyncTask();
        asyncTask.execute(view, url);
    }

    /**
     * AsyncTask异步进程
     */
    class MAsyncTask extends AsyncTask<Object, Integer, Bitmap> {
        ImageView img;
        String url;

        @Override
        protected Bitmap doInBackground(Object[] objects) {
            img = (ImageView) objects[0];
            url = (String) objects[1];
            Bitmap bitmap = downLoadImg(url);
//            if (bitmap!=null){
                fileDBUtil.wirteIcon(bitmap, url);
//            }else{
//                bitmap=getResources().getDrawable(R.mipmap.ic_launcher);
//            }
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (url.equals(img.getTag())) {
                img.setImageBitmap(bitmap);
            }
        }
    }

    /**
     * 下载图片
     */
    private Bitmap downLoadImg(String path) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(path);
            HttpURLConnection httpURL = (HttpURLConnection) url.openConnection();
            httpURL.setConnectTimeout(5000);
            httpURL.setRequestMethod("GET");
            httpURL.setUseCaches(false);
            if (httpURL.getResponseCode() == 200) {
                InputStream in = httpURL.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
            }else{
                bitmap=null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
