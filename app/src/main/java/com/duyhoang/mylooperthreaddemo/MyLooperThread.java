package com.duyhoang.mylooperthreaddemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by rogerh on 4/24/2018.
 */

public class MyLooperThread extends Thread{

    private static String TAG = MyLooperThread.class.getSimpleName();

    Handler handler;

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e(TAG, msg.obj.toString());
            }
        };

        Looper.loop();

    }
}
