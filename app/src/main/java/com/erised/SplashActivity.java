package com.erised;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private Thread mSplashThread;
    SplashActivity sPlashScreen;

    protected int _splashTime = 5000;  // 5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sPlashScreen = this;

        mSplashThread = new Thread() {
            @Override
            public void run() {
                super.run();

                try {

                    synchronized (this) {
                        wait(3000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();

                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        };

        mSplashThread.start();
    }

}
