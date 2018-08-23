package com.duyhoang.mylooperthreaddemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = MainActivity.class.getSimpleName();
    int counter;
    Button btnStart, btnStop;
    TextView txtCounter;

    boolean isRunning;
    MyLooperThread looperThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.button_start_thread);
        btnStop = (Button)findViewById(R.id.button_stop_thread);
        txtCounter = (TextView)findViewById(R.id.text_counter);
        counter = 0;

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        looperThread = new MyLooperThread();
        looperThread.start();
        Log.i(TAG, "main thread id: "  + Thread.currentThread().getId());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_start_thread:
                executeOnCustomLooperThread();
                break;
            case R.id.button_stop_thread:
                isRunning = false;
                break;
        }
    }

    private void executeOnCustomLooperThread() {
        isRunning = true;
        looperThread.handler.post(new Runnable() {
            @Override
            public void run() {
                while(isRunning){
                    try {
                        Thread.sleep(1000);
                        counter++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtCounter.setText("" + counter);
                        }
                    });

                }

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(looperThread != null && looperThread.isAlive())
            looperThread.handler.getLooper().quit();

    }
}
