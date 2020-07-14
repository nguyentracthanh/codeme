package com.tuandm.codeme.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;

    public ImageAsyncTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            URLConnection connection = url.openConnection();
            return BitmapFactory.decodeStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageView.setImageBitmap(bitmap);
    }
}
