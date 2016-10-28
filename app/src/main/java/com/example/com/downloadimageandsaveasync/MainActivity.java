package com.example.com.downloadimageandsaveasync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.a_main_progress);
    }

    public void doMagic(View view) {
        DownloadImageAsyncTask daniTask = new DownloadImageAsyncTask(progressBar);
        daniTask.execute("https://developer.android.com/images/ui/settings/settings.png",  "settings.png");
    }

}
