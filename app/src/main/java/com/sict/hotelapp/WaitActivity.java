package com.sict.hotelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ProgressBar;

public class WaitActivity extends AppCompatActivity {

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        getSupportActionBar().hide();
        SetWidget();
        AsyncTask task = new AsyncTask();
        task.execute(5);

    }

    public void SetWidget() {
        progressBar=findViewById(R.id.progressBar);
    }

    public class AsyncTask extends android.os.AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressBar.setProgress(5);
            Intent intent = new Intent(WaitActivity.this, MainActivity.class);
            startActivity(intent);
            WaitActivity.this.finish();
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            int percent= (int) values[0];
            progressBar.setProgress(percent);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            int n = (int) objects[0];
            for(int i = 0; i < n; i++){
                int percent = i*5/n;
                publishProgress(percent);
                SystemClock.sleep(1000);
            }
            return null;
        }
    }
}