package com.example.com.downloadimageandsaveasync;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by raul on 27/10/2016.
 */

public class DownloadImageAsyncTask extends AsyncTask<String, Integer, Void> {

    private static final String TAG = "DaniTaskTAG_";
    private ProgressBar progressBar;

    public static final String URL          = "urlpath";
    public static final String FILENAME     = "filename";
    public static final String FILEPATH     = "filepath";
    public static final String RESULT       = "result";


    public DownloadImageAsyncTask(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setMax(1000);
    }

    @Override
    protected Void doInBackground(String... params) {
        Log.d(TAG, "doInBackground: ");
        String urlPath = params[0];
        String fileName = params[1];
        int result = Activity.RESULT_CANCELED;
        try {
            java.net.URL url = new URL(urlPath);
            InputStream input = url.openStream();
            //The sdcard directory e.g. '/sdcard' can be used directly, or
            //more safely abstracted with getExternalStorageDirectory()
            File storagePath    = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");
            Log.d(TAG, "doInBackground: " + storagePath);
            OutputStream output = new FileOutputStream(new File(storagePath, fileName));
            try {
                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                    output.write(buffer, 0, bytesRead);
                    publishProgress(bytesRead);
                }
                result = Activity.RESULT_OK;
                Log.d(TAG, "doInBackground: " + result);
            } finally {
                output.close();
                input.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: " + values[0]);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d(TAG, "onPostExecute: ");
    }

}
